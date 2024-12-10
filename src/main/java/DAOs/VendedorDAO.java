/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import isi.deso.tp.usuarios.Vendedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariano
 */
public interface VendedorDAO {

    public List<Vendedor> listarVendedores(); // Listado

    public void crearVendedor(Vendedor p); // crea

    public void editarVendedor(Vendedor p); // edita

    public void eliminarVendedor(int id); // elimina 

    public Vendedor buscarVendedorPorId(int id); // busca
    
     public ArrayList<Integer> obtenerIds();
}
