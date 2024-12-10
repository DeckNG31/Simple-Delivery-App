/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import isi.deso.tp.usuarios.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariano
 */
public interface ClienteDAO {

    public List<Cliente> listarClientes(); // Listado

    public void crearCliente(Cliente p); // crea

    public void editarCliente(Cliente p); // edita

    public void eliminarCliente(int id); // elimina 

    public Cliente buscarClientePorId(int id); // busca
    
    public ArrayList<Integer> obtenerIds();
}
