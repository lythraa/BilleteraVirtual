package co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

public abstract class ValidadorTransaccion {
    protected ValidadorTransaccion siguiente;

    /**
     * Método para establecer el siguiente validador en la cadena
     * @param siguiente
     * @return
     */
    public ValidadorTransaccion setSiguiente(ValidadorTransaccion siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public void validar(Movimiento movimiento) {
        realizarValidacion(movimiento);
        if (siguiente != null) {
            siguiente.validar(movimiento);
        }
    }

    protected abstract void realizarValidacion(Movimiento movimiento);
}
