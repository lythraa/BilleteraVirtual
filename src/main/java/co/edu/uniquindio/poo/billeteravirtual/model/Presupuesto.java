package co.edu.uniquindio.poo.billeteravirtual.model;
/**
 * Clase que representa un presupuesto asignado,
 * permitiendo registrar gastos y controlar el monto gastado.
 */
public class Presupuesto {

    private double montoAsignado;
    private double montoGastado;

    /**
     * Constructor de la clase Presupuesto.
     * @param montoAsignado Monto total asignado a este presupuesto.
     */
    public Presupuesto(String id, String nombre, double montoAsignado, Categoria categoria) {
        this.montoAsignado = montoAsignado;
        this.montoGastado = 0.0;
    }

    /**
     * Registra un gasto en el presupuesto.
     * Lanza excepción si el gasto supera el monto asignado.
     * @param monto Monto del gasto a registrar.
     */
    public void registrarGasto(double monto) {
        if (montoGastado + monto > montoAsignado) {
            throw new IllegalArgumentException("El gasto excede el monto asignado del presupuesto.");
            //O MEJOR HACER UNA ALERTAAAA
        }
        this.montoGastado += monto;
    }

    //===============GETTERS Y SETTERS==================//

    public double getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(double montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public double getMontoGastado() {
        return montoGastado;
    }

    public void setMontoGastado(double montoGastado) {
        this.montoGastado = montoGastado;
    }
}
