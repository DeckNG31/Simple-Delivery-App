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

import DAOs.VendedorDAO;
import JDBCs.VendedorJDBC;
import controllers.VendedorController;
import isi.deso.tp.usuarios.Vendedor;
import isi.deso.tp.usuarios.Coordenada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

public class VendedorControllerTest {

    @Mock
    private VendedorDAO vendedorDAOMock;

    private VendedorController vendedorController;

    @BeforeEach
    public void setUp() {
        vendedorDAOMock = Mockito.mock(VendedorJDBC.class);
        vendedorController = VendedorController.getInstance();
        vendedorController.setCLienteDAO(vendedorDAOMock);
    }

    @Test
    public void testCrearVendedor() {
        // Datos del vendedor
        String nombre = "Carlos Perez";
        String direccion = "Calle Real 123";
        String lat = "40.7128";
        String lng = "-74.0060";

        // Llamar al método de creación de vendedor
        vendedorController.crearVendedor(nombre, direccion, lat, lng);

        // Capturar el argumento
        ArgumentCaptor<Vendedor> vendedorCaptor = ArgumentCaptor.forClass(Vendedor.class);
        verify(vendedorDAOMock).crearVendedor(vendedorCaptor.capture());

        // Verificar los valores del vendedor
        Vendedor vendedorCapturado = vendedorCaptor.getValue();
        assertEquals(nombre, vendedorCapturado.getNombre());
        assertEquals(direccion, vendedorCapturado.getDireccion());
        assertEquals(Double.parseDouble(lat), vendedorCapturado.getCoord().getLat(), 0.0001);
        assertEquals(Double.parseDouble(lng), vendedorCapturado.getCoord().getLng(), 0.0001);
    }

    @Test
    public void testEditarVendedor() {
        // Datos del vendedor original
        String nombreOriginal = "Carlos Perez";
        String direccionOriginal = "Calle Real 123";
        String latOriginal = "40.7128";
        String lngOriginal = "-74.0060";
        Vendedor vendedorOriginal = new Vendedor(1, nombreOriginal, direccionOriginal, new Coordenada(Double.parseDouble(latOriginal), Double.parseDouble(lngOriginal)));

        // Datos para la edición
        String nombreEditado = "Maria Gomez";
        String direccionEditada = "Calle Nueva 456";
        String latEditada = "38.8977";
        String lngEditada = "-77.0369";

        // Mockear comportamiento de vendedorDAOMock
        when(vendedorDAOMock.buscarVendedorPorId(vendedorOriginal.getId())).thenReturn(vendedorOriginal);

        // Llamar al método editarVendedor
        vendedorController.editarVendedor(vendedorOriginal.getId(), nombreEditado, direccionEditada, latEditada, lngEditada);

        // Verificar que se llame a vendedorDAOMock.editarVendedor con los datos actualizados
        verify(vendedorDAOMock).editarVendedor(argThat(vendedor
                -> vendedor.getNombre().equals(nombreEditado)
                && vendedor.getDireccion().equals(direccionEditada)
                && vendedor.getCoord().getLat() == Double.parseDouble(latEditada)
                && vendedor.getCoord().getLng() == Double.parseDouble(lngEditada)));
    }

    @Test
    public void testEliminarVendedor() {
        int idVendedorAEliminar = 1; // Suponemos que existe un vendedor con este ID

        // Mockear comportamiento de vendedorDAOMock
        when(vendedorDAOMock.buscarVendedorPorId(idVendedorAEliminar)).thenReturn(new Vendedor(idVendedorAEliminar, "Vendedor a eliminar", "...", null));

        // Llamar al método eliminarVendedor
        vendedorController.eliminarVendedor(idVendedorAEliminar);

        // Verificar que se llame a vendedorDAOMock.eliminarVendedor con el ID correcto
        verify(vendedorDAOMock).eliminarVendedor(idVendedorAEliminar);
    }

    @Test
    public void testListarVendedores() {
        // Lista mockeada de vendedores
        List<Vendedor> vendedoresMockeados = new ArrayList<>();
        vendedoresMockeados.add(new Vendedor(1, "Vendedor 1", "...", null));
        vendedoresMockeados.add(new Vendedor(2, "Vendedor 2", "...", null));

        // Mockear comportamiento de vendedorDAOMock
        when(vendedorDAOMock.listarVendedores()).thenReturn(vendedoresMockeados);

        // Llamar al método listarVendedores
        List<Vendedor> vendedoresObtenidos = vendedorController.listarVendedores();

        // Verificar que la lista obtenida tenga el mismo tamaño que la mockeada
        assertEquals(vendedoresMockeados.size(), vendedoresObtenidos.size());
    }

    @Test
    public void testBuscarVendedorPorIdExistente() {
        int idVendedorABuscar = 1;

        // Coordenada para evitar NullPointerException y tener un objeto completo
        Coordenada coordenada = new Coordenada(10.0, 20.0);

        // Vendedor mockeado CON Coordenada
        Vendedor vendedorMockeado = new Vendedor(idVendedorABuscar, "Vendedor buscado", "Dirección de prueba", coordenada);

        // Mockear comportamiento de vendedorDAOMock
        when(vendedorDAOMock.buscarVendedorPorId(idVendedorABuscar)).thenReturn(vendedorMockeado);

        // Llamar al método buscarVendedorPorId
        Vendedor vendedorEncontrado = vendedorController.buscarVendedorPorId(idVendedorABuscar);

        // Aserciones
        assertNotNull(vendedorEncontrado, "El vendedor encontrado no debería ser nulo");
        assertEquals(vendedorMockeado.getId(), vendedorEncontrado.getId(), "El ID del vendedor no coincide");
        assertEquals(vendedorMockeado.getNombre(), vendedorEncontrado.getNombre(), "El nombre del vendedor no coincide");
        assertEquals(vendedorMockeado.getDireccion(), vendedorEncontrado.getDireccion(), "La dirección del vendedor no coincide");
        assertEquals(vendedorMockeado.getCoord().getLat(), vendedorEncontrado.getCoord().getLat(), 0.001, "La latitud no coincide"); // Tolerancia para doubles
        assertEquals(vendedorMockeado.getCoord().getLng(), vendedorEncontrado.getCoord().getLng(), 0.001, "La longitud no coincide"); // Tolerancia para doubles
    }
}

