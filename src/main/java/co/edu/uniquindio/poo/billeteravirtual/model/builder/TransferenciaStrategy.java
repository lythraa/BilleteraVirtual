package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility.*;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;

/**
 * Estrategia concreta que representa un movimiento de tipo transferencia.
 * Implementa el patrón Strategy para aplicar la lógica específica de transferencia
 * entre dos cuentas bancarias.
 *
 * Utiliza el patrón Chain of Responsibility para validar diferentes aspectos
 * antes de realizar la transferencia:
 * - Cuenta no nula,
 * - Cuenta destino válida,
 * - Saldo suficiente en la cuenta origen.
 */
public class TransferenciaStrategy implements MovimientoStrategy{
    private final ValidadorTransaccion validador;

    /**
     * Constructor que construye la cadena de validadores para la transferencia.
     * El orden de validación es:
     * 1. Validar que la cuenta origen no sea nula.
     * 2. Validar que la cuenta destino sea diferente y válida.
     * 3. Validar que la cuenta origen tenga saldo suficiente.
     */
    public TransferenciaStrategy() {
        ValidadorTransaccion validador1 = new ValidadorCuentaNula();
        ValidadorTransaccion validador2 = new ValidadorCuentaDestino();
        ValidadorTransaccion validador3 = new ValidadorSaldoSuficiente();

        validador1.setSiguiente(validador2);
        validador2.setSiguiente(validador3);

        this.validador = validador1;
    }

    /**
     * Procesa la transacción de transferencia luego de pasar las validaciones.
     * Resta el monto de la cuenta origen y lo agrega a la cuenta destino.
     *
     * @param movimiento Objeto Movimiento que contiene datos de la transferencia.
     */
    @Override
    public void procesarTransaccion(Movimiento movimiento) {
        validador.validar(movimiento);

        CuentaBancaria origen = movimiento.getCuentaBancariaOrigen();
        CuentaBancaria destino = movimiento.getCuentaBancariaDestino();
        double monto = movimiento.getMonto();

        origen.retirarSaldo(monto);
        destino.agregarSaldo(monto);
    }
}
