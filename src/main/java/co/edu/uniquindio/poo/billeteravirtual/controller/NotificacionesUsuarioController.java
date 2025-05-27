package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorNotificaciones;
import co.edu.uniquindio.poo.billeteravirtual.model.Notificacion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador de la vista de notificaciones para el usuario.
 * Permite suscribirse a eventos y visualizar mensajes recibidos.
 */
public class NotificacionesUsuarioController {

    @FXML
    private VBox contenedorEventos;
    @FXML
    private TableView<Notificacion> tablaNotificaciones;
    @FXML
    private TableColumn<Notificacion, String> colMensaje;
    @FXML
    private TableColumn<Notificacion, String> colAdministrador;
    @FXML
    private TableColumn<Notificacion, LocalDateTime> colfecha;
    @FXML
    private TableColumn<Notificacion, String> colEvento;
    @FXML
    private Button btnGuardar;

    private final Map<String, CheckBox> mapaCheckEventos = new HashMap<>();

    private Usuario usuario;

    /**
     * Inicializa la vista cargando eventos disponibles y notificaciones previas.
     * También suscribe al usuario a eventos seleccionados.
     */
    @FXML
    public void initialize() {
        usuario = (Usuario) GestorSesion.getInstance().getPerfilActual();
        ObservableList<Notificacion> listaNotificacionesObservable = FXCollections.observableArrayList();

        colEvento.setCellValueFactory(new PropertyValueFactory<>("evento"));
        colAdministrador.setCellValueFactory(new PropertyValueFactory<>("adminNombre"));
        colMensaje.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
        colfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        colfecha.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.format(formatter));
            }
        });

        tablaNotificaciones.setItems(listaNotificacionesObservable);
        listaNotificacionesObservable.addAll(usuario.getHistorialNotificaciones());

        Set<String> eventos = GestorNotificaciones.getInstancia().obtenerEventos();
        if (eventos.isEmpty()) return;

        Set<String> eventosUsuario = GestorNotificaciones.getInstancia().obtenerEventosPorUsuario(usuario);

        for (String evento : eventos) {
            if (evento.equalsIgnoreCase("soporte")) continue;

            CheckBox checkBox = new CheckBox("Evento " + evento);
            checkBox.setStyle("-fx-font-size: 14px;");
            checkBox.getStyleClass().add("label-subtitle");

            if (eventosUsuario.contains(evento)) {
                checkBox.setSelected(true);
            }

            contenedorEventos.getChildren().add(checkBox);
            mapaCheckEventos.put(evento, checkBox);
        }
        if (mapaCheckEventos.isEmpty()) {
            btnGuardar.setDisable(true);
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
        UtilAlerta.mostrarAlertaInformacion("Suscripciones guardadas", "Tus preferencias han sido actualizadas correctamente.");
    }

    /**
     * Regresa a la vista principal del usuario.
     */
    @FXML
    public void onVolver() {
        Stage stage = (Stage) contenedorEventos.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Vista Usuario");
    }

}
