/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.menu;

/**
 *
 * @author deck
 */
public class Plato extends ItemMenu  {

    private Double calorias;
    private boolean aptoCeliaco;

    public Plato() {
    }

    public Plato(Integer id,String nombre, String descripcion, Double precio, boolean aptoVegano, Double peso, Double calorias, boolean aptoCeliaco, Integer vendedorId) {
        super(id,nombre, descripcion, precio, aptoVegano, peso, vendedorId);
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
    }

    @Override
    public Double peso() {
        peso = peso * (1.1);
        return peso;
    }

    @Override
    public boolean aptoVegano() {
        return this.aptoVegano;
    }

    @Override
    public boolean esBebida() {
        return false;
    }

    @Override
    public boolean esComida() {
        return true;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public boolean isAptoCeliaco() {
        return aptoCeliaco;
    }

    public void setAptoCeliaco(boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    

    public boolean isAptoVegano() {
        return aptoVegano;
    }

    public void setAptoVegano(boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
    }

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

    /*public Object getCategoria() {
        return categoria;
    }
     */
 /*  public void setCategoria(Object categoria) {
        this.categoria = categoria;
    }
     */
    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

}
