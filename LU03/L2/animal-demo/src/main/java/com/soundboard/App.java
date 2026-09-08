package com.soundboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        // HBox puts them side-by-side with 20px spacing
        HBox root = new HBox(20);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        // Using inheritance: Lion and Duck are both AnimalNodes
        root.getChildren().addAll(new Lion(), new Duck());

        stage.setScene(new Scene(root, 300, 150));
        stage.setTitle("Simple Demo");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}