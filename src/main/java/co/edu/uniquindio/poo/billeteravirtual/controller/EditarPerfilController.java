package co.edu.uniquindio.poo.billeteravirtual.controller;
//CLASE LISTA
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarPerfilController {

    private String vistaOrigen;

    public void setVistaOrigen(String vistaOrigen) {
        this.vistaOrigen = vistaOrigen;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField campoCorreo;

    @FXML
    private TextField campoTelefono;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    private TextField campoDireccion;

    @FXML
    private TextField campoNombre;

    @FXML
    private void onGuardarCambios() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        perfilActual.setNombre(campoNombre.getText());
        perfilActual.setContrasenia(campoContrasena.getText());
        perfilActual.setCorreo(campoCorreo.getText());
        perfilActual.setTelefono(campoTelefono.getText());
        perfilActual.setDireccion(campoDireccion.getText());
    }


    @FXML
    private void onVolver() {
        Stage stage = (Stage) campoTelefono.getScene().getWindow();
        if ("admin".equals(vistaOrigen)) {
            GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Admin");
        } else {
            GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
        }
    }

    @FXML
    void initialize() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        campoNombre.setText(perfilActual.getNombre());
        campoCorreo.setText(perfilActual.getCorreo());
        campoTelefono.setText(perfilActual.getTelefono());
        campoDireccion.setText(perfilActual.getDireccion());
        campoContrasena.setText(perfilActual.getContrasenia());
        assert campoCorreo != null : "fx:id=\"campoCorreo\" was not injected: check your FXML file 'EditarPerfilView.fxml'.";
        assert campoTelefono != null : "fx:id=\"campoTelefono\" was not injected: check your FXML file 'EditarPerfilView.fxml'.";
        assert campoContrasena != null : "fx:id=\"campoContrasena\" was not injected: check your FXML file 'EditarPerfilView.fxml'.";
        assert campoDireccion != null : "fx:id=\"campoDireccion\" was not injected: check your FXML file 'EditarPerfilView.fxml'.";
        assert campoNombre != null : "fx:id=\"campoNombre\" was not injected: check your FXML file 'EditarPerfilView.fxml'.";

    }
}
