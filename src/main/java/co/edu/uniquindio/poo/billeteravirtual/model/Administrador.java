package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.model.observer.GestorNotificaciones;
import co.edu.uniquindio.poo.billeteravirtual.model.observer.Notificacion;

/**
 * Clase que representa un administrador del sistema.
 */
public class Administrador extends Perfil {

    /**
     * Constructor de la clase Administrador.
     *
     * @param id Cédula del administrador
     * @param contrasenia Contraseña de acceso
     * @param nombre Nombre completo
     * @param correo Correo electrónico
     * @param telefono Número de teléfono
     * @param direccion Dirección de residencia
     */
    public Administrador(String id, String contrasenia, String nombre, String correo, String telefono, String direccion){
        super(id, contrasenia, nombre, correo, telefono, direccion);
    }

    public void enviarNotificacion(String evento, String mensaje) {
        Notificacion notificacion = new Notificacion("ADMIN " + this.getNombre() + ": " + mensaje);
        GestorNotificaciones.getInstancia().notificar(evento, notificacion);
    }
}
