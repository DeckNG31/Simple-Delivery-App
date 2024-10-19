/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.metodos.pago;

import isi.deso.tp.ItemPedidoMemory;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author mariano
 */
public class Pago {

    LocalDateTime fecha;
    ItemPedidoMemory pedido;
    Double total;
    MetodoPago metodoPago;

    

    public Pago(ItemPedidoMemory pedido) {
        this.pedido = pedido;
        this.total = pedido.calcularTotal();
        this.fecha = LocalDateTime.now();
    }

    public void setStrategyPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void pagar() {
        //crear Pagar class
        System.out.println("Pagando pedido: "+ this.pedido.toString());
        System.out.println("Total: "+ this.total);
         System.out.println("Fecha: "+ this.fecha);
        this.metodoPago.pagar();
    }
}
