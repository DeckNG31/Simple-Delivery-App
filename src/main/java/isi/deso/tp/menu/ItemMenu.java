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
    public abstract boolean esComida();
    public abstract boolean esBebida();
    public abstract boolean aptoVegano();
    
    
   
    
    
    
    
}
