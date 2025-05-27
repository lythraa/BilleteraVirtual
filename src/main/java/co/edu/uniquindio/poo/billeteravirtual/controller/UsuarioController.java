package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador principal para la vista del usuario.
 * Gestiona acciones como ver saldo, movimientos, editar perfil, entre otras.
 */
public class UsuarioController {

    private Stage stage;

    @FXML
    private Label textoSaldo;
    @FXML
    private Label textoHolaUsuario;

    /**
     * Abre la vista de contacto.
     */
    public void onContactenos() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "SoporteView.fxml", "Vista Contacto");
    }

    /**
     * Abre la vista para gestionar los movimientos del usuario.
     */
    @FXML
    void onMovimientos() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionMovimientosView.fxml", "Vista Movimientos");

    }

    /**
     * Acción para mostrar la vista de presupuestos y categorias.
     */
    @FXML
    void onPresupuestosYCategorias() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "GestionCategoriasPresupuestosView.fxml", "Vista Presupuestos y Categorias");
    }

    /**
     * Acción para mostrar la vista de cuentas.
     */
    @FXML
    void onCuentas() {
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "CuentasBancariasUsuarioView.fxml", "Vista Cuentas");
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
     * Abre la vista para recibir las notificaciones del usuario actual.
     */
    @FXML
    void onNotificaciones(){
        stage = (Stage) textoHolaUsuario.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "NotificacionesUsuarioView.fxml", "Vista Notificaciones");
    }

    /**
     * Inicializa el controlador cargando datos iniciales en la vista.
     * Muestra un saludo personalizado con el nombre del usuario actual si está logueado.
     */
    @FXML
    void initialize() {
        Usuario usuario = (Usuario) GestorSesion.getInstance().getPerfilActual();
        if (usuario != null) {
            textoHolaUsuario.setText("Hola de nuevo, " + usuario.getNombre());

            textoSaldo.setText("Saldo: " + usuario.getSaldoTotal());
        } else {
            textoHolaUsuario.setText("Bienvenido");
            textoSaldo.setText("Usuario no disponible");
        }
        assert textoSaldo != null : "fx:id=\"textoSaldo\" was not injected: check your FXML file 'UsuarioView.fxml'.";
        assert textoHolaUsuario != null : "fx:id=\"textoHolaUsuario\" was not injected: check your FXML file 'UsuarioView.fxml'.";
    }

}
