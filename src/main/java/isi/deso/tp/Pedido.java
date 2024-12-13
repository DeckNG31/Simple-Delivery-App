/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp;

import isi.deso.tp.menu.RegistroDetalle;
import isi.deso.tp.metodos.pago.MetodoPago;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author mariano
 */
public class Pedido {

    private Integer id;
    private EstadoPedido estado;
    private Double total;
    private LocalDate fecha;
    private Integer clienteId;
    private MetodoPago metodoPago;
    private List<RegistroDetalle> detalle;
    private Integer vendedorId;

    public Integer getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Integer vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Pedido(Integer id, EstadoPedido estado, Double total, LocalDate fecha, Integer clienteId, MetodoPago metodoPago, Integer vendedorId) {
        this.id = id;
        this.estado = estado;
        this.total = total;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.metodoPago = metodoPago;
        this.vendedorId = vendedorId;
    }

    public Pedido(EstadoPedido estado, Double total, LocalDate fecha, Integer clienteId, MetodoPago metodoPago, Integer vendedorId) {
        this.estado = estado;
        this.total = total;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.metodoPago = metodoPago;
        this.vendedorId = vendedorId;
    }

    public List<RegistroDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<RegistroDetalle> detalle) {
        this.detalle = detalle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

}
