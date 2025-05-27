package co.edu.uniquindio.poo.billeteravirtual.model.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

/**
 * Validador de la cadena de responsabilidad que verifica
 * si la cuenta bancaria de origen tiene saldo suficiente
 * para realizar la transacción.
 */
public class ValidadorSaldoSuficiente extends ValidadorMovimiento {

    /**
     * Verifica que el saldo disponible en la cuenta de origen
     * sea suficiente para cubrir el monto de la transacción.
     * Lanza una excepción si no hay fondos suficientes. De lo contrario,
     * muestra una alerta de validación exitosa.
     *
     * @param movimiento Movimiento que contiene la cuenta origen y el monto
     * @throws IllegalArgumentException si el movimiento es nulo
     */
    @Override
    protected void realizarValidacion(Movimiento movimiento) {
        if (movimiento==null){
            throw new IllegalArgumentException("El movimiento no puede ser nulo.");
        }
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
