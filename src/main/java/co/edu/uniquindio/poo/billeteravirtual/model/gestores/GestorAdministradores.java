package co.edu.uniquindio.poo.billeteravirtual.model.gestores;

import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;

import java.util.ArrayList;

public class GestorAdministradores extends GestorBaseCRUD<Administrador> {
    private static GestorAdministradores instancia;

    private GestorAdministradores() {
        super(new ArrayList<Administrador>());
    }

    public static GestorAdministradores getInstancia() {
        if (instancia == null) {
            instancia = new GestorAdministradores();
        }
        return instancia;
    }

}
