/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp;

import exceptions.ItemNoEncontradoException;
import isi.deso.tp.menu.ItemPedido;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Deck
 */
public class ItemPedidoMemory implements ItemsPedidoDao{
    
    public List<ItemPedido> itemsPedido;  //""""Simula"""" la BD

    public ItemPedidoMemory() {
    }
   

    public ItemPedidoMemory(List<ItemPedido> itemsPedido) {
        this.itemsPedido = new ArrayList<>();
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
    
    if(resultado.isEmpty()) throw new ItemNoEncontradoException("Item No Encontrado");
       
     return resultado;
    
    }

    @Override
    public List<ItemPedido> buscarPorRangoDePrecios(double precioMin, double precioMax) {
       List <ItemPedido> resultado = itemsPedido.stream()
       .filter(item -> item.getPrecio() >= precioMin && item.getPrecio()<=precioMax)
       .collect(Collectors.toList());

       if(resultado.isEmpty()) throw new ItemNoEncontradoException("Item No Encontrado");
       
       return resultado;
    }

    @Override
    public List<ItemPedido> buscarPorRestaurante(String restaurante) {
    List<ItemPedido> resultado = itemsPedido.stream()
    .filter(item-> item.getRestaurante().equalsIgnoreCase(restaurante))
    .collect(Collectors.toList());
    
    /*
    NOTA!!!:
    equalsIgnoreCase no tiene en cuenta mayusculas y minusculas , esto es bueno porque por ahi se pone el nombre
    del mismo restaurante en minus o mayus y queremos que se tome por lo mismo
    */
    
    if(resultado.isEmpty()) throw new ItemNoEncontradoException("Item No Encontrado");
    
    return resultado;
    }
    
 
}
