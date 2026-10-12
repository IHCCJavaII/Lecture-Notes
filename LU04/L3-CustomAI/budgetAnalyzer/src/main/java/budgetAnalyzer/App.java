package budgetAnalyzer;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {

    public List<Transaction> transactions = new ArrayList<>();
    private Path selectedFile;

    @Override
    public void start(Stage stage) {
        var transactionTable = createTransactionTable();

        var outputArea = new TextArea();
        outputArea.setPromptText("AI Output will be displayed here...");

        var root = new VBox(
                16,
                transactionTable,
                outputArea,
                createBtnBar(stage, transactionTable, outputArea));

        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public HBox createBtnBar(Stage stage, TableView<Transaction> transactionTable, TextArea outputArea) {
        var uploadBtn = new Button("Upload CSV");
        uploadBtn.setOnAction(e -> {
            // TODO: Make user select a CSV
            var chooser = new FileChooser();
            chooser.setTitle("Select CSV File");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            var file = chooser.showOpenDialog(stage);
            // TODO: Passs file file path to TransactionIO.load()
            try {
                selectedFile = file.toPath();
                // Using the file path, we load the []
                transactions = TransactionIO.load(selectedFile);
                // Update the table with the loaded transactions
                transactionTable.getItems().setAll(transactions);
            } catch (IOException | RuntimeException ex) {
                outputArea.setText("Error loading transactions: " + ex.getMessage());
            }
        });
        var analyzeBtn = new Button("Analyze");

        analyzeBtn.setOnAction(e -> {

            if (transactions.isEmpty()) {
                outputArea.setText("No transactions to analyze. Please upload a CSV file first.");
                return;
            }
            outputArea.setText("Analyzing transactions...");
            new Thread(() -> {
                try {
                    String analysisResult = AIHandler.createReport(transactions);
                    Platform.runLater(() -> outputArea.setText(analysisResult));
                } catch (Exception ex) {
                    Platform.runLater(() -> outputArea.setText("Error analyzing transactions: "));
                }
            }).start();
        });

        return new HBox(10, uploadBtn, analyzeBtn);
    }

    public TableView<Transaction> createTransactionTable() {
        var table = new TableView<Transaction>();

        var descriptionColumn = new TableColumn<Transaction, String>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        var amountColumn = new TableColumn<Transaction, Double>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        var categoryColumn = new TableColumn<Transaction, Category>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        var dateColumn = new TableColumn<Transaction, LocalDate>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.getColumns().addAll(descriptionColumn, amountColumn, categoryColumn, dateColumn);
        table.setPlaceholder(new Label("No transactions to display"));
        return table;
    }

    public static void main(String[] args) {
        launch();
    }

}