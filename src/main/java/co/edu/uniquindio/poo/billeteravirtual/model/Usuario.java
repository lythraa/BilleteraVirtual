package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Perfil implements Observer {

    private double saldoTotal;
    private ArrayList<Movimiento> historialMovimientos;
    private ArrayList<Presupuesto> listaPresupuestos;
    private ArrayList<CuentaBancaria> listaCuentasBancarias;
    private ArrayList<Categoria> listaCategorias;
    private List<Notificacion> historialNotificaciones;

    /**
     * Constructor público de la clase Usuario que hereda de Perfil.
     * Inicializa el saldo total en 0 y las listas vacías para transacciones,
     * presupuestos, categorias y cuentas bancarias.
     *
     * @param id        Cédula del usuario
     * @param contrasenia Clave de acceso del usuario
     * @param nombre    Nombre y apellido del usuario
     * @param correo    Dirección de correo electrónico del usuario
     * @param telefono  Número de teléfono del usuario
     * @param direccion Dirección de residencia del usuario
     */
    public Usuario(String id, String contrasenia, String nombre, String correo, String telefono, String direccion){
        super(id, contrasenia, nombre, correo, telefono, direccion);
        this.saldoTotal = 0.0;
        historialMovimientos = new ArrayList<>();
        listaPresupuestos = new ArrayList<>();
        listaCuentasBancarias = new ArrayList<>();
        listaCategorias = new ArrayList<>();
        historialNotificaciones = new ArrayList<>();

    }

    /**
     * Calcula la suma del saldo de todas las cuentas bancarias asociadas
     * al usuario.
     *
     * @return suma del saldo de todas las cuentas bancarias
     */
    public void calcularSaldoTotal(){
        double total = 0;
        for (CuentaBancaria c : listaCuentasBancarias){
            total+=c.getSaldo();
        }
        this.saldoTotal = total;
    }

    /**
     * Crea una nueva categoría con un monto asignado o devuelve una ya existente.
     *
     * Si la categoría con el nombre dado ya existe, la retorna.
     * Si no existe, la crea con el monto asignado y la agrega a la lista.
     *
     * @param idNombreCategoria nombre o ID de la categoría (no puede ser nulo o vacío).
     * @param montoAsignado monto de dinero asignado (no puede ser negativo).
     * @return la categoría existente o la nueva categoría creada.
     * @throws IllegalArgumentException si el nombre es nulo/vacío o el monto es negativo.
     */

    public Categoria obtenerOCrearCategoria(String idNombreCategoria, double montoAsignado) {
        if (idNombreCategoria == null || idNombreCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede ser nulo ni vacío.");
        }
        if (montoAsignado < 0) {
            throw new IllegalArgumentException("El monto asignado no puede ser negativo.");
        }


        Categoria existente = buscarCategoria(idNombreCategoria);
        if (existente != null) {
            return existente;
        }

        Categoria nueva = crearCategoria(idNombreCategoria, montoAsignado);
        listaCategorias.add(nueva);
        return nueva;
    }

    /**
     * Método para obtener una categoría del usuario dado su nombre
     * @param idNombre nombre de la categoría
     * @return categoría encontrada
     */
    public Categoria buscarCategoria(String idNombre) {
        for (Categoria c : listaCategorias) {
            if (c.getId_Nombre().equals(idNombre)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Crea una nueva categoría para el usuario
     * @param idNombre nombre de la categoría
     * @param monto monto máximo a gastar
     * @return categoría creada
     */
    private Categoria crearCategoria(String idNombre, double monto) {
        return new Categoria(idNombre, new Presupuesto(monto));
    }

    /**
     * Método para registrar un movimiento del usuario
     * @param movimiento movimiento a registrar
     */
    public void registrarMovimiento(Movimiento movimiento){
        calcularSaldoTotal();
        historialMovimientos.add(movimiento);
    }

    /**
     * Método de la interface observer, permite que el usuario reciba notificaciones
     * @param notificacion notificacion enviada
     */
    @Override
    public void recibirNotificacion(Notificacion notificacion) {
        System.out.println("Usuario " + this.getNombre() + " recibió: " + notificacion.getMensaje());
        historialNotificaciones.add(notificacion);
    }

    /**
     * Método para agregar una nueva cuenta a la lista de cuentas bancarias del usuario
     * @param cuentaNueva cuenta a agregar
     */
    public void agregarCuenta(CuentaBancaria cuentaNueva){
        listaCuentasBancarias.add(cuentaNueva);
        SistemaBilleteraFacade.getInstancia().getGestorCuentasBancarias().agregar(cuentaNueva);
    }

    /**
     * Método para enviar mensajes a soporte a modo de notificación
     * @param evento evento del mensaje
     * @param mensaje mensaje a enviar
     */
    public void enviarMensaje(String evento, String mensaje) {
        Notificacion n = new Notificacion("USUARIO " + this.getNombre() + ": " + mensaje);
        GestorNotificaciones.getInstancia().notificar(evento, n);
    }

    //====================GETTERS Y SETTERS=======================//

    public double getSaldoTotal() {
        calcularSaldoTotal();
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public ArrayList<Movimiento> getHistorialMovimientos() {
        return historialMovimientos;
    }

    public void setHistorialMovimientos(ArrayList<Movimiento> historialMovimientos) {
        this.historialMovimientos = historialMovimientos;
    }

    public ArrayList<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(ArrayList<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public ArrayList<CuentaBancaria> getListaCuentasBancarias() {
        return listaCuentasBancarias;
    }

    public void setListaCuentasBancarias(ArrayList<CuentaBancaria> listaCuentasBancarias) {
        this.listaCuentasBancarias = listaCuentasBancarias;
    }

    public ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    public List<Notificacion> getHistorialNotificaciones() {
        return historialNotificaciones;
    }
}

