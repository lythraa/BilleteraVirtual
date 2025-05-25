package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Controlador de la vista de inicio de sesión.
 * Permite a usuarios y administradores iniciar sesión en la aplicación.
 */
public class IniciarSesionController {


    @FXML
    private TextField campoId;

    @FXML
    private PasswordField campoContrasena;

    /**
     * Maneja el intento de inicio de sesión.
     * Verifica credenciales y redirige según el tipo de perfil.
     */
    @FXML
    void onIniciarSesion() {
        if (camposInvalidos()) return;

        String usuario = campoId.getText().trim();
        String contrasena = campoContrasena.getText().trim();

        boolean sesionIniciada = GestorSesion.getInstance().iniciarSesion(usuario, contrasena);

        if (sesionIniciada) {
            Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
            Stage stage = (Stage) campoId.getScene().getWindow();

            if (perfilActual instanceof Usuario) {
                GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Vista Usuario");
            } else if (perfilActual instanceof Administrador) {
                GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Vista Administrador");
            }

        } else {
            UtilAlerta.mostrarAlertaError(
                    "Error de inicio de sesión",
                    "Usuario o contraseña incorrectos. Intente nuevamente."
            );
        }
    }

    /**
     * Abre un enlace web al formulario de registro (o sitio externo).
     */
    @FXML
    void enlaceRegistro() {
        try {
            String url = "https://www.google.com/";
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception e) {
            UtilAlerta.mostrarAlertaError("Error al abrir enlace", "No se pudo abrir el navegador.");
            e.printStackTrace();
        }
    }

    /**
     * Valida que los campos de ID y contraseña no estén vacíos.
     * @return true si hay campos vacíos, false en caso contrario.
     */
    private boolean camposInvalidos() {
        if (campoId.getText().isBlank() || campoContrasena.getText().isBlank()) {
            UtilAlerta.mostrarAlertaError("Campos vacíos", "Por favor, ingresa tu ID y contraseña.");
            return true;
        }
        return false;
    }

    /**
     * Inicializa la vista asegurando que los campos estén correctamente inyectados.
     */
    @FXML
    void initialize() {
        assert campoId != null : "fx:id=\"campoId\" no fue inyectado. Verifica el FXML 'IniciarSesionView.fxml'.";
        assert campoContrasena != null : "fx:id=\"campoContrasena\" no fue inyectado. Verifica el FXML 'IniciarSesionView.fxml'.";
    }
}
