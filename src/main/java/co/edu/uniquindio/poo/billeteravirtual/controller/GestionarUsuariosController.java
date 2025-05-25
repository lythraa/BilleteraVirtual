package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.gestores.GestorUsuarios;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionarUsuariosController {

    @FXML private TextField campoId;
    @FXML private TextField campoCorreo;
    @FXML private TextField campoTelefono;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoDireccion;
    @FXML private TextField campoNombre;
    @FXML private TextField campoIdBusqueda;

    @FXML private TableColumn<Usuario, String> columnaId;
    @FXML private TableColumn<Usuario, String> columnaNombre;
    @FXML private TableColumn<Usuario, String> columnaCorreo;
    @FXML private TableColumn<Usuario, String> columnaTelefono;
    @FXML private TableColumn<Usuario, String> columnaDireccion;
    @FXML private TableColumn<Usuario, Double> columnaSaldo;
    @FXML private TableView<Usuario> tablaUsuarios;

    private final ObservableList<Usuario> listaObservableUsuarios = FXCollections.observableArrayList();

    private boolean camposInvalidos() {
        if (campoId.getText().isEmpty() || campoContrasena.getText().isEmpty() ||
                campoNombre.getText().isEmpty() || campoCorreo.getText().isEmpty()) {
            UtilAlerta.mostrarAlertaError("Campos incompletos", "Por favor, completa todos los campos requeridos.");
            return true;
        }
        return false;
    }


    private Usuario buildUsuario() {
        return new Usuario(
                campoId.getText(),
                campoContrasena.getText(),
                campoNombre.getText(),
                campoCorreo.getText(),
                campoTelefono.getText(),
                campoDireccion.getText()
        );
    }

    private void actualizarUsuarioDesdeCampos(Usuario usuario) {
        usuario.setId(campoId.getText());
        usuario.setContrasenia(campoContrasena.getText());
        usuario.setNombre(campoNombre.getText());
        usuario.setCorreo(campoCorreo.getText());
        usuario.setTelefono(campoTelefono.getText());
        usuario.setDireccion(campoDireccion.getText());
    }

    @FXML
    void onAgregar() {
        if (camposInvalidos()) return;

        Usuario nuevoUsuario = buildUsuario();

        if (GestorUsuarios.getInstancia().buscar(nuevoUsuario.getId()) != null) {
            UtilAlerta.mostrarAlertaError("Usuario existente", "Ya existe un usuario con este ID.");
            return;
        }

        GestorUsuarios.getInstancia().agregar(nuevoUsuario);
        cargarUsuarios();
        limpiarCampos();
    }

    @FXML
    void onEditar() {
        Usuario seleccionado = getUsuarioSeleccionado();
        if (seleccionado != null) {
            if (camposInvalidos()) return;
            actualizarUsuarioDesdeCampos(seleccionado);
            cargarUsuarios();
            limpiarCampos();
        }
    }

    @FXML
    void onBuscar() {
        String idBuscado = campoIdBusqueda.getText().trim();

        if (idBuscado.isEmpty()) {
            UtilAlerta.mostrarAlertaError("Búsqueda incorrecta", "Por favor, ingresa un ID válido para buscar.");
            return;
        }

        Usuario encontrado = GestorUsuarios.getInstancia().buscar(idBuscado);

        if (encontrado != null) {
            listaObservableUsuarios.setAll(encontrado);
        } else {
            UtilAlerta.mostrarAlertaError("Sin coincidencias", "No se encontró ningún usuario con el ID ingresado.");
            listaObservableUsuarios.clear();
        }
    }

    @FXML
    void onEliminar() {
        Usuario seleccionado = getUsuarioSeleccionado();
        if (seleccionado != null) {
            boolean confirmado = UtilAlerta.mostrarAlertaConfirmacion("¿Estás seguro?", "Esta acción eliminará al usuario permanentemente.");
            if (!confirmado) return;

            GestorUsuarios.getInstancia().eliminar(seleccionado);
            listaObservableUsuarios.remove(seleccionado);
            limpiarCampos();
            cargarUsuarios();
        } else {
            UtilAlerta.mostrarAlertaError("Selección vacía", "No se ha seleccionado ningún usuario para eliminar.");
        }
    }

    public void onVolver() {
        Stage stage = (Stage) campoId.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Vista Administrador");
    }

    @FXML
    void onRefrescarTabla() {
        limpiarCampos();
        tablaUsuarios.getSelectionModel().clearSelection();
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        listaObservableUsuarios.setAll(GestorUsuarios.getInstancia().getListaObjetos());
    }

    private void llenarCamposConUsuario(Usuario usuario) {
        campoId.setText(usuario.getId());
        campoContrasena.setText(usuario.getContrasenia());
        campoNombre.setText(usuario.getNombre());
        campoCorreo.setText(usuario.getCorreo());
        campoTelefono.setText(usuario.getTelefono());
        campoDireccion.setText(usuario.getDireccion());
    }

    private void limpiarCampos() {
        campoId.clear();
        campoContrasena.clear();
        campoNombre.clear();
        campoCorreo.clear();
        campoTelefono.clear();
        campoDireccion.clear();
    }

    private Usuario getUsuarioSeleccionado() {
        return tablaUsuarios.getSelectionModel().getSelectedItem();
    }

    @FXML
    void initialize() {
        columnaId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnaCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        columnaTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columnaDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        columnaSaldo.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSaldoTotal()).asObject());

        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                llenarCamposConUsuario(newSel);
            }
        });

        tablaUsuarios.setItems(listaObservableUsuarios);
        cargarUsuarios();
    }
}
