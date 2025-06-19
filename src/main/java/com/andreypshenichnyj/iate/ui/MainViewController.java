package com.andreypshenichnyj.iate.ui;

import com.andreypshenichnyj.iate.db.AppLoader;
import com.andreypshenichnyj.iate.parser.core.FastDataParser;
import com.andreypshenichnyj.iate.parser.core.FullDataParser;
import com.andreypshenichnyj.iate.parser.core.SmartDataParser;
import com.andreypshenichnyj.iate.parser.dtos.ParsedLine;
import com.andreypshenichnyj.iate.parser.interfaces.DataParser;
import com.andreypshenichnyj.iate.visitor.DdlVisitor;
import com.andreypshenichnyj.iate.visitor.dto.FieldDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainViewController {

    private final ObservableList<File> selectedDatFiles = FXCollections.observableArrayList();
    @FXML
    private TabPane tabPane;
    @FXML
    private Label ddlFileLabel;
    @FXML
    private ListView<String> datFilesListView;
    @FXML
    private ComboBox<String> encodingComboBox;
    @FXML
    private Label exportDirectoryLabel;
    @FXML
    private TextField fileNameField;
    @FXML
    private RadioButton fullParserRadio;
    @FXML
    private RadioButton fastParserRadio;
    @FXML
    private RadioButton smartParserRadio;
    @FXML
    private CheckBox csvCheckBox;
    @FXML
    private CheckBox jsonCheckBox;
    @FXML
    private CheckBox txtCheckBox;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private CheckBox addToDbCheckBox;
    private File ddlFile;
    private File exportDirectory;

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        fullParserRadio.setToggleGroup(group);
        fastParserRadio.setToggleGroup(group);
        smartParserRadio.setToggleGroup(group);
        fullParserRadio.setSelected(true);
        encodingComboBox.getSelectionModel().select("UTF-8");
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
            selectedDatFiles.addAll(files);
            updateDatFileListView();
        }
    }

    private void updateDatFileListView() {
        datFilesListView.getItems().setAll(
                selectedDatFiles.stream().map(File::getName).collect(Collectors.toList())
        );
    }

    @FXML
    public void onDatFileListClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            int index = datFilesListView.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem removeItem = new MenuItem("Удалить");
                removeItem.setOnAction(e -> {
                    selectedDatFiles.remove(index);
                    updateDatFileListView();
                });
                contextMenu.getItems().add(removeItem);
                contextMenu.show(datFilesListView, event.getScreenX(), event.getScreenY());
            }
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
        if (ddlFile == null || selectedDatFiles.isEmpty()) {
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
                DataParser dataParser = getSelectedParser();
                String baseFileName = fileNameField.getText().trim();
                if (baseFileName.isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                    baseFileName = LocalDateTime.now().format(formatter) + "_result";
                }
                Map<String, List<String>> fileToLines = loadDatFiles(selectedDatFiles);
                List<FieldDTO> fieldDTOS = loadFieldsFromDdl(ddlFile);
                long startTime = System.nanoTime();
                for (Map.Entry<String, List<String>> entry : fileToLines.entrySet()) {
                    dataParser.parse(entry.getValue(), fieldDTOS);
                    if (addToDbCheckBox.isSelected()) {
                        if (!fullParserRadio.isSelected()){
                            DataParser fullDataParser = new FullDataParser();
                            fullDataParser.parse(entry.getValue(), fieldDTOS);
                            saveDataInDB(ddlFile, entry.getKey(), fullDataParser.getParsedLines());
                        }
                        saveDataInDB(ddlFile, entry.getKey(), dataParser.getParsedLines());
                    }
                }
                long durationMs = (System.nanoTime() - startTime) / 1_000_000;
                for (String format : formats) {
                    File outputFile = new File(exportDirectory, baseFileName + "." + format.toLowerCase());
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
                }
                Platform.runLater(() -> {
                    progressBar.setProgress(1.0);
                    showAlert("Успешно", "Файлы успешно сохранены. Парсинг занял " + durationMs + " мс");
                });
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showAlert("Ошибка", "Ошибка во время экспорта: " + e.getMessage()));
            }
        }).start();
    }

    private DataParser getSelectedParser() {
        if (fullParserRadio.isSelected()) return new FullDataParser();
        if (fastParserRadio.isSelected()) return new FastDataParser();
        if (smartParserRadio.isSelected()) return new SmartDataParser();
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

    private Map<String, List<String>> loadDatFiles(List<File> datFiles) {
        Map<String, List<String>> fileToLines = new LinkedHashMap<>();
        String encoding = encodingComboBox.getValue();
        for (File datFile : datFiles) {
            try {
                List<String> lines = Files.readAllLines(datFile.toPath(), Charset.forName(encoding));
                fileToLines.put(datFile.getName(), lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileToLines;
    }

    private List<FieldDTO> loadFieldsFromDdl(File ddlFile) throws IOException {
        ParseTree tree = getTree(ddlFile);
        DdlVisitor visitor = new DdlVisitor();
        visitor.visit(tree);
        return visitor.getFields();
    }

    private void saveDataInDB(File ddlFile, String fileName, List<ParsedLine> parsedLines) throws IOException {
        ParseTree tree = getTree(ddlFile);
        DdlVisitor visitor = new DdlVisitor();
        visitor.visit(tree);
        int length = Integer.parseInt(visitor.getFamilyFormat().replaceAll("[A-Z()]", ""));
        AppLoader.loadToDb(
                visitor.getFamilyName(),
                length,
                visitor.getFields(),
                parsedLines,
                fileName
        );
    }

    private ParseTree getTree(File ddlFile) throws IOException {
        YaodlangLexer lexer = new YaodlangLexer(CharStreams.fromFileName(ddlFile.toString()));
        TokenStream tokens = new CommonTokenStream(lexer);
        YaodlangParser parser = new YaodlangParser(tokens);
        return parser.yaodfile();
    }

    @FXML
    public void openTableTab() {
        for (Tab tab : tabPane.getTabs()) {
            if ("Таблица".equals(tab.getText())) {
                tabPane.getSelectionModel().select(tab);
                return;
            }
        }
    }
}

