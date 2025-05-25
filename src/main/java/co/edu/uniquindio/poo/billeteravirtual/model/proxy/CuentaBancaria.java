package co.edu.uniquindio.poo.billeteravirtual.model.proxy;

import co.edu.uniquindio.poo.billeteravirtual.model.Identificable;
import co.edu.uniquindio.poo.billeteravirtual.model.TipoCuenta;

public class CuentaBancaria implements Identificable, ICuentaBancaria {
    private String id;
    private double saldo;
    private String nombreBanco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta; // Ahorros o Corriente

    public CuentaBancaria(String id, double saldo, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        this.id = id;
        this.saldo = saldo;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public void agregarSaldo(double monto){
        setSaldo(getSaldo() + monto);
    }

    public void retirarSaldo(double monto){
        setSaldo(getSaldo() - monto);
    }

    //======================GETTERS Y SETTERS=========================//


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}