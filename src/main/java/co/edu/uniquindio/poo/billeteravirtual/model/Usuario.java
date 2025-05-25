package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;

import java.util.ArrayList;

public class Usuario extends Perfil {

    private double saldoTotal;
    private ArrayList<Movimiento> historialTransacciones;
    private ArrayList<Presupuesto> listaPresupuestos;
    private ArrayList<CuentaBancaria> listaCuentasBancarias;

    /**
     * Constructor público de la clase Usuario que hereda de Perfil.
     * Inicializa el saldo total en 0 y las listas vacías para transacciones,
     * presupuestos y cuentas bancarias.
     *
     * @param id        Cédula del usuario
     * @param contrasenia Clave de acceso del usuario
     * @param nombre    Nombre y apellido del usuario
     * @param correo    Dirección de correo electrónico del usuario
     * @param telefono  Número de teléfono del usuario
     * @param direccion Dirección de residencia del usuario
     */
    public Usuario(String id, String contrasenia, String nombre, String correo, String telefono, String direccion){
        super(id, contrasenia, nombre, correo, telefono, direccion);
        this.saldoTotal = 0.0;
        historialTransacciones = new ArrayList<>();
        listaPresupuestos = new ArrayList<>();
        listaCuentasBancarias = new ArrayList<>();
    }

    /**
     * Calcula la suma del saldo de todas las cuentas bancarias asociadas
     * al usuario.
     *
     * @return suma del saldo de todas las cuentas bancarias
     */
    public double calcularSaldoTotal(){
        double total = 0;
        for (CuentaBancaria c : listaCuentasBancarias){
            total+=c.getSaldo();
        }
        return total;
    }

    /**
     * Actualiza el atributo saldoTotal con la suma de los saldos actuales
     * de las cuentas bancarias del usuario.
     */
    public void actualizarSaldoTotal(){
        this.saldoTotal = calcularSaldoTotal();
    }

    //====================GETTERS Y SETTERS=======================//

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public ArrayList<Movimiento> getHistorialTransacciones() {
        return historialTransacciones;
    }

    public void setHistorialTransacciones(ArrayList<Movimiento> historialTransacciones) {
        this.historialTransacciones = historialTransacciones;
    }

    public ArrayList<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(ArrayList<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public ArrayList<CuentaBancaria> getListaCuentasBancarias() {
        return listaCuentasBancarias;
    }

    public void setListaCuentasBancarias(ArrayList<CuentaBancaria> listaCuentasBancarias) {
        this.listaCuentasBancarias = listaCuentasBancarias;
    }

}

