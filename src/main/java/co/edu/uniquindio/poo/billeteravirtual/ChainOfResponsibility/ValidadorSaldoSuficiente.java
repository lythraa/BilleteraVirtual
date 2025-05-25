package co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

public class ValidadorSaldoSuficiente extends ValidadorTransaccion {
    /**
     * Valida que la cuenta de origen tenga saldo suficiente
     * para realizar la transacción con el monto deseado
     * @param movimiento
     */
    @Override
    protected void realizarValidacion(Movimiento movimiento) {
        double saldo = movimiento.getCuentaBancariaOrigen().getSaldo();
        double monto = movimiento.getMonto();
        if (saldo < monto) {
            throw new IllegalStateException("Saldo insuficiente en la cuenta de origen.");
        }
    }
}
