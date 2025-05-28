package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;

/**
 * Gestor singleton que administra las cuentas bancarias.
 * Sobrescribe el método buscar para devolver un proxy de la cuenta bancaria,
 * permitiendo control de acceso y seguridad.
 */
public class GestorCuentasBancarias extends GestorBaseCRUD<CuentaBancaria> {
    private static GestorCuentasBancarias instancia;

    private GestorCuentasBancarias() {
        super(new ArrayList<CuentaBancaria>());
    }

    /**
     * Obtiene la instancia única de GestorCuentasBancarias (Singleton).
     * @return instancia de GestorCuentasBancarias
     */
    public static synchronized GestorCuentasBancarias getInstancia() {
        if (instancia == null) {
            instancia = new GestorCuentasBancarias();
        }
        return instancia;
    }

    /**
     * Busca una cuenta bancaria por ID y devuelve un proxy de la misma.
     * El proxy controla el acceso a la cuenta para respetar las reglas de seguridad.
     *
     * @param id ID de la cuenta bancaria a buscar
     * @return proxy de la cuenta bancaria o null si no se encuentra
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
