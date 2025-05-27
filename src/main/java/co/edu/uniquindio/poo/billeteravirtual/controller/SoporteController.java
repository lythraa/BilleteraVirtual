package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la vista de contacto.
 * Permite al usuario o administrador enviar solicitudes de contacto
 * y volver a la vista correspondiente según su origen.
 */
public class SoporteController {

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

        if (UtilAlerta.esInvalido(correo, "Correo")) return;
        if (UtilAlerta.esInvalido(asunto, "Asunto")) return;
        if (UtilAlerta.esInvalido(mensaje, "Mensaje")) return;


        Usuario usuario = (Usuario) GestorSesion.getInstance().getPerfilActual();
        String cuerpo = correo + "\n" + asunto + "\n" + mensaje;
        Notificacion notificacion = new Notificacion("soporte", cuerpo, usuario.getNombre());
        GestorNotificaciones.getInstancia().notificar("soporte", notificacion);
        UtilAlerta.mostrarAlertaInformacion("Solicitud enviada", "¡Gracias! Tu solicitud ha sido enviada.");

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
        GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
    }


    @FXML
    public void initialize() {
        Usuario usuario = (Usuario) GestorSesion.getInstance().getPerfilActual();
        campoCorreo.setText(usuario.getCorreo());
    }
}
