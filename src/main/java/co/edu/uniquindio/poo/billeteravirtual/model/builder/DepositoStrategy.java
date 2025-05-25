package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;

/**
 * Estrategia concreta que representa un movimiento de tipo depósito.
 * Utiliza el patrón Strategy para implementar la lógica de añadir saldo a una cuenta bancaria.
 */
public class DepositoStrategy implements MovimientoStrategy{

    /**
     * Procesa un movimiento de tipo depósito, agregando el monto indicado a la cuenta bancaria origen.
     * Después de agregar el saldo, muestra una alerta informativa indicando que el depósito fue exitoso.
     *
     * @param movimiento Objeto Movimiento que contiene la información de la transacción, incluyendo la cuenta y el monto.
     */
    @Override
    public void procesarTransaccion(Movimiento movimiento) {
        CuentaBancaria destino = movimiento.getCuentaBancariaOrigen();
        destino.agregarSaldo(movimiento.getMonto());
        UtilAlerta.mostrarAlertaInformacion(
                "Depósito exitoso",
                "El depósito de $" + movimiento.getMonto() + " con la ID" + movimiento.getId() + " se ha realizado correctamente.\n" +
                        "Presenta este mensaje en la sucursal más cercana para reclamar el dinero. 💰"
        );
    }
}
