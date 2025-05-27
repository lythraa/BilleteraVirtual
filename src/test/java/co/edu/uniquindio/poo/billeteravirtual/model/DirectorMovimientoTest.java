package co.edu.uniquindio.poo.billeteravirtual.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectorMovimientoTest {

    private DirectorMovimiento directorMovimiento;
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;


    @BeforeEach
    public void setUp(){
        directorMovimiento = new DirectorMovimiento();
        cuentaOrigen = new CuentaBancaria("1", "Banco A", TipoCuenta.AHORRO);
        cuentaDestino = new CuentaBancaria("2", "Banco B", TipoCuenta.CORRIENTE);
    }

    @Test
    public void testCrearDeposito() {
        Movimiento deposito = directorMovimiento.crearDeposito(cuentaDestino,500,"");
        assertEquals(500.0,deposito.getMonto());
        assertInstanceOf(DepositoStrategy.class,deposito.getEstrategia());
    }

    @Test
    public void testCrearRetiro() {
        cuentaOrigen.agregarSaldo(500);
        Movimiento retiro = directorMovimiento.crearRetiro(cuentaOrigen,500,new Categoria("COMIDA", new Presupuesto(1000)),"");
        assertEquals(500.0,retiro.getMonto());
        assertInstanceOf(RetiroStrategy.class,retiro.getEstrategia());
    }

    @Test
    public void testCrearTransferencia() {
        cuentaOrigen.agregarSaldo(500);
        Movimiento transferencia = directorMovimiento.crearTransferencia(cuentaOrigen,cuentaDestino,500,new Categoria("COMIDA", new Presupuesto(1000)),"");
        assertEquals(500.0,transferencia.getMonto());
        assertInstanceOf(TransferenciaStrategy.class,transferencia.getEstrategia());
    }

}
