package com.andreypshenichnyj.iate.ui;

import com.andreypshenichnyj.iate.db.dao.impl.DataValueDAOImpl;
import com.andreypshenichnyj.iate.db.dao.impl.FamilyDAOImpl;
import com.andreypshenichnyj.iate.db.dao.impl.FieldDAOImpl;
import com.andreypshenichnyj.iate.db.dao.impl.FormatDescriptionDAOImpl;
import com.andreypshenichnyj.iate.db.dao.interfaces.DataValueDAO;
import com.andreypshenichnyj.iate.db.dao.interfaces.FamilyDAO;
import com.andreypshenichnyj.iate.db.dao.interfaces.FieldDAO;
import com.andreypshenichnyj.iate.db.dao.interfaces.FormatDescriptionDAO;
import com.andreypshenichnyj.iate.db.entity.DataValue;
import com.andreypshenichnyj.iate.db.entity.Family;
import com.andreypshenichnyj.iate.db.entity.Field;
import com.andreypshenichnyj.iate.db.entity.FormatDescription;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class TableViewController {

    private final String DB_URL = "jdbc:sqlite:data.db?busy_timeout=5000";
    @FXML
    private ComboBox<String> familyComboBox;
    @FXML
    private ComboBox<String> formatComboBox;
    @FXML
    private ComboBox<String> fileComboBox;
    @FXML
    private Button loadButton;
    @FXML
    private TableView<Map<String, String>> dataTable;
    @FXML
    private ComboBox<String> statFieldComboBox;
    @FXML
    private Button statButton;
    @FXML
    private Label statLabel;
    @FXML
    private ComboBox<String> xFieldComboBox;
    @FXML
    private ComboBox<String> yFieldComboBox;
    @FXML
    private Button chartButton;
    @FXML
    private Button histogramButton;
    private Connection conn;
    private FamilyDAO familyDAO;
    private FormatDescriptionDAO formatDAO;
    private DataValueDAO dataValueDAO;
    private FieldDAO fieldDAO;

    private int currentFormatId;
    private String currentFileName;
    private List<Field> currentFields;

    @FXML
    public void initialize() {
        try {
            conn = DriverManager.getConnection(DB_URL);
            familyDAO = new FamilyDAOImpl(conn);
            formatDAO = new FormatDescriptionDAOImpl(conn);
            dataValueDAO = new DataValueDAOImpl(conn);
            fieldDAO = new FieldDAOImpl(conn);

            List<Family> families = familyDAO.findAll();
            List<String> familyCodes = families.stream()
                    .map(Family::getCode)
                    .collect(Collectors.toList());
            familyComboBox.setItems(FXCollections.observableArrayList(familyCodes));

            familyComboBox.setOnAction(e -> onFamilySelected());
            formatComboBox.setOnAction(e -> onFormatSelected());

            loadButton.setOnAction(this::onLoad);
            statButton.setOnAction(this::onStatsClicked);
            chartButton.setOnAction(this::onChartClicked);
            histogramButton.setOnAction(this::onHistogramClicked);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void onFamilySelected() {
        String family = familyComboBox.getValue();
        if (family == null) return;

        var fam = familyDAO.findByCode(family);
        if (fam == null) return;

        int familyId = fam.getId();
        List<FormatDescription> formats = formatDAO.findByFamilyId(familyId);

        formatComboBox.setItems(FXCollections.observableArrayList(
                formats.stream().map(FormatDescription::getCode).collect(Collectors.toList())
        ));
    }

    private void onFormatSelected() {
        String code = formatComboBox.getValue();
        if (code == null) return;

        FormatDescription fd = formatDAO.findByCode(code);
        if (fd != null) {
            currentFormatId = fd.getId();
            List<String> files = dataValueDAO.findDistinctFileNamesByFormatId(currentFormatId);
            fileComboBox.setItems(FXCollections.observableArrayList(files));
        }
    }

    private void onLoad(ActionEvent event) {
        currentFileName = fileComboBox.getValue();
        if (currentFileName == null || currentFormatId == 0) return;

        currentFields = fieldDAO.findByFormatDescriptionId(currentFormatId);
        List<DataValue> dataValues = dataValueDAO.findByFormatAndFile(currentFormatId, currentFileName);

        Map<Integer, Map<String, String>> rowMap = new TreeMap<>();
        for (DataValue dv : dataValues) {
            rowMap.putIfAbsent(dv.getLineNumber(), new HashMap<>());
            Field f = currentFields.stream().filter(fl -> fl.getId() == dv.getFieldId()).findFirst().orElse(null);
            if (f != null) {
                rowMap.get(dv.getLineNumber()).put(f.getLabel(), dv.getValue());
            }
        }

        dataTable.getItems().clear();
        dataTable.getColumns().clear();

        for (Field f : currentFields) {
            TableColumn<Map<String, String>, String> col = new TableColumn<>(f.getLabel());
            col.setCellValueFactory(param -> {
                Map<String, String> row = param.getValue();
                String val = row.get(f.getLabel());
                return new ReadOnlyStringWrapper(val == null ? "" : val);
            });
            dataTable.getColumns().add(col);
        }

        dataTable.setItems(FXCollections.observableArrayList(rowMap.values()));

        List<String> fieldNames = currentFields.stream().map(Field::getLabel).collect(Collectors.toList());
        statFieldComboBox.setItems(FXCollections.observableArrayList(fieldNames));
        xFieldComboBox.setItems(FXCollections.observableArrayList(fieldNames));
        yFieldComboBox.setItems(FXCollections.observableArrayList(fieldNames));
    }

    private void onStatsClicked(ActionEvent event) {
        String fieldName = statFieldComboBox.getValue();
        if (fieldName == null || dataTable.getItems().isEmpty()) {
            statLabel.setText("Выберите поле.");
            return;
        }

        List<Double> values = new ArrayList<>();
        for (Map<String, String> row : dataTable.getItems()) {
            String val = row.get(fieldName);
            try {
                values.add(Double.parseDouble(val));
            } catch (Exception ignored) {
            }
        }

        if (values.isEmpty()) {
            statLabel.setText("Нет числовых данных для поля.");
            return;
        }

        double sum = values.stream().mapToDouble(Double::doubleValue).sum();
        double avg = sum / values.size();
        double min = values.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        double max = values.stream().mapToDouble(Double::doubleValue).max().orElse(0);

        statLabel.setText(String.format("Статистика для %s: min=%.2f, max=%.2f, avg=%.2f", fieldName, min, max, avg));
    }

    private void onChartClicked(ActionEvent event) {
        String xField = xFieldComboBox.getValue();
        String yField = yFieldComboBox.getValue();

        if (xField == null || yField == null) {
            statLabel.setText("Выберите X и Y поля.");
            return;
        }

        List<XYChart.Data<Number, Number>> dataPoints = new ArrayList<>();
        for (Map<String, String> row : dataTable.getItems()) {
            try {
                double x = Double.parseDouble(row.get(xField));
                double y = Double.parseDouble(row.get(yField));
                dataPoints.add(new XYChart.Data<>(x, y));
            } catch (NumberFormatException ignored) {
            }
        }

        if (dataPoints.isEmpty()) {
            statLabel.setText("Недостаточно числовых данных для построения графика.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/andreypshenichnyj/iate/ui/chart_view.fxml"));
            Parent root = loader.load();

            ChartViewController controller = loader.getController();
            controller.setChartData(xField, yField, dataPoints);

            Stage stage = new Stage();
            stage.setTitle("График");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            statLabel.setText("Ошибка загрузки окна графика.");
            e.printStackTrace();
        }
    }

    private void onHistogramClicked(ActionEvent event) {
        String xField = xFieldComboBox.getValue();
        String yField = yFieldComboBox.getValue();

        if (xField == null && yField == null) {
            statLabel.setText("Выберите хотя бы одно поле.");
            return;
        }

        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        for (Map<String, String> row : dataTable.getItems()) {
            try {
                if (xField != null) xValues.add(Double.parseDouble(row.get(xField)));
            } catch (NumberFormatException ignored) {
            }
            try {
                if (yField != null) yValues.add(Double.parseDouble(row.get(yField)));
            } catch (NumberFormatException ignored) {
            }
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/andreypshenichnyj/iate/ui/histogram_view.fxml"));
            Parent root = loader.load();

            HistogramViewController controller = loader.getController();
            controller.setHistogramData(xField, xValues, yField, yValues);

            Stage stage = new Stage();
            stage.setTitle("Гистограмма");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            statLabel.setText("Ошибка при открытии гистограммы.");
            e.printStackTrace();
        }
    }

}
