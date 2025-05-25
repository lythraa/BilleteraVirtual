package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility.ValidadorSaldoSuficiente;
import co.edu.uniquindio.poo.billeteravirtual.ChainOfResponsibility.ValidadorTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.app.UtilAlerta;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;


/**
 * Estrategia concreta que representa un movimiento de tipo retiro.
 * Implementa el patrón Strategy para aplicar la lógica de retirar saldo
 * de una cuenta bancaria, asegurando que la transacción sea válida
 * mediante la cadena de responsabilidad para validar saldo suficiente.
 */
public class RetiroStrategy implements MovimientoStrategy{

    private final ValidadorTransaccion validador;

    /**
     * Constructor que inicializa el validador de transacciones.
     * En este caso, se utiliza un validador que verifica si la cuenta
     * tiene saldo suficiente para realizar el retiro.
     */
    public RetiroStrategy() {
        // Construcción de la cadena de validación: actualmente sólo valida saldo suficiente.
        this.validador = new ValidadorSaldoSuficiente();
    }

    /**
     * Procesa la transacción de retiro. Primero valida la operación
     * usando la cadena de responsabilidad, luego retira el monto
     * de la cuenta origen y agrega el mismo monto a la cuenta destino.
     *
     * @param movimiento Movimiento que contiene la información de la transacción, incluyendo cuentas y monto.
     */
    @Override
    public void procesarTransaccion(Movimiento movimiento) {
        // Validar la transacción según las reglas definidas en la cadena
        validador.validar(movimiento);

        CuentaBancaria origen = movimiento.getCuentaBancariaOrigen();
        CuentaBancaria destino = movimiento.getCuentaBancariaDestino();

        double monto = movimiento.getMonto();

        origen.retirarSaldo(monto);
        destino.agregarSaldo(monto);
        UtilAlerta.mostrarAlertaInformacion(
                "Retiro exitoso",
                "El retiro de $" + monto + " con la ID " + movimiento.getId() + " se ha realizado correctamente.\n" +
                        "Presenta este código en la sucursal más cercana para reclamar el dinero: "+generarCodigoReclamo()
        );
    }

    /**
     * Genera un código alfanumérico corto de 6 caracteres para reclamo.
     * Usa letras mayúsculas y dígitos numéricos.
     *
     * @return Código aleatorio de 6 caracteres
     */
    private String generarCodigoReclamo() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int pos = (int) (Math.random() * chars.length());
            codigo.append(chars.charAt(pos));
        }
        return codigo.toString();
    }

}
