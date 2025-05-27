package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.model.GestorMovimientos;
import co.edu.uniquindio.poo.billeteravirtual.model.Movimiento;
import co.edu.uniquindio.poo.billeteravirtual.model.DepositoStrategy;
import co.edu.uniquindio.poo.billeteravirtual.model.RetiroStrategy;
import co.edu.uniquindio.poo.billeteravirtual.model.TransferenciaStrategy;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
/**
 * Controlador para la vista de gestión global de transacciones.
 * Permite visualizar y eliminar movimientos desde la vista del administrador.
 */
public class MovimientosGlobalesController {

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

    /**
     * Inicializa la tabla de movimientos y carga los datos disponibles.
     */
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
                                ? cellData.getValue().getCategoriaOpcional().getId()
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

    /**
     * Carga todos los movimientos registrados en la tabla.
     */
    private void cargarDatos() {
        tablaMovimientos.setItems(FXCollections.observableArrayList(
                GestorMovimientos.getInstancia().getListaObjetos()
        ));
    }

    /**
     * Refresca la tabla de movimientos recargando los datos.
     */
    @FXML
    private void onRefrescarTabla() {
        cargarDatos();
    }


    /**
     * Vuelve a la vista del panel del administrador.
     */
    @FXML
    private void onVolver() {
        Stage stage = (Stage) tablaMovimientos.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Administrador");
    }
}

