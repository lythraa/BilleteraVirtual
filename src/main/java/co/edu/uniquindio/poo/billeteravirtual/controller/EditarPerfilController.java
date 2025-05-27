package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la vista de edición de perfil.
 * Permite modificar los datos del perfil actual del usuario o administrador.
 */
public class EditarPerfilController {

    private String vistaOrigen;

    /**
     * Establece la vista de origen (admin o usuario) para regresar correctamente.
     * @param vistaOrigen "admin" o cualquier otro valor para usuario
     */
    public void setVistaOrigen(String vistaOrigen) {
        this.vistaOrigen = vistaOrigen;
    }

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

    /**
     * Guarda los cambios realizados en el perfil actual del usuario.
     * Se asume que el perfil ya está autenticado y cargado en sesión.
     */
    @FXML
    private void onGuardarCambios() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();

        if (perfilActual == null) {
            UtilAlerta.mostrarAlertaNoEncontrado("Perfil actual");
            return;
        }

        if (UtilAlerta.esInvalido(campoNombre.getText(), "Nombre")) return;
        if (UtilAlerta.esInvalido(campoContrasena.getText(), "Contraseña")) return;
        if (UtilAlerta.esInvalido(campoCorreo.getText(), "Correo")) return;
        if (UtilAlerta.esInvalido(campoTelefono.getText(), "Teléfono")) return;
        if (UtilAlerta.esInvalido(campoDireccion.getText(), "Dirección")) return;

        perfilActual.setNombre(campoNombre.getText());
        perfilActual.setContrasenia(campoContrasena.getText());
        perfilActual.setCorreo(campoCorreo.getText());
        perfilActual.setTelefono(campoTelefono.getText());
        perfilActual.setDireccion(campoDireccion.getText());

        UtilAlerta.mostrarAlertaInformacion("Perfil actualizado", "Los cambios se guardaron correctamente.");
    }


    /**
     * Regresa a la vista de origen desde la edición del perfil.
     */
    @FXML
    private void onVolver() {
        Stage stage = (Stage) campoTelefono.getScene().getWindow();
        if ("admin".equals(vistaOrigen)) {
            GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Admin");
        } else {
            GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
        }
    }

    /**
     * Inicializa la vista con los datos actuales del perfil autenticado.
     * Verifica que todos los campos estén correctamente enlazados.
     */
    @FXML
    void initialize() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();

        if (perfilActual != null) {
            campoNombre.setText(perfilActual.getNombre());
            campoCorreo.setText(perfilActual.getCorreo());
            campoTelefono.setText(perfilActual.getTelefono());
            campoDireccion.setText(perfilActual.getDireccion());
            campoContrasena.setText(perfilActual.getContrasenia());
        }
    }
}
