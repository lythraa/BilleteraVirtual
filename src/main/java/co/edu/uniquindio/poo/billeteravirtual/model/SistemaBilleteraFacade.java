package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.model.ChainOfResponsibility.*;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.DirectorMovimiento;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;
import co.edu.uniquindio.poo.billeteravirtual.model.gestores.*;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;
import org.jetbrains.annotations.Nullable;

/**
 * Fachada que simplifica la interacción con los diferentes gestores
 * del sistema de la billetera virtual, centralizando operaciones comunes.
 */
public class SistemaBilleteraFacade {

    private DirectorMovimiento directorMovimiento;
    private final GestorUsuarios gestorUsuarios;
    private final GestorAdministradores gestorAdministradores;
    private final GestorMovimientos gestorMovimientos;
    private final GestorCuentasBancarias gestorCuentasBancarias;

    /**
     * Constructor que inicializa los gestores utilizando el patrón Singleton.
     */
    public SistemaBilleteraFacade() {
        this.gestorAdministradores = GestorAdministradores.getInstancia();
        this.gestorMovimientos = GestorMovimientos.getInstancia();
        this.gestorCuentasBancarias = GestorCuentasBancarias.getInstancia();
        this.gestorUsuarios = GestorUsuarios.getInstancia();
    }


    /**
     * Método para ejecutar depósitos, pasa por una cadena de validaciones antes de crearlo
     * y si todo está correcto, lo ejecuta y registra en las listas correspondientes
     * @param usuario Usuario actual en sesión
     * @param cuentaDestino Cuenta a la cual se dirige el depósito (Siempre corresponderá a la cuenta del usuario actual en sesión)
     * @param monto monto del deposito
     * @param categoria categoría opcional relacionada con los presupuestos del usuario
     * @param descripcion descripción opcional del depósito
     */
    public void realizarDeposito(Usuario usuario, CuentaBancaria cuentaDestino, double monto, @Nullable Categoria categoria, @Nullable String descripcion){
        try {
            // Antes de crear el objeto, debe pasar por las validaciones
            ValidadorMovimiento validador = new ValidadorCuentaDestino()
                    .setSiguiente(new ValidadorCuentaNula());

            Movimiento temporal = directorMovimiento.crearDeposito(cuentaDestino, monto, categoria, descripcion);
            validador.validar(temporal);  // lanza excepción si algo falla

            // Construye el objeto real
            Movimiento deposito = directorMovimiento.crearDeposito(cuentaDestino, monto, categoria, descripcion);

            // Ejecuta, registra
            deposito.procesarTransaccion();
            gestorMovimientos.agregar(deposito);
            usuario.registrarMovimiento(deposito);
            usuario.calcularSaldoTotal();

        } catch (Exception e) {
            System.out.println("No se pudo realizar el deposito: " + e.getMessage());
        }
    }

    /**
     * Método para ejecutar retiros, pasa por una cadena de validaciones antes de crearlo
     * y si todo está correcto, lo ejecuta y registra en las listas correspondientes
     * @param usuario Usuario actual en sesión
     * @param cuentaOrigen Cuenta desde la cual se realiza el retiro (Siempre corresponderá a la cuenta del usuario actual en sesión)
     * @param monto monto del retiro
     * @param categoria categoría opcional relacionada con los presupuestos del usuario
     * @param descripcion descripcion opcional del retiro
     */
    public void realizarRetiro(Usuario usuario, CuentaBancaria cuentaOrigen, double monto, @Nullable Categoria categoria, @Nullable String descripcion){
        try {
            // Antes de crear el objeto, debe pasar por las validaciones
            ValidadorMovimiento validador = new ValidadorSaldoSuficiente();

            Movimiento temporal = directorMovimiento.crearRetiro(cuentaOrigen, monto, categoria, descripcion);
            validador.validar(temporal);  // lanza excepción si algo falla

            // Construye el objeto real
            Movimiento retiro = directorMovimiento.crearRetiro(cuentaOrigen, monto, categoria, descripcion);

            // Ejecuta, registra
            retiro.procesarTransaccion();
            gestorMovimientos.agregar(retiro);
            usuario.registrarMovimiento(retiro);
            usuario.calcularSaldoTotal();

        } catch (Exception e) {
            System.out.println("No se pudo realizar el retiro: " + e.getMessage());
        }
    }

    /**
     * Método para ejecutar retiros, pasa por una cadena de validaciones antes de crearlo
     * y si todo está correcto, lo ejecuta y registra en las listas correspondientes
     * @param usuario Usuario actual en sesión
     * @param cuentaOrigen Cuenta desde la cual se envía la transferencia (Siempre corresponderá a la cuenta del usuario actual en sesión)
     * @param cuentaDestino Cuenta a la cual se dirige la transferencia
     * @param monto monto de la transferencia
     * @param categoria categoría opcional de la transferencia
     * @param descripcion descripción opcional de la transferencia
     */
    public void realizarTransferencia(Usuario usuario, CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double monto, @Nullable Categoria categoria, @Nullable String descripcion){
        try {
            // Antes de crear el objeto, debe pasar por las validaciones
            ValidadorMovimiento validador = new ValidadorCuentaDestino()
                    .setSiguiente(new ValidadorCuentaNula())
                    .setSiguiente(new ValidadorSaldoSuficiente());

            if(categoria != null){
                usuario.buscarCategoria(categoria.getId_Nombre())
                        .getPresupuesto().registrarGasto(monto);///checkkk
            }

            Movimiento temporal = directorMovimiento.crearTransferencia(cuentaOrigen, cuentaDestino, monto, categoria, descripcion);

            validador.validar(temporal);  // lanza excepción si algo falla

            // Construye el objeto real
            Movimiento transferencia = directorMovimiento.crearTransferencia(cuentaOrigen, cuentaDestino, monto, categoria, descripcion);

            // Ejecuta, registra
            transferencia.procesarTransaccion();
            gestorMovimientos.agregar(transferencia);
            usuario.registrarMovimiento(transferencia);
            usuario.calcularSaldoTotal();

        } catch (Exception e) {
            System.out.println("No se pudo realizar la transferencia: " + e.getMessage());
        }
    }

    // TODO: añadir lógica para gestión de categorías (en proceso)



}
