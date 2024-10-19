/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp;

import exceptions.ItemNoEncontradoException;
import isi.deso.tp.menu.ItemPedido;
import isi.deso.tp.metodos.pago.MetodoPago;
import isi.deso.tp.usuarios.Cliente;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Deck
 */
public class ItemPedidoMemory implements ItemsPedidoDao {

    public List<ItemPedido> itemsPedido;  //""""Simula"""" la BD
    public Cliente cliente;
    //este seria el contexto de metodo de pago
    private MetodoPago metodoPago;
    private EstadoPedido estado;

    //TODO: calcular total metodo
    
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


    public EstadoPedido getEstado() {
        return estado;
    }

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
        //crear Pagar class
        
        this.metodoPago.pagar();
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

        /*NOTA!!!
    ItemPedido::getPrecio es equivalente a item -> item.getPrecio() , pero es mas compacto claramente
         */
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

        /*
    NOTA!!!:
    equalsIgnoreCase no tiene en cuenta mayusculas y minusculas , esto es bueno porque por ahi se pone el nombre
    del mismo restaurante en minus o mayus y queremos que se tome por lo mismo
         */
        if (resultado.isEmpty()) {
            throw new ItemNoEncontradoException("Item No Encontrado");
        }

        return resultado;
    }

}
