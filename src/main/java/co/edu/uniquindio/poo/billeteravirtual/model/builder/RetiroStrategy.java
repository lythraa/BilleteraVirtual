package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility.ValidadorSaldoSuficiente;
import co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility.ValidadorTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;

public class RetiroStrategy implements MovimientoStrategy{

    private final ValidadorTransaccion validador;

    public RetiroStrategy() {
        // Construcción de la cadena: primero cuenta, luego saldo
        this.validador = new ValidadorSaldoSuficiente();
    }

    @Override
    public void procesarTransaccion(Movimiento movimiento) {
        validador.validar(movimiento);

        CuentaBancaria origen = movimiento.getCuentaBancariaOrigen();
        CuentaBancaria destino = movimiento.getCuentaBancariaDestino();

        double monto = movimiento.getMonto();

        origen.retirarSaldo(monto);
        destino.agregarSaldo(monto);
        //Saltar alerta con un código para presentar en la sucursal más cercana y reclamar el dinero :DD
    }
}
