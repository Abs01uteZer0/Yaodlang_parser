package com.andreypshenichnyj.iate.ui;

import com.andreypshenichnyj.iate.parser.core.FastDataParser;
import com.andreypshenichnyj.iate.parser.core.FullDataParser;
import com.andreypshenichnyj.iate.parser.core.SmartDataParser;
import com.andreypshenichnyj.iate.parser.interfaces.DataParser;
import com.andreypshenichnyj.iate.visitor.DdlVisitor;
import com.andreypshenichnyj.iate.visitor.dto.Field;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.YaodlangLexer;
import parser.YaodlangParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MainViewController {

    @FXML private Label ddlFileLabel;
    @FXML private Label datFilesLabel;
    @FXML private Label exportDirectoryLabel;
    @FXML private TextField fileNameField;
    @FXML private RadioButton fullParserRadio;
    @FXML private RadioButton fastParserRadio;
    @FXML private RadioButton smartParserRadio;
    @FXML private CheckBox csvCheckBox;
    @FXML private CheckBox jsonCheckBox;
    @FXML private CheckBox txtCheckBox;
    @FXML private ProgressBar progressBar;

    private File ddlFile;
    private List<File> datFiles = new ArrayList<>();
    private File exportDirectory;

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        fullParserRadio.setToggleGroup(group);
        fastParserRadio.setToggleGroup(group);
        smartParserRadio.setToggleGroup(group);
        fullParserRadio.setSelected(true);
    }

    @FXML
    public void onSelectDdlFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите DDL файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DDL файлы", "*.ddl"));
        File file = fileChooser.showOpenDialog(getStage());
        if (file != null) {
            ddlFile = file;
            ddlFileLabel.setText(file.getName());
        }
    }

    @FXML
    public void onSelectDatFiles() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите DAT файлы");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DAT файлы", "*.dat"));
        List<File> files = fileChooser.showOpenMultipleDialog(getStage());
        if (files != null && !files.isEmpty()) {
            datFiles = files;
            datFilesLabel.setText(datFiles.stream().map(File::getName).collect(Collectors.joining(", ")));
        }
    }

    @FXML
    public void onSelectExportDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Выберите папку для экспорта");
        File selectedDir = chooser.showDialog(getStage());
        if (selectedDir != null) {
            exportDirectory = selectedDir;
            exportDirectoryLabel.setText(exportDirectory.getAbsolutePath());
        }
    }

    @FXML
    public void onRunParser() {
        if (ddlFile == null || datFiles.isEmpty()) {
            showAlert("Ошибка", "Выберите DDL и DAT файлы.");
            return;
        }

        if (exportDirectory == null) {
            showAlert("Ошибка", "Выберите папку для экспорта.");
            return;
        }

        List<String> formats = getSelectedFormats();
        if (formats.isEmpty()) {
            showAlert("Ошибка", "Выберите хотя бы один формат экспорта.");
            return;
        }

        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);

        new Thread(() -> {
            try {
                // Получаем выбранный парсер
                DataParser dataParser = getSelectedParser();

                // Имя файла
                String baseFileName = fileNameField.getText().trim();
                if (baseFileName.isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                    baseFileName = LocalDateTime.now().format(formatter) + "_result";
                }

                // Загружаем данные из файлов DDL и DAT
                List<String> lines = loadDatFiles(datFiles); // Метод для загрузки данных из DAT файлов (пиши свой метод загрузки)
                List<Field> fields = loadFieldsFromDdl(ddlFile); // Метод для загрузки полей из DDL (пиши свой метод загрузки)

                for (Field field : fields){
                    System.out.println("Поле " + field.getName() + " с форматами " +  field.getFormats() + " и описанием " + field.getComment());
                }

                // Парсим данные
                dataParser.parse(lines, fields);

                // Для каждого выбранного формата сохраняем данные
                for (String format : formats) {
                    File outputFile = new File(exportDirectory, baseFileName + "." + format.toLowerCase());
                    try {
                        switch (format) {
                            case "CSV":
                                dataParser.saveAsCsv(outputFile.getAbsolutePath());
                                break;
                            case "JSON":
                                dataParser.saveAsJson(outputFile.getAbsolutePath());
                                break;
                            case "TXT":
                                dataParser.saveAsTxt(outputFile.getAbsolutePath());
                                break;
                            default:
                                throw new IllegalStateException("Неизвестный формат: " + format);
                        }
                    } catch (IOException e) {
                        String msg = "Ошибка при записи файла " + format + ": " + e.getMessage();
                        Platform.runLater(() -> showAlert("Ошибка", msg));
                        return;
                    }
                }

                // Обновление UI на основной поток
                Platform.runLater(() -> {
                    progressBar.setProgress(1.0);
                    showAlert("Успешно", "Файлы успешно сохранены.");
                });
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showAlert("Ошибка", "Ошибка во время экспорта: " + e.getMessage()));
            }
        }).start();
    }

    private DataParser getSelectedParser() {
        if (fullParserRadio.isSelected()) {
            return new FullDataParser();
        }
        if (fastParserRadio.isSelected()) {
            return new FastDataParser();
        }
        if (smartParserRadio.isSelected()) {
            return new SmartDataParser();
        }
        throw new IllegalStateException("Не выбран парсер");
    }

    private List<String> getSelectedFormats() {
        List<String> formats = new ArrayList<>();
        if (csvCheckBox.isSelected()) formats.add("CSV");
        if (jsonCheckBox.isSelected()) formats.add("JSON");
        if (txtCheckBox.isSelected()) formats.add("TXT");
        return formats;
    }

    private Stage getStage() {
        return (Stage) ddlFileLabel.getScene().getWindow();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private List<String> loadDatFiles(List<File> datFiles) {
        List<String> lines = new ArrayList<>();
        for (File datFile : datFiles) {
            try {
                List<String> fileLines = Files.readAllLines(datFile.toPath(), StandardCharsets.UTF_8);
                lines.addAll(fileLines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    private List<Field> loadFieldsFromDdl(File ddlFile) throws IOException {
        YaodlangLexer lexer = new YaodlangLexer(CharStreams.fromFileName(ddlFile.toString()));
        TokenStream tokens = new CommonTokenStream(lexer);
        YaodlangParser parser = new YaodlangParser(tokens);
        ParseTree tree = parser.yaodfile();

        DdlVisitor visitor = new DdlVisitor();
        visitor.visit(tree);

        return visitor.getFields();
    }
}
