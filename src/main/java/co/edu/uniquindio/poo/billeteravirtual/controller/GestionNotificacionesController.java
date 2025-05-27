package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorNotificaciones;
import co.edu.uniquindio.poo.billeteravirtual.model.Notificacion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class GestionNotificacionesController {

    @FXML
    private ListView<Notificacion> listaMensajesSoporte;

    @FXML
    private TextArea campoMensaje;

    @FXML
    private ComboBox<String> comboEventos;

    @FXML
    private TextField campoEnCasoCrearEvento;

    private final String OPCION_NUEVO_EVENTO = "Crear nuevo evento...";

    @FXML
    void onVolver() {
        Stage stage = (Stage) campoMensaje.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Administrador");
    }

    @FXML
    void onEventoSeleccionado() {
        boolean crearNuevo = OPCION_NUEVO_EVENTO.equals(comboEventos.getValue());
        campoEnCasoCrearEvento.setVisible(crearNuevo);
        campoEnCasoCrearEvento.setManaged(crearNuevo);
    }

    @FXML
    void onEnviar() {

        String eventoSeleccionado = comboEventos.getValue();
        if (eventoSeleccionado == null) {
            UtilAlerta.mostrarAlertaAdvertencia("Evento no seleccionado", "Debes seleccionar o crear un evento.");
            return;
        }

        String eventoFinal;

        if (OPCION_NUEVO_EVENTO.equals(eventoSeleccionado)) {
            eventoFinal = campoEnCasoCrearEvento.getText().trim();

            if (UtilAlerta.esInvalido(eventoFinal, "Nombre del nuevo evento")) return;

            if ("soporte".equalsIgnoreCase(eventoFinal)) {
                UtilAlerta.mostrarAlertaAdvertencia("Evento no permitido", "No puedes enviar notificaciones al evento de soporte.");
                return;
            }

            GestorNotificaciones.getInstancia().registrarEvento(eventoFinal);

            if (!comboEventos.getItems().contains(eventoFinal)) {
                comboEventos.getItems().add(comboEventos.getItems().size() - 1, eventoFinal);
            }

        } else {
            eventoFinal = eventoSeleccionado;
        }

        String mensaje = campoMensaje.getText().trim();
        if (UtilAlerta.esInvalido(eventoFinal, "Evento") || UtilAlerta.esInvalido(mensaje, "Mensaje")) {
            return;
        }

        Administrador administrador = (Administrador) GestorSesion.getInstance().getPerfilActual();
        administrador.enviarNotificacion(eventoFinal, mensaje);
        UtilAlerta.mostrarAlertaInformacion("Notificación enviada", "La notificación fue enviada correctamente.");

        campoMensaje.clear();
        campoEnCasoCrearEvento.clear();
        campoEnCasoCrearEvento.setVisible(false);
        campoEnCasoCrearEvento.setManaged(false);
        comboEventos.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        Set<String> eventos = GestorNotificaciones.getInstancia().obtenerEventos();
        for (String evento : eventos) {
            if (!evento.equalsIgnoreCase("soporte")) {
                comboEventos.getItems().add(evento);
            }
        }
        comboEventos.getItems().add(OPCION_NUEVO_EVENTO);

        List<Notificacion> notificacionesSoporte = GestorNotificaciones.getInstancia().getNotificacionesSoporte();
        listaMensajesSoporte.getItems().addAll(notificacionesSoporte);

        listaMensajesSoporte.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Notificacion noti, boolean empty) {
                super.updateItem(noti, empty);
                if (empty || noti == null) {
                    setText(null);
                } else {
                    String fecha = noti.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    String remitente = noti.getAdminNombre();
                    String mensaje = noti.getMensaje();

                    setStyle("-fx-padding: 10px; -fx-font-size: 13px;");
                    setText(String.format("[%s] %s\nMensaje: %s", fecha, remitente, mensaje));
                }
            }
        });
    }
}
