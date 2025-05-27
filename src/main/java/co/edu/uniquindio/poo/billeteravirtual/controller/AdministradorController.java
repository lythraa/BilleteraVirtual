package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador para la vista principal del Administrador.
 * Permite gestionar usuarios, transacciones, cuentas bancarias, estadísticas,
 * editar perfil, contactar soporte y cerrar sesión.
 */
public class AdministradorController {

    private Stage stage;
    @FXML
    private Label textoHolaUsuario;


    /**
     * Inicializa el controlador cargando el nombre del usuario actual en la etiqueta.
     */
    @FXML
    void initialize() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (perfilActual != null) {
            textoHolaUsuario.setText("[Admin] Hola de nuevo, " + perfilActual.getNombre());
        } else {
            textoHolaUsuario.setText("[Admin] Bienvenido");
        }
}

    /**
     * Navega a la vista de gestión de usuarios.
     */
    @FXML
    void onGestionarUsuarios() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionUsuariosView.fxml", "Vista Usuario");
    }

    /**
     * Navega a la vista de gestión de transacciones.
     */
    @FXML
    void onGestionarTransacciones() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "MovimientosGlobalesView.fxml", "Vista Gestionar Transacciones");
    }

    /**
     * Navega a la vista de gestión de cuentas bancarias.
     */
    @FXML
    void onGestionarCuentasBancarias() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionCuentasBancariasView.fxml", "Vista Gestionar Cuentas Bancarias");
    }

    /**
     * Navega a la vista de estadísticas.
     */
    @FXML
    void onVerEstadisticas() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "EstadisticasView.fxml", "Vista Estadísticas");
    }

    /**
     * Abre la vista para editar el perfil del administrador.
     */
    @FXML
    void onEditarPerfil() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.abrirVistaEditarPerfilDesde(stage, "admin");
    }

    /**
     * Cierra la sesión actual y redirige a la vista de inicio de sesión.
     */
    @FXML
    void onCerrarSesion() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "IniciarSesionView.fxml", "Vista Iniciar Sesión");
        GestorSesion.getInstance().cerrarSesion();
    }

    /**
     * Navega a la vista de mandar notificaciones
     */
    @FXML
    void onMandarNotificacion(){
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionNotificaciones.fxml", "Vista Mandar Notificaciones");

    }
}
