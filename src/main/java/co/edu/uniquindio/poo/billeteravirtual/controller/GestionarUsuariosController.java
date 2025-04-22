package co.edu.uniquindio.poo.billeteravirtual.controller;
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

    private ObservableList<Usuario> listaObservableUsuarios = FXCollections.observableArrayList();


    public Usuario buildUsuario(){
        return new Usuario(campoId.getText(),campoContrasena.getText(),campoNombre.getText(),
                campoCorreo.getText(),campoTelefono.getText(),campoDireccion.getText());
    }

    @FXML
    void onAgregar() {
        Usuario usuario = buildUsuario();
        GestorUsuarios.getInstancia().agregar(usuario);
        listaObservableUsuarios.add(usuario);
        limpiarCampos();
    }

    @FXML
    void onEditar() {

    }

    @FXML
    void onBuscar() {

    }

    @FXML
    void onEliminar() {

    }

    public void onVolver(ActionEvent actionEvent) {
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

        // Llenar la tabla con la lista de usuarios desde GestorUsuarios
        tablaUsuarios.setItems(listaObservableUsuarios);

        // Cargar los usuarios preexistentes desde el GestorUsuarios
        cargarUsuarios();
    }

    private void cargarUsuarios() {

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

}
