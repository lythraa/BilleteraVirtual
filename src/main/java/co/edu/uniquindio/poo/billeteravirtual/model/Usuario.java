package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String direccion;
    private double saldo;

    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Transaccion> transacciones = new ArrayList<>();
    private List<Presupuesto> presupuestos = new ArrayList<>();

    protected Usuario(Builder builder) {
        this.idUsuario = builder.idUsuario;
        this.nombreCompleto = builder.nombreCompleto;
        this.correo = builder.correo;
        this.telefono = builder.telefono;
        this.direccion = builder.direccion;
        this.saldo = 0.0;
    }

    public static class Builder {
        private String idUsuario;
        private String nombreCompleto;
        private String correo;
        private String telefono;
        private String direccion;

        public Builder(String idUsuario, String nombreCompleto, String correo) {
            this.idUsuario = idUsuario;
            this.nombreCompleto = nombreCompleto;
            this.correo = correo;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    public void modificarPerfil(String nombre, String correo, String telefono) {
        this.nombreCompleto = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta) {
        cuentas.remove(cuenta);
    }

    public void agregarPresupuesto(Presupuesto presupuesto) {
        presupuestos.add(presupuesto);
    }

    public void eliminarPresupuesto(Presupuesto presupuesto) {
        presupuestos.remove(presupuesto);
    }

    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public void retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
        }
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public boolean transferir(Usuario destino, double monto) {
        if (monto <= saldo) {
            this.retirar(monto);
            destino.depositar(monto);
            return true;
        }
        return false;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(List<Presupuesto> presupuestos) {
        this.presupuestos = presupuestos;
    }
}
