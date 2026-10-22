package dynamic;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Voting voting = new Voting(false);

        // Candidate form
        VBox form = new VBox(10);
        Label candidate = new Label("Who are you voting for?");
        TextField candidateField = new TextField();
        form.getChildren().addAll(candidate, candidateField);
        form.setVisible(false);

        // Are you old enough to vote radio buttons
        Label questionLabel = new Label("Are you old enough to vote?");
        RadioButton yesButton = new RadioButton("Yes");
        RadioButton noButton = new RadioButton("No");
        Label resultLabel = new Label();

        // Groups button together so only one can be selected at a time
        ToggleGroup toggleGroup = new ToggleGroup();
        yesButton.setToggleGroup(toggleGroup);
        noButton.setToggleGroup(toggleGroup);

        yesButton.setOnAction(e -> {
            voting.setIsOldEnough(true);
            resultLabel.setText("You are old enough to vote.");
            // Only show the form if the user is old enough to vote
            form.setVisible(true);
        });

        noButton.setOnAction(e -> {
            System.out.println(noButton.isSelected());
            voting.setIsOldEnough(false);
            resultLabel.setText("You are not old enough to vote.");
            form.setVisible(false);
        });

        VBox vbox = new VBox(10, questionLabel, yesButton, noButton, resultLabel, form);

        var scene = new Scene(vbox, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}