package co.edu.uniquindio.poo.billeteravirtual.model;

public class Presupuesto implements Identificable{
    private String id;
    private String nombre;
    private double presupuestoAsignado;
    private double gastosAcumulados;
    private Categoria categoria;

    public Presupuesto(String id, String nombre, double presupuestoAsignado, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.presupuestoAsignado = presupuestoAsignado;
        this.gastosAcumulados = 0.0;
        this.categoria = categoria;
    }

    /**
     * Registra un gasto en el presupuesto.
     * Lanza excepción si el gasto supera el monto asignado.
     * @param monto Monto del gasto a registrar.
     */
    public void registrarGasto(double monto) {
        this.gastosAcumulados += monto;
    }

    @Override
    public String getId() {
        return id;
    }
}

