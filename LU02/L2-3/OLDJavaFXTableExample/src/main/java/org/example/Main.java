package org.example;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application {

    private File file;

    // ObservableList will work with TableView to update UI on data changes
    private ObservableList<Name> nameObservableList = FXCollections.observableArrayList();

    // Wraps the raw list so we can apply/remove a filter predicate without mutating the source list
    private FilteredList<Name> filteredNameList = new FilteredList<>(nameObservableList, name -> true);

    // Tracks whether the "sub name + age > 18" filter is currently applied
    private boolean subFilterActive = false;

    private TableView<Name> nameTableView;

    public static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        // later we can use that file to read in the contents

        Button uploadAFileButton = new Button("Upload A file");

        uploadAFileButton.setOnAction(e -> {
            file = fileChooser.showOpenDialog(stage);
            // read the contents of the file
            readFile();
            displayTable(stage);
        });

        HBox hBox = new HBox(uploadAFileButton);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hBox, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    public void readFile() {
        try {
            Scanner scanner = new Scanner(file);

            // throws away the header line
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                // read the entire line in, split by comma
                String[] names = scanner.nextLine().split(",");

                // read into name objects
                Name name = new Name(new SimpleStringProperty(names[0]),
                                     new SimpleStringProperty(names[1]),
                                     new SimpleIntegerProperty(Integer.parseInt(names[2])));
                // save to list
                nameObservableList.add(name);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot load file " + e.getMessage());
        }
    }

    public void displayTable(Stage stage) {

        nameTableView = new TableView<>();
        // Create our columns for the table
        TableColumn<Name, String> firstNameColumn = new TableColumn<>("First Name");
        // Mapping the firstName field from the Name class to the firstNameColumn
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Name, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // TODO change this to an integer.
        TableColumn<Name, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asString());

        // Add all the columns to the tableview
        nameTableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn);

        // set items from the observable list onto the tableview
        nameTableView.setItems(nameObservableList);

        // display the tableview on the scene
        VBox vbox = new VBox(nameTableView);
        vbox.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(vbox, 500, 500));
    }
}