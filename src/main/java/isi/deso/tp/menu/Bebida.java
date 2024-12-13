/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.menu;

/**
 *
 * @author deck
 */
public class Bebida extends ItemMenu  {

    private Double volumen;
    private Double graduacionAlcohol;

    public Bebida() {
    }

    public Bebida(Integer id,String nombre, String descripcion, Double precio, boolean aptoVegano, Double peso, Double volumen, Double graduacionAlcohol, Integer vendedorId) {
        super(id,nombre, descripcion, precio, aptoVegano, peso, vendedorId);
        this.volumen = volumen;
        this.graduacionAlcohol = graduacionAlcohol;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Double getGraduacionAlcohol() {
        return graduacionAlcohol;
    }

    public void setGraduacionAlcohol(Double graduacionAlcohol) {
        this.graduacionAlcohol = graduacionAlcohol;
    }

    public boolean esAlcohol() {
        return this.graduacionAlcohol > 0;
    }

    @Override
    public Double peso() {
        if (this.esAlcohol()) {
            return (volumen * 0.99) + (peso * 1.2);
        }
        return (volumen * 1.04) + (peso * 1.2);

    }

    @Override
    public boolean esBebida() {
        return true;
    }

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean aptoVegano() {
        return this.aptoVegano;
    }

}
