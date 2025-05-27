package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.model.ChainOfResponsibility.*;
import co.edu.uniquindio.poo.billeteravirtual.model.adapter.ReporteExcelAdapter;
import co.edu.uniquindio.poo.billeteravirtual.model.adapter.ReporteExportable;
import co.edu.uniquindio.poo.billeteravirtual.model.adapter.ReportePDFService;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.DirectorMovimiento;
import co.edu.uniquindio.poo.billeteravirtual.model.builder.Movimiento;
import co.edu.uniquindio.poo.billeteravirtual.model.gestores.*;
import co.edu.uniquindio.poo.billeteravirtual.model.proxy.CuentaBancaria;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;

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
    private static SistemaBilleteraFacade instancia;

    /**
     * Constructor que inicializa los gestores utilizando el patrón Singleton.
     */
    public SistemaBilleteraFacade() {
        this.directorMovimiento = new DirectorMovimiento();
        this.gestorAdministradores = GestorAdministradores.getInstancia();
        this.gestorMovimientos = GestorMovimientos.getInstancia();
        this.gestorCuentasBancarias = GestorCuentasBancarias.getInstancia();
        this.gestorUsuarios = GestorUsuarios.getInstancia();
    }

    //=========================MÉTODOS DE REALIZACIÓN DE MOVIMIENTOS===========================//

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

    //TODO: Métodos para alimentar los gráficos de las estadísticas de admins (Almost Done!!!)

    //=========================MÉTODOS DE CÁLCULO DE ESTADÍSTICAS===========================//

    //TENER PRESENTE POR SI ES NECESARIO CORREGIR
    /**
     * Método para obtener el porcentaje de movimientos globales realizados por categoría
     * @return Hashmap porcentaje por categoría
     */
    public Map<String, Double> obtenerMovimientosPorCategoria() {
        List<Movimiento> movimientos = gestorMovimientos.getListaObjetos();
        Map<String, Integer> conteoPorCategoria = new HashMap<>();

        // Contar movimientos por categoría
        for (Movimiento movimiento : movimientos) {
            String categoria = movimiento.getCategoriaOpcional().getId_Nombre(); // Asegúrate de que exista
            conteoPorCategoria.merge(categoria, 1, Integer::sum);
        }

        // Total de movimientos
        int totalMovimientos = movimientos.size();

        // Calcular porcentaje por categoría
        Map<String, Double> porcentajePorCategoria = new HashMap<>();
        for (Map.Entry<String, Integer> entry : conteoPorCategoria.entrySet()) {
            double porcentaje = (entry.getValue() * 100.0) / totalMovimientos;
            porcentajePorCategoria.put(entry.getKey(), porcentaje);
        }

        return porcentajePorCategoria;
    }

    /**
     * Método para obtener los usuarios con mayor cantidad de movimientos
     * @param limite máximo de usuarios mostrados en el chart
     * @return Hashmap de usuarios con su respectivo número de movimientos
     */
    public Map<String, Integer> obtenerUsuariosConMasMovimientos(int limite) {
        Map<String, Integer> movimientosPorUsuario = new HashMap<>();

        for (Usuario usuario : gestorUsuarios.getListaObjetos()) {
            int cantidad = usuario.getHistorialMovimientos().size(); // o desde gestorMovimientos si lo manejas ahí
            movimientosPorUsuario.put(usuario.getNombre(), cantidad); // o ID si prefieres
        }

        // Ordenar por cantidad de movimientos (desc) y limitar resultados
        return movimientosPorUsuario.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(limite)
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue(),
                        (e1, e2) -> e1,
                        () -> new LinkedHashMap<>()
                ));
    }

    /**
     * Método para calcular el saldo promedio de los usuarios del
     * histórico de movimientos
     * @return saldo promedio general
     */
    public double calcularSaldoPromedioUsuarios() {
        List<Usuario> usuarios = gestorUsuarios.getListaObjetos();
        double saldoPromedio = 0;
        for (Usuario usuario : usuarios) {
            double saldo = usuario.getSaldoTotal();
            saldoPromedio += saldo;
        }
        saldoPromedio /= usuarios.size();
        return saldoPromedio;
    }

    //Este es opcional, de momento no lo vamos a usar, si da el tiempo lo acomodo
    /*
    public Map<LocalDate, Double> obtenerSaldoPromedioPorFecha() {

    }
    */

    public void generarReportePDF(Usuario usuario) {
        ReporteExportable reporte = new ReportePDFService();
        try {
            reporte.exportarReporte(usuario, "reporte_usuario.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporteExcel(Usuario usuario) {
        ReporteExportable reporte = new ReporteExcelAdapter();
        try {
            reporte.exportarReporte(usuario, "reporte_usuario.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //========================GETTERS=========================//

    public static synchronized SistemaBilleteraFacade getInstancia() {
        if (instancia == null) {
            instancia = new SistemaBilleteraFacade();
        }
        return instancia;
    }

    public DirectorMovimiento getDirectorMovimiento() {
        return directorMovimiento;
    }

    public void setDirectorMovimiento(DirectorMovimiento directorMovimiento) {
        this.directorMovimiento = directorMovimiento;
    }

    public GestorUsuarios getGestorUsuarios() {
        return gestorUsuarios;
    }

    public GestorAdministradores getGestorAdministradores() {
        return gestorAdministradores;
    }

    public GestorMovimientos getGestorMovimientos() {
        return gestorMovimientos;
    }

    public GestorCuentasBancarias getGestorCuentasBancarias() {
        return gestorCuentasBancarias;
    }

}
