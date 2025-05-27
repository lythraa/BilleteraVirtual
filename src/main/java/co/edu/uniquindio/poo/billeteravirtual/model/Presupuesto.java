package co.edu.uniquindio.poo.billeteravirtual.model;
/**
 * Clase que representa un presupuesto asignado,
 * permitiendo registrar gastos y controlar el monto gastado.
 */
public class Presupuesto implements Cloneable {

    private double montoAsignado;
    private double montoGastado;

    /**
     * Constructor de la clase Presupuesto.
     * @param montoAsignado Monto total asignado a este presupuesto.
     */
    public Presupuesto(double montoAsignado) {
        this.montoAsignado = montoAsignado;
        this.montoGastado = 0.0;
    }

    /**
     * Registra un gasto en el presupuesto.
     * @param monto Monto del gasto a registrar.
     * @throws IllegalArgumentException si el gasto excede el monto asignado del presupuesto
     */
    public void registrarGasto(double monto) {
        if (montoGastado + monto > montoAsignado) {
            throw new IllegalArgumentException("El gasto excede el monto asignado del presupuesto.");
        }
        this.montoGastado += monto;
    }

    /**
     * Clona el presupuesto
     * @return Copia del presupuesto
     * @throws CloneNotSupportedException si por algún motivo falla
     */
    @Override
    public Presupuesto clone() throws CloneNotSupportedException {
        return (Presupuesto) super.clone();
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
