package com.sigep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aplicación Principal SIGEP - JavaFX
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/sigep/MainView.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root, 820, 540);
        stage.setTitle("SIGEP — Principal   MainView.fxml");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
