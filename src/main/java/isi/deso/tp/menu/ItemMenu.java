/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.menu;

/**
 *
 * @author deck
 */
public abstract class ItemMenu {

    Integer id;
    String nombre;
    String descripcion;
    Double precio;
    boolean aptoVegano;
    //Categoria categoria;
    Double peso;

    public abstract Double peso();

    public ItemMenu() {
    }

    public ItemMenu(Integer id, String nombre, String descripcion, Double precio, boolean aptoVegano, Double peso) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.aptoVegano = aptoVegano;
        this.peso = peso;
    }

    public abstract boolean esComida();

    public abstract boolean esBebida();

    public abstract boolean aptoVegano();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isAptoVegano() {
        return aptoVegano;
    }

    public void setAptoVegano(boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "ItemMenu{" + "nombre=" + nombre + '}';
    }

}
