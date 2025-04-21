package co.edu.uniquindio.poo.billeteravirtual.model;

public class Presupuesto {
    private String idPresupuesto;
    private String nombre;
    private double presupuestoAsignado;
    private double gastosAcumulados;
    private Categoria categoria;

    public Presupuesto(String idPresupuesto, String nombre, double presupuestoAsignado, Categoria categoria) {
        this.idPresupuesto = idPresupuesto;
        this.nombre = nombre;
        this.presupuestoAsignado = presupuestoAsignado;
        this.gastosAcumulados = 0.0;
        this.categoria = categoria;
    }

    public void registrarGasto(double monto) {
        this.gastosAcumulados += monto;
    }
}
