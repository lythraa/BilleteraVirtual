package co.edu.uniquindio.poo.billeteravirtual.model;

public class Cuenta {
    private String idCuenta;
    private String nombreBanco;
    private String numeroCuenta;
    private String tipoCuenta; // Ahorros o Corriente

    public Cuenta(String idCuenta, String nombreBanco, String numeroCuenta, String tipoCuenta) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
    }
}
