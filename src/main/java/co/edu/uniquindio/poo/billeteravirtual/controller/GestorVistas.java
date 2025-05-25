package co.edu.uniquindio.poo.billeteravirtual.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class GestorVistas {

    /**
     * Cambia la escena actual del Stage (ventana) por una nueva definida en un archivo FXML.
     *
     * @param escenario El Stage actual donde se mostrará la nueva vista.
     * @param rutaFXML  Ruta del archivo FXML que contiene la vista a cargar (ej: "/views/UsuarioView.fxml").
     * @param titulo    Título que tendrá la ventana después del cambio de escena.
     */
    public static void CambiarEscena(Stage escenario, String rutaFXML, String titulo) {
        try{
            // Carga el archivo FXML desde la ruta especificada usando la clase actual como referencia.
            FXMLLoader loader = new FXMLLoader(GestorVistas.class.getResource("/views/"+rutaFXML));

            // Carga la jerarquía de nodos definida en el FXML como raíz de la escena.
            Parent root = loader.load();

            // Crea una nueva escena con el diseño cargado.
            Scene escena = new Scene(root);

            // Cambia el título de la ventana (Stage).
            escenario.setTitle(titulo);

            // Establece la nueva escena en la ventana actual.
            escenario.setScene(escena);

            // Muestra la ventana (si no estaba visible).
            escenario.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void abrirVistaConOrigen(String rutaFXML, String tituloVentana, Stage stageActual, String vistaOrigen, Consumer<Object> configController) {
        try {
            FXMLLoader loader = new FXMLLoader(GestorVistas.class.getResource(rutaFXML));
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof ContactoController) {
                ((ContactoController) controller).setVistaOrigen(vistaOrigen);
            } else if (controller instanceof EditarPerfilController) {
                ((EditarPerfilController) controller).setVistaOrigen(vistaOrigen);
            }

            if (configController != null) {
                configController.accept(controller);
            }

            stageActual.setTitle(tituloVentana);
            stageActual.setScene(new Scene(root));
            stageActual.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Usos específicos:
    public static void abrirVistaEditarPerfilDesde(Stage stageActual, String vistaOrigen) {
        abrirVistaConOrigen("/views/EditarPerfilView.fxml", "Editar Perfil", stageActual, vistaOrigen, controller -> {
            ((EditarPerfilController) controller).setVistaOrigen(vistaOrigen);
        });
    }

    public static void abrirVistaContactoController(Stage stageActual, String vistaOrigen) {
        abrirVistaConOrigen("/views/ContactoView.fxml", "Contacto Vista", stageActual, vistaOrigen, controller -> {
            ((ContactoController) controller).setVistaOrigen(vistaOrigen);
        });
    }
}
