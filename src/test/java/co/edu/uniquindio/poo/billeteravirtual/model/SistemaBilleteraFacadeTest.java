package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.model.*;
import co.edu.uniquindio.poo.billeteravirtual.util.UtilAlerta;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaBilleteraFacadeTest {

    private SistemaBilleteraFacade facade;
    private Usuario usuario;
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;

    @BeforeEach
    void setUp() {
        facade = SistemaBilleteraFacade.getInstancia();

        usuario = new Usuario(
                "123456789",
                "clave123",
                "Carlos Pérez",
                "carlos@mail.com",
                "3111234567",
                "Calle 123 #45-67");

        cuentaOrigen = new CuentaBancaria("ACC-001", "Banco A", TipoCuenta.CORRIENTE);
        cuentaDestino = new CuentaBancaria("ACC-002", "Banco B", TipoCuenta.AHORRO);

        usuario.agregarCuenta(cuentaOrigen);
        usuario.agregarCuenta(cuentaDestino);

        facade.getGestorCuentasBancarias().agregar(cuentaOrigen);
        facade.getGestorCuentasBancarias().agregar(cuentaDestino);

        cuentaOrigen.agregarSaldo(1000);

        facade.getGestorUsuarios().agregar(usuario);

        UtilAlerta.modoTest = true;
    }


    @Test
    void testRealizarRetiroExitoso() {
        facade.realizarRetiro(usuario, cuentaOrigen, 300.0, null, "Retiro exitoso");

        assertEquals(700.0, cuentaOrigen.getSaldo(), 0.01);
        assertEquals(1, usuario.getHistorialMovimientos().size());
    }

    @Test
    void testRealizarTransferencia() {
        Usuario usuarioReceptor = new Usuario(
                "987654321",
                "clave456",
                "María López",
                "maria@mail.com",
                "3129876543",
                "Carrera 45 #12-34");

        CuentaBancaria cuentaReceptora = new CuentaBancaria("ACC-003", "Banco C", TipoCuenta.AHORRO);
        usuarioReceptor.agregarCuenta(cuentaReceptora);

        facade.getGestorUsuarios().agregar(usuarioReceptor);

        facade.realizarTransferencia(usuario, cuentaOrigen, cuentaReceptora, 150.0, null, "Transferencia test");

        assertEquals(850.0, cuentaOrigen.getSaldo(), 0.01);
        assertEquals(150.0, cuentaReceptora.getSaldo(), 0.01);
        assertEquals(1, usuario.getHistorialMovimientos().size());
        assertEquals(1, usuarioReceptor.getHistorialMovimientos().size());
    }

    @AfterAll
    static void tearDown() {
        UtilAlerta.modoTest = false;
    }
}
