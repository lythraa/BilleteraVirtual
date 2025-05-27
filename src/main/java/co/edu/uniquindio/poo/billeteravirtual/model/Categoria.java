package co.edu.uniquindio.poo.billeteravirtual.model;

/**
 * Representa una categoría con un presupuesto asignado.
 */
public class Categoria implements Identificable, Cloneable {
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
     * Hace una copia profunda (Deep Copy) de la categoría
     * @return Categoria clonada
     */
    @Override
    public Categoria clone() {
        try {
            Categoria copia = (Categoria) super.clone();
            copia.presupuesto = presupuesto.clone();
            return copia;
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }

    }


//==============================GETTERS AND SETTERS=========================

    /**
     * Retorna el identificador de la categoría.
     *
     * @return id de la categoría
     */
    @Override
    public String getId() {
        return id_Nombre;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setId_Nombre(String id_Nombre) {
        this.id_Nombre = id_Nombre;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
}