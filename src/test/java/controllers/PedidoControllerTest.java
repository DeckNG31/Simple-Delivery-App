/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author mariano
 */
import DAOs.ItemMenuDAO;
import DAOs.PedidoDAO;
import DTOs.PedidoItemMenuDTO;
import isi.deso.tp.EstadoPedido;
import isi.deso.tp.Pedido;
import isi.deso.tp.menu.RegistroDetalle;
import isi.deso.tp.metodos.pago.Efectivo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mockito.ArgumentMatchers;

public class PedidoControllerTest {

    @Mock
    private PedidoDAO pedidoDAOMock;

    @Mock
    private ItemMenuDAO itemMenuDAOMock;

    private PedidoController pedidoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoController = PedidoController.getInstance();
        pedidoController.setPedidoDAO(pedidoDAOMock);

        itemMenuDAOMock = ItemMenuController.getInstance().getItemMenuDAO();
    }

    @Test
    public void testCrearPedido() throws Exception {
        // Arrange
        List<RegistroDetalle> detalle = new ArrayList<>();
        detalle.add(new RegistroDetalle(1, 2)); // ItemMenuId: 1, Cantidad: 2

        Double total = 100.0;
        LocalDate fecha = LocalDate.now();
        Integer clienteId = 1;
        String metodoPagoStr = "efectivo";
        String alias = null;
        String cbu = null;
        String cuit = null;
        Integer vendedorId = 1;

        when(pedidoDAOMock.crearPedido(any(Pedido.class))).thenReturn(1);

        // Act
        pedidoController.crearPedido(detalle, total, fecha, clienteId, metodoPagoStr, alias, cbu, cuit, vendedorId);

        // Assert
        ArgumentCaptor<Pedido> pedidoCaptor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidoDAOMock).crearPedido(pedidoCaptor.capture());

        Pedido pedidoCapturado = pedidoCaptor.getValue();
        assertNotNull(pedidoCapturado);
        assertEquals(EstadoPedido.PREPARACION, pedidoCapturado.getEstado());
        assertEquals(total, pedidoCapturado.getTotal());
        assertEquals(fecha, pedidoCapturado.getFecha());
        assertEquals(clienteId, pedidoCapturado.getClienteId());
        assertEquals(vendedorId, pedidoCapturado.getVendedorId());
        assertTrue(pedidoCapturado.getMetodoPago() instanceof Efectivo);

        verify(pedidoDAOMock).cargarDetalle(eq(1), eq(detalle));
    }

    @Test
    public void testActualizarEstadoPedido() {
        // Arrange
        Integer pedidoId = 1;
        String nuevoEstado = "ENTREGADO";

        // Act
        pedidoController.actualizarEstadoPedido(pedidoId, nuevoEstado);

        // Assert
        verify(pedidoDAOMock).actualizarEstadoPedido(pedidoId, nuevoEstado);
    }

    @Test
    public void testEliminarPedido() {
        // Arrange
        int pedidoId = 1;

        // Act
        pedidoController.eliminarPedido(pedidoId);

        // Assert
        verify(pedidoDAOMock).eliminarPedido(pedidoId);
    }

}
