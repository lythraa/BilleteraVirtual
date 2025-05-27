package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorUsuarios;
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
/**
 * Controlador para gestionar usuarios desde el panel de administrador.
 * Permite agregar, editar, buscar, eliminar y listar usuarios del sistema.
 */
public class GestionUsuariosController {

    @FXML
    private TextField campoId;
    @FXML
    private TextField campoCorreo;
    @FXML
    private TextField campoTelefono;
    @FXML
    private PasswordField campoContrasena;
    @FXML
    private TextField campoDireccion;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoIdBusqueda;

    @FXML
    private TableColumn<Usuario, String> columnaId;
    @FXML
    private TableColumn<Usuario, String> columnaNombre;
    @FXML
    private TableColumn<Usuario, String> columnaCorreo;
    @FXML
    private TableColumn<Usuario, String> columnaTelefono;
    @FXML
    private TableColumn<Usuario, String> columnaDireccion;
    @FXML
    private TableColumn<Usuario, Double> columnaSaldo;
    @FXML
    private TableView<Usuario> tablaUsuarios;

    private final ObservableList<Usuario> listaObservableUsuarios = FXCollections.observableArrayList();

    /**
     * Verifica si hay campos obligatorios vacíos.
     *
     * @return true si hay campos inválidos, false están está completo.
     */
    private boolean camposInvalidos() {
        return UtilAlerta.esInvalido(campoId.getText(), "ID") ||
                UtilAlerta.esInvalido(campoContrasena.getText(), "Contraseña") ||
                UtilAlerta.esInvalido(campoNombre.getText(), "Nombre") ||
                UtilAlerta.esInvalido(campoCorreo.getText(), "Correo");
    }


    /**
     * Crea un nuevo objeto Usuario con los datos ingresados en los campos.
     *
     * @return Usuario creado.
     */
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

    /**
     * Actualiza un usuario existente con los datos actuales del formulario.
     *
     * @param usuario Usuario a actualizar.
     */
    private void actualizarUsuarioDesdeCampos(Usuario usuario) {
        usuario.setId(campoId.getText());
        usuario.setContrasenia(campoContrasena.getText());
        usuario.setNombre(campoNombre.getText());
        usuario.setCorreo(campoCorreo.getText());
        usuario.setTelefono(campoTelefono.getText());
        usuario.setDireccion(campoDireccion.getText());
    }

    /**
     * Agrega un nuevo usuario si no existe otro con el mismo ID.
     */
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
        UtilAlerta.mostrarAlertaInformacion("Usuario agregado", "El usuario se agregó correctamente.");
    }

    /**
     * Edita los datos del usuario seleccionado en la tabla.
     */
    @FXML
    void onEditar() {
        Usuario seleccionado = getUsuarioSeleccionado();
        if (seleccionado == null) {
            UtilAlerta.mostrarAlertaError("Selección vacía", "No has seleccionado ningún usuario para editar.");
            return;
        }
        if (camposInvalidos()) return;
        actualizarUsuarioDesdeCampos(seleccionado);
        cargarUsuarios();
        limpiarCampos();
    }

    /**
     * Busca un usuario por su ID y actualiza la tabla con el resultado.
     */
    @FXML
    void onBuscar() {
        String idBuscado = campoIdBusqueda.getText().trim();

        if (idBuscado.isEmpty()) {
            cargarUsuarios();
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

    /**
     * Elimina el usuario seleccionado, tras confirmación.
     */
    @FXML
    void onEliminar() {
        Usuario seleccionado = getUsuarioSeleccionado();

        if (seleccionado == null) {
            UtilAlerta.mostrarAlertaError("Selección vacía", "No has seleccionado ningún usuario para eliminar.");
            return;
        }

        boolean confirmado = UtilAlerta.mostrarAlertaConfirmacion(
                "¿Estás seguro?",
                "Esta acción eliminará al usuario permanentemente."
        );

        if (!confirmado) {
            return;
        }

        GestorUsuarios.getInstancia().eliminar(seleccionado);
        listaObservableUsuarios.remove(seleccionado);
        limpiarCampos();
        cargarUsuarios();

        UtilAlerta.mostrarAlertaInformacion(
                "Usuario eliminado",
                "El usuario ha sido eliminado correctamente."
        );
    }

    /**
     * Vuelve a la vista principal del administrador.
     */
    public void onVolver() {
        Stage stage = (Stage) campoId.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "AdministradorView.fxml", "Vista Administrador");
    }

    /**
     * Limpia y recarga la tabla de usuarios.
     */
    @FXML
    void onRefrescarTabla() {
        limpiarCampos();
        tablaUsuarios.getSelectionModel().clearSelection();
        cargarUsuarios();
    }

    /**
     * Carga todos los usuarios registrados en la tabla.
     */
    private void cargarUsuarios() {
        listaObservableUsuarios.setAll(GestorUsuarios.getInstancia().getListaObjetos());
    }


    /**
     * Llena los campos del formulario con los datos del usuario seleccionado.
     * @param usuario Usuario cuyos datos se van a mostrar.
     */
    private void llenarCamposConUsuario(Usuario usuario) {
        campoId.setText(usuario.getId());
        campoContrasena.setText(usuario.getContrasenia());
        campoNombre.setText(usuario.getNombre());
        campoCorreo.setText(usuario.getCorreo());
        campoTelefono.setText(usuario.getTelefono());
        campoDireccion.setText(usuario.getDireccion());
    }


    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCampos() {
        campoId.clear();
        campoContrasena.clear();
        campoNombre.clear();
        campoCorreo.clear();
        campoTelefono.clear();
        campoDireccion.clear();
    }


    /**
     * Obtiene el usuario actualmente seleccionado en la tabla.
     * @return Usuario seleccionado o null si no hay selección.
     */
    private Usuario getUsuarioSeleccionado() {
        return tablaUsuarios.getSelectionModel().getSelectedItem();
    }


    /**
     * Inicializa la tabla de usuarios y configura la selección.
     */
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
