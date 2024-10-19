/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.menu;

import isi.deso.tp.usuarios.Vendedor;

/**
 *
 * @author Deck
 */
public class ItemPedido {

    private ItemMenu item;
    private Integer cantidad;
    private Vendedor vendedor;
    private String restaurante;
    private Double precio = 0.0;

    public ItemPedido(ItemMenu item, Integer cantidad, Vendedor vendedor) {
        this.item = item;
        this.cantidad = cantidad;
        this.vendedor = vendedor;
        this.restaurante = vendedor.getNombre();
        this.precio = cantidad * item.getPrecio();
    }

    public Double getPrecio() {
        return precio;
    }

    public ItemMenu getItem() {
        return item;
    }

    public void setItem(ItemMenu item) {
        this.item = item;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getRestaurante() {
        return restaurante;
    }

    @Override
    public String toString() {
        return "ItemPedido{" + "item=" + item + ", cantidad=" + cantidad + ", restaurante=" + restaurante + ", total=" + precio + '}';
    }

}
