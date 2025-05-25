package co.edu.uniquindio.poo.billeteravirtual.model;

import java.time.LocalDate;

/**
 * Representa un movimiento financiero en la billetera virtual.
 * Implementa el patrón Builder para facilitar la creación de instancias complejas.
 * Incluye información de cuentas de origen y destino, monto, fecha, categoría, descripción y estrategia de procesamiento.
 */
public class Movimiento implements Identificable, Cloneable {

    private final CuentaBancaria cuentaBancariaOrigen;
    private final CuentaBancaria cuentaBancariaDestino;
    private final String id;
    private final LocalDate fecha;
    private final double monto;
    private final String descripcionOpcional;
    private final Categoria categoriaOpcional;
    private final MovimientoStrategy estrategia;

    /**
     * Constructor privado. Solo puede ser instanciado mediante el Builder.
     * @param builder Objeto Builder con los datos para construir el movimiento.
     */
    private Movimiento(Builder builder) {
        this.cuentaBancariaOrigen = builder.cuentaBancariaOrigen;
        this.cuentaBancariaDestino = builder.cuentaBancariaDestino;
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.monto = builder.monto;
        this.categoriaOpcional = builder.categoriaOpcional;
        this.descripcionOpcional = builder.descripcionOpcional;
        this.estrategia = builder.estrategia;
    }

    /**
     * Clase Builder para construir objetos Movimiento de manera flexible y legible.
     * Permite establecer solo los atributos deseados antes de crear la instancia final.
     */
    public static class Builder {
        private CuentaBancaria cuentaBancariaOrigen;
        private CuentaBancaria cuentaBancariaDestino;
        private String id;
        private LocalDate fecha;
        private double monto;
        private String descripcionOpcional;
        private Categoria categoriaOpcional;
        private MovimientoStrategy estrategia;

        public Builder setCuentaBancariaOrigen(CuentaBancaria cuentaBancariaOrigen) {
            this.cuentaBancariaOrigen = cuentaBancariaOrigen;
            return this;
        }

        public Builder setCuentaBancariaDestino(CuentaBancaria cuentaBancariaDestino) {
            this.cuentaBancariaDestino = cuentaBancariaDestino;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setFecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder setMonto(double monto) {
            this.monto = monto;
            return this;
        }

        public Builder setDescripcionOpcional(String descripcionOpcional) {
            this.descripcionOpcional = descripcionOpcional;
            return this;
        }

        public Builder setCategoriaOpcional(Categoria categoriaOpcional) {
            this.categoriaOpcional = categoriaOpcional;
            return this;
        }

        public Builder setEstrategia(MovimientoStrategy estrategia) {
            this.estrategia = estrategia;
            return this;
        }


        /**
         * Construye el objeto Movimiento validando que la estrategia no sea nula.
         * @return Nueva instancia de Movimiento.
         * @throws IllegalStateException si no se ha asignado una estrategia.
         */
        public Movimiento build() {
            if (estrategia == null) throw new IllegalStateException("Estrategia requerida");
            return new Movimiento(this);
        }
    }

    /**
     * Método para clonar un movimiento
     * @return
     */
    @Override
    public Movimiento clone() {
        try {
            return (Movimiento) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    /**
     * Ejecuta el procesamiento del movimiento según la estrategia asignada.
     */
    public void procesarTransaccion() {
        estrategia.procesarTransaccion(this);
    }

    //===============GETTERS AND SETTERS==================//

    public CuentaBancaria getCuentaBancariaOrigen() {
        return cuentaBancariaOrigen;
    }

    public CuentaBancaria getCuentaBancariaDestino() {
        return cuentaBancariaDestino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcionOpcional() {
        return descripcionOpcional;
    }

    public Categoria getCategoriaOpcional() {
        return categoriaOpcional;
    }

    public MovimientoStrategy getEstrategia() {
        return estrategia;
    }



    /**
     * Obtiene el identificador único del movimiento.
     * @return ID del movimiento.
     */
    @Override
    public String getId() {
        return id;
    }
}
