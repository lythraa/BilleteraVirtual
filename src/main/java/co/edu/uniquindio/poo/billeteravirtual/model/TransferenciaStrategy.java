package co.edu.uniquindio.poo.billeteravirtual.model;

/**
 * Estrategia concreta que representa un movimiento de tipo transferencia.
 * Implementa el patrón Strategy para aplicar la lógica específica de transferencia
 * entre dos cuentas bancarias.
 * Utiliza el patrón Chain of Responsibility para validar diferentes aspectos
 * antes de realizar la transferencia:
 * - Cuenta no nula,
 * - Cuenta destino válida,
 * - Saldo suficiente en la cuenta origen.
 */
public class TransferenciaStrategy implements MovimientoStrategy{

    public TransferenciaStrategy() {
    }

    /**
     * Procesa la transacción de transferencia luego de pasar las validaciones.
     * Resta el monto de la cuenta origen y lo agrega a la cuenta destino.
     *
     * @param movimiento Objeto Movimiento que contiene datos de la transferencia.
     */
    @Override
    public void procesarTransaccion(Movimiento movimiento) {

        CuentaBancaria origen = movimiento.getCuentaBancariaOrigen();
        CuentaBancaria destino = movimiento.getCuentaBancariaDestino();
        double monto = movimiento.getMonto();

        origen.retirarSaldo(monto);
        destino.agregarSaldo(monto);
    }
}
