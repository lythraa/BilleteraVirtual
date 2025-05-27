package co.edu.uniquindio.poo.billeteravirtual.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class GestorTest {
    private GestorBaseCRUD<Usuario> gestor;
    private List<Usuario> lista;

    @BeforeEach
    public void setUp() {
        lista = new ArrayList<>();
        gestor = new GestorBaseCRUD<>(lista);
    }

    @Test
    public void testAgregarYBuscar() {
        Usuario obj = new Usuario("123", "prueba1", "Esteban Rodríguez", "esteban@email.com", "3333333", "Cra. 123");
        gestor.agregar(obj);
        assertEquals(obj, gestor.buscar("123"));
    }

    @Test
    public void testAgregarObjetoNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> gestor.agregar(null));
    }

    @Test
    public void testBuscarConIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> gestor.buscar(null));
        assertThrows(IllegalArgumentException.class, () -> gestor.buscar(""));
    }

    @Test
    public void testEliminar() {
        Usuario obj = new Usuario("123", "prueba1", "Esteban Rodríguez", "esteban@email.com", "3333333", "Cra. 123");
        gestor.agregar(obj);
        gestor.eliminar(obj);
        assertNull(gestor.buscar("123"));
    }

    @Test
    public void testEliminarObjetoNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> gestor.eliminar(null));
    }

    @Test
    public void testReemplazar() {
        Usuario original = new Usuario("321", "prueba1", "Esteban Rodríguez", "esteban@email.com", "3333333", "Cra. 123");
        Usuario nuevo = new Usuario("321", "prueba1", "Esteban Rodríguez", "esteban@email.com", "3333333", "Cra. 123");
        gestor.agregar(original);
        boolean reemplazado = gestor.reemplazar("321", nuevo);
        assertTrue(reemplazado);
        assertEquals(nuevo, gestor.buscar("321"));
    }

    @Test
    public void testReemplazarConDatosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> gestor.reemplazar(null, new Usuario("321", "prueba1", "Esteban Rodríguez", "esteban@email.com", "3333333", "Cra. 123")));
        assertThrows(IllegalArgumentException.class, () -> gestor.reemplazar("", new Usuario("321", "prueba1", "Esteban Rodríguez", "esteban@email.com", "3333333", "Cra. 123")));
        assertThrows(IllegalArgumentException.class, () -> gestor.reemplazar("321", null));
    }

    @Test
    public void testGetListaObjetos() {
        Usuario obj = new Usuario("456", "prueba1", "Esteban Rodríguez", "esteban@email.com", "3333333", "Cra. 123");
        gestor.agregar(obj);
        assertTrue(gestor.getListaObjetos().contains(obj));
    }
}
