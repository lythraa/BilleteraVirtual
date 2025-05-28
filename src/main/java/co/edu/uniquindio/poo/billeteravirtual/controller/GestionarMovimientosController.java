package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestionarMovimientosController {

    private Stage stage;

    @FXML
    private Label lblTitulo;

    @FXML
    private TableView<Movimiento> tablaMovimientos;

    @FXML
    private TableColumn<Movimiento, String> columnaTipo;

    @FXML
    private TableColumn<Movimiento, String> columnaFecha;

    @FXML
    private TableColumn<Movimiento, String> columnaMonto;

    @FXML
    private TableColumn<Movimiento, String> columnaDescripcion;

    @FXML
    private TableColumn<Movimiento, String> columnaCategoria;

    @FXML
    private TableColumn<Movimiento, String> columnaDestino;

    private ObservableList<Movimiento> movimientosObservable = FXCollections.observableArrayList();

    private Usuario usuarioActual;

    @FXML
    public void initialize() {
        usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();
        configurarColumnas();
        cargarMovimientos();
    }

    private void configurarColumnas() {
        columnaTipo.setCellValueFactory(cellData -> {
            MovimientoStrategy estrategia = cellData.getValue().getEstrategia();
            String tipo = "";

            if (estrategia instanceof RetiroStrategy) {
                tipo = "Retiro";
            } else if (estrategia instanceof DepositoStrategy) {
                tipo = "Depósito";
            } else if (estrategia instanceof TransferenciaStrategy) {
                tipo = "Transferencia";
            }

            return new SimpleStringProperty(tipo);
        });

        columnaFecha.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getFecha().toString())
        );

        columnaMonto.setCellValueFactory(c ->
                new SimpleStringProperty(String.valueOf(c.getValue().getMonto()))
        );

        columnaDescripcion.setCellValueFactory(c -> {
            String desc = c.getValue().getDescripcionOpcional();
            return new SimpleStringProperty(desc != null ? desc : "");
        });

        columnaCategoria.setCellValueFactory(c -> {
            if (c.getValue().getCategoriaOpcional() != null) {
                return new SimpleStringProperty(c.getValue().getCategoriaOpcional().getId());
            } else {
                return new SimpleStringProperty("");
            }
        });

        columnaDestino.setCellValueFactory(c -> {
            Movimiento movimiento = c.getValue();
            if (movimiento.getEstrategia() instanceof TransferenciaStrategy) {
                if (movimiento.getCuentaBancariaDestino() != null) {
                    return new SimpleStringProperty(movimiento.getCuentaBancariaDestino().getId());
                }
                return new SimpleStringProperty("");
            }
            return new SimpleStringProperty("No aplica");
        });
    }

    private void cargarMovimientos() {
        movimientosObservable.setAll(usuarioActual.getHistorialMovimientos());
        tablaMovimientos.setItems(movimientosObservable);
    }

    @FXML
    private void onRefrescar() {
        cargarMovimientos();
        tablaMovimientos.getSelectionModel().clearSelection();
    }

    @FXML
    private void onRepetirMovimiento() {
        Movimiento movimientoSeleccionado = tablaMovimientos.getSelectionModel().getSelectedItem();
        if (movimientoSeleccionado != null) {
            // Aquí va la lógica para realizar nuevamente la transacción
        }
    }

    @FXML
    void onGenerarReporte() {
        List<String> opciones = Arrays.asList("PDF", "Excel");
        ChoiceDialog<String> dialogo = new ChoiceDialog<>("PDF", opciones);
        dialogo.setTitle("Generar Reporte");
        dialogo.setHeaderText("Seleccione el formato del reporte:");
        dialogo.setContentText("Formato:");

        Optional<String> resultado = dialogo.showAndWait();

        resultado.ifPresent(formato -> {
            Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();
            try {
                if (formato.equals("PDF")) {
                    SistemaBilleteraFacade.getInstancia().generarReportePDF(usuarioActual);
                    mostrarAlerta("Reporte generado exitosamente en PDF.");
                } else {
                    SistemaBilleteraFacade.getInstancia().generarReporteExcel(usuarioActual);
                    mostrarAlerta("Reporte generado exitosamente en Excel.");
                }
            } catch (Exception e) {
                mostrarAlerta("Error al generar el reporte: " + e.getMessage());
            }
        });
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Resultado del Reporte");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void onVolver() {
        stage = (Stage) tablaMovimientos.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
    }
}
