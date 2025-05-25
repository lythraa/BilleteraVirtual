package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;
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
     * @param id id del movimiento
     * @param monto monto deseado a depositar en la cuenta
     * @param categoria categoría opcional del movimiento
     * @param descripcion descripción opcional del depósito
     * @return Movimiento del tipo Depósito con una estrategia definida DepositoStrategy
     */
    public Movimiento crearDeposito(CuentaBancaria cuentaDestino, String id, double monto, @Nullable Categoria categoria, @Nullable String descripcion) {

        Movimiento.Builder builder = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaDestino) // En depósito la cuenta destino es tratada como origen
                .setId(id) // Se recomienda que el id sea generado aleatoriamente o secuencialmente
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
     * @param id Identificador único del movimiento.
     * @param monto Monto que se retirará de la cuenta.
     * @param categoria Categoría opcional que clasifica el movimiento.
     * @param descripcion Descripción opcional del movimiento.
     * @return Objeto Movimiento configurado para un retiro, con la estrategia RetiroStrategy.
     */
    public Movimiento crearRetiro(CuentaBancaria cuentaOrigen, String id, double monto, @Nullable Categoria categoria, @Nullable String descripcion) {

        Movimiento.Builder builder = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setId(id) // Se recomienda que el id sea generado aleatoriamente o secuencialmente
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
     * @param id Identificador único del movimiento.
     * @param monto Monto a transferir.
     * @param categoria Categoría opcional que clasifica el movimiento.
     * @param descripcion Descripción opcional del movimiento.
     * @return Objeto Movimiento configurado para una transferencia, con la estrategia TransferenciaStrategy.
     */
    public Movimiento crearTransferencia(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, String id, double monto, @Nullable Categoria categoria, @Nullable String descripcion) {

        Movimiento.Builder builder = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setCuentaBancariaDestino(cuentaDestino)
                .setId(id) // Se recomienda que el id sea generado aleatoriamente o secuencialmente
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
}

