package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.model.GestorMovimientos;
import co.edu.uniquindio.poo.billeteravirtual.model.Movimiento;
import co.edu.uniquindio.poo.billeteravirtual.model.DepositoStrategy;
import co.edu.uniquindio.poo.billeteravirtual.model.RetiroStrategy;
import co.edu.uniquindio.poo.billeteravirtual.model.TransferenciaStrategy;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class GestionarTransaccionesGlobalesController {

    @FXML
    private TableView<Movimiento> tablaMovimientos;

    @FXML
    private TableColumn<Movimiento, String> columnaId;

    @FXML
    private TableColumn<Movimiento, String> columnaFecha;

    @FXML
    private TableColumn<Movimiento, String> columnaDescripcion;

    @FXML
    private TableColumn<Movimiento, String> columnaCategoria;

    @FXML
    private TableColumn<Movimiento, String> columnaTipo;

    @FXML
    private TableColumn<Movimiento, Double> columnaMonto;

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getId()));

        columnaFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFecha()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        columnaMonto.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getMonto()).asObject());

        columnaDescripcion.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getDescripcionOpcional() != null
                                ? cellData.getValue().getDescripcionOpcional()
                                : ""
                ));

        columnaCategoria.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getCategoriaOpcional() != null
                                ? cellData.getValue().getCategoriaOpcional().getId_Nombre()
                                : "Sin categoría"
                ));

        columnaTipo.setCellValueFactory(cellData -> {
            String tipo;
            if (cellData.getValue().getEstrategia() instanceof DepositoStrategy) {
                tipo = "Depósito";
            } else if (cellData.getValue().getEstrategia() instanceof RetiroStrategy) {
                tipo = "Retiro";
            } else if (cellData.getValue().getEstrategia() instanceof TransferenciaStrategy) {
                tipo = "Transferencia";
            } else {
                tipo = "Desconocido";
            }
            return new SimpleStringProperty(tipo);
        });

        cargarDatos();
    }


    private void cargarDatos() {
        tablaMovimientos.setItems(FXCollections.observableArrayList(
                GestorMovimientos.getInstancia().getListaObjetos()
        ));
    }

    @FXML
    private void onEliminar() {
        Movimiento movimientoSeleccionado = tablaMovimientos.getSelectionModel().getSelectedItem();

        if (movimientoSeleccionado == null) {
            // No hay selección, avisar al usuario
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Eliminar Movimiento");
            alerta.setHeaderText(null);
            alerta.setContentText("Debe seleccionar un movimiento para eliminar.");
            alerta.showAndWait();
            return;
        }

        // Confirmar eliminación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Está seguro de eliminar el movimiento seleccionado?");

        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            GestorMovimientos.getInstancia().eliminar(movimientoSeleccionado);
            onRefrescarTabla();  // Actualizar tabla luego de eliminar
        }
    }

    @FXML
    private void onRefrescarTabla() {
        cargarDatos();
    }

    @FXML
    private void onVolver() {
        Stage stage = (Stage) tablaMovimientos.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Administrador");
    }
}

