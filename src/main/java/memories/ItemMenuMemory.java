/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memories;

import isi.deso.tp.menu.ItemMenu;
import java.util.HashSet;

/**
 *
 * @author mariano
 */
public class ItemMenuMemory {

    private static ItemMenuMemory instance;
    public HashSet<ItemMenu> itemsMenu;
    public Integer idCount = 0;

    public ItemMenuMemory() {
        itemsMenu = new HashSet<>();
    }

    public static ItemMenuMemory getInstance() {
        if (instance == null) {
            instance = new ItemMenuMemory();
        }
        return instance;
    }

    public void agregarItemMenu(ItemMenu im) {

        im.setId(idCount);
        itemsMenu.add(im);
        idCount++;
    }

    //recive el id del plato a reemplazar
    public void editarItemMenu(Integer id, ItemMenu im) {

        ItemMenu actual = buscarItemMenu(id);
        
        im.setId(id);
        
        if (actual != null) {
            itemsMenu.remove(actual);
        }

        itemsMenu.add(im);
    }

    public void mostrar() {
        itemsMenu.stream().forEach(c -> System.out.println(c.toString()));
    }

    public ItemMenu buscarItemMenu(Integer id) {
        return itemsMenu.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void eliminarItemMenu(int id) {
        ItemMenu itemMenuAEliminar = itemsMenu.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        // Si se encuentra, eliminarlo del conjunto
        if (itemMenuAEliminar != null) {
            itemsMenu.remove(itemMenuAEliminar);
        }
    }

}
