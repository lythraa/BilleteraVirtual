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

    public void registrarGasto(double monto) {
        this.gastosAcumulados += monto;
    }

    @Override
    public String getId() {
        return id;
    }
}

