package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;

public class GestorDatos {

    private static GestorDatos instancia;
    private ArrayList<Perfil> perfiles;
    private ArrayList<CuentaBancaria> cuentasGlobales;
    private ArrayList<Transaccion> transaccionesGlobales;
    private ArrayList<Presupuesto> presupuestosGlobales;
    private ArrayList<Categoria> categorias;

    /**
     * Constructor privado de la clase para evitar múltiples instancias de la misma
     */
    private GestorDatos(){
        ArrayList<Usuario> perfiles = new ArrayList<>();
        ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
        ArrayList<Transaccion> transacciones = new ArrayList<>();
        ArrayList<Presupuesto> presupuestos = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();
    }

    /**
     * Método para obtener la instancia única del gestor de datos (Singleton)
     * @return instance Instancia del gestor
     */
    public static GestorDatos getInstancia() {
        if (instancia == null) {
            instancia = new GestorDatos();
        }
        return instancia;
    }

    //=========================GETTERS============================//


    public ArrayList<Perfil> getPerfiles() {
        return perfiles;
    }

    public ArrayList<CuentaBancaria> getCuentasGlobales() {
        return cuentasGlobales;
    }

    public ArrayList<Transaccion> getTransaccionesGlobales() {
        return transaccionesGlobales;
    }

    public ArrayList<Presupuesto> getPresupuestosGlobales() {
        return presupuestosGlobales;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
}
