package co.edu.uniquindio.poo.billeteravirtual.model;

public class CuentaBancaria {
    private String idCuenta;
    private String nombreBanco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta; // Ahorros o Corriente

    public CuentaBancaria(String idCuenta, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
    }



}
