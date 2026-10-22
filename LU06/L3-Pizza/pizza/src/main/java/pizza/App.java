package pizza;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class App extends Application {

    ComboBox<Pizza> allPizzas = new ComboBox<>();

    @Override
    public void start(Stage stage) {

        allPizzas.setId("all-pizzas-combo-box");
        
        Pizza userPizza = new Pizza();

        VBox form = createForm(userPizza);

        VBox root = new VBox(form, allPizzas);

        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public VBox createForm(Pizza userPizza) {

        VBox form = new VBox();

        ComboBox<Topping> toppingComboBox = new ComboBox<>();
        toppingComboBox.setId("topping-combo-box");
        toppingComboBox.getItems().addAll(Topping.values());

        toppingComboBox.setOnAction(event -> {
            Topping selectedTopping = toppingComboBox.getValue();
            userPizza.setTopping(selectedTopping);
            System.out.println("Selected Topping: " + userPizza.getTopping());
        });

        TextField sizeTextField = new TextField();
        sizeTextField.setId("size-text-field");
        sizeTextField.setPromptText("Enter pizza size");
        sizeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            int size = Integer.parseInt(newValue);
            userPizza.setSize(size);
            System.out.println("Pizza Size: " + userPizza.getSize());
        });

        RadioButton extraCheeseYes = new RadioButton("Yes");
        extraCheeseYes.setId("extra-cheese-yes");
        RadioButton extraCheeseNo = new RadioButton("No");
        extraCheeseNo.setId("extra-cheese-no");

        extraCheeseYes.setOnAction(event -> {
            userPizza.setExtraCheese(true);
            System.out.println("Extra Cheese: " + userPizza.isExtraCheese());
        });

        extraCheeseNo.setOnAction(event -> {
            userPizza.setExtraCheese(false);
            System.out.println("Extra Cheese: " + userPizza.isExtraCheese());
        });

        Button submitButton = new Button("Submit");
        submitButton.setId("submit-button");
        submitButton.setOnAction(event -> {
            System.out.println("Form submitted!");
            System.out.println(userPizza.toString());
            addPizzaToOrder(userPizza);
        });

        form.getChildren().addAll(toppingComboBox, sizeTextField, extraCheeseYes, extraCheeseNo, submitButton);

        return form;

    }

    public void addPizzaToOrder(Pizza pizza) {
        allPizzas.getItems().add(pizza);
    }

    public static void main(String[] args) {
        launch();
    }

}