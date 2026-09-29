package FXImageChanger;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    // make weather object instance variable
    private Weather weather;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Make a new weather object
        weather = new Weather();

        // Make a button to change out the forecast
        Button changeForecastButton = new Button("Change Forecast");
        changeForecastButton.setOnAction(e -> changeForecast());

        // Add text, image and button to the screen
        VBox vbox = new VBox(weather.getDate(), weather.getForecast(), weather.getForecastImageView(),
                changeForecastButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);

        Scene scene = new Scene(vbox, 500, 500);

        stage.setScene(scene);
        stage.setTitle("Weather");
        stage.show();
    }

    private void changeForecast() {
        if (weather.getForecast().getText().equalsIgnoreCase("Sunny")) {
            weather.setForecast("Rainy");
            weather.setDate("September 4th, 2025");
            weather.setForecastImageView(new Image("rainy.jpg"));
        } else if (weather.getForecast().getText().equalsIgnoreCase("Rainy")) {
            weather.setForecast("Snowy");
            weather.setDate("September 5th, 2025");
            weather.setForecastImageView(new Image("snowy.jpg"));
        } else {
            weather.setForecast("Sunny");
            weather.setDate("September 3rd, 2025");
            weather.setForecastImageView(new Image("sunny.jpg"));
        }
    }
}