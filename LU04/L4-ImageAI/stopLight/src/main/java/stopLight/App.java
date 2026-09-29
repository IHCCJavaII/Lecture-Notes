package stopLight;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;

public class App extends Application {

    File selectedFile;

    @Override
    public void start(Stage stage) {

        ImageView imageView = new ImageView();
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);

        var AIOutput = new TextArea();
        AIOutput.setPromptText("AI analysis will appear here...");

        Button uploadBtn = new Button("Upload Image");
        uploadBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an Image");
            fileChooser.getExtensionFilters().add(
                    new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.webp"));

            selectedFile = fileChooser.showOpenDialog(stage);

            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);

            AIOutput.setText(AIHandler.analyzeImage(selectedFile.getAbsolutePath()));

        });

        var vbox = new VBox(uploadBtn, imageView, AIOutput);

        var scene = new Scene(vbox, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}