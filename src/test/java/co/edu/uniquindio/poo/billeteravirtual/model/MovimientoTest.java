package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MovimientoTest {

    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;

    @BeforeEach
    void setUp() {
        cuentaOrigen = new CuentaBancaria("1", "Banco A", TipoCuenta.AHORRO);
        cuentaDestino = new CuentaBancaria("2", "Banco B", TipoCuenta.CORRIENTE);
    }

    @Test
    void testBuilderConTodosLosCampos() {
        Movimiento movimiento = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setCuentaBancariaDestino(cuentaDestino)
                .setId("M1")
                .setFecha(LocalDate.now())
                .setMonto(100.0)
                .setDescripcionOpcional("Transferencia")
                .setEstrategia(new TransferenciaStrategy())
                .build();

        assertEquals("M1", movimiento.getId());
        assertEquals(100.0, movimiento.getMonto());
        assertEquals("Transferencia", movimiento.getDescripcionOpcional());
    }

    @Test
    void testBuilderSinEstrategiaLanzaExcepcion() {
        Movimiento.Builder builder = new Movimiento.Builder()
                .setId("M2")
                .setMonto(50.0);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    void testProcesarDeposito() {
        UtilAlerta.modoTest = true;
        Movimiento movimiento = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setId("D1")
                .setMonto(200.0)
                .setEstrategia(new DepositoStrategy())
                .build();

        movimiento.procesarTransaccion();
        assertEquals(200.0, cuentaOrigen.getSaldo());
        UtilAlerta.modoTest = false;
    }

    @Test
    void testProcesarRetiro() {
        UtilAlerta.modoTest = true;
        cuentaOrigen.agregarSaldo(300);

        Movimiento movimiento = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setId("R1")
                .setMonto(150)
                .setEstrategia(new RetiroStrategy())
                .build();

        movimiento.procesarTransaccion();

        assertEquals(150.0, cuentaOrigen.getSaldo());
        UtilAlerta.modoTest = false;
    }

    @Test
    void testProcesarTransferencia() {
        cuentaOrigen.agregarSaldo(500.0);

        Movimiento movimiento = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setCuentaBancariaDestino(cuentaDestino)
                .setId("T1")
                .setMonto(250.0)
                .setEstrategia(new TransferenciaStrategy())
                .build();

        movimiento.procesarTransaccion();

        assertEquals(250.0, cuentaDestino.getSaldo());
        assertEquals(250.0, cuentaOrigen.getSaldo());
    }

    @Test
    void testClonarMovimiento() {
        Movimiento original = new Movimiento.Builder()
                .setCuentaBancariaOrigen(cuentaOrigen)
                .setId("Clone1")
                .setMonto(100.0)
                .setEstrategia(new DepositoStrategy())
                .build();

        Movimiento clon = original.clone();

        assertNotSame(original, clon);
        assertEquals(original.getId(), clon.getId());
        assertEquals(original.getMonto(), clon.getMonto());
    }

}
