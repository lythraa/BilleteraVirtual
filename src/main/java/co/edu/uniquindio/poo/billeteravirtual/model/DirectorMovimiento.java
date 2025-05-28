package co.edu.uniquindio.poo.billeteravirtual.model;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

/**
 * Clase directora que se encarga de construir objetos Movimiento usando
 * el patrón Builder.
 *
 * Dependiendo del tipo de movimiento (depósito, retiro, transferencia),
 * construye un Movimiento con las propiedades y la estrategia apropiada.
 */
public class DirectorMovimiento {

    /**
     * Crea un Movimiento de tipo Depósito.
     * @param cuentaDestino Cuenta a la cual se realizará el depósito,
     *                      corresponde al parámetro cuentaOrigen de la clase
     *                      Movimiento, pues se refiera a la cuenta de quien ejecuta
     *                      la aplicación actualmente.
     * @param monto monto deseado a depositar en la cuenta
     * @param categoria categoría opcional del movimiento
     * @param descripcion descripción opcional del depósito
     * @return Movimiento del tipo Depósito con una estrategia definida DepositoStrategy
     */
    public Movimiento crearDeposito(CuentaBancaria cuentaDestino, double monto, @Nullable Categoria categoria, @Nullable String descripcion) {

        Movimiento.Builder builder = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaDestino) // En depósito la cuenta destino es tratada como origen
                .setId(generarCodigoId())
                .setFecha(LocalDate.now())
                .setMonto(monto)
                .setEstrategia(new DepositoStrategy());

        if (categoria != null) {
            builder.setCategoriaOpcional(categoria);
        }

        if (descripcion != null) {
            builder.setDescripcionOpcional(descripcion);
        }

        return builder.build();
    }

    /**
     * Crea un Movimiento de tipo Retiro.
     *
     * @param cuentaOrigen Cuenta desde la cual se realizará el retiro.
     * @param monto Monto que se retirará de la cuenta.
     * @param categoria Categoría opcional que clasifica el movimiento.
     * @param descripcion Descripción opcional del movimiento.
     * @return Objeto Movimiento configurado para un retiro, con la estrategia RetiroStrategy.
     */
    public Movimiento crearRetiro(CuentaBancaria cuentaOrigen, double monto, @Nullable Categoria categoria, @Nullable String descripcion) {

        Movimiento.Builder builder = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setId(generarCodigoId())
                .setFecha(LocalDate.now())
                .setMonto(monto)
                .setEstrategia(new RetiroStrategy());

        if (categoria != null) {
            builder.setCategoriaOpcional(categoria);
            // Nota: se podría validar que no se exceda el presupuesto asignado en la categoría
        }

        if (descripcion != null) {
            builder.setDescripcionOpcional(descripcion);
        }

        return builder.build();

    }

    /**
     * Crea un Movimiento de tipo Transferencia.
     *
     * @param cuentaOrigen Cuenta desde la cual se transferirá el monto.
     * @param cuentaDestino Cuenta que recibirá la transferencia.
     * @param monto Monto a transferir.
     * @param categoria Categoría opcional que clasifica el movimiento.
     * @param descripcion Descripción opcional del movimiento.
     * @return Objeto Movimiento configurado para una transferencia, con la estrategia TransferenciaStrategy.
     */
    public Movimiento crearTransferencia(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double monto, @Nullable Categoria categoria, @Nullable String descripcion) {

        Movimiento.Builder builder = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setCuentaBancariaDestino(cuentaDestino)
                .setId(generarCodigoId())
                .setFecha(LocalDate.now())
                .setMonto(monto)
                .setEstrategia(new TransferenciaStrategy());

        if (categoria != null) {
            builder.setCategoriaOpcional(categoria);
        }

        if (descripcion != null) {
            builder.setDescripcionOpcional(descripcion);
        }

        return builder.build();

    }

    /**
     * Genera un código alfanumérico corto de 6 caracteres para identificar las trnsacciones.
     * Usa letras mayúsculas y dígitos numéricos.
     *
     * @return Código aleatorio de 6 caracteres
     */
    private String generarCodigoId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int pos = (int) (Math.random() * chars.length());
            codigo.append(chars.charAt(pos));
        }
        return codigo.toString();
    }
}

