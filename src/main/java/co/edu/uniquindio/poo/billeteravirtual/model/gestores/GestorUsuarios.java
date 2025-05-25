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
}
