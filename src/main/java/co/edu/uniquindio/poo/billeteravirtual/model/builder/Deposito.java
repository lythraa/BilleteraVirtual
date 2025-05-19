package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.CuentaBancaria;

import java.time.LocalDate;

public class Deposito extends Transaccion {
    private final CuentaBancaria cuentaDestino;

    public Deposito(String id, LocalDate fecha, double monto, String descripcionOpcional, Categoria categoria, CuentaBancaria cuentaDestino) {
        super(id, fecha, monto, descripcionOpcional, categoria);
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    protected void ejecutar() {
        //tiki
    }

    @Override
    public String getId() {
        return "";
    }
}
