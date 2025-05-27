package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.util.GestorVistas;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Fachada que simplifica la interacción con los diferentes gestores
 * del sistema de la billetera virtual, centralizando operaciones comunes.
 */
public class SistemaBilleteraFacade {

    private final DirectorMovimiento directorMovimiento;
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
     * @param descripcion descripción opcional del depósito
     */
    public void realizarDeposito(Usuario usuario, CuentaBancaria cuentaDestino, double monto, @Nullable String descripcion){
        try {
            // Antes de crear el objeto pasa por las validaciones
            ValidadorMovimiento validador = new ValidadorCuentaDestino()
                    .setSiguiente(new ValidadorCuentaNula());

            Movimiento temporal = directorMovimiento.crearDeposito(cuentaDestino, monto, descripcion);
            validador.validar(temporal);  // lanza excepción si algo falla

            // Construye el objeto real
            Movimiento deposito = directorMovimiento.crearDeposito(cuentaDestino, monto, descripcion);

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
                usuario.buscarCategoria(categoria.getId())
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

            Usuario usuarioDestino = null;

            for(Usuario u : gestorUsuarios.getListaObjetos()){
                for(CuentaBancaria cuentaBancaria : u.getListaCuentasBancarias()){
                    if (Objects.equals(cuentaBancaria.getId(), cuentaDestino.getId())) {
                        usuarioDestino = u;
                        break;
                    }
                }
            }

            if(usuarioDestino != null){
                usuarioDestino.registrarMovimiento(transferencia);
            }

        } catch (Exception e) {
            System.out.println("No se pudo realizar la transferencia: " + e.getMessage());
        }
    }

    /**
     * Método para buscar cuentas bancarias en la lista del gestor
     * @param id o número de la cuenta deseada
     * @return cuenta encontrada
     */
    public CuentaBancaria buscarCuenta(String id){
        try {
            for (CuentaBancaria cuenta : getGestorCuentasBancarias().getListaObjetos()) {
                if (cuenta.getId().equals(id)) {
                    return cuenta;
                }
            }
        } catch (Exception e) {
            System.out.println("Cuenta no encontrada: " + e.getMessage());
        }
        return null;
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
            String categoria = movimiento.getCategoriaOpcional().getId(); // Asegúrate de que exista
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
     *Obtiene los usuarios con mayor cantidad de movimientos
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
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    /**
     * Calcula el saldo promedio de los usuarios del
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

    /**
     * Genera un reporte en pdf de los movimientos del usuario
     * @param usuario usuario en sesión
     */
    public void generarReportePDF(Usuario usuario) {
        ReporteExportable reporte = new ReportePDFService();
        try {
            reporte.exportarReporte(usuario, "Documentos-Creados/reporte_usuario.pdf");
        } catch (Exception e) {
            Logger.getLogger(GestorVistas.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Método para generar un reporte en excel de los movimientos del usuario
     * @param usuario usuario en sesión
     */
    public void generarReporteExcel(Usuario usuario) {
        ReporteExportable reporte = new ReporteExcelAdapter();
        try {
            reporte.exportarReporte(usuario, "Documentos-Creados/reporte_usuario.xlsx");
        } catch (Exception e) {
            Logger.getLogger(GestorVistas.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Genera un código alfanumérico corto de 6 caracteres para identificar las trnsacciones.
     * Usa letras mayúsculas y dígitos numéricos.
     *
     * @return Código aleatorio de 6 caracteres
     */
    public String generarCodigoId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int pos = (int) (Math.random() * chars.length());
            codigo.append(chars.charAt(pos));
        }
        return codigo.toString();
    }

    //========================GETTERS=========================//

    public static synchronized SistemaBilleteraFacade getInstancia() {
        if (instancia == null) {
            instancia = new SistemaBilleteraFacade();
        }
        return instancia;
    }

    public GestorUsuarios getGestorUsuarios() {
        return gestorUsuarios;
    }

    public GestorAdministradores getGestorAdministradores() {
        return gestorAdministradores;
    }

    public GestorCuentasBancarias getGestorCuentasBancarias() {
        return gestorCuentasBancarias;
    }

}
