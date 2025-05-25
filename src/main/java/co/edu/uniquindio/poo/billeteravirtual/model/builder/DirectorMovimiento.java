package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

/**
 * Clase directora para crear transacciones haciendo uso
 * del Builder de la clase Movimiento.
 *
 * Dependiendo del tipo de movimiento a realizar deseado,
 * se construye un objeto Movimiento de manera distinta.
 */
public class DirectorMovimiento {
    /**
     * Método para crear un Movimiento de tipo Deposito
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
                .setCuentaBancariaOrigen(cuentaDestino) // tratada como origen en depósito
                .setId(id) //Estaría chevere que el id se genere aleatoriamente o secuencialmente, algo así
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
     * Método para crear un movimiento de tipo Retiro
     * @param cuentaOrigen Cuenta de la cual se realizará el retiro
     * @param id id del movimiento
     * @param monto monto deseado a retirar de la cuenta
     * @param categoria categoría opcional del movimiento
     * @param descripcion
     * @return
     */
    public Movimiento crearRetiro(CuentaBancaria cuentaOrigen, String id, double monto, @Nullable Categoria categoria, @Nullable String descripcion) {

        Movimiento.Builder builder = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setId(id) //Estaría chevere que el id se genere aleatoriamente o secuencialmente, algo así
                .setFecha(LocalDate.now())
                .setMonto(monto)
                .setEstrategia(new RetiroStrategy());

        if (categoria != null) {
            builder.setCategoriaOpcional(categoria);
        }

        if (descripcion != null) {
            builder.setDescripcionOpcional(descripcion);
        }

        return builder.build();
    }

    public Movimiento crearTransferencia(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, String id, double monto, @Nullable Categoria categoria, @Nullable String descripcion) {

        Movimiento.Builder builder = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setCuentaBancariaDestino(cuentaDestino)
                .setId(id) //Estaría chevere que el id se genere aleatoriamente o secuencialmente, algo así
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
