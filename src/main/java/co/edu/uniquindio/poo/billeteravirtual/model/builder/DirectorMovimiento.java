package co.edu.uniquindio.poo.billeteravirtual.model.builder;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public class DirectorMovimiento {
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
