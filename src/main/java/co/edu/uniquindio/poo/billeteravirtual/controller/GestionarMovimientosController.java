package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GestionarMovimientosController {

    @FXML
    private ComboBox<String> comboTipoTransaccion;

    @FXML
    private TextField campoDescripcion;

    @FXML
    private TextField campoIdReceptor;

    @FXML
    private Label labelReceptor;

    @FXML
    private TextField campoMonto;

    @FXML
    private TextField campoIdEmisor;

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
        comboTipoTransaccion.setItems(FXCollections.observableArrayList("Transferencia", "Depósito", "Retiro"));
        comboTipoTransaccion.setOnAction(event -> actualizarCampos());
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
            Movimiento clon = movimientoSeleccionado.clone();

            // Mostrar input para nuevo monto
            TextInputDialog dialog = new TextInputDialog(String.valueOf(clon.getMonto()));
            dialog.setTitle("Repetir Movimiento");
            dialog.setHeaderText("Modificar el monto antes de ejecutar");
            dialog.setContentText("Nuevo monto:");

            Optional<String> resultado = dialog.showAndWait();
            resultado.ifPresent(input -> {
                try {
                    double nuevoMonto = Double.parseDouble(input);
                    clon.setMonto(nuevoMonto);

                    // Procesar dependiendo de la estrategia
                    if (clon.getStrategy() instanceof DepositoStrategy) {
                        sistemaBilletera.realizarDeposito(usuarioActual, clon.getCuentaDestino(), nuevoMonto, clon.getCategoria(), clon.getDescripcion());
                    } else if (clon.getStrategy() instanceof RetiroStrategy) {
                        sistemaBilletera.realizarRetiro(usuarioActual, clon.getCuentaOrigen(), nuevoMonto, clon.getCategoria(), clon.getDescripcion());
                    } else if (clon.getStrategy() instanceof TransferenciaStrategy) {
                        sistemaBilletera.realizarTransferencia(usuarioActual, clon.getCuentaOrigen(), clon.getCuentaDestino(), nuevoMonto, clon.getCategoria(), clon.getDescripcion());
                    }

                } catch (NumberFormatException e) {
                    mostrarError("El monto ingresado no es válido.");
                }
            });
        } else {
            mostrarError("Debe seleccionar un movimiento para repetir.");
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

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
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
        Stage stage = (Stage) tablaMovimientos.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
    }

    @FXML
    private void onEnviar() {
        String tipo = (String) comboTipoTransaccion.getValue();
        String idEmisor = campoIdEmisor.getText();
        String montoTexto = campoMonto.getText();
        String descripcion = campoDescripcion.getText();
        String idReceptor = campoIdReceptor.getText();
        Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();

        if (tipo == null || idEmisor.isEmpty() || montoTexto.isEmpty()) {
            UtilAlerta.mostrarAlertaAdvertencia("Campos Vacios", "Por favor complete todos los campos obligatorios.");
            return;
        }
        double monto;
        try {
            monto = Double.parseDouble(montoTexto);
        } catch (NumberFormatException e) {
            UtilAlerta.mostrarAlertaInformacion("Numero invalido","El monto debe ser un número válido.");
            return;
        }

        try {
            switch (tipo) {
                case "Transferencia":
                    if (idReceptor == null || idReceptor.isEmpty()) {
                        UtilAlerta.mostrarAlertaInformacion("Campo Vacio", "Por favor ingrese el ID del receptor para una transferencia.");
                        return;
                    }
                    //SistemaBilleteraFacade.getInstancia().realizarTransferencia(usuarioActual,);
                    break;

                case "Depósito":
                    //SistemaBilleteraFacade.getInstancia().realizarDeposito(idEmisor, monto, descripcion);
                    break;

                case "Retiro":
                    //SistemaBilleteraFacade.getInstancia().realizarRetiro(idEmisor, monto, descripcion);
                    break;

                default:
                    mostrarAlerta("Tipo de transacción desconocido.");
                    return;
            }

            //mostrarAlerta("¡Transacción realizada con éxito!");XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            onLimpiar();
            onRefrescar();

        } catch (Exception e) {
            mostrarAlerta("Error al realizar la transacción: " + e.getMessage());
        }
    }

    @FXML
    private void onLimpiar(){
        comboTipoTransaccion.getSelectionModel().clearSelection();
        campoIdEmisor.clear();
        campoIdReceptor.clear();
        campoMonto.clear();
        campoDescripcion.clear();
        campoIdReceptor.setVisible(false);
        campoIdReceptor.setManaged(false);
        labelReceptor.setVisible(false);
        labelReceptor.setManaged(false);
    }

    private void actualizarCampos() {
        String tipoSeleccionado = (String) comboTipoTransaccion.getValue();
        boolean mostrarReceptor = "Transferencia".equalsIgnoreCase(tipoSeleccionado);

        campoIdReceptor.setVisible(mostrarReceptor);
        campoIdReceptor.setManaged(mostrarReceptor);

        labelReceptor.setVisible(mostrarReceptor);
        labelReceptor.setManaged(mostrarReceptor);
    }

}
