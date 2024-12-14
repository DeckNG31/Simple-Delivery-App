/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author mariano
 */
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import DAOs.ItemMenuDAO;
import JDBCs.ItemMenuJDBC;
import controllers.ItemMenuController;
import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.menu.Plato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ItemMenuControllerTest {

    @Mock
    private ItemMenuDAO itemMenuDAOMock;

    private ItemMenuController itemMenuController;

    @BeforeEach
    public void setUp() {
        itemMenuDAOMock = Mockito.mock(ItemMenuJDBC.class);
        itemMenuController = ItemMenuController.getInstance();
        itemMenuController.setItemMenuDAO(itemMenuDAOMock);
    }

    @Test
    public void testCrearPlato() throws Exception {
        String nombre = "Ensalada César";
        String descripcion = "Ensalada con aderezo César y crutones";
        String precio = "12.5";
        boolean aptoVegano = false;
        String peso = "300";
        String calorias = "450";
        boolean aptoCeliaco = true;
        int vendedorId = 1;

        itemMenuController.crearPlato(nombre, descripcion, precio, aptoVegano, peso, calorias, aptoCeliaco, vendedorId);

        ArgumentCaptor<ItemMenu> captor = ArgumentCaptor.forClass(ItemMenu.class);
        verify(itemMenuDAOMock).crearItemMenu(captor.capture());

        ItemMenu plato = captor.getValue();
        assertTrue(plato instanceof Plato);
        Plato platoCapturado = (Plato) plato;

        assertEquals(nombre, platoCapturado.getNombre());
        assertEquals(descripcion, platoCapturado.getDescripcion());
        assertEquals(12.5, platoCapturado.getPrecio(), 0.0001);
        assertEquals(300, platoCapturado.getPeso(), 0.0001);
        assertEquals(450, platoCapturado.getCalorias(), 0.0001);
        assertTrue(platoCapturado.isAptoCeliaco());
        assertFalse(platoCapturado.isAptoVegano());
        assertEquals(vendedorId, platoCapturado.getVendedorId());
    }

    @Test
    public void testEditarPlato() throws Exception {
        int id = 1;
        String nombre = "Ensalada César Editada";
        String descripcion = "Nueva descripción";
        String precio = "15.0";
        boolean aptoVegano = true;
        String peso = "350";
        String calorias = "400";
        boolean aptoCeliaco = false;
        int vendedorId = 2;

        itemMenuController.editarPlato(id, nombre, descripcion, precio, aptoVegano, peso, calorias, aptoCeliaco, vendedorId);

        ArgumentCaptor<ItemMenu> captor = ArgumentCaptor.forClass(ItemMenu.class);
        verify(itemMenuDAOMock).editarItemMenu(captor.capture());

        ItemMenu plato = captor.getValue();
        assertTrue(plato instanceof Plato);
        Plato platoEditado = (Plato) plato;

        assertEquals(id, platoEditado.getId());
        assertEquals(nombre, platoEditado.getNombre());
        assertEquals(descripcion, platoEditado.getDescripcion());
        assertEquals(15.0, platoEditado.getPrecio(), 0.0001);
        assertEquals(350, platoEditado.getPeso(), 0.0001);
        assertEquals(400, platoEditado.getCalorias(), 0.0001);
        assertFalse(platoEditado.isAptoCeliaco());
        assertTrue(platoEditado.isAptoVegano());
        assertEquals(vendedorId, platoEditado.getVendedorId());
    }

    @Test
    public void testEliminarItemMenu() {
        int id = 5;

        itemMenuController.eliminarItemMenu(id);

        verify(itemMenuDAOMock).eliminarItemMenu(id);
    }

    @Test
    public void testListarItemsMenu() {
        List<ItemMenu> itemsMockeados = new ArrayList<>();
        itemsMockeados.add(new Plato(1, "Plato 1", "Descripción 1", 10.0, true, 300.0, 500.0, false, 1));
        itemsMockeados.add(new Bebida(2, "Bebida 1", "Descripción 2", 5.0, true, 500.0, 250.0, 12.5, 2));

        when(itemMenuDAOMock.listarItemMenus()).thenReturn(itemsMockeados);

        List<ItemMenu> items = itemMenuController.listarItemsMenu();

        assertEquals(itemsMockeados.size(), items.size());
        assertEquals(itemsMockeados, items);
    }

    @Test
    public void testBuscarItemMenuPorId() {
        int id = 1;
        Plato platoMockeado = new Plato(id, "Plato 1", "Descripción 1", 10.0, true, 300.0, 500.0, true, 1);

        when(itemMenuDAOMock.buscarItemMenuPorId(id)).thenReturn(platoMockeado);

        ItemMenu item = itemMenuController.buscarItemMenuPorId(id);

        assertNotNull(item);
        assertTrue(item instanceof Plato);
        assertEquals(platoMockeado, item);
    }

    @Test
    public void testCrearBebida() throws Exception {
        String nombre = "Coca-Cola";
        String descripcion = "Bebida gaseosa";
        String precio = "2.5";
        boolean aptoVegano = true;
        String peso = "500";
        String volumen = "500";
        String alcohol = "0";
        int vendedorId = 1;

        itemMenuController.crearBebida(nombre, descripcion, precio, aptoVegano, peso, volumen, alcohol, vendedorId);

        ArgumentCaptor<ItemMenu> captor = ArgumentCaptor.forClass(ItemMenu.class);
        verify(itemMenuDAOMock).crearItemMenu(captor.capture());

        ItemMenu bebida = captor.getValue();
        assertTrue(bebida instanceof Bebida);
        Bebida bebidaCapturada = (Bebida) bebida;

        assertEquals(nombre, bebidaCapturada.getNombre());
        assertEquals(descripcion, bebidaCapturada.getDescripcion());
        assertEquals(2.5, bebidaCapturada.getPrecio(), 0.0001);
        assertEquals(500, bebidaCapturada.getPeso(), 0.0001);
        assertEquals(500, bebidaCapturada.getVolumen(), 0.0001);
        assertEquals(0, bebidaCapturada.getGraduacionAlcohol(), 0.0001);
        assertTrue(bebidaCapturada.isAptoVegano());
        assertEquals(vendedorId, bebidaCapturada.getVendedorId());
    }
}

