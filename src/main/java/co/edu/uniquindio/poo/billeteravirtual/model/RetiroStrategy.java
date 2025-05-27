package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;


/**
 * Estrategia concreta que representa un movimiento de tipo retiro.
 * Implementa el patrón Strategy para aplicar la lógica de retirar saldo
 * de una cuenta bancaria, asegurando que la transacción sea válida
 * mediante la cadena de responsabilidad para validar saldo suficiente.
 */
public class RetiroStrategy implements MovimientoStrategy{

    /**
     * Constructor que inicializa el validador de transacciones.
     * En este caso, se utiliza un validador que verifica si la cuenta
     * tiene saldo suficiente para realizar el retiro.
     */
    public RetiroStrategy() {

    }

    /**
     * Procesa la transacción de retiro. Primero válida la operación
     * usando la cadena de responsabilidad, luego retira el monto
     * de la cuenta origen y agrega el mismo monto a la cuenta destino.
     *
     * @param movimiento Movimiento que contiene la información de la transacción, incluyendo cuentas y monto.
     */
    @Override
    public void procesarTransaccion(Movimiento movimiento) {
        CuentaBancaria origen = movimiento.getCuentaBancariaOrigen();

        double monto = movimiento.getMonto();

        origen.retirarSaldo(monto);
        UtilAlerta.mostrarAlertaInformacion(
                "Retiro exitoso",
                "El retiro de $" + monto + " con la ID " + movimiento.getId() + " se ha realizado correctamente.\n" +
                        "Presenta este código en la sucursal más cercana para reclamar el dinero: "+SistemaBilleteraFacade.getInstancia().generarCodigoId()
        );
    }

}
