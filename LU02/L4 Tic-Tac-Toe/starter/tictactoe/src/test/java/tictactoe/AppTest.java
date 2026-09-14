package tictactoe;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

// TODO these tests don't work. 
public class AppTest extends ApplicationTest {

    private App app;

    @Override
    public void start(Stage stage) {
        app = new App();
        app.start(stage);
        stage.toFront();
        stage.requestFocus();
    }

    @Test
    void clickSquareShowsX() {
        clickOn("#square-0-0");
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#square-0-0", hasText("X"));
    }

    @Test
    void clickSquareShowsOAfterX() {
        clickOn("#square-0-0");
        // Wait for 1 second.
        
        clickOn("#square-0-1");
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#square-0-1", hasText("O"));
    }

    @Test
    void boardStartsWithNineTiles() {
        verifyThat("#board", (board) -> board.lookupAll(".button").size() == 9);
    }
}
