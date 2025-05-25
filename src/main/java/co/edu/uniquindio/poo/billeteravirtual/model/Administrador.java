package co.edu.uniquindio.poo.billeteravirtual.model;

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

}
