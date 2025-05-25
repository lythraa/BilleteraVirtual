package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la vista de contacto.
 * Permite al usuario o administrador enviar solicitudes de contacto
 * y volver a la vista correspondiente según su origen.
 */
public class ContactoController {

    private String vistaOrigen;


    /**
     * Establece la vista desde la cual se abrió esta ventana (admin o usuario).
     * @param vistaOrigen "admin" o "usuario"
     */
    public void setVistaOrigen(String vistaOrigen) {
        this.vistaOrigen = vistaOrigen;
    }

    @FXML
    private TextField campoCorreo;
    @FXML
    private TextField campoAsunto;
    @FXML
    private TextArea campoMensaje;

    /**
     * Maneja el evento de enviar la solicitud de contacto.
     * Válida los campos y muestra una alerta según el resultado.
     */
    @FXML
    private void onEnviarSolicitud() {
        String correo = campoCorreo.getText();
        String asunto = campoAsunto.getText();
        String mensaje = campoMensaje.getText();

        if (correo.isBlank() || asunto.isBlank() || mensaje.isBlank()) {
            UtilAlerta.mostrarAlertaAdvertencia("Campos incompletos", "Por favor completa todos los campos.");
            return;
        }

        UtilAlerta.mostrarAlertaInformacion("Solicitud enviada", "¡Gracias! Tu solicitud ha sido enviada.");

        // Limpiar campos después del envío
        campoCorreo.clear();
        campoAsunto.clear();
        campoMensaje.clear();
    }

    /**
     * Regresa a la vista correspondiente según el origen: administrador o usuario.
     */
    @FXML
    private void onVolver() {
        Stage stage = (Stage) campoCorreo.getScene().getWindow();
        if ("admin".equals(vistaOrigen)) {
            GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Admin");
        } else {
            GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
        }
    }
}
