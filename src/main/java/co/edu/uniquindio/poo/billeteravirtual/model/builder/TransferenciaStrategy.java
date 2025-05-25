package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility.*;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;

public class TransferenciaStrategy implements MovimientoStrategy{
    private final ValidadorTransaccion validador;

    public TransferenciaStrategy() {
        // Construcción de la cadena: primero cuenta, luego saldo
        ValidadorTransaccion validador1 = new ValidadorCuentaNula();
        ValidadorTransaccion validador2 = new ValidadorCuentaDestino();
        ValidadorTransaccion validador3 = new ValidadorSaldoSuficiente();

        validador1.setSiguiente(validador2);
        validador2.setSiguiente(validador3);

        this.validador = validador1;
    }

    @Override
    public void procesarTransaccion(Movimiento movimiento) {
        validador.validar(movimiento);

        // Si pasa la validación, realiza la transferencia
        CuentaBancaria origen = movimiento.getCuentaBancariaOrigen();
        CuentaBancaria destino = movimiento.getCuentaBancariaDestino();
        double monto = movimiento.getMonto();

        origen.retirarSaldo(monto);
        destino.agregarSaldo(monto);
    }
}
