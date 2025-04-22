package co.edu.uniquindio.poo.billeteravirtual.model.gestores;

import co.edu.uniquindio.poo.billeteravirtual.model.*;

import java.util.ArrayList;

public class GestorUsuarios extends GestorBaseCRUD<Usuario> {

    private static GestorUsuarios instancia;

    /**
     * Constructor privado para evitar la creación de instancias desde fuera de la clase.
     * Utiliza el método estático filtrarUsuarios() para extraer solo los objetos de tipo Usuario
     * desde la lista general de perfiles en la base de datos.
     */
    private GestorUsuarios() {
        super(filtrarUsuarios()); // Llama al constructor de la clase base
    }

    /**
     * Método estático que devuelve la instancia única de la clase GestorUsuarios.
     * Si la instancia no ha sido creada, la crea. Si ya existe, devuelve la instancia existente.
     *
     * @return La única instancia de GestorUsuarios.
     */
    public static GestorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuarios(); // Si la instancia es null, crea una nueva.
        }
        return instancia;
    }

    /**
     * Método auxiliar que obtiene todos los objetos de tipo Usuario almacenados en la lista de perfiles
     * de la base de datos. Se marca como static porque se necesita usar antes de que se construya
     * completamente el objeto GestorUsuarios, específicamente al llamar al constructor de la clase base.
     *
     * Al ser static, puede ejecutarse sin necesidad de que exista una instancia de GestorUsuarios.
     */
    public static ArrayList<Usuario> filtrarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (Perfil perfil : BaseDatos.getInstancia().getPerfiles()) {
            if (perfil instanceof Usuario) {
                usuarios.add((Usuario) perfil);
            }
        }
        return usuarios;
    }

    /**
     * Método agregar personalizado del gestor de usuarios,
     * permite actualizar la lista global simultáneamente
     * @param usuario objeto a agregar
     */
    @Override
    public void agregar(Usuario usuario) {
        super.agregar(usuario);
        if (!BaseDatos.getInstancia().getPerfiles().contains(usuario)) {
            BaseDatos.getInstancia().getPerfiles().add(usuario);
        }
    }

    /**
     * Elimina un usuario tanto de la lista local como de la global
     */
    @Override
    public void eliminar(Usuario usuario) {
        super.eliminar(usuario);
        BaseDatos.getInstancia().getPerfiles().remove(usuario);
    }

    /**
     * Reemplaza un usuario tanto en la lista local como en la global
     */
    @Override
    public void reemplazar(String id, Usuario nuevoUsuario) {
        Usuario existente = buscar(id);
        if (existente != null) {
            super.reemplazar(id, nuevoUsuario);

            ArrayList<Perfil> perfiles = BaseDatos.getInstancia().getPerfiles();
            int index = perfiles.indexOf(existente);
            if (index != -1) {
                perfiles.set(index, nuevoUsuario);
            }
        }
    }
}
