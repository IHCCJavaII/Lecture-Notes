package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Nodes
        Label myLabel = new Label("Welcome to JavaFx");
        Label statusLabel = new Label("Ready!");
        Button myButton = new Button("Click Me!");

        myButton.setOnAction(event -> {
            statusLabel.setText("Button Clicked");
        });

        // Layout / Pane
        VBox rootLayout = new VBox(15); // 15px of spacing
        rootLayout.getChildren().addAll(myLabel, myButton, statusLabel);
        // Scene
        Scene scene = new Scene(rootLayout, 300, 200);
        // Display Scene on Stage
        stage.setTitle("Layout of JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}