package co.edu.uniquindio.poo.billeteravirtual.controller;
import co.edu.uniquindio.poo.billeteravirtual.app.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.gestores.GestorUsuarios;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class GestionarUsuariosController {

    @FXML
    private TextField campoId;

    @FXML
    private TableColumn<Usuario, String> columnaDireccion;

    @FXML
    private TextField campoCorreo;

    @FXML
    private TextField campoSaldo;

    @FXML
    private TableColumn<Usuario, String> columnaCorreo;

    @FXML
    private TableColumn<Usuario, String> columnaTelefono;

    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private TextField campoTelefono;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    private TableColumn<Usuario, Double> columnaSaldo;

    @FXML
    private TableColumn<Usuario, String> columnaId;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private TextField campoDireccion;

    @FXML
    private TableColumn<Usuario, String> columnaNombre;

    @FXML
    private TextField campoNombre;

    @FXML
    private ImageView iconoVolver;

    //Se obtiene la instancia única del gestorUsuarios para obtener sus datos
    private final GestorUsuarios gestorUsuarios = GestorUsuarios.getInstancia();


    private ObservableList<Usuario> listaObservableUsuarios = FXCollections.observableArrayList();


    public Usuario buildUsuario(){
        return new Usuario(campoId.getText(),campoContrasena.getText(),campoNombre.getText(),
                campoCorreo.getText(),campoTelefono.getText(),campoDireccion.getText());
    }

    @FXML
    void onAgregar() {
        Usuario usuario = buildUsuario();
        gestorUsuarios.agregar(usuario);
        cargarUsuarios();
        limpiarCampos();
    }

    @FXML
    void onEditar() {
        Usuario seleccionado = getUsuarioSeleccionado();
        if (seleccionado != null) {
            seleccionado.setNombre(campoNombre.getText());
            seleccionado.setCorreo(campoCorreo.getText());
            seleccionado.setTelefono(campoTelefono.getText());
            seleccionado.setDireccion(campoDireccion.getText());
            seleccionado.setSaldoTotal(Double.parseDouble(campoSaldo.getText()));

            cargarUsuarios();
            limpiarCampos();
        }
    }

    @FXML
    void onBuscar() {
        String idBuscado = campoBusqueda.getText().trim();

        if (idBuscado.isEmpty()) {
            UtilAlerta.mostrarAlertaError("Búsqueda incorrecta.","Por favor, ingresa un ID válido para buscar.");
            return;
        }

        Usuario encontrado = gestorUsuarios.buscar(idBuscado);

        if (encontrado != null) {
            listaObservableUsuarios.setAll(encontrado); // solo muestra ese usuario
        } else {
            UtilAlerta.mostrarAlertaError("Sin coincidencias", "No se encontró ningún usuario con el ID ingresado.");
            listaObservableUsuarios.clear(); // limpia la tabla si no encuentra nada
        }
    }

    @FXML
    void onEliminar() {
        Usuario seleccionado = getUsuarioSeleccionado();
        if (seleccionado != null) {
            gestorUsuarios.eliminar(seleccionado);
            listaObservableUsuarios.remove(seleccionado);
            limpiarCampos();
            cargarUsuarios();
        }
        else{
            UtilAlerta.mostrarAlertaError("Selección vacía.","No se ha seleccionado ningún usuario para eliminar.");
        }
    }

    public void onVolver(ActionEvent actionEvent) {
        Stage stage = (Stage) campoId.getScene().getWindow();
        GestorVistas.CambiarEscena(stage,"AdministradorView.fxml","Vista Administrador");
    }

    @FXML
    void onLimpiar(ActionEvent event) {
        limpiarCampos();
        tablaUsuarios.getSelectionModel().clearSelection();
    }


    @FXML
    void initialize() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/left-arrow.png")));
        iconoVolver.setImage(image);
        // Inicializar las columnas de la tabla
        assert campoId != null : "fx:id=\"campoId\" was not injected: check your FXML file 'GestionarUsuariosView.fxml'.";
        assert columnaDireccion != null : "fx:id=\"columnaDireccion\" was not injected: check your FXML file 'GestionarUsuariosView.fxml'.";

        columnaId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnaCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        columnaTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columnaDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        columnaSaldo.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSaldoTotal()).asObject());

        //Permite escuchar el evento de selección en la tabla (Listener Selección)
        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                llenarCamposConUsuario(newSel);
            }
        });

        // Llenar la tabla con la lista de usuarios desde GestorUsuarios
        tablaUsuarios.setItems(listaObservableUsuarios);

        // Cargar los usuarios preexistentes desde el GestorUsuarios
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        listaObservableUsuarios.setAll(gestorUsuarios.filtrarUsuarios());
    }

    /**
     * Método para autocompletar los campos con información del usuario seleccionado
     * @param usuario el usuario seleccionado
     */
    private void llenarCamposConUsuario(Usuario usuario) {
        campoId.setText(usuario.getId());
        campoContrasena.setText(usuario.getContrasenia());
        campoNombre.setText(usuario.getNombre());
        campoCorreo.setText(usuario.getCorreo());
        campoTelefono.setText(usuario.getTelefono());
        campoDireccion.setText(usuario.getDireccion());
        campoSaldo.setText(String.valueOf(usuario.getSaldoTotal()));
    }

    private void limpiarCampos() {
        campoId.clear();
        campoContrasena.clear();
        campoNombre.clear();
        campoCorreo.clear();
        campoTelefono.clear();
        campoDireccion.clear();
        campoSaldo.clear();
    }

    private Usuario getUsuarioSeleccionado() {
        return tablaUsuarios.getSelectionModel().getSelectedItem();
    }

}
