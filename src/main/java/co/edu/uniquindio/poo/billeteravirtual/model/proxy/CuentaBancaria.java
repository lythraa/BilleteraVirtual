package co.edu.uniquindio.poo.billeteravirtual.model.proxy;

import co.edu.uniquindio.poo.billeteravirtual.model.Identificable;
import co.edu.uniquindio.poo.billeteravirtual.model.TipoCuenta;

public class CuentaBancaria implements Identificable, ICuentaBancaria {
    private String id;
    private double saldo;
    private String nombreBanco;
    private TipoCuenta tipoCuenta;

    /**
     * Constructor de CuentaBancaria
     * @param id Identificador único de la cuenta
     * @param saldo Saldo inicial de la cuenta
     * @param nombreBanco Nombre del banco asociado
     * @param tipoCuenta Tipo de cuenta: AHORRO o CORRIENTE
     */
    public CuentaBancaria(String id, double saldo, String nombreBanco, TipoCuenta tipoCuenta) {
        this.id = id;
        this.saldo = saldo;
        this.nombreBanco = nombreBanco;
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Agrega un monto al saldo actual
     * @param monto Cantidad a sumar
     * @throws IllegalArgumentException si el monto es negativo
     */
    public void agregarSaldo(double monto){
        if (monto < 0){
            throw new IllegalArgumentException("Monto negativo");
        }
        setSaldo(getSaldo() + monto);
    }

    /**
     * Resta un monto al saldo actual
     * @param monto Cantidad a retirar
     * @throws IllegalArgumentException si el monto es negativo o si sobrepasa el saldo actual de la cuenta
     */
    public void retirarSaldo(double monto){
        if (monto < 0 || monto > saldo){
            throw new IllegalArgumentException("Monto negativo o Monto sobrepasa el saldo actual de la cuenta");
        }
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

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}