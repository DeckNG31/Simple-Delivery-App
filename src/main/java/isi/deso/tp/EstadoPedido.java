/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package isi.deso.tp;

/**
 *
 * @author maria
 */
public enum EstadoPedido {
    RECIBIDO,
    ACEPTADO,
    PREPARACION,
    EN_ENVIO,
    ENVIADO;

    @Override
    public String toString() {
        return name();
    }
}
