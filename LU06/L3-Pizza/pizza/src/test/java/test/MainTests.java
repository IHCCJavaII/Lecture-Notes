package test;

import javafx.stage.Stage;
import pizza.App;
import pizza.Pizza;
import pizza.Topping;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.ComboBoxMatchers.*;

public class MainTests extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        new App().start(stage);
    }

    @Test
    public void selectToppingTest() {
        ComboBox<Topping> comboBox = lookup("#topping-combo-box").query();
        interact(() -> comboBox.setValue(Topping.PEPPERONI));

        verifyThat("#topping-combo-box", hasSelectedItem(Topping.PEPPERONI));

        verifyThat("#topping-combo-box", containsExactlyItemsInOrder(
                Topping.PEPPERONI, Topping.SAUSAGE, Topping.CHEESE));
    }

    @Test
    public void selectUserPizza() {
        ComboBox<Topping> toppingComboBox = lookup("#topping-combo-box").query();
        TextField sizeTextField = lookup("#size-text-field").query();
        RadioButton extraCheeseYes = lookup("#extra-cheese-yes").query();

        interact(() -> {
            toppingComboBox.setValue(Topping.PEPPERONI);
            sizeTextField.setText("12");
            extraCheeseYes.fire();
            lookup("#submit-button").queryButton().fire();
        });

        ComboBox<Pizza> allPizzas = lookup("#all-pizzas-combo-box").query();

        // There should be one pizza
        assertEquals(1, allPizzas.getItems().size());

        // Get the pizza that was submitted and verify its properties
        Pizza submittedPizza = allPizzas.getItems().get(0);
        interact(() -> allPizzas.getSelectionModel().select(submittedPizza));

        // Check that the pizza data is correct
        verifyThat("#all-pizzas-combo-box", hasSelectedItem(submittedPizza));
        assertEquals(Topping.PEPPERONI, submittedPizza.getTopping());
        assertEquals(12, submittedPizza.getSize());
        assertEquals("Yes", submittedPizza.isExtraCheese());
    }
}
