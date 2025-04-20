package co.edu.uniquindio.poo.billeteravirtual.viewcontroller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionarcuentasController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField campoUsuario;

    @FXML
    private TableColumn<?, ?> columnaDireccion;

    @FXML
    private TextField campoCorreo;

    @FXML
    private TextField campoSaldo;

    @FXML
    private TableColumn<?, ?> columnaCorreo;

    @FXML
    private TableColumn<?, ?> columnaTelefono;

    @FXML
    private TableView<?> tablaUsuarios;

    @FXML
    private TextField campoTelefono;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    private TableColumn<?, ?> columnaSaldo;

    @FXML
    private TableColumn<?, ?> columnaId;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private TextField campoDireccion;

    @FXML
    private TableColumn<?, ?> columnaNombre;

    @FXML
    private TextField campoNombre;

    @FXML
    void onAgregar() {

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

    @FXML
    void initialize() {
        assert campoUsuario != null : "fx:id=\"campoUsuario\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert columnaDireccion != null : "fx:id=\"columnaDireccion\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert campoCorreo != null : "fx:id=\"campoCorreo\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert campoSaldo != null : "fx:id=\"campoSaldo\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert columnaCorreo != null : "fx:id=\"columnaCorreo\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert columnaTelefono != null : "fx:id=\"columnaTelefono\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert tablaUsuarios != null : "fx:id=\"tablaUsuarios\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert campoTelefono != null : "fx:id=\"campoTelefono\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert campoContrasena != null : "fx:id=\"campoContrasena\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert columnaSaldo != null : "fx:id=\"columnaSaldo\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert columnaId != null : "fx:id=\"columnaId\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert campoBusqueda != null : "fx:id=\"campoBusqueda\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert campoDireccion != null : "fx:id=\"campoDireccion\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert columnaNombre != null : "fx:id=\"columnaNombre\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";
        assert campoNombre != null : "fx:id=\"campoNombre\" was not injected: check your FXML file 'GestionarCuentasView.fxml'.";

    }
}
