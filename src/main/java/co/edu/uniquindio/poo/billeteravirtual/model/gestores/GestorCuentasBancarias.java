package co.edu.uniquindio.poo.billeteravirtual.model.gestores;
import co.edu.uniquindio.poo.billeteravirtual.model.Perfil;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancariaProxy;

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
    /**
     * Sobrescribe el método buscar para devolver siempre un proxy.
     * @param id ID de la cuenta
     * @return ICuentaBancaria (proxy)
     */
    @Override
    public CuentaBancaria buscar(String id) {
        CuentaBancaria cuenta = super.buscar(id);
        if (cuenta != null) {
            return new CuentaBancariaProxy(cuenta).getCuentaBancariaReal();
        }
        return null;
    }
}
