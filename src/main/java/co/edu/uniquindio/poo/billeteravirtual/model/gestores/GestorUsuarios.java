package co.edu.uniquindio.poo.billeteravirtual.model.gestores;

import co.edu.uniquindio.poo.billeteravirtual.model.*;

import java.util.ArrayList;

public class GestorUsuarios extends GestorBaseCRUD<Usuario> {

    private static GestorUsuarios instancia;

    private GestorUsuarios() {
        super(new ArrayList<Usuario>());
    }

    public static GestorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuarios(); // Si la instancia es null, crea una nueva.
        }
        return instancia;
    }

    @Override
    public void agregar(Usuario usuario) {
        super.agregar(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        super.eliminar(usuario);
    }

    @Override
    public void reemplazar(String id, Usuario nuevoUsuario) {
        super.reemplazar(id, nuevoUsuario);
    }
}
