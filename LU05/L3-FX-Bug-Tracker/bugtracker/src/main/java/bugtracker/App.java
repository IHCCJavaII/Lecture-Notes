package bugtracker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private final ObservableList<Issue> issueList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Simple Bug Tracker");

        //Create Form Components
        TextField titleField = new TextField();
        titleField.setPromptText("Bug Title");

        TextField descField = new TextField();
        descField.setPromptText("Description");

        ComboBox<Priority> priorityBox = new ComboBox<>();
        priorityBox.setItems(FXCollections.observableArrayList(Priority.values()));
        priorityBox.setPromptText("Priority");

        Button addButton = new Button("Add Bug");

        //Create the Table
        TableView<Issue> table = new TableView<>();
        table.setItems(issueList);

        TableColumn<Issue, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Issue, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Issue, Priority> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));

        table.getColumns().addAll(titleCol, descCol, priorityCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String desc = descField.getText();
            Priority priority = priorityBox.getValue();

            if (title != null && !title.isEmpty() && priority != null) {
                issueList.add(new Issue(title, desc, priority));
                
                // Clear inputs
                titleField.clear();
                descField.clear();
                priorityBox.getSelectionModel().clearSelection();
            }
        });

        VBox form = new VBox(10, titleField, descField, priorityBox, addButton);
        VBox root = new VBox(10, form, table);


        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}