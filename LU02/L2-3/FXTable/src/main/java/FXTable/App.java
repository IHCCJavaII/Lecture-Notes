package FXTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {

    private static final String DEFAULT_FILE_PATH = "names.txt";

    private File file;

    private ObservableList<Name> nameOL = FXCollections.observableArrayList();

    public TableView<Name> nameTableView;

    @Override
    public void start(Stage stage) {

        // load the default file automatically if present, skipping the upload prompt
        file = new File(DEFAULT_FILE_PATH);
        if (file.exists()) {
            readFile();
            displayTable(stage);
            stage.show();
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");

        Button uploadButton = new Button("Upload File");
        uploadButton.setId("uploadButton");

        uploadButton.setOnAction(e -> {
            file = fileChooser.showOpenDialog(stage);
            // read in the file
            // put data into an array of objects
            readFile();
            // display the data in a table
            displayTable(stage);
        });

        HBox hbox = new HBox(uploadButton);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hbox, 400, 300);

        stage.setScene(scene);
        stage.show();
    }

    public void displayTable(Stage stage) {
        nameTableView = new TableView<>();
        nameTableView.setId("nameTableView");

        // Create out column
        TableColumn<Name, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setId("firstNameCol");
        // Map column to the class properties
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Name, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setId("lastNameCol");
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Name, String> ageCol = new TableColumn<>("Age");
        ageCol.setId("ageCol");
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asString());

        // TODO add other column later
        nameTableView.getColumns().addAll(firstNameCol, lastNameCol, ageCol);

        nameTableView.setItems(nameOL);

        VBox vbox = new VBox(nameTableView);
        vbox.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(vbox, 500, 500));
    }

    private void readFile() {
        try {
            Scanner scanner = new Scanner(file);

            scanner.nextLine(); // skip the header line

            while (scanner.hasNextLine()) {
                // read the while line and split it into an array of strings
                String[] names = scanner.nextLine().split(",");
                // put sting[] it to the name class
                Name name = new Name(
                        new SimpleStringProperty(names[0]),
                        new SimpleStringProperty(names[1]),
                        new SimpleIntegerProperty(Integer.parseInt(names[2])));
                // add new name object to array
                nameOL.add(name);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }

}