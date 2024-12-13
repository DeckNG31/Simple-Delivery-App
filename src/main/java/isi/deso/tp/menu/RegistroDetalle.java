/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.menu;

import isi.deso.tp.Pedido;

/**
 *
 * @author mariano
 */
public class RegistroDetalle {

    private Integer itemMenuId;
    private Integer cantidad;

    public RegistroDetalle(Integer itemMenuId,Integer cantidad ) {

        this.itemMenuId = itemMenuId;
        this.cantidad = cantidad;
    }

    public Integer getItemMenuId() {
        return itemMenuId;
    }

    public void setItemMenuId(Integer itemMenuId) {
        this.itemMenuId = itemMenuId;
    }

   

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

   

}
