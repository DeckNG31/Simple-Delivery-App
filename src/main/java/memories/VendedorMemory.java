/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memories;

import isi.deso.tp.usuarios.Coordenada;
import isi.deso.tp.usuarios.Vendedor;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author mariano
 */
public class VendedorMemory {

    private static VendedorMemory instance;
    public HashSet<Vendedor> vendedores;
    public Integer idCount = 0;

    public VendedorMemory() {
        vendedores = new HashSet<>();
    }

    public static VendedorMemory getInstance() {
        if (instance == null) {
            instance = new VendedorMemory();
        }
        return instance;
    }

    public void crearVendedor(String nombre, String direccion, Coordenada cord) {
        Vendedor v = new Vendedor(idCount, nombre, direccion, cord);
        vendedores.add(v);
        idCount++;

    }

    public void editarVendedor(Integer id, String nombre, String direccion, Coordenada cord) {
        Vendedor v = buscarVendedor(id);

        v.setNombre(nombre);
        v.setDireccion(direccion);
        v.setCoord(cord);
    }

    public void mostrar() {
        vendedores.stream().forEach(v -> System.out.println(v.toString()));
    }

    public Vendedor buscarVendedor(int id) {
        return vendedores.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void eliminarVendedor(int id) {
        Vendedor vendedorAEliminar = vendedores.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        // Si se encuentra, eliminarlo del conjunto
        if (vendedorAEliminar != null) {
            vendedores.remove(vendedorAEliminar);
        }
    }
}
