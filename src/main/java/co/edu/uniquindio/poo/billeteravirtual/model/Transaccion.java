package co.edu.uniquindio.poo.billeteravirtual.model;

import java.time.LocalDate;

public class Transaccion {
    private String idTransaccion;
    private LocalDate fecha;
    private String tipo; // Depósito, Retiro, Transferencia
    private double monto;
    private String descripcion;
    private CuentaBancaria cuentaBancariaOrigen;
    private CuentaBancaria cuentaBancariaDestino;
    private Categoria categoria;

    public Transaccion(String idTransaccion, LocalDate fecha, String tipo, double monto, String descripcion,
                       CuentaBancaria cuentaBancariaOrigen, CuentaBancaria cuentaBancariaDestino, Categoria categoria) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.cuentaBancariaOrigen = cuentaBancariaOrigen;
        this.cuentaBancariaDestino = cuentaBancariaDestino;
        this.categoria = categoria;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CuentaBancaria getCuentaOrigen() {
        return cuentaBancariaOrigen;
    }

    public void setCuentaOrigen(CuentaBancaria cuentaBancariaOrigen) {
        this.cuentaBancariaOrigen = cuentaBancariaOrigen;
    }

    public CuentaBancaria getCuentaDestino() {
        return cuentaBancariaDestino;
    }

    public void setCuentaDestino(CuentaBancaria cuentaBancariaDestino) {
        this.cuentaBancariaDestino = cuentaBancariaDestino;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
