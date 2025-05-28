package co.edu.uniquindio.poo.billeteravirtual.model;

/**
 * Clase base abstracta para la implementación del patrón Chain of Responsibility
 * en la validación de transacciones. Define la estructura general para encadenar
 * múltiples validadores y ejecutar sus validaciones secuencialmente.
 */
public abstract class ValidadorMovimiento {

    protected ValidadorMovimiento siguiente;

    /**
     * Establece el siguiente validador en la cadena.
     * Permite construir la cadena de validación de manera fluida.
     *
     * @param siguiente El siguiente validador en la cadena.
     * @return El validador recién asignado para permitir el encadenamiento.
     */
    public ValidadorMovimiento setSiguiente(ValidadorMovimiento siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    /**
     * Ejecuta la validación definida en el validador actual.
     * Si hay un siguiente validador en la cadena, delega la validación.
     *
     * @param movimiento El movimiento sobre el cual se realiza la validación.
     * @throws IllegalArgumentException si el movimiento es nulo
     */

    public void validar(Movimiento movimiento) {
        if (movimiento==null){
            throw new IllegalArgumentException("El movimiento no puede ser nulo.");
        }
        realizarValidacion(movimiento);
        if (siguiente != null) {
            siguiente.validar(movimiento);
        }
    }

    /**
     * Método abstracto que debe ser implementado por las subclases
     * para definir una validación específica del movimiento.
     *
     * @param movimiento Movimiento a validar.
     */
    protected abstract void realizarValidacion(Movimiento movimiento);
}
