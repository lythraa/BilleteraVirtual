package co.edu.uniquindio.poo.billeteravirtual.model.gestores;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

import java.util.ArrayList;

/**
 * Gestor singleton que administra las transacciones (Movimientos).
 * Implementa operaciones CRUD básicas heredadas de GestorBaseCRUD.
 */
public class GestorTransacciones extends GestorBaseCRUD<Movimiento> {

    private static GestorTransacciones instancia;

    /**
     * Constructor privado para evitar la creación directa de instancias.
     * Inicializa la lista interna de movimientos vacía.
     */
    private GestorTransacciones() {
        super(new ArrayList<Movimiento>());
    }

    /**
     * Obtiene la instancia única del gestor de transacciones.
     *
     * @return instancia única del gestor de transacciones
     */
    public static synchronized GestorTransacciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorTransacciones();
        }
        return instancia;
    }

}
