package co.edu.uniquindio.poo.billeteravirtual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IniciarSesionController {
//nos referimos aqui a Usuario como Perfil
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField campoId;

    @FXML
    private PasswordField campoContrasena;
    @FXML
    void onIniciarSesion() {
        String usuario = campoId.getText();
        String contrasena = campoContrasena.getText();

        boolean sesionIniciada = GestorSesion.getInstance().iniciarSesion(usuario, contrasena);

        if (sesionIniciada) {

            Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
            //se obtiene el escenario
            Stage stage = (Stage) campoId.getScene().getWindow();

            if (perfilActual instanceof Usuario) {
                GestorVistas.CambiarEscena(stage, "UsuarioView.fxml","Vista Usuario");

            } else if (perfilActual instanceof Administrador) {
                GestorVistas.CambiarEscena(stage, "AdministradorView.fxml","Vista Administrador");
            }

        } else {
            UtilAlerta.mostrarAlertaError("Error de inicio de sesion","Usuario o contraseña incorrectos. Intente nuevamente");
        }

    }

    @FXML
    void enlaceRegistro() {

    }

    @FXML
    void initialize() {
        assert campoId != null : "fx:id=\"campoId\" was not injected: check your FXML file 'IniciarSesionView.fxml'.";
        assert campoContrasena != null : "fx:id=\"campoContrasena\" was not injected: check your FXML file 'IniciarSesionView.fxml'.";

    }
}
