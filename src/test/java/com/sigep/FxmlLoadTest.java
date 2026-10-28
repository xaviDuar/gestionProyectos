package com.sigep;

import com.sigep.controller.HorasFormViewController;
import com.sigep.controller.MainViewController;
import com.sigep.controller.PracticaFichaViewController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FxmlLoadTest {

    @BeforeAll
    public static void initJavaFX() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // Already started
        }
    }

    @Test
    public void testMainViewLoads() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigep/MainView.fxml"));
        Object root = loader.load();
        assertNotNull(root, "MainView root should not be null");
        MainViewController controller = loader.getController();
        assertNotNull(controller, "MainViewController should not be null");
    }

    @Test
    public void testPracticaFichaViewLoads() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigep/PracticaFichaView.fxml"));
        Object root = loader.load();
        assertNotNull(root, "PracticaFichaView root should not be null");
        PracticaFichaViewController controller = loader.getController();
        assertNotNull(controller, "PracticaFichaViewController should not be null");
    }

    @Test
    public void testHorasFormViewLoads() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigep/HorasFormView.fxml"));
        Object root = loader.load();
        assertNotNull(root, "HorasFormView root should not be null");
        HorasFormViewController controller = loader.getController();
        assertNotNull(controller, "HorasFormViewController should not be null");
    }
}
