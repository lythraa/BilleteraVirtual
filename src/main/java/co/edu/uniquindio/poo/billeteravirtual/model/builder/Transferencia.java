package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.CuentaBancaria;

import java.time.LocalDate;

public class Transferencia extends Transaccion {
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;


    public Transferencia(String id, LocalDate fecha, double monto, String descripcionOpcional, Categoria categoria, CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino) {
        super(id, fecha, monto, descripcionOpcional, categoria);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    protected void ejecutar() {

    }

    @Override
    public String getId() {
        return "";
    }
}
