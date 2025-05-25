package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;

public class DepositoStrategy implements MovimientoStrategy{
    @Override
    public void procesarTransaccion(Movimiento movimiento) {
        CuentaBancaria destino = movimiento.getCuentaBancariaOrigen();
        destino.agregarSaldo(movimiento.getMonto());
        //Saltar alerta con un código para presentar en la sucursal más cercana y reclamar el dinero :DD
    }
}
