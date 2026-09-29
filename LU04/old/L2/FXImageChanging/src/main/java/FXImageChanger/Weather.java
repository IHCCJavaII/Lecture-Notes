package FXImageChanger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Weather {

    // I changed these to JavaFX Text objects
    private Text date;
    private Text forecast;

    private ImageView forecastImageView;

    public Weather() {
        // set default starter values
        this.date = new Text("September 3rd, 2025");
        this.forecast = new Text("Sunny");
        this.forecastImageView = new ImageView("sunny.jpg");
        this.forecastImageView.setFitHeight(200);
        this.forecastImageView.setPreserveRatio(true);
    }

    public Text getDate() {
        return date;
    }

    public void setDate(String newDate) {
        this.date.setText(newDate);
    }

    public Text getForecast() {
        return forecast;
    }

    public void setForecast(String newForecast) {
        this.forecast.setText(newForecast);
    }

    public ImageView getForecastImageView() {
        return forecastImageView;
    }

    public void setForecastImageView(Image image) {
        this.forecastImageView.setImage(image);
    }
}
