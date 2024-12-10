/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAOs.ClienteDAO;
import DAOs.VendedorDAO;
import JDBCs.VendedorJDBC;
import isi.deso.tp.usuarios.Coordenada;
import isi.deso.tp.usuarios.Vendedor;
import java.util.ArrayList;
import memories.VendedorMemory;

import java.util.List;

/**
 *
 * @author mariano
 */
public class VendedorController {

    private static VendedorController instance;
    private final VendedorDAO vendedorDAO;

    // Constructor privado para Singleton
    public VendedorController() {
        this.vendedorDAO = VendedorJDBC.getInstance(); // Implementación concreta del DAO
    }

    // Método para obtener la instancia única (Singleton)
    public static synchronized VendedorController getInstance() {
        if (instance == null) {
            instance = new VendedorController();
        }
        return instance;
    }

    // Crear un nuevo vendedor
    public void crearVendedor(String nombre, String direccion, String lat, String lng) {
        Coordenada coord = new Coordenada(Double.parseDouble(lat), Double.parseDouble(lng));
        Vendedor vendedor = new Vendedor(0, nombre, direccion, coord); // ID inicializado en 0 para nuevo registro
        vendedorDAO.crearVendedor(vendedor); // Llama al DAO para guardar el vendedor
    }

    // Editar un vendedor existente
    public void editarVendedor(int id, String nombre, String direccion, String lat, String lng) {
        Vendedor vendedor = vendedorDAO.buscarVendedorPorId(id); // Busca el vendedor en la base de datos
        if (vendedor != null) {
            vendedor.setNombre(nombre);
            vendedor.setDireccion(direccion);
            vendedor.getCoord().setLat(Double.parseDouble(lat));
            vendedor.getCoord().setLng(Double.parseDouble(lng));
            vendedorDAO.editarVendedor(vendedor); // Llama al DAO para actualizar el vendedor
        } else {
            throw new IllegalArgumentException("No se encontró el vendedor con ID: " + id);
        }
    }

    // Eliminar un vendedor
    public void eliminarVendedor(int id) {
        vendedorDAO.eliminarVendedor(id); // Llama al DAO para eliminar el vendedor
    }

    // Listar todos los vendedores
    public List<Vendedor> listarVendedores() {
        return vendedorDAO.listarVendedores(); // Llama al DAO para obtener la lista de vendedores
    }

    // Buscar un vendedor por ID
    public Vendedor buscarVendedorPorId(int id) {
        return vendedorDAO.buscarVendedorPorId(id); // Llama al DAO para buscar un vendedor por ID
    }

    public ArrayList<Integer> obtenerIds() {
        return vendedorDAO.obtenerIds();
    }
}
