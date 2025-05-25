package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;
import co.edu.uniquindio.poo.billeteravirtual.model.Identificable;

import java.time.LocalDate;
public class Movimiento implements Identificable {

    private final CuentaBancaria cuentaBancariaOrigen;
    private final CuentaBancaria cuentaBancariaDestino;
    private final String id;
    private final LocalDate fecha;
    private final double monto;
    private final String descripcionOpcional;
    private final Categoria categoriaOpcional;
    private final MovimientoStrategy estrategia;

    /**
     * Constructor privado de la clase Movimiento, para que pueda ser creado
     * mediante el Builder
     * @param builder
     */
    private Movimiento(Builder builder) {
        this.cuentaBancariaOrigen = builder.cuentaBancariaOrigen;
        this.cuentaBancariaDestino = builder.cuentaBancariaDestino;
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.monto = builder.monto;
        this.categoriaOpcional = builder.categoriaOpcional;
        this.descripcionOpcional = builder.descripcionOpcional;
        this.estrategia = builder.estrategia;
    }

    /**
     * Clase interna Builder para manejar la creación de movimientos
     * Permite la creación dinámica de objetos de este tipo mediante el director,
     * el cual solicita los atributos necesarios según se requiera (Transacción, Depósito,
     * Retiro)
     */
    public static class Builder {
        private CuentaBancaria cuentaBancariaOrigen;
        private CuentaBancaria cuentaBancariaDestino;
        private String id;
        private LocalDate fecha;
        private double monto;
        private String descripcionOpcional;
        private Categoria categoriaOpcional;
        private MovimientoStrategy estrategia;

        public Builder setCuentaBancariaOrigen(CuentaBancaria cuentaBancariaOrigen) {
            this.cuentaBancariaOrigen = cuentaBancariaOrigen;
            return this;
        }

        public Builder setCuentaBancariaDestino(CuentaBancaria cuentaBancariaDestino) {
            this.cuentaBancariaDestino = cuentaBancariaDestino;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setFecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder setMonto(double monto) {
            this.monto = monto;
            return this;
        }

        public Builder setDescripcionOpcional(String descripcionOpcional) {
            this.descripcionOpcional = descripcionOpcional;
            return this;
        }

        public Builder setCategoriaOpcional(Categoria categoriaOpcional) {
            this.categoriaOpcional = categoriaOpcional;
            return this;
        }

        public Builder setEstrategia(MovimientoStrategy estrategia) {
            this.estrategia = estrategia;
            return this;
        }

        public Movimiento build() {
            if (estrategia == null) throw new IllegalStateException("Estrategia requerida");
            return new Movimiento(this);
        }
    }

    public void procesarTransaccion() {
        estrategia.procesarTransaccion(this);
    }

    //===============GETTERS==================//

    public CuentaBancaria getCuentaBancariaOrigen() {
        return cuentaBancariaOrigen;
    }

    public CuentaBancaria getCuentaBancariaDestino() {
        return cuentaBancariaDestino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcionOpcional() {
        return descripcionOpcional;
    }

    public Categoria getCategoriaOpcional() {
        return categoriaOpcional;
    }

    public MovimientoStrategy getEstrategia() {
        return estrategia;
    }

    @Override
    public String getId() {
        return id;
    }
}
