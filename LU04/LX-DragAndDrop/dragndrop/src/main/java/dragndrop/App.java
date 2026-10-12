package dragndrop;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button uploadButton = new Button("Choose files\nor drag and drop here");
        Label fileName = new Label("No file selected");

        uploadButton.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        uploadButton.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = db.hasFiles();
            if (success) {
                fileName.setText("File selected: " + db.getFiles().get(0).getName());
            }
            event.setDropCompleted(success);
            event.consume();
        });

        VBox hbox = new VBox(10, uploadButton, fileName);

        Scene scene = new Scene(hbox, 400, 200);
        primaryStage.setTitle("File Upload");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
