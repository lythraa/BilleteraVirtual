package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
     * Boton que genera un reporte de movimientos del usuario
     */
    @FXML
    void onGenerarReporte() {
        List<String> opciones = Arrays.asList("PDF", "Excel");
        ChoiceDialog<String> dialogo = new ChoiceDialog<>("PDF", opciones);
        dialogo.setTitle("Generar Reporte");
        dialogo.setHeaderText("Seleccione el formato del reporte:");
        dialogo.setContentText("Formato:");

        Optional<String> resultado = dialogo.showAndWait();

        resultado.ifPresent(formato -> {
            Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();
            try {
                if (formato.equals("PDF")) {
                    SistemaBilleteraFacade.getInstancia().generarReportePDF(usuarioActual);
                    mostrarAlerta("Reporte generado exitosamente en PDF.");
                } else {
                    SistemaBilleteraFacade.getInstancia().generarReporteExcel(usuarioActual);
                    mostrarAlerta("Reporte generado exitosamente en Excel.");
                }
            } catch (Exception e) {
                mostrarAlerta("Error al generar el reporte: " + e.getMessage());
            }
        });
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Resultado del Reporte");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Inicializa el controlador cargando datos iniciales en la vista.
     * Muestra un saludo personalizado con el nombre del usuario actual si está logueado.
     */
    @FXML
    void initialize() {
        Usuario usuario = (Usuario) GestorSesion.getInstance().getPerfilActual();
        if (usuario != null) {
            textoHolaUsuario.setText("Bienvenido, " + usuario.getNombre());

            textoSaldo.setText("Saldo: " + usuario.getSaldoTotal());
        } else {
            textoHolaUsuario.setText("Bienvenido");
            textoSaldo.setText("Usuario no disponible");
        }
        assert textoSaldo != null : "fx:id=\"textoSaldo\" was not injected: check your FXML file 'UsuarioView.fxml'.";
        assert textoHolaUsuario != null : "fx:id=\"textoHolaUsuario\" was not injected: check your FXML file 'UsuarioView.fxml'.";
    }

}
