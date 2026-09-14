package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // nodes
        Button buyBurgerButton = new Button("Buy Burger");
        buyBurgerButton.setId("buy-burger-button");

        Text thankYouText = new Text("Thank you for your purchase!");
        thankYouText.setId("thank-you-text");
        thankYouText.setFill(Color.GREEN);
        thankYouText.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        thankYouText.setVisible(false);

        buyBurgerButton.setOnAction(e -> {
            thankYouText.setVisible(true);
        });

        ImageView burgerImage = new ImageView("deluxe-double.png");
        burgerImage.setId("burger-image");
        burgerImage.setFitHeight(200);
        burgerImage.setPreserveRatio(true);

        // layout controls
        VBox vBox = new VBox(buyBurgerButton, burgerImage, thankYouText);
        vBox.setSpacing(50);

        HBox hBox = new HBox(vBox);
        hBox.setAlignment(Pos.CENTER);
        // create a scene, put the scene into stage
        Scene scene = new Scene(hBox, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}