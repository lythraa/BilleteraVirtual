package co.edu.uniquindio.poo.billeteravirtual.model;

public class Categoria {
    private String idCategoria;
    private String nombre;
    private String descripcion;

    public Categoria(String idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    //==============================GETTERS AND SETTERS=========================
    public String getId_Nombre() {
        return id_Nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}