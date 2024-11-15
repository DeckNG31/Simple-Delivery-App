/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.usuarios;

import isi.deso.tp.EstadoPedido;
import memories.PedidoMemory;
import isi.deso.tp.SuscriptorPedido;
import isi.deso.tp.metodos.pago.MercadoPago;
import isi.deso.tp.metodos.pago.MetodoPago;
import isi.deso.tp.metodos.pago.Pago;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deck
 */
public class Cliente implements SuscriptorPedido {

    private int id;
    private String nombre;
    private String cuit;
    private String email;
    private String direccion;
    
    private Coordenada coord;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Coordenada getCoord() {
        return coord;
    }

    public String getCoordString() {
        return coord.getLat() + ", " + coord.getLng();
    }

    public void setCoord(Coordenada coord) {
        this.coord = coord;
    }

    public String getNombre() {
        return nombre.toLowerCase();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente(int id, String nombre, String cuit, String email, String direccion, Coordenada coord) {
        this.id = id;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coord = coord;
        this.nombre = nombre;
    }

    public Cliente() {

    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", cuit=" + cuit + ", email=" + email + ", direccion=" + direccion + ", coord=" + coord + ", nombre=" + nombre + '}';
    }

    public static List<Cliente> buscarCliente(int id, List<Cliente> listaCliente) {
        List<Cliente> clientes = new ArrayList<Cliente>();

        for (Cliente cliente : listaCliente) {
            if (cliente.getId() == id) {
                clientes.add(cliente);
                return clientes;
            }
        }

        return clientes;
    }

    public static List<Cliente> buscarCliente(String nombre, List<Cliente> listaCliente) {
        List<Cliente> clientes = new ArrayList<Cliente>();

        nombre = nombre.toLowerCase();
        for (Cliente cliente : listaCliente) {
            if (nombre.equals(cliente.getNombre())) {
                clientes.add(cliente);
            }
        }

        return clientes;
    }

    @Override
    public void update(PedidoMemory p) {
        //aca va la logica que se ejecuta cuando el vendedor cambia el estado del pedido al que el cliente se suscribió
        System.out.println("Cliente: " + this.nombre + " ,Estado: " + p.getEstado());
        if (p.getEstado() == EstadoPedido.ACEPTADO && p.cliente.getId() == this.id) {

            Pago factura = new Pago(p);
            //setea estrategia
            MetodoPago mercadoPago = new MercadoPago("la.parri.mp");

            factura.setStrategyPago(mercadoPago);

            factura.pagar();
        }
    }

}
