package co.edu.uniquindio.poo.billeteravirtual.util;

import co.edu.uniquindio.poo.billeteravirtual.controller.EditarPerfilController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorVistas {

    /**
     * Cambia la escena actual del Stage (ventana) por una nueva definida en un archivo FXML.
     *
     * @param escenario El Stage actual donde se mostrará la nueva vista.
     * @param rutaFXML  Ruta relativa del archivo FXML que contiene la vista a cargar (ejemplo: "UsuarioView.fxml").
     *                  Se concatena con "/views/" internamente.
     * @param titulo    Título que tendrá la ventana después del cambio de escena.
     */
    public static void CambiarEscena(Stage escenario, String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(GestorVistas.class.getResource("/views/" + rutaFXML));
            Parent root = loader.load();
            Scene escena = new Scene(root);
            escenario.setTitle(titulo);
            escenario.setScene(escena);
            escenario.show();
        } catch (Exception e) {
            Logger.getLogger(GestorVistas.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Abre una vista en el Stage dado, configurando el controlador con un origen y opcionalmente
     * realizando una configuración adicional mediante un Consumer.
     *
     * @param rutaFXML       Ruta completa del archivo FXML (con /views/ incluido, ej: "/views/EditarPerfilView.fxml").
     * @param tituloVentana  Título para la ventana.
     * @param stageActual    Stage donde se cargará la vista.
     * @param vistaOrigen    Cadena que representa la vista origen que invoca esta apertura.
     * @param configController Función que recibe el controlador cargado para hacer configuraciones adicionales (puede ser null).
     */
    @SuppressWarnings("unused")
    public static void abrirVistaConOrigen(String rutaFXML, String tituloVentana, Stage stageActual, String vistaOrigen, Consumer<Object> configController) {
        try {
            FXMLLoader loader = new FXMLLoader(GestorVistas.class.getResource(rutaFXML));
            Parent root = loader.load();
            Object controller = loader.getController();

            if (configController != null) {
                configController.accept(controller);
            }

            stageActual.setTitle(tituloVentana);
            stageActual.setScene(new Scene(root));
            stageActual.show();
        } catch (Exception e) {
            Logger.getLogger(GestorVistas.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Abre la vista de editar perfil desde una vista origen dada.
     *
     * @param stageActual Stage donde se abrirá la vista.
     * @param vistaOrigen Nombre o clave de la vista origen.
     */
    public static void abrirVistaEditarPerfilDesde(Stage stageActual, String vistaOrigen) {
        abrirVistaConOrigen("/views/EditarPerfilView.fxml", "Editar Perfil", stageActual, vistaOrigen, controller -> ((EditarPerfilController) controller).setVistaOrigen(vistaOrigen));
    }

}
