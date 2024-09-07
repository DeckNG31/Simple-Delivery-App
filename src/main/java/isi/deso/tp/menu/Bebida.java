/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.menu;

/**
 *
 * @author deck
 */
public class Bebida extends ItemMenu{
    Double volumen;
    Double graduacionAlcohol;
   
 
    
    public boolean esAlcohol(){
        if(this.graduacionAlcohol > 0) return true;
        else return false;
    }
    
   @Override
    public Double peso(){
      if(this.esAlcohol())  peso = (volumen*0.99) + (peso*1.2);
      else peso = (volumen*1.04) + (peso*1.2);
      return peso;
      
    }
    
    @Override
    public boolean esBebida(){
        return true;
    }
    
    @Override
    public boolean esComida(){
        return false;
    }
    
    @Override
    public boolean aptoVegano(){
        return this.aptoVegano;
    }
    
}
