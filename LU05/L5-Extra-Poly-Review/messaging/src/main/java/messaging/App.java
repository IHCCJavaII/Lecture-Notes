package messaging;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import messaging.models.ImageMessage;
import messaging.models.Message;
import messaging.models.TextMessage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        List<Message> messages = List.of(
            new TextMessage("Alice", "Bob", "Hey, are you free tonight?"),
            new ImageMessage("Bob", "Alice", "photo.png"),
            new TextMessage("Carol", "Dave", "Meeting at 3pm"),
            new ImageMessage("Dave", "Carol", "diagram.jpg")
        );

        ListView<String> listView = new ListView<>();
        for (Message m : messages) {
            // Question: Which send() method is called here? How does Java know which one to call?
            listView.getItems().add(m.send());
        }

        VBox root = new VBox(listView);

        stage.setScene(new Scene(root, 600, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}