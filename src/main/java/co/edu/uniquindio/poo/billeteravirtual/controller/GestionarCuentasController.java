package co.edu.uniquindio.poo.billeteravirtual.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarCuentasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField campoNumeroCuenta;

    @FXML
    private TableView<?> tablaCuentas;

    @FXML
    private TextField campoSaldo;

    @FXML
    private TableColumn<?, ?> columnaNombreUsuario;

    @FXML
    private TextField campoNombreBanco;

    @FXML
    private TableColumn<?, ?> columnaIdUsuario;

    @FXML
    private TableColumn<?, ?> columnaSaldo;

    @FXML
    private TableColumn<?, ?> columnaBanco;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ComboBox<?> comboTipoCuenta;

    @FXML
    private TableColumn<?, ?> columnaTipoCuenta;

    @FXML
    private TextField campoIdUsuario;

    @FXML
    private TableColumn<?, ?> columnaNumeroCuenta;

    @FXML
    void onVolver() {

    }

    @FXML
    void onAgregar() {

    }

    @FXML
    void onEditar() {

    }

    @FXML
    void onBuscar() {

    }

    @FXML
    void onEliminar() {

    }

    @FXML
    void initialize() {
        assert campoNumeroCuenta != null : "fx:id=\"campoNumeroCuenta\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert tablaCuentas != null : "fx:id=\"tablaCuentas\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert campoSaldo != null : "fx:id=\"campoSaldo\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert columnaNombreUsuario != null : "fx:id=\"columnaNombreUsuario\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert campoNombreBanco != null : "fx:id=\"campoNombreBanco\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert columnaIdUsuario != null : "fx:id=\"columnaIdUsuario\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert columnaSaldo != null : "fx:id=\"columnaSaldo\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert columnaBanco != null : "fx:id=\"columnaBanco\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert campoBusqueda != null : "fx:id=\"campoBusqueda\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert comboTipoCuenta != null : "fx:id=\"comboTipoCuenta\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert columnaTipoCuenta != null : "fx:id=\"columnaTipoCuenta\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert campoIdUsuario != null : "fx:id=\"campoIdUsuario\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";
        assert columnaNumeroCuenta != null : "fx:id=\"columnaNumeroCuenta\" was not injected: check your FXML file 'GestionarCuentasBancariasView.fxml'.";

    }
}
