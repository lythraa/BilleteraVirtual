package co.edu.uniquindio.poo.billeteravirtual.model.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

/**
 * Validador de la cadena de responsabilidad que verifica que la cuenta bancaria destino
 * no sea nula antes de continuar con la transacción.
 * Es una validación inicial para evitar errores al procesar cuentas inexistentes.
 */
public class ValidadorCuentaNula extends ValidadorMovimiento {

    /**
     * Verifica que la cuenta bancaria destino no sea nula.
     * Si es nula, lanza una excepción. Si no lo es, muestra una alerta indicando que la validación fue exitosa.
     *
     * @param movimiento Movimiento a validar que contiene la cuenta bancaria destino
     * @throws IllegalArgumentException si la cuenta de destino es nula
     */
    @Override
    protected void realizarValidacion(Movimiento movimiento) {
        if (movimiento.getCuentaBancariaDestino() == null) {
            throw new IllegalArgumentException("La cuenta destino no puede ser nula.");
        }
    }
}
