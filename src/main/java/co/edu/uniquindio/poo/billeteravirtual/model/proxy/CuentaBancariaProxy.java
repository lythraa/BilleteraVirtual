package co.edu.uniquindio.poo.billeteravirtual.model.proxy;

import co.edu.uniquindio.poo.billeteravirtual.app.GestorSesion;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.TipoCuenta;
import co.edu.uniquindio.poo.billeteravirtual.model.exception.*;


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
     * @throws AccesoNoAutorizadoException si el perfil no tiene permiso
     */

    public CuentaBancaria getCuentaBancariaReal() {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (perfilActual instanceof Administrador) {
            return cuentaReal;
        } else {
            throw new AccesoNoAutorizadoException("No tienes permiso para acceder a la cuenta real.");
        }
    }

    //========================GETTERS AND SETTERS LÓGICA CON ACCESO=============================

    /**
     * Obtiene el identificador de la cuenta bancaria.
     *
     * @return id de la cuenta
     */
    @Override
    public String getId() {
        return cuentaReal.getId();
    }

    /**
     * Obtiene el saldo actual de la cuenta bancaria.
     *
     * @return saldo actual de la cuenta
     */
    @Override
    public double getSaldo() {
        System.out.println("Accediendo al saldo de la cuenta " + cuentaReal.getId());
        return cuentaReal.getSaldo();
    }

    /**
     * Establece el saldo de la cuenta bancaria.
     * Solo puede ser modificado por usuarios con perfil Administrador.
     *
     * @param saldo Nuevo saldo a establecer
     * @throws AccesoNoAutorizadoException si el perfil actual no tiene permisos para modificar el saldo
     * @throws IllegalArgumentException si el saldo es negativo
     */
    @Override
    public void setSaldo(double saldo) {
        if (saldo<0){
            throw new IllegalArgumentException("EL saldo no puede ser negativo");
        }
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (!(perfilActual instanceof Administrador)) {
            throw new AccesoNoAutorizadoException("No tienes permiso para modificar el saldo.");
        }
        cuentaReal.setSaldo(saldo);
    }

    /**
     * Obtiene el nombre del banco asociado a la cuenta.
     *
     * @return nombre del banco
     */
    @Override
    public String getNombreBanco() {
        return cuentaReal.getNombreBanco();
    }


    /**
     * Establece el nombre del banco asociado a la cuenta.
     * Solo puede ser modificado por usuarios con perfil Administrador.
     *
     * @param nombreBanco Nuevo nombre del banco
     * @throws RuntimeException si el perfil actual no tiene permisos para modificar el nombre del banco
     * @throws IllegalArgumentException si el nombre del banco es vacio
     */
    @Override
    public void setNombreBanco(String nombreBanco) {
        if (nombreBanco == null || nombreBanco.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del banco no puede estar vacio");
        }
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();
        if (!(perfilActual instanceof Administrador)) {
            throw new RuntimeException("No tienes permiso para modificar el nombre del banco.");
        }
        cuentaReal.setNombreBanco(nombreBanco);
    }

    /**
     * Obtiene el tipo de cuenta bancaria.
     *
     * @return tipo de cuenta
     */
    @Override
    public TipoCuenta getTipoCuenta() {
        return cuentaReal.getTipoCuenta();
    }

    /**
     * Establece el tipo de cuenta bancaria.
     * Solo puede ser modificado por usuarios con perfil Administrador.
     *
     * @param tipoCuenta Nuevo tipo de cuenta
     * @throws RuntimeException si el perfil actual no tiene permisos para modificar el tipo de cuenta
     * @throws IllegalArgumentException si el tipo de cuenta es nulo
     */
    @Override
    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        Perfil perfilActual = GestorSesion.getInstance().getPerfilActual();

        if (!(perfilActual instanceof Administrador)) {
            throw new RuntimeException("No tienes permiso para modificar el tipo de cuenta.");
        }

        if (tipoCuenta == null) {
            throw new IllegalArgumentException("El tipo de cuenta no puede ser null.");
        }
        cuentaReal.setTipoCuenta(tipoCuenta);
    }

}
