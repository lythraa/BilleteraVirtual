package co.edu.uniquindio.poo.billeteravirtual.model;

/**
 * Interfaz que define la estrategia para procesar un movimiento financiero.
 * Implementa el patrón Strategy para permitir distintas formas de procesar
 * transacciones, como depósitos, retiros o transferencias.
 */
public interface MovimientoStrategy {

    /**
     * Procesa una transacción financiera.
     * La implementación específica define cómo se maneja el movimiento.
     *
     * @param movimiento Objeto Movimiento que contiene los datos necesarios para procesar la transacción.
     */
    void procesarTransaccion(Movimiento movimiento);
}
