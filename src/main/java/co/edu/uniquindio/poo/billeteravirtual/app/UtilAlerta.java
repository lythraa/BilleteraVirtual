package co.edu.uniquindio.poo.billeteravirtual.app;

import javafx.scene.control.Alert;

public class UtilAlerta {

    public static void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
