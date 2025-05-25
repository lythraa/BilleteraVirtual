package co.edu.uniquindio.poo.billeteravirtual.model;

/**
 * Representa una categoría con un presupuesto asignado.
 */
public class Categoria implements Identificable {
    private String id_Nombre;
    private Presupuesto presupuesto;

    /**
     * Crea una nueva categoría con nombre y presupuesto asignado.
     *
     * @param id_Nombre Nombre o identificador de la categoría
     * @param presupuesto Presupuesto asociado a la categoría
     */
    public Categoria(String id_Nombre, Presupuesto presupuesto) {
        this.id_Nombre = id_Nombre;
        this.presupuesto = presupuesto;
    }

    /**
     * Retorna el identificador de la categoría.
     *
     * @return id de la categoría
     */
    @Override
    public String getId() {
        return id_Nombre;
    }

    //==============================GETTERS AND SETTERS=========================
    public String getId_Nombre() {
        return id_Nombre;
    }

    public void setId_Nombre(String id_Nombre) {
        this.id_Nombre = id_Nombre;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
}