package co.edu.uniquindio.poo.billeteravirtual.app;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Clase UtilAlerta que proporciona métodos estáticos para mostrar diferentes tipos de alertas
 * (error, información, advertencia y confirmación) utilizando JavaFX.
 */
public class UtilAlerta {

    /**
     * Muestra una alerta de tipo ERROR con un título y mensaje especificados.
     *
     * @param titulo Título de la alerta
     * @param mensaje Mensaje que se muestra en la alerta
     */
    public static void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de tipo INFORMACIÓN con un título y mensaje especificados.
     *
     * @param titulo Título de la alerta
     * @param mensaje Mensaje que se muestra en la alerta
     */
    public static void mostrarAlertaInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de tipo ADVERTENCIA con un título y mensaje especificados.
     *
     * @param titulo Título de la alerta
     * @param mensaje Mensaje que se muestra en la alerta
     */
    public static void mostrarAlertaAdvertencia(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de tipo CONFIRMACIÓN con un título y mensaje especificados.
     * Espera la respuesta del usuario y devuelve true si confirma, false si cancela.
     *
     * @param titulo Título de la alerta
     * @param mensaje Mensaje que se muestra en la alerta
     * @return true si el usuario confirma la acción, false si cancela o cierra la ventana
     */
    public static boolean mostrarAlertaConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
}
