package co.edu.uniquindio.poo.billeteravirtual.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IniciarSesionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    void onIniciarSesion() {

    }

    @FXML
    void enlaceRegistro() {

    }

    @FXML
    void initialize() {
        assert campoUsuario != null : "fx:id=\"campoUsuario\" was not injected: check your FXML file 'IniciarSesionView.fxml'.";
        assert campoContrasena != null : "fx:id=\"campoContrasena\" was not injected: check your FXML file 'IniciarSesionView.fxml'.";

    }
}
