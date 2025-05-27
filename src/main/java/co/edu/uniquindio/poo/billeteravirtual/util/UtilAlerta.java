package co.edu.uniquindio.poo.billeteravirtual.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Objects;
import java.util.Optional;

/**
 * Clase UtilAlerta que proporciona métodos estáticos para mostrar diferentes tipos de alertas
 * (error, información, advertencia y confirmación) utilizando JavaFX.
 */
public class UtilAlerta {
    public static boolean modoTest = false;

    /**
     * Muestra una alerta de tipo ERROR con un título y mensaje especificados.
     *
     * @param titulo Título de la alerta
     * @param mensaje Mensaje que se muestra en la alerta
     */
    public static void mostrarAlertaError(String titulo, String mensaje) {
        if (modoTest) return;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        aplicarEstilo(alert);
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
        if (modoTest) return;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        aplicarEstilo(alert);
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
        if (modoTest) return;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        aplicarEstilo(alert);
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
        aplicarEstilo(alert);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }


    /**
     * Valida que un campo de texto no sea nulo ni esté vacío.
     * Si el campo es inválido, se muestra una alerta al usuario.
     *
     * @param campo El contenido del campo a validar.
     * @param nombreCampo El nombre descriptivo del campo (para mostrar en la alerta).
     * @return true si el campo es válido (no nulo ni vacío), false en caso contrario.
     */
    public static boolean esInvalido(String campo, String nombreCampo) {
        if (campo == null) {
            mostrarAlertaAdvertencia("Campo inválido", "El campo '" + nombreCampo + "' no puede ser nulo.");
            return true;
        }
        if (campo.trim().isEmpty()) {
                mostrarAlertaAdvertencia("Campo inválido", "El campo '" + nombreCampo + "' no puede estar vacío.");
                return true;
        }
        return false;
    }

    /**
     * Válida que un campo de texto contenga un número válido mayor que cero y lo convierte a double.
     * Si el valor no es numérico o no es mayor que cero, se muestra una alerta y se retorna null.
     *
     * @param texto El texto que representa el monto a validar.
     * @param nombreCampo El nombre descriptivo del campo (para mostrar en la alerta).
     * @return El valor numérico convertido si es válido, null en caso de error de validación.
     */
    public static Double validarYConvertirMonto(String texto, String nombreCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            mostrarAlertaAdvertencia("Campo inválido", "El campo '" + nombreCampo + "' no puede estar vacío.");
            return null;
        }

        try {
            double valor = Double.parseDouble(texto);
            if (valor <= 0) {
                mostrarAlertaAdvertencia("Campo inválido", "El campo '" + nombreCampo + "' debe ser mayor que cero.");
                return null;
            }
            return valor;
        } catch (NumberFormatException e) {
            mostrarAlertaInformacion("Número inválido", "El campo '" + nombreCampo + "' debe contener un número válido.");
            return null;
        }


    }

    public static void mostrarAlertaNoEncontrado(String entidad) {
        if (modoTest) return;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        aplicarEstilo(alert);
        alert.setTitle(entidad + " no encontrado");
        alert.setHeaderText(null);
        alert.setContentText("No se encontró " + entidad.toLowerCase() + " con los datos proporcionados.");
        alert.showAndWait();
    }

    private static void aplicarEstilo(Alert alert) {
        if (modoTest) return;
        alert.getDialogPane().getStylesheets().add(
                Objects.requireNonNull(UtilAlerta.class.getResource("/css/style.css")).toExternalForm()
        );
        alert.getDialogPane().getStyleClass().add("my-alert");
    }


}
