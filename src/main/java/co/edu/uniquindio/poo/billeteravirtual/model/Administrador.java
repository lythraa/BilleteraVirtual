package co.edu.uniquindio.poo.billeteravirtual.model;
import java.util.List;

public class Administrador extends Perfil {

    /**
     * Método constructor de la clase Administrador
     * @param id Cédula del administrador
     * @param contrasenia Clave de acceso del administrador
     * @param nombre Nombre y apellido del administrador
     * @param correo Dirección de correo electrónico del administrador
     * @param telefono Número de telefono del administrador
     * @param direccion Dirección de residencia del administrador
     */
    public Administrador(String id, String contrasenia, String nombre, String correo, String telefono, String direccion){
        super(id, contrasenia, nombre, correo, telefono, direccion);
    }

}
