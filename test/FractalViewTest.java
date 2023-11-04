import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;



class FractalViewTest {
    FractalView view;

    @BeforeEach
    void setUp() {
        view = new FractalView();
    }

    @AfterEach
    void tearDown() {
        view = null;
    }

    @Test
    void testArt() {
        //view.start();
    }
}