package test;

import javafx.stage.Stage;
import pizza.App;
import pizza.Topping;
import javafx.scene.control.ComboBox;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

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
    public void selectUserPizza(){
        // Fill out the form

        // Submit

        // Select new pizza

        // Assert pizza is in the ComboBox
    }
}
