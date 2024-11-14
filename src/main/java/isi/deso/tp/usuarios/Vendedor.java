/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.usuarios;


import exceptions.ItemNoEncontradoException;
import memories.PedidoMemory;

import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.menu.Plato;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deck
 */
public class Vendedor {

    private int id;
    private String nombre;
    private String direccion;
    private Coordenada coord;
    
    private List<ItemMenu> listItems = new ArrayList();
    
    
    private List<PedidoMemory> listPedidos = new ArrayList();

    public List<PedidoMemory> getListPedidos() {
        return listPedidos;
    }

    public void setListPedidos(List<PedidoMemory> listPedidos) {
        this.listPedidos = listPedidos;
    }

    public void addPedido(PedidoMemory pedido) {
        this.listPedidos.add(pedido);
        pedido.setVendedor(this.id);
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return  coord.getLat() + ", " + coord.getLng();
    }
    
    public void setCoord(Coordenada coord) {
        this.coord = coord;
    }

    public Vendedor(int id, String nombre, String direccion, Coordenada coord) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coord = coord;
    }

    public Vendedor() {
    }

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", coord= " + coord.toString() + '}';
    }

    public static List<Vendedor> buscarVendedor(int id, List<Vendedor> v) {
        List<Vendedor> nombre = new ArrayList<Vendedor>();

        for (Vendedor vendedor : v) {
            if (vendedor.getId() == id) {
                nombre.add(vendedor);
                return nombre;
            }
        }
        throw new ItemNoEncontradoException("Vendedor con ID " + id + " no encontrado.");
    }

    public static List<Vendedor> buscarVendedor(String nombre, List<Vendedor> v) {
        List<Vendedor> listaVendedores = new ArrayList<Vendedor>();
        nombre = nombre.toLowerCase();
        for (Vendedor vendedor : v) {
            if (nombre.equals(vendedor.getNombre().toLowerCase())) {
                listaVendedores.add(vendedor);
            }
        }
        if (listaVendedores.isEmpty()) {
            throw new ItemNoEncontradoException("Vendedor con nombre " + nombre + " no encontrado.");
        }
        return listaVendedores;
    }

    public double distancia(Cliente cliente) {
        double R = 6371.0; // Radio de la Tierra en km

        double lat1 = Math.toRadians(this.coord.getLat());
        double lon1 = Math.toRadians(this.coord.getLng());
        double lat2 = Math.toRadians(cliente.getCoord().getLat());
        double lon2 = Math.toRadians(cliente.getCoord().getLng());

        double deltaLat = lat1 - lat2;
        double deltaLng = lon2 - lon1;

        double semiversinLat = Math.pow(Math.sin((deltaLat) / 2), 2);
        double semiversinLng = Math.pow(Math.sin((deltaLng) / 2), 2);

        double h = semiversinLat + Math.cos(lat1) * Math.cos(lat2) * semiversinLng;

        // Retorna la distancia en kilómetros
        return 2 * R * Math.asin(Math.sqrt(h));

    }
/*
    public void AddItemMenu(ItemMenu item) {
        listItems.add(item);
    }

    public List<Plato> getPlatos() {
        List<Plato> aux = new ArrayList<Plato>();
        for (ItemMenu e : listItems) {
            if (e instanceof Plato) {
                aux.add((Plato) e);
            }
        }
        return aux;
    }

    public List<Bebida> getBebidas() {
        List<Bebida> aux = new ArrayList<Bebida>();
        for (ItemMenu e : listItems) {
            if (e instanceof Bebida) {
                aux.add((Bebida) e);
            }
        }
        return aux;
    }

    public List<Plato> getComidasVeganas() {
        List<Plato> aux = new ArrayList<Plato>();
        for (ItemMenu e : listItems) {
            if (e instanceof Plato) {
                if (e.aptoVegano()) {
                    aux.add((Plato) e);
                }
            }
        }
        return aux;
    }

    public List<Bebida> getBebidasSinAlcohol() {
        List<Bebida> aux = new ArrayList<Bebida>();
        for (ItemMenu e : listItems) {
            if (e instanceof Bebida) {
                if (!((Bebida) e).esAlcohol()) {
                    aux.add((Bebida) e);
                }
            }
        }
        return aux;
    }
*/
}
