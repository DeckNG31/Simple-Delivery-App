/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memories;

import isi.deso.tp.usuarios.Cliente;
import isi.deso.tp.usuarios.Coordenada;
import java.util.HashSet;

/**
 *
 * @author mariano
 */
public class ClienteMemory {

    private static ClienteMemory instance;
    public HashSet<Cliente> clientes;
    public Integer idCount = 0;

    public ClienteMemory() {
        clientes = new HashSet<>();
    }

    public static ClienteMemory getInstance() {
        if (instance == null) {
            instance = new ClienteMemory();
        }
        return instance;
    }

    public void crearCliente(String nombre, String cuit, String email, String direccion, Coordenada coord) {
        Cliente c = new Cliente(idCount, nombre, cuit, email, direccion, coord);
        clientes.add(c);
        idCount++;

    }

    public void editarCliente(Integer id, String nombre, String cuit, String email, String direccion, Coordenada coord) {
        Cliente c = buscarCliente(id);

        c.setCuit(cuit);
        c.setEmail(email);
        c.setCoord(coord);
        c.setDireccion(direccion);
        c.setNombre(nombre);
    }

    public void mostrar() {
        clientes.stream().forEach(c -> System.out.println(c.toString()));
    }

    public Cliente buscarCliente(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void eliminarCliente(int id) {
        Cliente clienteAEliminar = clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        // Si se encuentra, eliminarlo del conjunto
        if (clienteAEliminar != null) {
            clientes.remove(clienteAEliminar);
        }
    }
}
