package co.edu.uniquindio.poo.billeteravirtual.model.gestores;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

import java.util.ArrayList;

public class GestorPresupuestos extends GestorBaseCRUD<Presupuesto> {

    private static GestorPresupuestos instancia;

    private GestorPresupuestos() {
        super(new ArrayList<Presupuesto>());
    }

    public static GestorPresupuestos getInstancia() {
        if (instancia == null) {
            instancia = new GestorPresupuestos();
        }
        return instancia;
    }

}
