package co.edu.uniquindio.poo.billeteravirtual.model.gestores;

import co.edu.uniquindio.poo.billeteravirtual.model.*;

import java.util.ArrayList;

/**
 * Gestor singleton para administrar los usuarios de la billetera virtual.
 * Proporciona operaciones CRUD básicas heredadas de GestorBaseCRUD.
 */
public class GestorUsuarios extends GestorBaseCRUD<Usuario> {

    private static GestorUsuarios instancia;

    /**
     * Constructor privado para evitar la creación directa de instancias.
     * Inicializa la lista interna de usuarios vacía.
     */
    private GestorUsuarios() {
        super(new ArrayList<Usuario>());
    }

    /**
     * Obtiene la instancia única del gestor de usuarios.
     *
     * @return la instancia única de GestorUsuarios
     */
    public static synchronized GestorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuarios(); // Si la instancia es null, crea una nueva.
        }
        return instancia;
    }
}
