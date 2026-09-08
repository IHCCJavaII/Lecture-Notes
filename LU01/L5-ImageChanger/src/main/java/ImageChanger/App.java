package ImageChanger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    private final Coffee coffee = new Coffee();
    private final ImageView imageView = new ImageView();

    @Override
    public void start(Stage stage) {
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");

        leftButton.setOnAction(e -> {
            coffee.moveLeft();
            imageView.setImage(new Image(coffee.getImageString()));
        });

        rightButton.setOnAction(e -> {
            coffee.moveRight();
            imageView.setImage(new Image(coffee.getImageString()));
        });

        HBox buttonRow = new HBox(20, leftButton, rightButton);

        VBox root = new VBox(20);
        root.getChildren().addAll(imageView, buttonRow);

        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);

        imageView.setImage(new Image(coffee.getImageString()));

        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Coffee Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
