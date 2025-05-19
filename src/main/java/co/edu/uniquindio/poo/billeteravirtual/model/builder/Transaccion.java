package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.Identificable;

import java.time.LocalDate;
public class Transaccion implements Identificable {
    private String id;
    private LocalDate fecha;
    private double monto;
    private String descripcionOpcional;
    private Categoria categoria;

    protected Transaccion(Builder builder) {
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.monto = builder.monto;
        this.descripcionOpcional = builder.descripcionOpcional;
        this.categoria = builder.categoria;
    }

    public static class Builder{
        private String id;
        private LocalDate fecha;
        private double monto;
        private String descripcionOpcional;
        private Categoria categoria;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder monto(double monto) {
            this.monto = monto;
            return this;
        }

        public Builder descripcionOpcional(String descripcionOpcional) {
            this.descripcionOpcional = descripcionOpcional;
            return this;
        }

        public Builder categoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }
        public abstract T build();
    }


    public final void procesarTransaccion() {
        try {
            validarMonto();
            ejecutar();
            registrar();
        } catch (Exception e) {
            //revisar si sacarlo como alerta
            throw new RuntimeException(e);
        }

    }

    protected void validarMonto() {
        //cuando el if es de una linea no necesita llaves
        //revisar si sacarlo como alerta
        if (monto <= 0) throw new IllegalArgumentException("Monto invalido");
    }

    protected abstract void ejecutar();

    protected void registrar() {
        System.out.println("transacción registrada");
    }

}
