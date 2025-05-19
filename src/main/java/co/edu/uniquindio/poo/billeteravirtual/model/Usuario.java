package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.model.builder.Transaccion;

import java.util.ArrayList;

public class Usuario extends Perfil {

    private double saldoTotal;
    private ArrayList<Transaccion> historialTransacciones;
    private ArrayList<Presupuesto> listaPresupuestos;
    private ArrayList<CuentaBancaria> listaCuentasBancarias;

    /**
     * Constructor público de la clase Usuario
     * @param id Cédula del usuario
     * @param contrasenia Clave de acceso del usuario
     * @param nombre Nombre y apellido del usuario
     * @param correo Dirección de correo electrónico del usuario
     * @param telefono Número de teléfono del usuario
     * @param direccion Dirección de residencia del usuario
     */
    public Usuario(String id, String contrasenia, String nombre, String correo, String telefono, String direccion){
        super(id, contrasenia, nombre, correo, telefono, direccion);
        this.saldoTotal = 0.0;
        historialTransacciones = new ArrayList<>();
        listaPresupuestos = new ArrayList<>();
        listaCuentasBancarias = new ArrayList<>();
    }

    public double calcularSaldoTotal(){
        double total = 0;
        for (CuentaBancaria c : listaCuentasBancarias){
            total+=c.getSaldo();
        }
        return saldoTotal;
    }

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

    public ArrayList<Transaccion> getHistorialTransacciones() {
        return historialTransacciones;
    }

    public void setHistorialTransacciones(ArrayList<Transaccion> historialTransacciones) {
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

