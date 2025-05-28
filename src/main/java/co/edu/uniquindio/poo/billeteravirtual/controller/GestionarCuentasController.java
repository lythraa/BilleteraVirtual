package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.model.CuentaBancaria;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorUsuarios;

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

public class GestionarCuentasController {

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
     * Método para regresar a la pantalla anterior
     */
    @FXML
    private void onVolver() {
        Stage stage = (Stage) campoNumeroCuenta.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Administrador");
    }


    @FXML
    void onAgregar() {
        // Implementa agregar aquí
    }

    @FXML
    void onEditar() {
        // Implementa editar aquí
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
        // Implementa eliminar aquí
    }

    @FXML
    void initialize() {
        // Asignar el tipo genérico a las columnas
        columnaIdUsuario.setCellValueFactory(cellData -> {
            CuentaBancaria cuenta = cellData.getValue();
            // Buscar el usuario que tiene esta cuenta
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

        // Cargar cuentas inicialmente
        cargarCuentasEnTabla();
    }

    private void cargarCuentasEnTabla() {
        listaObservableCuentas = FXCollections.observableArrayList();

        for (Usuario usuario : GestorUsuarios.getInstancia().getListaObjetos()) {
            listaObservableCuentas.addAll(usuario.getListaCuentasBancarias());
        }

        tablaCuentas.setItems(listaObservableCuentas);
    }
}
