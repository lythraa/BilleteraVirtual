package co.edu.uniquindio.poo.billeteravirtual.model;

public class Perfil implements Identificable {

    private String id;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private String contrasenia;

    /**
     *
     * @param id
     * @param nombre
     * @param correo
     * @param telefono
     * @param direccion
     * @param contrasenia Contraseña de acceso del usuario
     */
    public Perfil(String id, String contrasenia, String nombre, String correo, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.contrasenia = contrasenia;
    }

    //====================GETTERS Y SETTERS=======================//


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


}
