package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UsuarioController {

    private Stage stage;

    @FXML
    private Label textoSaldo;
    @FXML
    private Label textoHolaUsuario;

    /**
     * Acción para mostrar la vista de transacciones.
     * Actualmente no implementado.
     */
    @FXML
    void onTransacciones() {

    }

    /**
     * Acción para mostrar la vista de presupuestos.
     * Actualmente no implementado.
     */
    @FXML
    void onPresupuestos() {

    }

    /**
     * Acción para mostrar la vista de cuentas.
     * Actualmente no implementado.
     */
    @FXML
    void onCuentas() {

    }

    /**
     * Abre la vista para editar el perfil del usuario actual.
     */
    @FXML
    void onEditarPerfil() {
        Stage stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.abrirVistaEditarPerfilDesde(stage, "user");
    }

    /**
     * Cierra la sesión actual y vuelve a la vista de inicio de sesión.
     */
    @FXML
    void onCerrarSesion() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "IniciarSesionView.fxml", "Vista Iniciar Sesion");
        GestorSesion.getInstance().cerrarSesion();
    }

    /**
     * Abre la vista de contacto para que el usuario pueda enviar mensajes al administrador.
     */
    @FXML
    void onContactenos() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.abrirVistaContactoController(stage, "admin");
    }

    /**
     * Abre la vista para recibir las notificaciones del usuario actual.
     */
    @FXML
    void onNotificaciones(){
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "Notificaciones.fxml", "Vista Notificaciones");
    }

    /**
     * Inicializa el controlador cargando datos iniciales en la vista.
     * Muestra un saludo personalizado con el nombre del usuario actual si está logueado.
     */
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
