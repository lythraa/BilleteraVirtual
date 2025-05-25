package co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;

public class ValidadorCuentaDestino extends ValidadorTransaccion {
    @Override
    protected void realizarValidacion(Movimiento movimiento) {
        //Completar, debería buscar que la cuenta de destino exista dentro de la lista
        //de cuentas globales de la aplicación
    }
}
