package co.edu.uniquindio.poo.billeteravirtual.util;

import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorAdministradores;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorUsuarios;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase GestorSesion que implementa el patrón Singleton para manejar la sesión actual de un perfil (usuario o administrador).
 * Permite iniciar y cerrar sesión, y obtener el perfil actualmente autenticado.
 */
public class GestorSesion {

    private static GestorSesion instance;
    private Perfil perfilActual;

    /**
     * Constructor privado para evitar la creación directa de instancias.
     * Garantiza que solo exista una instancia única (Singleton).
     */

    private GestorSesion() {}

    /**
     * Obtiene la instancia única de GestorSesion.
     * Si no existe, la crea.
     *
     * @return instancia única de GestorSesion
     */

    public static GestorSesion getInstance() {
        if (instance == null) {
            instance = new GestorSesion();
        }
        return instance;
    }

    /**
     * Intenta iniciar sesión con el ID y contraseña dados.
     * Busca en las listas de usuarios y administradores para validar las credenciales.
     * Si se encuentra un perfil válido, se asigna como perfilActual.
     *
     * @param id Identificador del perfil (usuario o administrador)
     * @param contrasenia Contraseña correspondiente al perfil
     * @return true si el inicio de sesión fue exitoso, false en caso contrario
     */
    public boolean iniciarSesion(String id, String contrasenia) {
        List<Perfil> perfiles = new ArrayList<>();
        List<Usuario> usuarios = GestorUsuarios.getInstancia().getListaObjetos();
        List<Administrador> administradores = GestorAdministradores.getInstancia().getListaObjetos();
        perfiles.addAll(usuarios);
        perfiles.addAll(administradores);

        for (Perfil perfil : perfiles) {
            if (id.equals(perfil.getId()) && contrasenia.equals(perfil.getContrasenia())) {
                this.perfilActual =perfil;
                return true;
            }
        }
        return false;
    }

    /**
     * Cierra la sesión actual, estableciendo el perfilActual en null.
     * Esto implica que no hay ningún perfil autenticado en la aplicación.
     */

    public void cerrarSesion() {
        this.perfilActual = null;
    }

    //=========================GETTERS============================//

    /**
     * Obtiene el perfil actualmente autenticado en la sesión.
     *
     * @return perfilActual Perfil en sesión o null si no hay sesión activa
     */
    public Perfil getPerfilActual() {
        return perfilActual;
    }

}
