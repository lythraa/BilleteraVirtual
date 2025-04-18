package co.edu.uniquindio.poo.billeteravirtual.model;

public class Presupuesto {
    private String idPresupuesto;
    private String nombre;
    private double montoTotal;
    private double montoGastado;
    private Categoria categoria;

    public Presupuesto(String idPresupuesto, String nombre, double montoTotal, Categoria categoria) {
        this.idPresupuesto = idPresupuesto;
        this.nombre = nombre;
        this.montoTotal = montoTotal;
        this.montoGastado = 0.0;
        this.categoria = categoria;
    }

    public void registrarGasto(double monto) {
        this.montoGastado += monto;
    }
}
