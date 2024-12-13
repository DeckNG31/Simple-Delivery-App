/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import DAOs.ClienteDAO;
import JDBCs.ClienteJDBC;
import controllers.ClienteController;
import isi.deso.tp.usuarios.Cliente;
import isi.deso.tp.usuarios.Coordenada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ClienteControllerTest {

    @Mock
    private ClienteDAO clienteDAOMock;

    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        clienteDAOMock = Mockito.mock(ClienteJDBC.class);
        clienteController = ClienteController.getInstance();
        clienteController.setCLienteDAO(clienteDAOMock);
    }

    @Test
    public void testCrearCliente() {
        // Datos del cliente
        String nombre = "Juan Perez";
        String cuit = "20-12345678-9";
        String email = "juan.perez@email.com";
        String direccion = "Calle Falsa 123";
        String lat = "40.7128";
        String lng = "-74.0060";

        // Llamar al método de creación de cliente
        clienteController.crearCliente(nombre, cuit, email, direccion, lat, lng);

        // Capturar el argumento
        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);
        verify(clienteDAOMock).crearCliente(clienteCaptor.capture());

        // Verificar los valores del cliente
        Cliente clienteCapturado = clienteCaptor.getValue();
        assertEquals(nombre, clienteCapturado.getNombre());
        assertEquals(cuit, clienteCapturado.getCuit());
        assertEquals(email, clienteCapturado.getEmail());
        assertEquals(direccion, clienteCapturado.getDireccion());
        assertEquals(Double.parseDouble(lat), clienteCapturado.getCoord().getLat(), 0.0001);
        assertEquals(Double.parseDouble(lng), clienteCapturado.getCoord().getLng(), 0.0001);
    }

    @Test
    public void testEditarCliente() {
        // Datos del cliente original
        String nombreOriginal = "Juan Perez";
        String cuitOriginal = "20-12345678-9";
        String emailOriginal = "juan.perez@email.com";
        String direccionOriginal = "Calle Falsa 123";
        String latOriginal = "40.7128";
        String lngOriginal = "-74.0060";
        Cliente clienteOriginal = new Cliente(0, nombreOriginal, cuitOriginal, emailOriginal, direccionOriginal, new Coordenada(Double.parseDouble(latOriginal), Double.parseDouble(lngOriginal)));

        // Datos para la edición
        String nombreEditado = "Maria Gomez";
        String cuitEditado = "30-98765432-1";
        String emailEditado = "maria.gomez@email.com";
        String direccionEditada = "Calle Verdadera 456";
        String latEditada = "38.8977";
        String lngEditada = "-77.0369";

        // Mockear comportamiento de clienteDAOMock
        when(clienteDAOMock.buscarClientePorId(clienteOriginal.getId())).thenReturn(clienteOriginal);

        // Llamar al método editarCliente
        clienteController.editarCliente(clienteOriginal, nombreEditado, cuitEditado, emailEditado, direccionEditada, latEditada, lngEditada);

        // Verificar que se llame a clienteDAOMock.editarCliente con los datos actualizados
        verify(clienteDAOMock).editarCliente(argThat(cliente
                -> cliente.getNombre().equals(nombreEditado)
                && cliente.getCuit().equals(cuitEditado)
                && cliente.getEmail().equals(emailEditado)
                && cliente.getDireccion().equals(direccionEditada)
                && cliente.getCoord().getLat() == Double.parseDouble(latEditada)
                && cliente.getCoord().getLng() == Double.parseDouble(lngEditada)));
    }

    @Test
    public void testEliminarCliente() {
        int idClienteAEliminar = 1; // Suponemos que existe un cliente con este ID

        // Mockear comportamiento de clienteDAOMock
        when(clienteDAOMock.buscarClientePorId(idClienteAEliminar)).thenReturn(new Cliente(idClienteAEliminar, "Cliente a eliminar", "...", "...", "...", null));

        // Llamar al método eliminarCliente
        clienteController.eliminarCliente(idClienteAEliminar);

        // Verificar que se llame a clienteDAOMock.eliminarCliente con el ID correcto
        verify(clienteDAOMock).eliminarCliente(idClienteAEliminar);
    }

    @Test
    public void testListarClientes() {
        // Lista mockeada de clientes
        List<Cliente> clientesMockeados = new ArrayList<>();
        clientesMockeados.add(new Cliente(1, "Cliente 1", "...", "...", "...", null));
        clientesMockeados.add(new Cliente(2, "Cliente 2", "...", "...", "...", null));

        // Mockear comportamiento de clienteDAOMock
        when(clienteDAOMock.listarClientes()).thenReturn(clientesMockeados);

        // Llamar al método listarClientes
        List<Cliente> clientesObtenidos = clienteController.listarClientes();

        // Verificar que la lista obtenida tenga el mismo tamaño que la mockeada
        assertEquals(clientesMockeados.size(), clientesObtenidos.size());

        // Verificar que los clientes sean iguales (puedes usar un loop para iterar)
        // ...
    }

    @Test
    public void testBuscarClientePorIdExistente() {
        int idClienteABuscar = 1;

        // Coordenada para evitar NullPointerException y tener un objeto completo
        Coordenada coordenada = new Coordenada(10.0, 20.0);

        // Cliente mockeado CON Coordenada
        Cliente clienteMockeado = new Cliente(idClienteABuscar, "Cliente buscado", "CUIT de prueba", "email@prueba.com", "Direccion de prueba", coordenada);

        // Mockear comportamiento de clienteDAOMock
        when(clienteDAOMock.buscarClientePorId(idClienteABuscar)).thenReturn(clienteMockeado);

        // Llamar al método buscarClientePorId
        Cliente clienteEncontrado = clienteController.buscarClientePorId(idClienteABuscar);

        // Aserciones
        assertNotNull(clienteEncontrado, "El cliente encontrado no debería ser nulo");
        assertEquals(clienteMockeado.getId(), clienteEncontrado.getId(), "El ID del cliente no coincide");
        assertEquals(clienteMockeado.getNombre(), clienteEncontrado.getNombre(), "El nombre del cliente no coincide");
        assertEquals(clienteMockeado.getCuit(), clienteEncontrado.getCuit(), "El CUIT del cliente no coincide");
        assertEquals(clienteMockeado.getEmail(), clienteEncontrado.getEmail(), "El email del cliente no coincide");
        assertEquals(clienteMockeado.getDireccion(), clienteEncontrado.getDireccion(), "La dirección del cliente no coincide");
        assertEquals(clienteMockeado.getCoord().getLat(), clienteEncontrado.getCoord().getLat(), 0.001, "La latitud no coincide"); // Tolerancia para doubles
        assertEquals(clienteMockeado.getCoord().getLng(), clienteEncontrado.getCoord().getLng(), 0.001, "La longitud no coincide"); // Tolerancia para doubles
    }
}
