package jobapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        //Build the form elements
        TextField nameField = new TextField();
        TextField yearsField = new TextField();
        TextField portfolioField = new TextField();
        ComboBox<JobType> jobTypeBox = new ComboBox<>();
        CheckBox availableNowBox = new CheckBox("Available immediately");
        Button submitButton = new Button("Submit");
        Label messageLabel = new Label();
        TextArea resultArea = new TextArea();

        //Configure form elements
        jobTypeBox.getItems().addAll(JobType.values());
        jobTypeBox.setPromptText("Select a job type");
        yearsField.setPromptText("e.g. 3");
        portfolioField.setPromptText("Optional: https://example.com");
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefRowCount(5);

        //Layout the form
        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Full Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("Years Experience:"), 0, 2);
        form.add(yearsField, 1, 2);
        form.add(new Label("Job Type:"), 0, 3);
        form.add(jobTypeBox, 1, 3);
        form.add(new Label("Portfolio URL:"), 0, 4);
        form.add(portfolioField, 1, 4);
        form.add(availableNowBox, 1, 5);
        form.add(submitButton, 1, 6);

        submitButton.setOnAction(event -> {
            //Create empty application.
            JobApplication application = new JobApplication();
            List<String> errors = new ArrayList<>();

            //Try to set all the fields, and catch any exceptions that are thrown. 
            //If an exception is thrown, add the message to the errors list.
            try {
                application.setFullName(nameField.getText());
            } catch (InvalidNameException ex) {
                errors.add(ex.getMessage());
            }

            try {
                int years = Integer.parseInt(yearsField.getText().trim());
                application.setYearsExperience(years);
            } catch (NumberFormatException ex) {
                errors.add("Years of experience must be a whole number.");
            } catch (InvalidExperienceException ex) {
                errors.add(ex.getMessage());
            }

            try {
                application.setJobType(jobTypeBox.getValue());
            } catch (InvalidJobTypeException ex) {
                errors.add(ex.getMessage());
            }

            try {
                application.setPortfolioUrl(portfolioField.getText());
            } catch (InvalidPortfolioException ex) {
                errors.add(ex.getMessage());
            }

            application.setAvailableImmediately(availableNowBox.isSelected());

            if (errors.isEmpty()) {
                messageLabel.setTextFill(Color.DARKGREEN);
                messageLabel.setText("Application submitted successfully.");
                resultArea.setText(application.toString());
            } else {
                messageLabel.setTextFill(Color.FIREBRICK);
                messageLabel.setText(String.join("\n", errors));
                resultArea.clear();
            }
        });

        VBox root = new VBox(12, new Label("Simple Job Application"), form, messageLabel, resultArea);
        root.setStyle("-fx-padding: 16;");

        var scene = new Scene(root, 640, 460);
        stage.setTitle("Job App Form");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}