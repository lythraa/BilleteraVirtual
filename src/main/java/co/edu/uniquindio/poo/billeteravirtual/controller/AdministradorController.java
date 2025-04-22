package co.edu.uniquindio.poo.billeteravirtual.controller;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorVistas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdministradorController {

    @FXML
    private Label textoHolaUsuario;

    private Stage stage;

    @FXML
    void onGestionarTransacciones() {

    }

    @FXML
    void onGestionarUsuarios() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionarUsuariosView.fxml","Vista Usuario");
    }

    @FXML
    void onGestionarCuentasBancarias() {

    }

    @FXML
    void onVerEstadisticas() {

    }

    @FXML
    void onEditarPerfil() {

    }

    @FXML
    void onCerrarSesion() {

    }

    @FXML
    void onContactenos() {

    }

    @FXML
    void initialize() {
        assert textoHolaUsuario != null : "fx:id=\"textoHolaUsuario\" was not injected: check your FXML file 'AdministradorView.fxml'.";

    }
}
