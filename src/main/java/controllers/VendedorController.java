/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import isi.deso.tp.usuarios.Coordenada;
import memories.VendedorMemory;

/**
 *
 * @author mariano
 */
public class VendedorController {

    public void crearVendedor(String nombre, String direccion, String lat, String lng) {

        VendedorMemory InstanceVendedorMemory = VendedorMemory.getInstance();

        Coordenada coord = new Coordenada(Double.parseDouble(lat), Double.parseDouble(lng));

        InstanceVendedorMemory.crearVendedor(nombre, direccion,coord);
    }
    
    public void editarVendedor(Integer id, String nombre, String direccion, String lat, String lng) {

        VendedorMemory InstanceVendedorMemory = VendedorMemory.getInstance();

        Coordenada coord = new Coordenada(Double.parseDouble(lat), Double.parseDouble(lng));

        InstanceVendedorMemory.editarVendedor(id,nombre, direccion,coord);
    }
    
    public void eliminarVendedor(Integer id){
        VendedorMemory InstanceVendedorMemory = VendedorMemory.getInstance();
          InstanceVendedorMemory.eliminarVendedor(id);
    }
}
