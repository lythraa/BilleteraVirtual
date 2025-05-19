package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.CuentaBancaria;

import java.time.LocalDate;

public class Retiro extends Transaccion {
    private CuentaBancaria cuentaOrigen;

    public static class Builder extends Transaccion.Builder{

    }

    public static class Builder extends Transaccion.Builder<Retiro> {
        private CuentaBancaria cuentaOrigen;

        public Builder cuentaOrigen(CuentaBancaria cuenta) {
            this.cuentaOrigen = cuenta;
            return this;
        }

        @Override
        public Retiro build() {
            return new Retiro(this);
        }
    }

    @Override
    protected void ejecutar() {

    }

    @Override
    public String getId() {
        return "";
    }
}
