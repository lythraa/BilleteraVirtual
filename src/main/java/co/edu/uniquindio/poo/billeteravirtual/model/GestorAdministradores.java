package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;


/**
 * Gestor Singleton para administrar objetos Administrador.
 * Extiende GestorBaseCRUD para heredar operaciones CRUD básicas.
 */
public class GestorAdministradores extends GestorBaseCRUD<Administrador> {
    private static GestorAdministradores instancia;

    private GestorAdministradores() {
        super(new ArrayList<>());
    }

    /**
     * Obtiene la instancia única de GestorAdministradores (Singleton).
     * @return instancia de GestorAdministradores
     */
    public static synchronized GestorAdministradores getInstancia() {
        if (instancia == null) {
            instancia = new GestorAdministradores();
        }
        return instancia;
    }
}
