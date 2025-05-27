package co.edu.uniquindio.poo.billeteravirtual.model.ChainOfResponsibility;

import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;
import co.edu.uniquindio.poo.billeteravirtual.model.gestores.GestorCuentasBancarias;

/**
 * Validador concreto dentro de la cadena de responsabilidad que verifica si la cuenta bancaria
 * destino existe en el sistema antes de realizar una transacción.
 * Lanza una excepción si la cuenta no existe.
 */
public class ValidadorCuentaDestino extends ValidadorMovimiento {

    /**
     * Realiza la validación de la cuenta bancaria destino en una transacción.
     * Si la cuenta no existe, lanza una excepción. Si existe, muestra una alerta informativa indicando que la validación fue exitosa.
     *
     * @param movimiento Movimiento a validar que contiene la cuenta bancaria destino
     * @throws IllegalArgumentException si la cuenta bancaria no existe con el numero de cuenta asignado
     */
    @Override
    protected void realizarValidacion(Movimiento movimiento) {
        if (GestorCuentasBancarias.getInstancia().buscar(movimiento.getCuentaBancariaDestino().getId()) == null){
            throw new IllegalArgumentException("No existe una cuenta bancaria con el numero de cuenta ingresado.");
        }
        UtilAlerta.mostrarAlertaInformacion(
                "Cuenta válida",
                "✅ La cuenta bancaria destino fue encontrada correctamente en el sistema."
        );
    }
}
