package slopbot;

import java.time.Duration;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.response.OllamaResult;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Button btn = new Button("Generate Slop");
        TextArea textArea = new TextArea();

        btn.setOnAction(e -> {
            new Thread(() -> {
                try {
                    Ollama ollama = new Ollama("http://127.0.0.1:11434/");
                    String model = "qwen3.5:2b ";

                    // If your computer takes longer then 5 minutes it will timeout
                    ollama.setRequestTimeoutSeconds(300);

                    OllamaResult result = ollama.generate(
                            OllamaGenerateRequest.builder()
                                    .withModel(model)
                                    .withPrompt(
                                            "Generate a small amount of Lorem Ipsum. Don't think about it, just do it. Don't include any other text, just the Lorem Ipsum.")
                                    .build(),
                            null);

                    Platform.runLater(() -> {
                        textArea.setText(result.getResponse());
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        VBox vbox = new VBox(btn, textArea);
        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}