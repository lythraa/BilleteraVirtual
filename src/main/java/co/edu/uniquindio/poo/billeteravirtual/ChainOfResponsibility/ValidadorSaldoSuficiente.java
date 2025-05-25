package co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

/**
 * Validador de la cadena de responsabilidad que verifica
 * si la cuenta bancaria de origen tiene saldo suficiente
 * para realizar la transacción.
 */
public class ValidadorSaldoSuficiente extends ValidadorTransaccion {

    /**
     * Verifica que el saldo disponible en la cuenta de origen
     * sea suficiente para cubrir el monto de la transacción.
     * Lanza una excepción si no hay fondos suficientes. De lo contrario,
     * muestra una alerta de validación exitosa.
     *
     * @param movimiento Movimiento que contiene la cuenta origen y el monto
     */
    @Override
    protected void realizarValidacion(Movimiento movimiento) {
        double saldo = movimiento.getCuentaBancariaOrigen().getSaldo();
        double monto = movimiento.getMonto();
        if (saldo < monto) {
            throw new IllegalStateException("Saldo insuficiente en la cuenta de origen.");
        }
        UtilAlerta.mostrarAlertaInformacion(
                "Validación de saldo",
                "✅ La cuenta tiene saldo suficiente para realizar la transacción."
        );
    }
}
