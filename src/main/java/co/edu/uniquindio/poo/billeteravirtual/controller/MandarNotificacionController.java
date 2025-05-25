package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorNotificaciones;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Controlador para la vista de envío de notificaciones por parte del administrador.
 * Permite seleccionar un evento o crear uno nuevo, y enviar un mensaje relacionado.
 */
public class MandarNotificacionController {

    @FXML
    private ListView<String> listaSolicitudes;

    @FXML
    private TextArea campoMensaje;

    @FXML
    private ComboBox<String> comboEventos;

    @FXML
    private TextField campoEnCasoCrearEvento;

    private final String OPCION_NUEVO_EVENTO = "Crear nuevo evento...";


    /**
     * Regresa a la vista principal del administrador.
     */
    @FXML
    void onVolver() {
        Stage stage = (Stage) campoMensaje.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Administrador");
    }

    /**
     * Maneja la lógica de mostrar el campo de texto si se elige la opción de crear un nuevo evento.
     */
    @FXML
    void onEventoSeleccionado() {
        String seleccionado = comboEventos.getValue();
        boolean crearNuevo = OPCION_NUEVO_EVENTO.equals(seleccionado);

        campoEnCasoCrearEvento.setVisible(crearNuevo);
        campoEnCasoCrearEvento.setManaged(crearNuevo);

    }

    /**
     * Envía una notificación con un mensaje relacionado a un evento seleccionado o creado.
     * Verifica los campos y actualiza la lista de eventos si se crea uno nuevo.
     */
    @FXML
    void onEnviar() {
        String eventoSeleccionado = comboEventos.getValue();
        String eventoFinal;

        if (OPCION_NUEVO_EVENTO.equals(eventoSeleccionado)) {
            eventoFinal = campoEnCasoCrearEvento.getText().trim();

            if (eventoFinal.isEmpty()) {
                UtilAlerta.mostrarAlertaAdvertencia("Campo Vacío", "Debes escribir el nombre del nuevo evento.");
                return;
            }

            GestorNotificaciones.getInstancia().registrarEvento(eventoFinal);

            // Agrega al ComboBox si no está ya
            if (!comboEventos.getItems().contains(eventoFinal)) {
                comboEventos.getItems().add(comboEventos.getItems().size() - 1, eventoFinal);
            }

        } else {
            eventoFinal = eventoSeleccionado;
        }

        String mensaje = campoMensaje.getText().trim();
        if (eventoFinal == null || eventoFinal.isEmpty() || mensaje.isEmpty()) {
            UtilAlerta.mostrarAlertaAdvertencia("Campos Vacíos", "Debes seleccionar un evento y escribir un mensaje.");
            return;
        }

        Administrador administrador = (Administrador) GestorSesion.getInstance().getPerfilActual();
        administrador.enviarNotificacion(eventoFinal, mensaje);

        // Limpieza de campos
        campoMensaje.clear();
        campoEnCasoCrearEvento.clear();
        campoEnCasoCrearEvento.setVisible(false);
        campoEnCasoCrearEvento.setManaged(false);
        comboEventos.getSelectionModel().clearSelection();
    }

    /**
     * Inicializa los componentes de la vista, incluyendo la carga de eventos al ComboBox.
     */
    @FXML
    void initialize() {
        comboEventos.getItems().addAll(GestorNotificaciones.getInstancia().obtenerEventos());
        comboEventos.getItems().add(OPCION_NUEVO_EVENTO);

        assert listaSolicitudes != null : "fx:id=\"listaSolicitudes\" was not injected: check your FXML file 'MandarNotificacion.fxml'.";
        assert campoMensaje != null : "fx:id=\"campoMensaje\" was not injected: check your FXML file 'MandarNotificacion.fxml'.";
        assert comboEventos != null : "fx:id=\"comboEventos\" was not injected: check your FXML file 'MandarNotificacion.fxml'.";
        assert campoEnCasoCrearEvento != null : "fx:id=\"campoEnCasoCrearEvento\" was not injected: check your FXML file 'MandarNotificacion.fxml'.";

    }
}
