package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.model.gestores.*;
/**
 * Fachada que simplifica la interacción con los diferentes gestores
 * del sistema de la billetera virtual, centralizando operaciones comunes.
 */
public class SistemaBilleteraFacade {
    private final GestorUsuarios gestorUsuarios;
    private final GestorAdministradores gestorAdministradores;
    private final GestorTransacciones gestorTransacciones;
    private final GestorCuentasBancarias gestorCuentasBancarias;

    /**
     * Constructor que inicializa los gestores utilizando el patrón Singleton.
     */
    public SistemaBilleteraFacade() {
        this.gestorAdministradores = GestorAdministradores.getInstancia();
        this.gestorTransacciones = GestorTransacciones.getInstancia();
        this.gestorCuentasBancarias = GestorCuentasBancarias.getInstancia();
        this.gestorUsuarios = GestorUsuarios.getInstancia();
    }

    /**
     * Método para procesar una transacción delegando la responsabilidad
     * al gestor correspondiente.
     * (Pendiente de implementación)
     */

    // TODO: añadir método procesarTransaccion()

    /**
     * Método para manejar la lógica relacionada con categorías
     * (Pendiente de implementación)
     */

    // TODO: añadir lógica para gestión de categorías

}
