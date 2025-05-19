package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.model.gestores.*;

public class SistemaBilleteraFacade {
    //si no se hace nada con estos aca se pueden eliminar
    private final GestorUsuarios gestorUsuarios;
    private final GestorAdministradores gestorAdministradores;
    private final GestorTransacciones gestorTransacciones;
    private final GestorPresupuestos gestorPresupuestos;
    private final GestorCuentasBancarias gestorCuentasBancarias;

    public SistemaBilleteraFacade() {
        this.gestorAdministradores = GestorAdministradores.getInstancia();
        this.gestorTransacciones = GestorTransacciones.getInstancia();
        this.gestorCuentasBancarias = GestorCuentasBancarias.getInstancia();
        this.gestorUsuarios = GestorUsuarios.getInstancia();
        this.gestorPresupuestos = GestorPresupuestos.getInstancia();
    }

}
