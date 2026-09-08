package studentwrite;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;

public class App extends Application {

    // TODO remove "final" from everything
    // TODO clean up "Student" class to remove extra setters.

    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private TableView<Student> table = new TableView<>();
    private File currentFile;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Student Manager");

        buildTable();
        HBox form = buildForm();

        Button loadBtn = new Button("Open CSV");
        loadBtn.setOnAction(e -> loadFile(stage));

        Button saveBtn = new Button("Save & Exit");
        saveBtn.setOnAction(e -> {
            saveFile();
            stage.close();
        });

        HBox topBar = new HBox(10, loadBtn, saveBtn);
        topBar.setPadding(new Insets(10));

        VBox layout = new VBox(10, topBar, table, form);
        VBox.setVgrow(table, Priority.ALWAYS);

        stage.setScene(new Scene(layout, 800, 500));
        stage.show();
    }

    private void buildTable() {
        TableColumn<Student, String> firstCol = new TableColumn<>("First Name");
        firstCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Student, String> lastCol = new TableColumn<>("Last Name");
        lastCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Student, Number> gpaCol = new TableColumn<>("GPA");
        gpaCol.setCellValueFactory(cellData -> cellData.getValue().gpaProperty());

        TableColumn<Student, Major> majorCol = new TableColumn<>("Major");
        majorCol.setCellValueFactory(cellData -> cellData.getValue().majorProperty());

        table.getColumns().addAll(firstCol, lastCol, gpaCol, majorCol);
        table.setItems(studentList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private HBox buildForm() {
        TextField firstIn = new TextField(); firstIn.setPromptText("First Name");
        TextField lastIn = new TextField(); lastIn.setPromptText("Last Name");
        TextField gpaIn = new TextField(); gpaIn.setPromptText("GPA");
        ComboBox<Major> majorIn = new ComboBox<>(FXCollections.observableArrayList(Major.values()));
        majorIn.setPromptText("Major");

        Button addButton = new Button("Add Student");
        addButton.setOnAction(e -> {
            // FLOW: Input -> DTO -> Model -> List
            StudentDTO dto = new StudentDTO(
                firstIn.getText(),
                lastIn.getText(),
                Double.parseDouble(gpaIn.getText()),
                majorIn.getValue()
            );
            studentList.add(dto.toModel());

            firstIn.clear(); lastIn.clear(); gpaIn.clear(); majorIn.setValue(null);
        });

        HBox form = new HBox(10, firstIn, lastIn, gpaIn, majorIn, addButton);
        form.setPadding(new Insets(15));
        return form;
    }

    private void loadFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        currentFile = fileChooser.showOpenDialog(stage);

        if (currentFile != null) {
            try (Scanner scanner = new Scanner(currentFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] p = line.split(",");
                    
                    // FLOW: CSV -> DTO -> Model -> List
                    StudentDTO dto = new StudentDTO(p[0], p[1], Double.parseDouble(p[2]), Major.valueOf(p[3]));
                    studentList.add(dto.toModel());
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to parse CSV file.");
                alert.showAndWait();
            }
        }
    }

    private void saveFile() {
        try (PrintWriter writer = new PrintWriter(currentFile)) {
            for (Student s : studentList) {
                // FLOW: Model -> DTO -> CSV String
                StudentDTO dto = StudentDTO.fromModel(s);
                writer.println(dto.toCsv());
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save file.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}