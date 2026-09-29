package FXTable;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

public class MainTests extends ApplicationTest {

    private App main;

    @Override
    public void start(Stage stage) {
        main = new App();
        main.start(stage);
    }

    @Test
    public void testDefaultData() {
        verifyThat("#nameTableView", isVisible());

        assertEquals(48, main.nameTableView.getItems().size());
        assertEquals("james", main.nameTableView.getItems().get(0).getFirstName());
        assertEquals("smith", main.nameTableView.getItems().get(0).getLastName());
        assertEquals(45, main.nameTableView.getItems().get(0).getAge());
    }

    @Test
    public void testColumnClickSortsData() {
        WaitForAsyncUtils.waitForFxEvents();

        System.out.println("DEBUG screenBounds=" + javafx.stage.Screen.getPrimary().getBounds());
        System.out.println("DEBUG stageX=" + main.nameTableView.getScene().getWindow().getX()
                + " stageY=" + main.nameTableView.getScene().getWindow().getY());

        String firstNameBeforeSort = main.nameTableView.getItems().get(0).getFirstName();

        javafx.scene.Node header = lookup("#firstNameCol").query();
        javafx.geometry.Point2D point = header.localToScreen(10, header.getBoundsInLocal().getHeight() / 2);
        System.out.println("DEBUG header=" + header + " point=" + point);

        // Click the "First Name" column header to sort the table by that column
        clickOn(point);
        WaitForAsyncUtils.waitForFxEvents();

        System.out.println("DEBUG sortOrder=" + main.nameTableView.getSortOrder());

        String firstNameAfterSort = main.nameTableView.getItems().get(0).getFirstName();

        assertNotEquals(firstNameBeforeSort, firstNameAfterSort);
        assertEquals("amanda", firstNameAfterSort);
    }
}
