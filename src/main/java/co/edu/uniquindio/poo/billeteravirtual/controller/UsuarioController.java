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

public class UsuarioController {
    private Stage stage;

    @FXML
    private Label textoSaldo;

    @FXML
    private Label textoHolaUsuario;

    @FXML
    void onTransacciones() {

    }

    @FXML
    void onPresupuestos() {

    }

    @FXML
    void onCuentas() {

    }

    @FXML
    void onEditarPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditarPerfilView.fxml"));
            Parent root = loader.load();

            EditarPerfilController controller = loader.getController();
            controller.setVistaOrigen("admin");

            Stage stage = (Stage) textoHolaUsuario.getScene().getWindow();
            stage.setTitle("Editar Perfil");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCerrarSesion() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "IniciarSesionView.fxml","Vista Iniciar Sesion");

    }

    @FXML
    void enlaceSoporte() {

    }

    @FXML
    void initialize() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (perfilActual != null) {
            textoHolaUsuario.setText("Bienvenido, " + perfilActual.getNombre());
        } else {
            textoHolaUsuario.setText("Bienvenido");
        }
        assert textoSaldo != null : "fx:id=\"textoSaldo\" was not injected: check your FXML file 'UsuarioView.fxml'.";
        assert textoHolaUsuario != null : "fx:id=\"textoHolaUsuario\" was not injected: check your FXML file 'UsuarioView.fxml'.";

    }
}
