package co.edu.uniquindio.poo.billeteravirtual.model.gestores;

import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.CuentaBancaria;

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

    @Override
    public void agregar(Administrador administrador) {
        super.agregar(administrador);
    }

    @Override
    public void eliminar(Administrador administrador) {
        super.eliminar(administrador);
    }

    @Override
    public void reemplazar(String id, Administrador administrador) {
        super.reemplazar(id, administrador);
    }



}
