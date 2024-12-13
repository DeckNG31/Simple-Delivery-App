/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memories;

import DAOs.PedidoDAO;
import exceptions.ItemNoEncontradoException;
import isi.deso.tp.EstadoPedido;
import isi.deso.tp.Pedido;
import isi.deso.tp.menu.ItemPedido;
import isi.deso.tp.menu.RegistroDetalle;
import isi.deso.tp.metodos.pago.MetodoPago;
import isi.deso.tp.usuarios.Cliente;
import isi.deso.tp.usuarios.Vendedor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Deck
 */
public class PedidoMemory implements PedidoDAO {

    public List<ItemPedido> itemsPedido;  //""""Simula"""" la BD
    public Cliente cliente;
    public Integer vendedorId;

    private EstadoPedido estado;

    //Patron observer
    private List<Cliente> clientesSuscriptos = new ArrayList(); //clientes que se quieren enterar de este pedido

    public void addSuscriptor(Cliente c) {
        clientesSuscriptos.add(c);
    }

    public void removeSuscriptor(Cliente c) {
        clientesSuscriptos.remove(c);
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        //notificar a clientes (observer)
        clientesSuscriptos.stream().forEach(c -> c.update(this));
    }

    public Integer getVendedor() {
        return vendedorId;
    }

    public void setVendedor(Integer vendedor) {
        this.vendedorId = vendedor;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public Double calcularTotal() {
        return itemsPedido.stream()
                .map(ItemPedido::getPrecio) // Obtenemos los precios de los items
                .reduce(0.0, Double::sum); // Sumamos los precios
    }

    public PedidoMemory() {
    }

    public PedidoMemory(List<ItemPedido> itemsPedido, Cliente cliente) {
        this.itemsPedido = itemsPedido;
        this.cliente = cliente;
        this.estado = EstadoPedido.RECIBIDO;
    }

    @Override
    public String toString() {
        return "ItemPedidoMemory{" + "itemsPedido=" + itemsPedido + ", cliente=" + cliente + '}';
    }

    public List<ItemPedido> getTodosItems() {
        return itemsPedido;
    }

    @Override
    public List<ItemPedido> ordernarPorPrecio(boolean asc) {
        List<ItemPedido> resultado = itemsPedido.stream()
                .sorted(asc ? Comparator.comparing(ItemPedido::getPrecio) : Comparator.comparing(ItemPedido::getPrecio).reversed())
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemNoEncontradoException("Item No Encontrado");
        }

        return resultado;

    }

    @Override
    public List<ItemPedido> buscarPorRangoDePrecios(double precioMin, double precioMax) {
        List<ItemPedido> resultado = itemsPedido.stream()
                .filter(item -> item.getPrecio() >= precioMin && item.getPrecio() <= precioMax)
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemNoEncontradoException("Item No Encontrado");
        }

        return resultado;
    }

    @Override
    public List<ItemPedido> buscarPorRestaurante(String restaurante) {
        List<ItemPedido> resultado = itemsPedido.stream()
                .filter(item -> item.getRestaurante().equalsIgnoreCase(restaurante))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new ItemNoEncontradoException("Item No Encontrado");
        }

        return resultado;
    }

    

    @Override
    public Integer crearPedido(Pedido p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public void eliminarPedido(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public void cargarDetalle(Integer pedidoId, List<RegistroDetalle> detalle) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pedido> obtenerPedidosDeCliente(Integer clienteId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<RegistroDetalle> obtenerDetalleDePedido(Integer pedidoId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pedido> obtenerPedidosDeVendedor(Integer vendedorId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarEstadoPedido(Integer pedidoId, String estado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pedido> obtenerPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
