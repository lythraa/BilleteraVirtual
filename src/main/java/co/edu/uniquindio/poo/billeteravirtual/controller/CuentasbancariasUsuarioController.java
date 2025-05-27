package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.CuentaBancaria;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CuentasbancariasUsuarioController {

    @FXML
    private TableView<CuentaBancaria> tablaCuentas;

    @FXML
    private TableColumn<CuentaBancaria, String> columnaId;

    @FXML
    private TableColumn<CuentaBancaria, String> columnaBanco;

    @FXML
    private TableColumn<CuentaBancaria, String> columnaTipo;

    @FXML
    private TableColumn<CuentaBancaria, String> columnaSaldo;

    private final ObservableList<CuentaBancaria> cuentasObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarCuentas();
    }

    private void configurarColumnas() {
        columnaId.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getId()));
        columnaBanco.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNombreBanco()));
        columnaTipo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTipoCuenta().toString()));
        columnaSaldo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(String.format("$ %.2f", c.getValue().getSaldo())));
    }

    private void cargarCuentas() {
        Usuario usuario = (Usuario) GestorSesion.getInstance().getPerfilActual();
        cuentasObservable.setAll(usuario.getListaCuentasBancarias());
        tablaCuentas.setItems(cuentasObservable);
    }

    @FXML
    private void onVolver() {
        Stage stage = (Stage) tablaCuentas.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
    }
}
