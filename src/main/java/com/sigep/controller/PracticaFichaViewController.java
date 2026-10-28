package com.sigep.controller;

import com.sigep.model.Practica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PracticaFichaViewController implements Initializable {

    @FXML
    private Label lblSubtitulo;

    @FXML
    private TextField txtAlumno;

    @FXML
    private TextField txtOrganizacion;

    @FXML
    private TextField txtEstado;

    @FXML
    private Button btnCargarHoras;

    @FXML
    private Button btnEvaluacion;

    @FXML
    private Button btnCerrar;

    private Practica practicaActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Valores iniciales por defecto si se previsualiza en Scene Builder
        if (practicaActual == null) {
            lblSubtitulo.setText("Recibe: Practica de L. Gómez");
            txtAlumno.setText("L. Gómez");
            txtOrganizacion.setText("Hospital PIO");
            txtEstado.setText("EN_CURSO");
        }
    }

    /**
     * Recibe la práctica desde MainViewController (setPractica)
     */
    public void setPractica(Practica practica) {
        this.practicaActual = practica;
        if (practica != null) {
            lblSubtitulo.setText("Recibe: Practica de " + practica.getAlumno());
            txtAlumno.setText(practica.getAlumno());
            txtOrganizacion.setText(practica.getOrganizacion());
            txtEstado.setText(practica.getEstado());
        }
    }

    @FXML
    private void onCargarHoras(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigep/HorasFormView.fxml"));
            Parent root = loader.load();

            HorasFormViewController controller = loader.getController();
            controller.setPractica(practicaActual);

            Stage stage = new Stage();
            stage.setTitle("Horas modal");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnCargarHoras.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al abrir ventana de horas");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onEvaluacion(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Evaluación");
        alert.setHeaderText("Módulo de Evaluación");
        alert.setContentText("Evaluación de la práctica de " + (practicaActual != null ? practicaActual.getAlumno() : "alumno") + ".");
        alert.showAndWait();
    }

    @FXML
    private void onCerrar(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}
