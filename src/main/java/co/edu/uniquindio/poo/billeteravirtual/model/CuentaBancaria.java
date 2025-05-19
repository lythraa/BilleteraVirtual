package co.edu.uniquindio.poo.billeteravirtual.model;

public class CuentaBancaria implements Identificable {
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