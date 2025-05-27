package co.edu.uniquindio.poo.billeteravirtual.model.gestores;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

import java.util.ArrayList;

/**
 * Gestor singleton que administra las transacciones (Movimientos).
 * Implementa operaciones CRUD básicas heredadas de GestorBaseCRUD.
 */
public class GestorMovimientos extends GestorBaseCRUD<Movimiento> {

    private static GestorMovimientos instancia;

    /**
     * Constructor privado para evitar la creación directa de instancias.
     * Inicializa la lista interna de movimientos vacía.
     */
    private GestorMovimientos() {
        super(new ArrayList<Movimiento>());
    }

    /**
     * Obtiene la instancia única del gestor de transacciones.
     *
     * @return instancia única del gestor de transacciones
     */
    public static synchronized GestorMovimientos getInstancia() {
        if (instancia == null) {
            instancia = new GestorMovimientos();
        }
        return instancia;
    }

}
