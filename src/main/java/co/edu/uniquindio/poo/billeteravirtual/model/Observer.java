package co.edu.uniquindio.poo.billeteravirtual.model;

public interface Observer {
    /**
     * Método que permite recibir una notificación
     * @param notificacion
     */
    void recibirNotificacion(Notificacion notificacion);
}