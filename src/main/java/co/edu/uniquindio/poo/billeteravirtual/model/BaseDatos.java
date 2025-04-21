package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;

public class BaseDatos {

    private static BaseDatos instancia;

    private ArrayList<Perfil> perfiles;
    private ArrayList<CuentaBancaria> cuentasGlobales;
    private ArrayList<Transaccion> transaccionesGlobales;

    /**
     * Constructor privado de la clase para evitar múltiples instancias de la misma
     */
    private BaseDatos(){
        this.perfiles = new ArrayList<>();
        this.cuentasGlobales = new ArrayList<>();
        this.transaccionesGlobales = new ArrayList<>();
    }

    /**
     * Método para obtener la instancia única del gestor de datos (Singleton)
     * @return instance Instancia del gestor
     */
    public static BaseDatos getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatos();
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

}
