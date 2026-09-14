package org.example;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;
import static org.testfx.util.NodeQueryUtils.isVisible;

public class MainTests extends ApplicationTest {
    //create an instance of the class you are testing
    private Main main;

    @Override
    public void start(Stage stage){
        main = new Main();
        main.start(stage);
    }

    @Test
    public void clickButtonTest(){
        //click the button
        clickOn("#buy-burger-button");
        //verify that the text has changed

        // Find Image and see is visible
        verifyThat("#burger-image", isVisible());

        verifyThat("#thank-you-text", hasText("Thank you for your purchase!"));
    }
}
