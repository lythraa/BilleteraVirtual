package co.edu.uniquindio.poo.billeteravirtual.model.proxy;

import co.edu.uniquindio.poo.billeteravirtual.model.TipoCuenta;

public interface ICuentaBancaria {
    String getId();

    double getSaldo();
    void setSaldo(double saldo);

    String getNombreBanco();
    void setNombreBanco(String nombreBanco);

    TipoCuenta getTipoCuenta();
    void setTipoCuenta(TipoCuenta tipoCuenta);
}
