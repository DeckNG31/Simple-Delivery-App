/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isi.deso.tp;

import exceptions.ItemNoEncontradoException;
import isi.deso.tp.menu.ItemPedido;
import java.util.List;

/**
 *
 * @author marianoo
 */
public interface ItemsPedidoDao {

    public List<ItemPedido> getTodosItems();

    //Criterio podria ser un enum
    //Criterio: precio , restaurante , tipo(plato o bebida) ...
    //notar que en lenguaje natural nos referimos a "pedidos" , pero la implementacion es llevada en forma de "items de pedido"
    public List<ItemPedido> ordernarPorPrecio(boolean asc); //si es true los pedidos se ordenan de forma ascendente segun su precio , sino en forma descendente

    // Método para filtrar ítems por precio (rango de precios)
    public List<ItemPedido> buscarPorRangoDePrecios(double precioMin, double precioMax);

    // Método para buscar por nombre del restaurante
    public List<ItemPedido> buscarPorRestaurante(String restaurante);



  
   
    
}
