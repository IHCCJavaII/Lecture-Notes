package crystals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxRobot;

@ExtendWith(ApplicationExtension.class)
class AppTest {

    // TestFX creates the JavaFX runtime and calls this before each test.
    @Start
    void start(Stage stage) {
        new App().start(stage);
    }

    @Test
    void displaysStartButtonAndOneButtonForEachTone(FxRobot robot) {
        // Collect visible button labels so we can verify the game controls exist.
        Set<String> buttonLabels = robot.lookup(".button").queryAll().stream()
                .filter(Button.class::isInstance)
                .map(Button.class::cast)
                .map(Button::getText)
                .collect(Collectors.toSet());

        // Start is available initially; tone buttons stay disabled until playback ends.
        assertEquals(Tone.values().length + 1, buttonLabels.size());
        assertTrue(buttonLabels.contains("Start"));
        assertFalse(robot.lookup("Start").queryAs(Button.class).isDisabled());
        for (Tone tone : Tone.values()) {
            assertTrue(buttonLabels.contains(tone.name()));
            assertTrue(robot.lookup(tone.name()).queryAs(Button.class).isDisabled());
        }
    }
}
