package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorNotificaciones;
import co.edu.uniquindio.poo.billeteravirtual.model.Notificacion;
import co.edu.uniquindio.poo.billeteravirtual.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador de la vista de notificaciones para el usuario.
 * Permite suscribirse a eventos y visualizar mensajes recibidos.
 */
public class NotificacionesController implements Observer {

    @FXML
    private VBox contenedorEventos;

    @FXML
    private TableView<Notificacion> tablaNotificaciones;

    @FXML
    private TableColumn<Notificacion, String> colMensaje;

    private final Map<String, CheckBox> mapaCheckEventos = new HashMap<>();

    private Usuario usuario;

    private ObservableList<Notificacion> listaNotificacionesObservable;

    /**
     * Inicializa la vista cargando eventos disponibles y notificaciones previas.
     * También suscribe al usuario a eventos seleccionados.
     */
    @FXML
    public void initialize() {
        usuario = (Usuario) GestorSesion.getInstance().getPerfilActual();

        listaNotificacionesObservable = FXCollections.observableArrayList();

        // Configura la columna para que muestre el mensaje
        colMensaje.setCellValueFactory(new PropertyValueFactory<>("mensaje"));

        // Enlaza la tabla con la lista observable
        tablaNotificaciones.setItems(listaNotificacionesObservable);

        // Carga notificaciones previas si quieres mostrar historial guardado
        listaNotificacionesObservable.addAll(usuario.getHistorialNotificaciones());

        Set<String> eventos = GestorNotificaciones.getInstancia().obtenerEventos();
        System.out.println("EVENTOS DISPONIBLES: " + eventos);

        if (eventos.isEmpty()) {
            return;
        }

        Set<String> eventosUsuario = GestorNotificaciones.getInstancia().obtenerEventosPorUsuario(usuario);

        for (String evento : eventos) {
            CheckBox checkBox = new CheckBox("Evento " + evento);
            checkBox.setStyle("-fx-font-size: 14px;");

            if (eventosUsuario.contains(evento)) {
                checkBox.setSelected(true);
            }

            contenedorEventos.getChildren().add(checkBox);
            mapaCheckEventos.put(evento, checkBox);
        }

        // Registrar este controlador como observer para recibir notificaciones nuevas
        for (String evento : eventos) {
            GestorNotificaciones.getInstancia().registrar(evento, this);
        }
    }

    /**
     * Guarda las suscripciones del usuario a los eventos seleccionados.
     * Registra o elimina la suscripción según el estado del CheckBox.
     */
    @FXML
    public void onGuardarSuscripciones() {
        for (String evento : mapaCheckEventos.keySet()) {
            CheckBox checkBox = mapaCheckEventos.get(evento);

            if (checkBox.isSelected()) {
                GestorNotificaciones.getInstancia().registrar(evento, usuario);
            } else {
                GestorNotificaciones.getInstancia().eliminar(evento, usuario);
            }
        }
    }

    /**
     * Regresa a la vista principal del usuario.
     */
    @FXML
    public void onVolver() {
        Stage stage = (Stage) contenedorEventos.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Vista Usuario");
    }

    /**
     * Recibe una notificación nueva como parte del patrón Observer.
     * La agrega al historial del usuario y a la tabla visual.
     *
     * @param notificacion la notificación recibida.
     */
    @Override
    public void recibirNotificacion(Notificacion notificacion) {
        usuario.getHistorialNotificaciones().add(notificacion);

        listaNotificacionesObservable.add(notificacion);
    }
}
