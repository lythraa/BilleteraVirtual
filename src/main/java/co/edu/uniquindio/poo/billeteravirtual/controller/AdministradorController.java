package co.edu.uniquindio.poo.billeteravirtual.controller;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdministradorController {
    private Stage stage;


    @FXML
    private Label textoHolaUsuario;


    @FXML
    void onGestionarTransacciones() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionarTransaccionesView.fxml","Vista Gestionar Transacciones");
    }

    @FXML
    void onGestionarUsuarios() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionarUsuariosView.fxml","Vista Usuario");
    }

    @FXML
    void onGestionarCuentasBancarias() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionarCuentasBancariasView.fxml","Vista Gestionar Cuentas Bancarias");
    }

    @FXML
    void onVerEstadisticas() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "EstadisticasView.fxml","Vista Estadísticas");

    }

    @FXML
    void onEditarPerfil() {
            Stage stage = (Stage) textoHolaUsuario.getScene().getWindow();
            GestorVistas.abrirVistaEditarPerfilDesde(stage, "admin");
    }


    @FXML
    void onCerrarSesion() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "IniciarSesionView.fxml","Vista Iniciar Sesion");

    }

    @FXML
    void onContactenos() {

    }

    @FXML
    void initialize() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (perfilActual != null) {
            textoHolaUsuario.setText("[Admin] Bienvenido, " + perfilActual.getNombre());
        } else {
            textoHolaUsuario.setText("[Admin] Bienvenido");
        }
        assert textoHolaUsuario != null : "fx:id=\"textoHolaUsuario\" was not injected: check your FXML file 'AdministradorView.fxml'.";
    }
}
