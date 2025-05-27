package co.edu.uniquindio.poo.billeteravirtual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.observer.GestorNotificaciones;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    @FXML
    void onVolver() {
        Stage stage = (Stage) campoMensaje.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Panel Administrador");
    }

    @FXML
    void onEventoSeleccionado() {
        String seleccionado = comboEventos.getValue();
        boolean crearNuevo = OPCION_NUEVO_EVENTO.equals(seleccionado);

        campoEnCasoCrearEvento.setVisible(crearNuevo);
        campoEnCasoCrearEvento.setManaged(crearNuevo);

    }

    @FXML
    void onEnviar() {
        String eventoSeleccionado = comboEventos.getValue();
        String eventoFinal;

        if (OPCION_NUEVO_EVENTO.equals(eventoSeleccionado)) {
            eventoFinal = campoEnCasoCrearEvento.getText().trim();

            if (eventoFinal.isEmpty()) {
                UtilAlerta.mostrarAlertaAdvertencia("Campo Vacio","Debes escribir el nombre del nuevo evento.");
                return;
            }
            if (!comboEventos.getItems().contains(eventoFinal)) {
                comboEventos.getItems().add(comboEventos.getItems().size() - 1, eventoFinal);
            }

        } else {
            eventoFinal = eventoSeleccionado;
        }

        String mensaje = campoMensaje.getText().trim();
        if (eventoFinal == null || eventoFinal.isEmpty() || mensaje.isEmpty()) {
            UtilAlerta.mostrarAlertaAdvertencia("Campos Vacios","Debes seleccionar un evento y escribir un mensaje.");
            return;
        }

        Administrador administrador = (Administrador) GestorSesion.getInstance().getPerfilActual();
        administrador.enviarNotificacion(eventoFinal, mensaje);

        campoMensaje.clear();
        campoEnCasoCrearEvento.clear();
        campoEnCasoCrearEvento.setVisible(false);
        campoEnCasoCrearEvento.setManaged(false);
        comboEventos.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        comboEventos.getItems().addAll("usuarios", "reportes", OPCION_NUEVO_EVENTO);
        assert listaSolicitudes != null : "fx:id=\"listaSolicitudes\" was not injected: check your FXML file 'MandarNotificacion.fxml'.";
        assert campoMensaje != null : "fx:id=\"campoMensaje\" was not injected: check your FXML file 'MandarNotificacion.fxml'.";
        assert comboEventos != null : "fx:id=\"comboEventos\" was not injected: check your FXML file 'MandarNotificacion.fxml'.";
        assert campoEnCasoCrearEvento != null : "fx:id=\"campoEnCasoCrearEvento\" was not injected: check your FXML file 'MandarNotificacion.fxml'.";

    }
}
