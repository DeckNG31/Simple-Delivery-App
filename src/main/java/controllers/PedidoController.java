package controllers;

import DAOs.ItemMenuDAO;
import DAOs.PedidoDAO;
import DTOs.PedidoItemMenuDTO;
import JDBCs.ItemMenuJDBC;
import JDBCs.PedidoJDBC;
import helpers.HelpersVista;
import isi.deso.tp.EstadoPedido;
import isi.deso.tp.Pedido;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.menu.RegistroDetalle;
import isi.deso.tp.metodos.pago.Efectivo;
import isi.deso.tp.metodos.pago.MercadoPago;
import isi.deso.tp.metodos.pago.MetodoPago;
import isi.deso.tp.metodos.pago.Transferencia;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PedidoController {

    private static PedidoController instance;

    private PedidoDAO pedidoDAO;
    private final ItemMenuDAO itemMenuDAO;

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    private PedidoController() {
        pedidoDAO = PedidoJDBC.getInstance(); // Suponiendo que tienes un PedidoJDBC implementando PedidoDAO
        itemMenuDAO = ItemMenuJDBC.getInstance();
    }

    public static synchronized PedidoController getInstance() {
        if (instance == null) {
            instance = new PedidoController();
        }
        return instance;
    }

    private static final Pattern CUIT_PATTERN = Pattern.compile("^\\d{11}$");
    private static final Pattern CBU_PATTERN = Pattern.compile("^\\d{22}$");
    private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-zA-Z0-9\\.]{6,20}$");

    private void validarCUIT(String cuit) {
        Matcher matcher = CUIT_PATTERN.matcher(cuit);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El CUIT proporcionado no es válido.");
        }
    }

    private void validarCBU(String cbu) {
        Matcher matcher = CBU_PATTERN.matcher(cbu);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El CBU proporcionado no es válido.");
        }
    }

    private void validarAlias(String alias) {
        Matcher matcher = ALIAS_PATTERN.matcher(alias);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El alias proporcionado no es válido.");
        }
    }

    // Crear un pedido
    public void crearPedido(List<RegistroDetalle> detalle, Double total, LocalDate fecha, Integer clienteId, String metodoPagoStr, String alias, String cbu, String cuit, Integer vendedorId) {

        // Convertir el String de metodoPago a un objeto MetodoPago
        MetodoPago metodoPago = obtenerMetodoPago(metodoPagoStr, cbu, cuit, alias);

        // Crear un objeto Pedido
        Pedido pedido = new Pedido(EstadoPedido.PREPARACION, total, fecha, clienteId, metodoPago, vendedorId);

        // Persistir el pedido usando el DAO
        try {
            Integer pedidoId = pedidoDAO.crearPedido(pedido);
            pedidoDAO.cargarDetalle(pedidoId, detalle);
        } catch (Exception e) {
            HelpersVista.mostrarMensaje(e.getMessage(), "Error", "error");
        }

    }

    public void actualizarEstadoPedido(Integer pedidoId, String estado) {
        pedidoDAO.actualizarEstadoPedido(pedidoId, estado);
    }

    public PedidoItemMenuDTO obtenerPedidosDeCliente(Integer clienteId) {
        List<Pedido> pedidos = pedidoDAO.obtenerPedidosDeCliente(clienteId);
        Set<Integer> itemsMenuIds = new HashSet<>();
        pedidos.forEach((p) -> {
            List<RegistroDetalle> detalle = pedidoDAO.obtenerDetalleDePedido(p.getId());

            p.setDetalle(detalle);

            detalle.forEach((pd) -> {
                itemsMenuIds.add(pd.getItemMenuId());
            });
        });
        List<ItemMenu> itemsMenus = itemMenuDAO.buscarItemMenuPorIds(new ArrayList<Integer>(itemsMenuIds));

        return new PedidoItemMenuDTO(pedidos, itemsMenus);
    }

    public PedidoItemMenuDTO obtenerPedidos() {
        List<Pedido> pedidos = pedidoDAO.obtenerPedidos();
        Set<Integer> itemsMenuIds = new HashSet<>();
        pedidos.forEach((p) -> {
            List<RegistroDetalle> detalle = pedidoDAO.obtenerDetalleDePedido(p.getId());

            p.setDetalle(detalle);

            detalle.forEach((pd) -> {
                itemsMenuIds.add(pd.getItemMenuId());
            });
        });
        List<ItemMenu> itemsMenus = itemMenuDAO.buscarItemMenuPorIds(new ArrayList<Integer>(itemsMenuIds));

        return new PedidoItemMenuDTO(pedidos, itemsMenus);
    }

    public PedidoItemMenuDTO obtenerPedidosDeVendedor(Integer vendedorId) {
        List<Pedido> pedidos = pedidoDAO.obtenerPedidosDeVendedor(vendedorId);
        Set<Integer> itemsMenuIds = new HashSet<>();
        pedidos.forEach((p) -> {
            List<RegistroDetalle> detalle = pedidoDAO.obtenerDetalleDePedido(p.getId());

            p.setDetalle(detalle);

            detalle.forEach((pd) -> {
                itemsMenuIds.add(pd.getItemMenuId());
            });
        });
        List<ItemMenu> itemsMenus = itemMenuDAO.buscarItemMenuPorIds(new ArrayList<Integer>(itemsMenuIds));

        return new PedidoItemMenuDTO(pedidos, itemsMenus);
    }

    
    // Eliminar un pedido
    public void eliminarPedido(int id) {
        // Eliminar el pedido usando el DAO
        pedidoDAO.eliminarPedido(id);
    }

    // Método auxiliar para convertir un String a un objeto MetodoPago
    private MetodoPago obtenerMetodoPago(String metodoPagoStr, String cbu, String cuit, String alias) {
        // Aquí deberías mapear el String al tipo de MetodoPago adecuado.

        switch (metodoPagoStr.toLowerCase()) {
            case "efectivo":
                return new Efectivo();
            case "transferencia":
                validarCUIT(cuit);
                validarCBU(cbu);
                return new Transferencia(cuit, cbu);
            case "mercadopago":
                validarAlias(alias);
                return new MercadoPago(alias);
            default:
                return null; // O lanzar una excepción si el método de pago no es válido
        }
    }
}
