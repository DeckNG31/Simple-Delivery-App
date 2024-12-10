/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import JDBCs.ClienteJDBC;
import DAOs.ClienteDAO;
import isi.deso.tp.usuarios.Cliente;
import isi.deso.tp.usuarios.Coordenada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariano
 */
public class ClienteController {

    private static ClienteController instance;

    private final ClienteDAO clienteDAO;

    private ClienteController() {
        clienteDAO = ClienteJDBC.getInstance();
    }

    public static synchronized ClienteController getInstance() {
        if (instance == null) {
            instance = new ClienteController();
        }
        return instance;
    }

    public void crearCliente(String nombre, String cuit, String email, String direccion, String lat, String lng) {
        Coordenada coord = new Coordenada(Double.parseDouble(lat), Double.parseDouble(lng));
        Cliente cliente = new Cliente(0, nombre, cuit, email, direccion, coord);

        clienteDAO.crearCliente(cliente);
    }

    public void editarCliente(Cliente clienteEditar, String nombre, String cuit, String email, String direccion, String lat, String lng) {

        clienteEditar.getCoord().setLat(Double.parseDouble(lat));
        clienteEditar.getCoord().setLng(Double.parseDouble(lng));

        clienteEditar.setNombre(nombre);
        clienteEditar.setCuit(cuit);
        clienteEditar.setEmail(email);
        clienteEditar.setDireccion(direccion);

        clienteDAO.editarCliente(clienteEditar);
    }

    public void eliminarCliente(Integer id) {
        clienteDAO.eliminarCliente(id);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    public Cliente buscarClientePorId(int id) {
        return clienteDAO.buscarClientePorId(id);
    }
    
    public ArrayList<Integer> obtenerIds(){
    return clienteDAO.obtenerIds();
    }
}
