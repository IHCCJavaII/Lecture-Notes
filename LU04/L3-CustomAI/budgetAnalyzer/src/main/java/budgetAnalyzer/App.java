package budgetAnalyzer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    public List<Transaction> transactions = new ArrayList<Transaction>();
    private Path selectedFile;

    @Override
    public void start(Stage stage) {
        var transactionTable = createTransactionTable();

        var outputArea = new TextArea();
        outputArea.setPromptText("AI analysis will appear here...");

        var root = new VBox(
                16,
                createTransactionForm(transactionTable),
                transactionTable,
                outputArea,
                createButtonBar(stage, transactionTable, outputArea));

        var scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.setTitle("Budget Analyzer");
        stage.show();
    }

    private VBox createTransactionForm(TableView<Transaction> transactionTable) {
        var descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        var amountField = new TextField();
        amountField.setPromptText("Amount");

        var categoryField = new TextField();
        categoryField.setPromptText("Category");

        var addBtn = new Button("Add Transaction");

        addBtn.setOnAction(event -> {
            if (selectedFile == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No CSV selected");
                alert.setHeaderText("No CSV file selected");
                alert.setContentText("Upload a CSV file before adding a transaction.");
                alert.showAndWait();
                return;
            }

            try {
                var newTransaction = new Transaction(
                        descriptionField.getText().trim(),
                        Double.parseDouble(amountField.getText().trim()),
                        Category.valueOf(categoryField.getText().trim().toUpperCase()),
                        LocalDate.now());
                // Update local list
                transactions.add(newTransaction);
                // Update the CSV file
                TransactionIO.save(selectedFile, transactions);
                // Update the table
                transactionTable.getItems().setAll(transactions);
                descriptionField.clear();
                amountField.clear();
                categoryField.clear();
            } catch (IOException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Saving Transaction");
                alert.setHeaderText("Could not save transaction to CSV file");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });

        var fields = new HBox(
                10,
                descriptionField,
                amountField,
                categoryField,
                addBtn);

        return new VBox(8, new Label("New Transaction"), fields);
    }

    private TableView<Transaction> createTransactionTable() {
        var table = new TableView<Transaction>();

        var descriptionColumn = new TableColumn<Transaction, String>("Description");
        descriptionColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().description()));

        var amountColumn = new TableColumn<Transaction, Number>("Amount");
        amountColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().amount()));

        var categoryColumn = new TableColumn<Transaction, Category>("Category");
        categoryColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().category()));

        var dateColumn = new TableColumn<Transaction, LocalDate>("Date");
        dateColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().date()));

        table.getColumns().addAll(descriptionColumn, amountColumn, categoryColumn, dateColumn);
        table.setPlaceholder(new Label("No transactions to display"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        return table;
    }

    private HBox createButtonBar(
            Stage stage,
            TableView<Transaction> transactionTable,
            TextArea outputArea) {

        var uploadBtn = new Button("Upload CSV");
        uploadBtn.setOnAction(event -> {
            var chooser = new FileChooser();
            chooser.setTitle("Open Budget CSV");
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("CSV files", "*.csv"));
            var file = chooser.showOpenDialog(stage);

            if (file == null) {
                return;
            }

            try {
                selectedFile = file.toPath();
                transactions = TransactionIO.load(selectedFile);
                transactionTable.getItems().setAll(transactions);
            } catch (java.io.IOException | RuntimeException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Loading Transactions");
                alert.setHeaderText("Could not load transactions from CSV file");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });

        var analyzeBtn = new Button("Analyze");
        analyzeBtn.setOnAction(event -> {
            if (transactions.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Transactions");
                alert.setHeaderText("No transactions to analyze");
                alert.setContentText("Upload a CSV file before analyzing it.");
                alert.showAndWait();
                return;
            }

            outputArea.setText("Generating report...");
            new Thread(() -> {
                try {
                    var report = AIHandler.createReport(transactions);
                    Platform.runLater(() -> outputArea.setText(report));
                } catch (Exception exception) {
                    Platform.runLater(() -> {
                        outputArea.clear();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("AI Analysis Error");
                        alert.setHeaderText("Could not generate AI report");
                        alert.setContentText(exception.getMessage());
                    });
                }
            }).start();
        });

        return new HBox(
                10,
                uploadBtn,
                analyzeBtn);
    }

    public static void main(String[] args) {
        launch();
    }

}