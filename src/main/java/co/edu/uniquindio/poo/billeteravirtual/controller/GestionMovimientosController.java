package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador para gestionar movimientos: depósito, retiro y transferencia.
 */
public class GestionMovimientosController {

    @FXML
    private ComboBox<String> comboTipoTransaccion;
    @FXML
    private ComboBox<String> comboIdCuentaOrigen;
    @FXML
    private TextField campoDescripcion;
    @FXML
    private TextField campoIdCuentaDestino;
    @FXML
    private Label labelIdCuentaDestino;
    @FXML
    private TextField campoMonto;
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
    @FXML
    private TextField campoCategoria;

    private final ObservableList<Movimiento> movimientosObservable = FXCollections.observableArrayList();

    private Usuario usuarioActual;

    /**
     * Inicializa la vista y configura los componentes.
     */
    @FXML
    public void initialize() {
        usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();

        comboTipoTransaccion.setItems(FXCollections.observableArrayList("Transferencia", "Depósito", "Retiro"));
        comboTipoTransaccion.setOnAction(event -> actualizarCampos());

        List<CuentaBancaria> cuentas = usuarioActual.getListaCuentasBancarias();
        List<String> idsCuentas = cuentas.stream()
                .map(CuentaBancaria::getId)
                .collect(Collectors.toList());
        comboIdCuentaOrigen.setItems(FXCollections.observableArrayList(idsCuentas));

        configurarColumnas();
        cargarMovimientos();

        campoIdCuentaDestino.setVisible(false);
        campoIdCuentaDestino.setManaged(false);
        labelIdCuentaDestino.setVisible(false);
        labelIdCuentaDestino.setManaged(false);
    }

    /**
     * Configura las columnas de la tabla de movimientos.
     */
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

        columnaFecha.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFecha().toString()));

        columnaMonto.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getMonto())));

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

    /**
     * Carga los movimientos del usuario en la tabla.
     */
    private void cargarMovimientos() {
        movimientosObservable.setAll(usuarioActual.getHistorialMovimientos());
        tablaMovimientos.setItems(movimientosObservable);
        System.out.println("Movimientos actuales: " + usuarioActual.getHistorialMovimientos());
//TODO
    }

    /**
     * Refresca la tabla de movimientos.
     */
    @FXML
    private void onRefrescar() {
        cargarMovimientos();
        tablaMovimientos.getSelectionModel().clearSelection();
    }

    /**
     * Genera un reporte con los movimientos del usuario.
     * Si el reporte se desea en pdf,
     */
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
                    UtilAlerta.mostrarAlertaInformacion("Reporte generado", "Reporte generado exitosamente en PDF.");
                } else {
                    SistemaBilleteraFacade.getInstancia().generarReporteExcel(usuarioActual);
                    UtilAlerta.mostrarAlertaInformacion("Reporte generado", "Reporte generado exitosamente en Excel.");
                }
            } catch (Exception e) {
                UtilAlerta.mostrarAlertaError("Error al generar el reporte", e.getMessage());
            }
        });
    }


    /**
     * Regresa a la vista principal del usuario.
     */
    @FXML
    private void onVolver() {
        Stage stage = (Stage) tablaMovimientos.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
    }


    /**
     * Ejecuta la transacción seleccionada.
     */
    @FXML
    private void onEnviar() {
        String tipo = comboTipoTransaccion.getValue();
        String montoTexto = campoMonto.getText();
        String descripcion = campoDescripcion.getText();
        String idCuentaDestino = campoIdCuentaDestino.getText();
        String categoriaTexto = campoCategoria.getText();
        String idCuentaOrigen = comboIdCuentaOrigen.getValue();
        Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();
        CuentaBancaria cuentaBancariaOrigen = SistemaBilleteraFacade.getInstancia().buscarCuenta(idCuentaOrigen);
        if (cuentaBancariaOrigen == null) {
            UtilAlerta.mostrarAlertaNoEncontrado("Cuenta origen");
            return;
        }

        CuentaBancaria cuentaBancariaDestino = SistemaBilleteraFacade.getInstancia().buscarCuenta(idCuentaDestino);
        Categoria categoria = usuarioActual.buscarCategoria(categoriaTexto);

        if (UtilAlerta.esInvalido(tipo, "Tipo de transacción")) return;
        if (UtilAlerta.esInvalido(idCuentaOrigen, "ID Cuenta Origen")) return;
        if (UtilAlerta.esInvalido(montoTexto, "Monto")) return;
        Double monto = UtilAlerta.validarYConvertirMonto(montoTexto, "Monto");
        if (monto == null) return;

        try {
            switch (tipo) {
                case "Transferencia":
                    if (idCuentaDestino.isBlank()) {
                        UtilAlerta.mostrarAlertaAdvertencia("Campo vacío", "Por favor ingrese el ID de la cuenta destino.");
                        return;
                    }
                    if (cuentaBancariaDestino == null) {
                        UtilAlerta.mostrarAlertaNoEncontrado("Cuenta destino");
                        return;
                    }
                    SistemaBilleteraFacade.getInstancia().realizarTransferencia(usuarioActual, cuentaBancariaOrigen,cuentaBancariaDestino, monto, categoria, descripcion);

                    break;

                case "Depósito":
                    SistemaBilleteraFacade.getInstancia().realizarDeposito(usuarioActual, cuentaBancariaOrigen, monto, descripcion);
                    break;

                case "Retiro":
                    SistemaBilleteraFacade.getInstancia().realizarRetiro(usuarioActual, cuentaBancariaOrigen, monto, categoria, descripcion);
                    break;

                default:
                    UtilAlerta.mostrarAlertaAdvertencia("Tipo desconocido", "Tipo de transacción desconocido.");
                    return;
            }

            UtilAlerta.mostrarAlertaInformacion("Éxito", "¡Transacción realizada con éxito!");
            onLimpiar();
            onRefrescar();

        } catch (Exception e) {
            UtilAlerta.mostrarAlertaError("Error de transacción", e.getMessage());
        }
    }

    /**
     * Limpia los campos del formulario.
     */
    @FXML
    private void onLimpiar() {
        comboTipoTransaccion.getSelectionModel().clearSelection();
        campoIdCuentaDestino.clear();
        campoMonto.clear();
        campoDescripcion.clear();
        campoCategoria.clear();

        campoIdCuentaDestino.setVisible(false);
        campoIdCuentaDestino.setManaged(false);
        labelIdCuentaDestino.setVisible(false);
        labelIdCuentaDestino.setManaged(false);
    }

    /**
     * Muestra y oculta los campos según el tipo de transacción.
     */
    private void actualizarCampos() {
        String tipoSeleccionado = comboTipoTransaccion.getValue();
        boolean mostrarDestino = "Transferencia".equalsIgnoreCase(tipoSeleccionado);

        campoIdCuentaDestino.setVisible(mostrarDestino);
        campoIdCuentaDestino.setManaged(mostrarDestino);

        labelIdCuentaDestino.setVisible(mostrarDestino);
        labelIdCuentaDestino.setManaged(mostrarDestino);
    }
}
