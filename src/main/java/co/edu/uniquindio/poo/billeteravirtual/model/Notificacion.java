package co.edu.uniquindio.poo.billeteravirtual.model;

public class Notificacion {
    private String mensaje;

    /**
     * Método constructor de la clase Notificacion
     * @param mensaje mensaje de la notificacion
     */
    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}