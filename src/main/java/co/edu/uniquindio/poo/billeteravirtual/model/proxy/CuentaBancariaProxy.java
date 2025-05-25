package co.edu.uniquindio.poo.billeteravirtual.model.proxy;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.TipoCuenta;


/**
 * Proxy para la clase CuentaBancaria que controla el acceso y modificación
 * de los datos sensibles de la cuenta, restringiendo acciones según el perfil
 * del usuario actual.
 */
public class CuentaBancariaProxy implements ICuentaBancaria {

    private final CuentaBancaria cuentaReal;


    /**
     * Constructor que recibe la cuenta bancaria real a proteger.
     * @param cuentaReal instancia real de CuentaBancaria
     */
    public CuentaBancariaProxy(CuentaBancaria cuentaReal) {
        this.cuentaReal = cuentaReal;
    }


    /**
     * Obtiene la cuenta real solo si el perfil actual es Administrador.
     * @return CuentaBancaria real
     * @throws RuntimeException si el perfil no tiene permiso
     */
    public CuentaBancaria getCuentaBancariaReal() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (perfilActual instanceof Administrador) {
            return cuentaReal;
        } else {
            throw new RuntimeException("No tienes permiso para acceder a la cuenta real.");
        }
    }

    //========================GETTERS AND SETTERS LÓGICA CON ACCESO=============================
    @Override
    public String getId() {
        return cuentaReal.getId();
    }

    @Override
    public double getSaldo() {
        System.out.println("Accediendo al saldo de la cuenta " + cuentaReal.getId());
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
