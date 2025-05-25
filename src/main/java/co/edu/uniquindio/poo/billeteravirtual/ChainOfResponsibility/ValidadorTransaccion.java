package co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

public abstract class ValidadorTransaccion {
    protected ValidadorTransaccion siguiente;

    /**
     * Método para establecer el siguiente validador en la cadena.
     * @param siguiente
     * @return
     */
    public ValidadorTransaccion setSiguiente(ValidadorTransaccion siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    /**
     * Método para ejecutar la validación, si se ha establecido
     * la siguiente en la cadena, continúa, de lo contrario se detiene.
     * @param movimiento
     */
    public void validar(Movimiento movimiento) {
        realizarValidacion(movimiento);
        if (siguiente != null) {
            siguiente.validar(movimiento);
        }
    }

    /**
     * Método abstracto que valida según criterios propios de las clases que lo implementan.
     * @param movimiento
     */
    protected abstract void realizarValidacion(Movimiento movimiento);
}
