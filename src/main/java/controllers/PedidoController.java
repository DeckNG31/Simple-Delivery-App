package controllers;

import DAOs.PedidoDAO;
import JDBCs.PedidoJDBC;
import isi.deso.tp.Pedido;
import isi.deso.tp.metodos.pago.MetodoPago;
import java.util.List;
import java.time.LocalDate;

public class PedidoController {

    private static PedidoController instance;

    private final PedidoDAO pedidoDAO;

    private PedidoController() {
        pedidoDAO = PedidoJDBC.getInstance(); // Suponiendo que tienes un PedidoJDBC implementando PedidoDAO
    }

    public static synchronized PedidoController getInstance() {
        if (instance == null) {
            instance = new PedidoController();
        }
        return instance;
    }

    // Crear un pedido
    public void crearPedido(Double total, LocalDate fecha, Integer clienteId, String metodoPagoStr) {
        // Convertir el String de metodoPago a un objeto MetodoPago
        MetodoPago metodoPago = obtenerMetodoPago;

        // Crear un objeto Pedido
        Pedido pedido = new Pedido(total, fecha, clienteId, metodoPago /*, detallepedido*/);

        // Persistir el pedido usando el DAO
        pedidoDAO.crearPedido(pedido);
    }

    // Editar un pedido
    public void editarPedido(Pedido pedidoEditar, Double total, LocalDate fecha, Integer clienteId, String metodoPagoStr) {
        // Convertir el String de metodoPago a un objeto MetodoPago
        MetodoPago metodoPago = obtenerMetodoPago(metodoPagoStr);

        // Actualizar los atributos del pedido
        pedidoEditar.setTotal(total);
        pedidoEditar.setFecha(fecha);
        pedidoEditar.setClienteId(clienteId);
        pedidoEditar.setMetodoPago(metodoPago);

        // Llamar al DAO para actualizar el pedido
        pedidoDAO.editarPedido(pedidoEditar);
    }

    // Eliminar un pedido
    public void eliminarPedido(int id) {
        // Eliminar el pedido usando el DAO
        pedidoDAO.eliminarPedido(id);
    }

    // Listar todos los pedidos
    public List<Pedido> listarPedidos() {
        return pedidoDAO.listarPedidos();
    }

    // Buscar un pedido por su ID
    public Pedido buscarPedidoPorId(int id) {
        return pedidoDAO.buscarPedidoPorId(id);
    }

    // Método auxiliar para convertir un String a un objeto MetodoPago
    private MetodoPago obtenerMetodoPago(String metodoPagoStr) {
        // Aquí deberías mapear el String al tipo de MetodoPago adecuado.
        switch (metodoPagoStr.toLowerCase()) {
            case "efectivo":
                return (new Efectivo);
            case "mercadopago":
                return MetodoPago.MERCADOPAGO;
            case "transferencia":
                return new MercadoPago;
            default:
                return null; // O lanzar una excepción si el método de pago no es válido
        }
    }
}
