package com.sigep.controller;

import com.sigep.model.ListaEnlazada;
import com.sigep.model.Practica;
import com.sigep.model.RegistroHora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class HorasFormViewController implements Initializable {

    @FXML
    private Label lblHistorialTitulo;

    @FXML
    private TableView<RegistroHora> tblHoras;

    @FXML
    private TableColumn<RegistroHora, String> colFecha;

    @FXML
    private TableColumn<RegistroHora, Integer> colHoras;

    @FXML
    private TableColumn<RegistroHora, String> colTarea;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnDeshacer;

    // Estructura de datos requerida: Lista Enlazada
    private final ListaEnlazada<RegistroHora> historialListaEnlazada = new ListaEnlazada<>();
    private final ObservableList<RegistroHora> listaObservables = FXCollections.observableArrayList();

    private Practica practicaActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
        cargarDatosIniciales();
    }

    private void configurarColumnas() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
        colTarea.setCellValueFactory(new PropertyValueFactory<>("tarea"));
    }

    private void cargarDatosIniciales() {
        // Carga inicial en la Lista Enlazada con los datos de la maqueta
        historialListaEnlazada.agregar(new RegistroHora("02/09", 4, "Guardia"));
        historialListaEnlazada.agregar(new RegistroHora("09/09", 5, "Archivo"));
        historialListaEnlazada.agregar(new RegistroHora("16/09", 4, "Admisión"));

        sincronizarTabla();
    }

    private void sincronizarTabla() {
        listaObservables.setAll(historialListaEnlazada.aLista());
        tblHoras.setItems(listaObservables);
    }

    public void setPractica(Practica practica) {
        this.practicaActual = practica;
    }

    @FXML
    private void onAgregar(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("4 - Guardia");
        dialog.setTitle("Agregar Horas");
        dialog.setHeaderText("Nueva carga de horas para el alumno");
        dialog.setContentText("Ingrese Horas - Tarea (ej. 4 - Capacitación):");

        Optional<String> resultado = dialog.showAndWait();
        if (resultado.isPresent() && !resultado.get().trim().isEmpty()) {
            String input = resultado.get().trim();
            int horas = 4;
            String tarea = input;

            if (input.contains("-")) {
                String[] partes = input.split("-", 2);
                try {
                    horas = Integer.parseInt(partes[0].trim());
                    tarea = partes[1].trim();
                } catch (NumberFormatException ignored) {
                }
            }

            String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM"));
            RegistroHora nuevo = new RegistroHora(fechaActual, horas, tarea);

            // Inserción en la lista enlazada
            historialListaEnlazada.agregar(nuevo);
            sincronizarTabla();
            tblHoras.getSelectionModel().selectLast();
        }
    }

    @FXML
    private void onDeshacer(ActionEvent event) {
        if (historialListaEnlazada.estaVacia()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Historial vacío");
            alert.setHeaderText(null);
            alert.setContentText("No hay más registros para deshacer en la lista enlazada.");
            alert.showAndWait();
            return;
        }

        // Deshacer el último nodo de la lista enlazada
        RegistroHora eliminado = historialListaEnlazada.deshacer();
        sincronizarTabla();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acción Deshecha");
        alert.setHeaderText("Se eliminó el último registro:");
        alert.setContentText(eliminado.getFecha() + " | " + eliminado.getHoras() + " hs | " + eliminado.getTarea());
        alert.showAndWait();
    }
}
