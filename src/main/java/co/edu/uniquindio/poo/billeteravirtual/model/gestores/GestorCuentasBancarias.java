package co.edu.uniquindio.poo.billeteravirtual.model.gestores;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

import java.util.ArrayList;

public class GestorCuentasBancarias extends GestorBaseCRUD<CuentaBancaria> {
    private static GestorCuentasBancarias instancia;

    private GestorCuentasBancarias() {
        super(new ArrayList<CuentaBancaria>());
    }

    public static GestorCuentasBancarias getInstancia() {
        if (instancia == null) {
            instancia = new GestorCuentasBancarias();
        }
        return instancia;
    }

    @Override
    public void agregar(CuentaBancaria cuentaBancaria) {
        super.agregar(cuentaBancaria);
    }

    @Override
    public void eliminar(CuentaBancaria cuentaBancaria) {
        super.eliminar(cuentaBancaria);
    }

    @Override
    public void reemplazar(String id, CuentaBancaria cuentaBancaria) {
        super.reemplazar(id, cuentaBancaria);
    }


}
