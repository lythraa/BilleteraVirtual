package co.edu.uniquindio.poo.billeteravirtual.model.proxy;

import co.edu.uniquindio.poo.billeteravirtual.controller.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.TipoCuenta;
/**
 * Clase pensada para manejar la seguridad de las cuentas. Un usuario no puede cambiar el saldo por ejemplo
 */

public class CuentaBancariaProxy implements ICuentaBancaria {

    private final CuentaBancaria cuentaReal;

    public CuentaBancariaProxy(CuentaBancaria cuentaReal) {
        this.cuentaReal = cuentaReal;
    }

    public CuentaBancaria getCuentaBancariaReal() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (perfilActual instanceof Administrador) {
            return cuentaReal;
        } else {
            throw new RuntimeException("No tienes permiso para acceder a la cuenta real.");
        }
    }

    @Override
    public String getId() {
        return cuentaReal.getId();
    }

    @Override
    public double getSaldo() {
        System.out.println("Accediendo al saldo de la cuenta " + cuentaReal.getNumeroCuenta());
        return cuentaReal.getSaldo();
    }

    @Override
    public void setSaldo(double saldo) {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (!(perfilActual instanceof Administrador)) {
            throw new RuntimeException("No tienes permiso para modificar el saldo.");
        }
        cuentaReal.setSaldo(saldo);
    }

    @Override
    public String getNombreBanco() {
        return cuentaReal.getNombreBanco();
    }

    @Override
    public void setNombreBanco(String nombreBanco) {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (!(perfilActual instanceof Administrador)) {
            throw new RuntimeException("No tienes permiso para modificar el nombre del banco.");
        }
        cuentaReal.setNombreBanco(nombreBanco);
    }

    @Override
    public String getNumeroCuenta() {
        return cuentaReal.getNumeroCuenta();
    }

    @Override
    public void setNumeroCuenta(String numeroCuenta) {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (!(perfilActual instanceof Administrador)) {
            throw new RuntimeException("No tienes permiso para modificar el número de cuenta.");
        }
        cuentaReal.setNumeroCuenta(numeroCuenta);
    }

    @Override
    public TipoCuenta getTipoCuenta() {
        return cuentaReal.getTipoCuenta();
    }

    @Override
    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (!(perfilActual instanceof Administrador)) {
            throw new RuntimeException("No tienes permiso para modificar el tipo de cuenta.");
        }
        cuentaReal.setTipoCuenta(tipoCuenta);
    }

}
