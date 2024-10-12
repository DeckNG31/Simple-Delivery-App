/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp;

import exceptions.ItemNoEncontradoException;
import isi.deso.tp.menu.ItemPedido;
import isi.deso.tp.metodos.pago.MetodoPago;
import isi.deso.tp.usuarios.Cliente;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Deck
 */
public class ItemPedidoMemory implements ItemsPedidoDao {

    private List<ItemPedido> itemsPedido;  //""""Simula"""" la BD
    private Cliente cliente;
    //este seria el contexto de metodo de pago
    private MetodoPago metodoPago;
    private EstadoPedido estado;

    public ItemPedidoMemory() {
    }

    public ItemPedidoMemory(List<ItemPedido> itemsPedido, Cliente cliente) {
        this.itemsPedido = itemsPedido;
        this.cliente = cliente;
        this.estado = EstadoPedido.RECIBIDO;
    }

    public void setStrategyPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void pagar() {
        this.metodoPago.pagar();
        this.estado = EstadoPedido.REALIZADO;
    }

    @Override
    public String toString() {
        return "ItemPedidoMemory{" + "itemsPedido=" + itemsPedido + ", cliente=" + cliente + '}';
    }

    @Override
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

}
