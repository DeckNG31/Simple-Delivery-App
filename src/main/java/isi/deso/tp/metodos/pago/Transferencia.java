/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.metodos.pago;

/**
 *
 * @author maria
 */
public class Transferencia implements MetodoPago {

    private static final Double recargo = 0.02;

    private String cuit;
    private String cbu;

    public Transferencia(String cuit, String cbu) {
        this.cuit = cuit;
        this.cbu = cbu;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    @Override
    public void pagar() {
        System.out.println("Pagando con transferencia a " + cbu + ", recargo del " + recargo * 100 + "%");
    }
}
