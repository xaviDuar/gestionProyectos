package com.sigep.controller;

import com.sigep.model.Practica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private TableView<Practica> tblPracticas;

    @FXML
    private TableColumn<Practica, String> colAlumno;

    @FXML
    private TableColumn<Practica, String> colOrganizacion;

    @FXML
    private TableColumn<Practica, String> colEstado;

    @FXML
    private TableColumn<Practica, String> colInicio;

    @FXML
    private Button btnNuevaPractica;

    @FXML
    private Button btnCargarHoras;

    @FXML
    private Label lblStatus;

    private final ObservableList<Practica> listaPracticas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
        cargarDatosDePrueba();
        configurarEventosTabla();
    }

    private void configurarColumnas() {
        colAlumno.setCellValueFactory(new PropertyValueFactory<>("alumno"));
        colOrganizacion.setCellValueFactory(new PropertyValueFactory<>("organizacion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colInicio.setCellValueFactory(new PropertyValueFactory<>("inicio"));
    }

    private void cargarDatosDePrueba() {
        // Datos exactos que figuran en la maqueta
        listaPracticas.addAll(
            new Practica("L. Gómez", "Hospital PIO", "EN_CURSO", "01/08"),
            new Practica("M. Díaz", "Muni. Vicente L.", "PROPUESTA", "—"),
            new Practica("R. Acosta", "Cooperativa Sur", "FINALIZADA", "03/03"),
            new Practica("S. Ruiz", "Estudio López", "EN_CURSO", "15/08")
        );
        tblPracticas.setItems(listaPracticas);

        // Seleccionar por defecto la primera fila (L. Gómez)
        if (!listaPracticas.isEmpty()) {
            tblPracticas.getSelectionModel().select(0);
        }
    }

    private void configurarEventosTabla() {
        // Doble clic sobre una fila abre la ficha de la práctica
        tblPracticas.setRowFactory(tv -> {
            TableRow<Practica> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    abrirFichaPractica(row.getItem());
                }
            });
            return row;
        });
    }

    @FXML
    private void onNuevaPractica(ActionEvent event) {
        Practica nueva = new Practica("Nuevo Alumno", "Empresa / Org", "PROPUESTA", "Pendiente");
        listaPracticas.add(nueva);
        tblPracticas.getSelectionModel().select(nueva);
        abrirFichaPractica(nueva);
    }

    @FXML
    private void onCargarHoras(ActionEvent event) {
        Practica seleccionada = tblPracticas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atención");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione una práctica de la tabla.");
            alert.showAndWait();
            return;
        }
        abrirFichaPractica(seleccionada);
    }

    public void abrirFichaPractica(Practica practica) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sigep/PracticaFichaView.fxml"));
            Parent root = loader.load();

            PracticaFichaViewController controller = loader.getController();
            controller.setPractica(practica);

            Stage stage = new Stage();
            stage.setTitle("Ficha práctica modal");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(tblPracticas.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al abrir Ficha de Práctica", e.getMessage());
        }
    }

    @FXML
    private void onSalir(ActionEvent event) {
        Stage stage = (Stage) tblPracticas.getScene().getWindow();
        stage.close();
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
