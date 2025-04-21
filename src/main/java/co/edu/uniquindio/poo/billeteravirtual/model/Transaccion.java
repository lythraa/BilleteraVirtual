package co.edu.uniquindio.poo.billeteravirtual.model;

import java.time.LocalDate;

public class Transaccion {
    private String idTransaccion;
    private LocalDate fecha;
    private String tipo;
    private double monto;
    private String descripcionOpcional;
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;
    private Categoria categoria;

    /**
     * Constructor de la clase Transaccion,
     * Un objeto de tipo Transaccion se construye mediante el Builder
     * @param builder
     */
    private Transaccion(Builder builder) {
        this.idTransaccion = builder.idTransaccion;
        this.fecha = builder.fecha;
        this.tipo = builder.tipo;
        this.monto = builder.monto;
        this.descripcionOpcional = builder.descripcionOpcional;
        this.cuentaOrigen = builder.cuentaOrigen;
        this.cuentaDestino = builder.cuentaDestino;
    }

    //=====================CLASE INTERNA BUILDER====================//

    public static class Builder {
        private String idTransaccion;
        private LocalDate fecha;
        private String tipo;
        private double monto;
        private String descripcionOpcional;
        private CuentaBancaria cuentaOrigen;
        private CuentaBancaria cuentaDestino;

        public Transaccion build() {
            return new Transaccion(this);
        }

        public Builder idTransaccion(String id) {
            this.idTransaccion = id;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder monto(double monto) {
            this.monto = monto;
            return this;
        }

        public Builder descripcion(String desc) {
            this.descripcionOpcional = desc;
            return this;
        }

        public Builder cuentaOrigen(CuentaBancaria origen) {
            this.cuentaOrigen = origen;
            return this;
        }

        public Builder cuentaDestino(CuentaBancaria destino) {
            this.cuentaDestino = destino;
            return this;
        }

    }

    //=====================GETTERS Y SETTERS====================//


    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcionOpcional() {
        return descripcionOpcional;
    }

    public void setDescripcionOpcional(String descripcionOpcional) {
        this.descripcionOpcional = descripcionOpcional;
    }

    public CuentaBancaria getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(CuentaBancaria cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public CuentaBancaria getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(CuentaBancaria cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
