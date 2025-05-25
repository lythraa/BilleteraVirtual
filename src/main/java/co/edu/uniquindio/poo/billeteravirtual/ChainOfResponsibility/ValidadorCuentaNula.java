package co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

public class ValidadorCuentaNula extends ValidadorTransaccion {
    /**
     * Verifica que la cuenta de destino no sea nula antes de realizar la transferencia
     * @param movimiento
     */
    @Override
    protected void realizarValidacion(Movimiento movimiento) {
        if (movimiento.getCuentaBancariaDestino() == null) {
            throw new IllegalArgumentException("La cuenta destino no puede ser nula.");
        }
    }
}
