package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DVDLogo dvdLogo = new DVDLogo();

        Pane pane = new Pane(dvdLogo.getDvdImageView());

        // Core "gameplay" / animation loop
        // Every 10 milliseconds the moveLogo() method is called
        Timeline timeLine = new Timeline(
                new KeyFrame(Duration.millis(10),
                        e -> dvdLogo.moveLogo()));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();

        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}