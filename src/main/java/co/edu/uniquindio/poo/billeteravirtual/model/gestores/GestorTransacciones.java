package co.edu.uniquindio.poo.billeteravirtual.model.gestores;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Transaccion;

import java.util.ArrayList;

public class GestorTransacciones extends GestorBaseCRUD<Transaccion> {

    private static GestorTransacciones instancia;

    private GestorTransacciones() {
        super(new ArrayList<Transaccion>());
    }

    public static GestorTransacciones getInstancia() {
        if (instancia == null) {
            instancia = new GestorTransacciones();
        }
        return instancia;
    }

    @Override
    public void agregar(Transaccion transaccion) {
        super.agregar(transaccion);
    }

    @Override
    public void eliminar(Transaccion transaccion) {
        super.eliminar(transaccion);
    }

    @Override
    public void reemplazar(String id, Transaccion nuevaTransaccion) {
        super.reemplazar(id, nuevaTransaccion);
    }

}
