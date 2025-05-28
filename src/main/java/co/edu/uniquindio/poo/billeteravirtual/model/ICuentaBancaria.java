package co.edu.uniquindio.poo.billeteravirtual.model;

/**
 * Interfaz que define las operaciones básicas que una cuenta bancaria debe implementar.
 * Sirve para ser usada por clases proxy o la cuenta bancaria real.
 */
public interface ICuentaBancaria {
    String getId();

    double getSaldo();
    void setSaldo(double saldo);

    String getNombreBanco();
    void setNombreBanco(String nombreBanco);

    TipoCuenta getTipoCuenta();
    void setTipoCuenta(TipoCuenta tipoCuenta);
}
