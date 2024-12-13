/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.metodos.pago;

/**
 *
 * @author maria
 */
public class MercadoPago implements MetodoPago {

    private static final Double recargo = 0.04;

    private String alias;

    public MercadoPago(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public void pagar() {
        System.out.println("Pagando con mercado pago a " + alias + ", recargo del " + recargo * 100 + "%");

    }
    
    @Override
    public String getString(){
        return "mercadopago";
    }
    
     public MetodoPago getInstance(String alias){
    
        return null;
    
    }


    
}
