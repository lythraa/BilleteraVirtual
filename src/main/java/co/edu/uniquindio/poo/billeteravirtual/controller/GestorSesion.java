package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;

import java.util.ArrayList;

public class GestorSesion {

    private static GestorSesion instance;
    private Perfil perfilActual;

    /**
     * Constructor privado de la clase para evitar múltiples instancias de la misma
     */
    private GestorSesion() {}

    /**
     * Método para obtener la instancia única del gestor de sesión (Singleton)
     * @return instance Instancia del gestor
     */
    public static GestorSesion getInstance() {
        if (instance == null) {
            instance = new GestorSesion();
        }
        return instance;
    }

    /**
     * Método que permite iniciar sesión.
     * Al recibir el perfil que ingresó a la aplicación, se
     * asigna como perfil actual de la aplicación.
     * @param id id del perfil
     * @param contrasenia contrasenia del perfil
     * @return true en caso de iniciar sesion
     */
    public boolean iniciarSesion(String id, String contrasenia) {
        ArrayList<Perfil> perfiles = BaseDatos.getInstancia().getPerfiles();
        for (Perfil perfil : perfiles) {
            if (id.equals(perfil.getId()) && contrasenia.equals(perfil.getContrasenia())) {
                this.perfilActual =perfil;
                return true;
            }
        }
        return false;
    }

    /**
     * Método que cierra la sesión de perfil.
     * Al ser llamado, el perfil actual regresará a ser nulo
     * hasta que se inicie una nueva sesión.
     */
    public void cerrarSesion() {
        this.perfilActual = null;
    }

    //=========================GETTERS============================//

    /**
     * Método para obtener el perfil actualmente en sesión
     *
     * @return
     */
    public Perfil getPerfilActual() {
        return perfilActual;
    }

}
