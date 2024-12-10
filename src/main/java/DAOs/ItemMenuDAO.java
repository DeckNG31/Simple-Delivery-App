/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import isi.deso.tp.menu.ItemMenu;
import java.util.List;

/**
 *
 * @author mariano
 */
public interface ItemMenuDAO {

    public List<ItemMenu> listarItemMenus(); // Listado
    public List<ItemMenu> listarItemMenusPorVendedor(int vendedorId); // Listado

    
    public void crearItemMenu(ItemMenu p); // crea

    public void editarItemMenu(ItemMenu p); // edita

    public void eliminarItemMenu(int id); // elimina 

    public ItemMenu buscarItemMenuPorId(int id); // busca
}
