/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import isi.deso.tp.usuarios.Coordenada;
import memories.ClienteMemory;

/**
 *
 * @author mariano
 */
public class ClienteController {

    public void crearCliente(String nombre, String cuit, String email, String direccion, String lat, String lng) {

        ClienteMemory InstanceClienteMemory = ClienteMemory.getInstance();

        Coordenada coord = new Coordenada(Double.parseDouble(lat), Double.parseDouble(lng));

        InstanceClienteMemory.crearCliente(nombre, cuit, email, direccion, coord);
    }

    public void editarCliente(Integer id, String nombre, String cuit, String email, String direccion, String lat, String lng) {

        ClienteMemory InstanceClienteMemory = ClienteMemory.getInstance();

        Coordenada coord = new Coordenada(Double.parseDouble(lat), Double.parseDouble(lng));

        InstanceClienteMemory.editarCliente(id, nombre, cuit, email, direccion, coord);
    }

    public void eliminarCliente(Integer id) {
        ClienteMemory InstanceClienteMemory = ClienteMemory.getInstance();
        InstanceClienteMemory.eliminarCliente(id);
    }
}
