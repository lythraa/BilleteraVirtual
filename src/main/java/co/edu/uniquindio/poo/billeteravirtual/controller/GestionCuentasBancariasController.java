package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GestionCuentasBancariasController {

    @FXML
    private TextField campoNumeroCuenta;
    @FXML
    private TableView<CuentaBancaria> tablaCuentas;
    @FXML
    private TextField campoSaldo;
    @FXML
    private TableColumn<CuentaBancaria, String> columnaNombreUsuario;
    @FXML
    private TextField campoNombreBanco;
    @FXML
    private TableColumn<CuentaBancaria, String> columnaIdUsuario;
    @FXML
    private TableColumn<CuentaBancaria, Double> columnaSaldo;
    @FXML
    private TableColumn<CuentaBancaria, String> columnaBanco;
    @FXML
    private TextField campoBusqueda;
    @FXML
    private ComboBox<String> comboTipoCuenta;
    @FXML
    private TableColumn<CuentaBancaria, String> columnaTipoCuenta;
    @FXML
    private TextField campoIdUsuario;
    @FXML
    private TableColumn<CuentaBancaria, String> columnaNumeroCuenta;

    private ObservableList<CuentaBancaria> listaObservableCuentas;

    /**
     * Regresa a la Vista anterior
     */
    @FXML
    private void onVolver() {
        Stage stage = (Stage) campoNumeroCuenta.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Administrador");
    }


    @FXML
    void onAgregar() {
        String numeroCuenta = campoNumeroCuenta.getText().trim();
        String nombreBanco = campoNombreBanco.getText().trim();
        String idUsuario = campoIdUsuario.getText().trim();
        String tipoCuenta = comboTipoCuenta.getValue();
        String saldoTexto = campoSaldo.getText().trim();

        // Validación
        if (numeroCuenta.isEmpty() || nombreBanco.isEmpty() || idUsuario.isEmpty() || tipoCuenta == null || saldoTexto.isEmpty()) {
            UtilAlerta.mostrarAlertaError("Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        double saldo;
        try {
            saldo = Double.parseDouble(saldoTexto);
        } catch (NumberFormatException e) {
            UtilAlerta.mostrarAlertaError("Saldo inválido", "El saldo debe ser un número.");
            return;
        }

        Usuario usuario = GestorUsuarios.getInstancia().buscar(idUsuario);
        if (usuario == null) {
            UtilAlerta.mostrarAlertaError("Usuario no encontrado", "No se encontró un usuario con ese ID.");
            return;
        }

        CuentaBancaria nuevaCuenta = new CuentaBancaria(numeroCuenta, nombreBanco, TipoCuenta.valueOf(tipoCuenta));
        nuevaCuenta.agregarSaldo(saldo);
        usuario.getListaCuentasBancarias().add(nuevaCuenta);
        GestorCuentasBancarias.getInstancia().agregar(nuevaCuenta);

        cargarCuentasEnTabla();
        limpiarCampos();
    }

    @FXML
    void onEditar() {
        CuentaBancaria seleccionada = tablaCuentas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            UtilAlerta.mostrarAlertaAdvertencia("Sin selección", "Debes seleccionar una cuenta para editar.");
            return;
        }

        String numeroCuenta = campoNumeroCuenta.getText().trim();
        String nombreBanco = campoNombreBanco.getText().trim();
        String tipoCuenta = comboTipoCuenta.getValue();
        String saldoTexto = campoSaldo.getText().trim();

        if (numeroCuenta.isEmpty() || nombreBanco.isEmpty() || tipoCuenta == null || saldoTexto.isEmpty()) {
            UtilAlerta.mostrarAlertaError("Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        double saldo;
        try {
            saldo = Double.parseDouble(saldoTexto);
        } catch (NumberFormatException e) {
            UtilAlerta.mostrarAlertaError("Saldo inválido", "El saldo debe ser un número.");
            return;
        }

        CuentaBancaria cuentaEditada = new CuentaBancaria(numeroCuenta, nombreBanco, TipoCuenta.valueOf(tipoCuenta));
        cuentaEditada.agregarSaldo(saldo);
        for (Usuario usuario : GestorUsuarios.getInstancia().getListaObjetos()) {
            List<CuentaBancaria> cuentas = usuario.getListaCuentasBancarias();
            if (cuentas.contains(seleccionada)) {
                cuentas.remove(seleccionada);
                cuentas.add(cuentaEditada);
                break;
            }
        }

        GestorCuentasBancarias.getInstancia().reemplazar(seleccionada.getId(), cuentaEditada);
        UtilAlerta.mostrarAlertaInformacion("Cuenta editada", "La cuenta se ha editado correctamente.");
        cargarCuentasEnTabla();
        limpiarCampos();
    }

    @FXML
    void onBuscar() {
        String idUsuarioBuscado = campoBusqueda.getText().trim();
        if (idUsuarioBuscado.isEmpty()) {
            cargarCuentasEnTabla();
            return;
        }

        listaObservableCuentas.clear();

        for (Usuario usuario : GestorUsuarios.getInstancia().getListaObjetos()) {
            if (usuario.getId().equalsIgnoreCase(idUsuarioBuscado)) {
                listaObservableCuentas.clear();
                listaObservableCuentas.addAll(usuario.getListaCuentasBancarias());
                break;
            }
        }

        tablaCuentas.setItems(listaObservableCuentas);
    }

    @FXML
    void onEliminar() {
        CuentaBancaria seleccionada = tablaCuentas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            UtilAlerta.mostrarAlertaAdvertencia("Sin selección", "Debes seleccionar una cuenta para eliminar.");
            return;
        }

        boolean confirmacion = UtilAlerta.mostrarAlertaConfirmacion("¿Eliminar cuenta?", "¿Estás seguro de que deseas eliminar esta cuenta?");
        if (!confirmacion) return;

        for (Usuario usuario : GestorUsuarios.getInstancia().getListaObjetos()) {
            usuario.getListaCuentasBancarias().remove(seleccionada);
        }

        GestorCuentasBancarias.getInstancia().eliminar(seleccionada);

        cargarCuentasEnTabla();
        limpiarCampos();
    }

    @FXML
    void initialize() {
        columnaIdUsuario.setCellValueFactory(cellData -> {
            CuentaBancaria cuenta = cellData.getValue();
            for (Usuario usuario : GestorUsuarios.getInstancia().getListaObjetos()) {
                if (usuario.getListaCuentasBancarias().contains(cuenta)) {
                    return new SimpleStringProperty(usuario.getId());
                }
            }
            return new SimpleStringProperty("Desconocido");
        });

        columnaNombreUsuario.setCellValueFactory(cellData -> {
            CuentaBancaria cuenta = cellData.getValue();
            for (Usuario usuario : GestorUsuarios.getInstancia().getListaObjetos()) {
                if (usuario.getListaCuentasBancarias().contains(cuenta)) {
                    return new SimpleStringProperty(usuario.getNombre());
                }
            }
            return new SimpleStringProperty("Desconocido");
        });

        columnaBanco.setCellValueFactory(new PropertyValueFactory<>("nombreBanco"));
        columnaNumeroCuenta.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        columnaTipoCuenta.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipoCuenta().toString())
        );
        comboTipoCuenta.setItems(FXCollections.observableArrayList(
                Arrays.stream(TipoCuenta.values()).map(Enum::name).collect(Collectors.toList())
        ));
        cargarCuentasEnTabla();
    }

    private void cargarCuentasEnTabla() {
        listaObservableCuentas = FXCollections.observableArrayList();

        for (Usuario usuario : GestorUsuarios.getInstancia().getListaObjetos()) {
            listaObservableCuentas.addAll(usuario.getListaCuentasBancarias());
        }

        tablaCuentas.setItems(listaObservableCuentas);
    }

    private void limpiarCampos() {
        campoNumeroCuenta.clear();
        campoNombreBanco.clear();
        campoSaldo.clear();
        campoIdUsuario.clear();
        comboTipoCuenta.getSelectionModel().clearSelection();
    }
}
