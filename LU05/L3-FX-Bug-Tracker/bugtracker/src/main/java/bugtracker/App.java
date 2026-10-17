package bugtracker;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private final ObservableList<Issue> issues = FXCollections.observableArrayList();

    // Refactor: To move code around

    @Override
    public void start(Stage stage) {
        // Build the Form
        VBox form = buildForm();

        // Build the Table
        TableView<Issue> table = buildTable();

        VBox root = new VBox(
                20,
                new Label("Bug Tracker"),
                form,
                table);
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public TableView<Issue> buildTable() {
        TableColumn<Issue, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Issue, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Issue, Priority> priorityColumn = new TableColumn<>("Priority");
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));

        TableView<Issue> table = new TableView<>();
        table.setItems(issues);
        table.getColumns().addAll(titleColumn, descriptionColumn, priorityColumn);
        return table;
    }

    public VBox buildForm() {
        TextField titleField = new TextField();
        titleField.setPromptText("Enter bug title");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter bug description");

        ComboBox<Priority> priorityBox = new ComboBox<>();
        priorityBox.getItems().addAll(Priority.values());
        priorityBox.setPromptText("Select priority");

        Button addButton = new Button("Add Bug");

        // TODO when user clicks get form input -> new Issue -> add issue to list ->
        // refresh table
        addButton.setOnAction(e -> {
            addIssue(titleField, descriptionField, priorityBox);
        });

        return new VBox(10, titleField, descriptionField, priorityBox, addButton);
    }

    public void addIssue(TextField titleField, TextField descriptionField, ComboBox<Priority> priorityBox) {
        // Get form input
        String title = titleField.getText();
        String description = descriptionField.getText();
        Priority priority = priorityBox.getValue();

        // System.out.println("Adding bug: " + title + ", " + description + ", " +
        // priority);
        if (title.isEmpty() || description.isEmpty() || priority == null) {
            return;
        }
        Issue issue = new Issue(title, description, priority);
        // System.out.println("Adding bug: " + issue);

        issues.add(issue);
    }

    public static void main(String[] args) {
        launch();
    }

}