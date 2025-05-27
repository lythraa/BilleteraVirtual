package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.Presupuesto;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GestionCategoriasPresupuestosController {

    @FXML
    private TextField campoBusqueda;

    @FXML
    private TextField campoNombreIDCategoria;

    @FXML
    private TextField campoPresupuestoAsignado;

    @FXML
    private TilePane tilePaneCategorias;

    /**
     * Permite regresar a la pantalla anterior
     */
    @FXML
    private void onVolver() {
        Stage stage = (Stage) campoPresupuestoAsignado.getScene().getWindow();
        GestorVistas.CambiarEscena(stage, "UsuarioView.fxml", "Panel Usuario");
    }

    /**
     * Permite crear una nueva categoría para el usuario actual
     */
    @FXML
    private void onCrear() {
        Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();
        String nombre = campoNombreIDCategoria.getText();
        String presupuestoStr = campoPresupuestoAsignado.getText();

        if (UtilAlerta.esInvalido(nombre, "nombre de categoria")|| UtilAlerta.esInvalido(presupuestoStr, "presupuesto asignado")) {
            return;
        }

        try {
            double presupuesto = Double.parseDouble(presupuestoStr);
            Presupuesto nuevoPresupuesto = new Presupuesto(presupuesto);
            Categoria nuevaCategoria = new Categoria(nombre, nuevoPresupuesto);

            usuarioActual.getListaCategorias().add(nuevaCategoria);
            cargarCategoriasUsuario();

            limpiarFormulario();

        } catch (NumberFormatException e) {
            UtilAlerta.mostrarAlertaError("Numero no valido", "El presupuesto debe ser un número válido.");
        }
    }

    /**
     * Permite editar una categoría existente del usuario
     */
    @FXML
    private void onEditar() {
        String nuevoNombre = campoNombreIDCategoria.getText();
        String presupuestoStr = campoPresupuestoAsignado.getText();


        if (UtilAlerta.esInvalido(nuevoNombre, "nombre de categoria")|| UtilAlerta.esInvalido(presupuestoStr, "presupuesto asignado")) {
            return;
        }

        try {
            double nuevoPresupuesto = Double.parseDouble(presupuestoStr);
            Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();

            for (Categoria cat : usuarioActual.getListaCategorias()) {
                if (cat.getId().equals(nuevoNombre)) {
                    cat.setId_Nombre(nuevoNombre);
                    cat.getPresupuesto().setMontoAsignado(nuevoPresupuesto);
                    break;
                }
            }

            limpiarFormulario();
            cargarCategoriasUsuario();

        } catch (NumberFormatException e) {
            UtilAlerta.mostrarAlertaError("Numero no valido","El presupuesto debe ser un número válido.");
        }
    }

    /**
     * Permite eliminar una de las categorías del usuario
     */
    @FXML
    private void onEliminar() {
        String idCategoria = campoNombreIDCategoria.getText();
        if (UtilAlerta.esInvalido(idCategoria,"Nombre categoria")) {
            return;
        }

        Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();

        boolean eliminada = usuarioActual.getListaCategorias().removeIf(cat -> cat.getId().equals(idCategoria));

        if (eliminada) {
            limpiarFormulario();
            cargarCategoriasUsuario();
        } else {
            UtilAlerta.mostrarAlertaError("Categoría inexistente","No se encontró la categoría.");
        }
    }

    /**
     * Permite buscar una categoría entre la lista de categorias del usuario
     */
    @FXML
    private void onBuscar() {
        String textoBusqueda = campoBusqueda.getText().toLowerCase();
        tilePaneCategorias.getChildren().clear();

        Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();

        for (Categoria categoria : usuarioActual.getListaCategorias()) {
            if (categoria.getId().toLowerCase().contains(textoBusqueda)) {
                agregarTarjetaCategoria(categoria);
            }
        }
    }

    /**
     * Permite clonar una categoría seleccionada junto con toda su información.
     * Puede ser modificada después por el usuario
     */
    @FXML
    public void onClonar() {
        String idCategoria = campoNombreIDCategoria.getText();
        if (idCategoria.isEmpty()) {
            UtilAlerta.mostrarAlertaError("No seleccionado","Selecciona una categoría para eliminar.");
            return;
        }

        Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();
        usuarioActual.buscarCategoria(idCategoria).clone();
        usuarioActual.getListaCategorias().add(usuarioActual.buscarCategoria(idCategoria).clone());
        limpiarFormulario();
        cargarCategoriasUsuario();
    }

    /**
     * Inicializa la tabla con información del usuario actualmente en sesión
     * llamando a cargarCategoriasUsuario
     */
    @FXML
    public void initialize() {
        cargarCategoriasUsuario();
    }

    /**
     * Limpia los campos del formulario de creación de categorías
     */
    private void limpiarFormulario() {
        campoNombreIDCategoria.clear();
        campoPresupuestoAsignado.clear();
    }

    /**
     * Agrega a la vista la tarjeta de una categoría
     * @param categoria por agregar
     */
    private void agregarTarjetaCategoria(Categoria categoria) {
        VBox tarjeta = construirTarjetaCategoria(categoria);
        tilePaneCategorias.getChildren().add(tarjeta);
    }

    /**
     * Carga las categorías del usuario actualmente en sesión
     */
    private void cargarCategoriasUsuario() {
        tilePaneCategorias.getChildren().clear();
        Usuario usuarioActual = (Usuario) GestorSesion.getInstance().getPerfilActual();

        for (Categoria categoria : usuarioActual.getListaCategorias()) {
            agregarTarjetaCategoria(categoria);
        }
    }

    /**
     * Construye una tarjeta basádo en la información de una categoría
     * @param categoria para crear la tarjeta
     * @return tarjeta con información de la categoría recibida
     */
    private VBox construirTarjetaCategoria(Categoria categoria) {
        VBox tarjeta = new VBox(8);
        tarjeta.getStyleClass().add("categoria-card");

        double presupuesto = categoria.getPresupuesto().getMontoAsignado();
        double gastado = categoria.getPresupuesto().getMontoGastado();
        double porcentaje = presupuesto == 0 ? 0 : gastado / presupuesto;

        if (porcentaje < 0.6) {
            tarjeta.getStyleClass().add("card-ok");
        } else if (porcentaje < 0.9) {
            tarjeta.getStyleClass().add("card-alerta");
        } else {
            tarjeta.getStyleClass().add("card-peligro");
        }

        Label nombreLabel = new Label(categoria.getId());
        nombreLabel.getStyleClass().add("label-nombre");

        Label presupuestoLbl = new Label("Presupuesto: $" + presupuesto);
        presupuestoLbl.getStyleClass().add("label-presupuesto");

        Label gastadoLbl = new Label("Gastado: $" + gastado);
        gastadoLbl.getStyleClass().add("label-gastado");

        tarjeta.getChildren().addAll(nombreLabel, presupuestoLbl, gastadoLbl);

        tarjeta.setOnMouseClicked(event -> {
            campoNombreIDCategoria.setText(categoria.getId());
            campoPresupuestoAsignado.setText(String.valueOf(presupuesto));
        });

        return tarjeta;
    }


}
