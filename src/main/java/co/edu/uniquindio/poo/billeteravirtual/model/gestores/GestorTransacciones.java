package co.edu.uniquindio.poo.billeteravirtual.model.gestores;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

import java.util.ArrayList;

public class GestorTransacciones extends GestorBaseCRUD<Movimiento> {

    private static GestorTransacciones instancia;

    private GestorTransacciones() {
        super(new ArrayList<Movimiento>());
    }

    public static GestorTransacciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorTransacciones();
        }
        return instancia;
    }

}
