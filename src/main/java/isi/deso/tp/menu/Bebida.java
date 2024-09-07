/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.menu;

/**
 *
 * @author deck
 */
public class Bebida extends ItemMenu {

    Double volumen;
    Double graduacionAlcohol;

    public Bebida() {
    }
    
    public Bebida(Double volumen, Double graduacionAlcohol, Integer id, String nombre, String descripcion, Double precio, boolean aptoVegano, Double peso) {
        super(id, nombre, descripcion, precio, aptoVegano, peso);
        this.volumen = volumen;
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
