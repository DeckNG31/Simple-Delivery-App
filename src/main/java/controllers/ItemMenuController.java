/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAOs.ItemMenuDAO;
import JDBCs.ItemMenuJDBC;
import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.menu.Plato;
import java.util.List;

/**
 *
 * @author mariano
 */
public class ItemMenuController {

    private final ItemMenuDAO itemMenuDAO;

    //singleton
    private static ItemMenuController instance;

    private ItemMenuController() {
        itemMenuDAO = ItemMenuJDBC.getInstance();
    }

    public static synchronized ItemMenuController getInstance() {
        if (instance == null) {
            instance = new ItemMenuController();
        }
        return instance;
    }

    public void crearPlato(String nombre, String descripcion, String precio, boolean aptoVegano, String peso, String calorias, boolean aptoCeliaco, Integer vendedorId) throws Exception {
        try {
            double precioFormateado = parsearADouble(precio, "El precio debe ser un número");
            double pesoFormateado = parsearADouble(peso, "El peso debe ser un número");
            double caloriasFormateado = parsearADouble(calorias, "Las calorías deben ser un número");

            ItemMenu plato = new Plato(0, nombre, descripcion, precioFormateado, aptoVegano, pesoFormateado, caloriasFormateado, aptoCeliaco, vendedorId);
            itemMenuDAO.crearItemMenu(plato);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void editarPlato(Integer id, String nombre, String descripcion, String precio, boolean aptoVegano, String peso, String calorias, boolean aptoCeliaco, Integer vendedorId) throws Exception {
        try {
            double precioFormateado = parsearADouble(precio, "El precio debe ser un número");
            double pesoFormateado = parsearADouble(peso, "El peso debe ser un número");
            double caloriasFormateado = parsearADouble(calorias, "Las calorías deben ser un número");

            ItemMenu plato = new Plato(id, nombre, descripcion, precioFormateado, aptoVegano, pesoFormateado, caloriasFormateado, aptoCeliaco, vendedorId);
            itemMenuDAO.editarItemMenu(plato);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void eliminarItemMenu(Integer id) {
        itemMenuDAO.eliminarItemMenu(id);
    }

    public void crearBebida(String nombre, String descripcion, String precio, boolean aptoVegano, String peso, String volumen, String alcohol, Integer vendedorId) throws Exception {
        try {
            double precioFormateado = parsearADouble(precio, "El precio debe ser un número");
            double pesoFormateado = parsearADouble(peso, "El peso debe ser un número");
            double volumenFormateado = parsearADouble(volumen, "El volumen debe ser un número");
            double alcoholFormateado = parsearADouble(alcohol, "La graduación del alcohol debe ser un número");

            ItemMenu bebida = new Bebida(0,nombre, descripcion, precioFormateado, aptoVegano, pesoFormateado, volumenFormateado, alcoholFormateado, vendedorId);
            itemMenuDAO.crearItemMenu(bebida);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void editarBebida(Integer id, String nombre, String descripcion, String precio, boolean aptoVegano, String peso, String volumen, String alcohol, Integer vendedorId) throws Exception {
        try {
            double precioFormateado = parsearADouble(precio, "El precio debe ser un número");
            double pesoFormateado = parsearADouble(peso, "El peso debe ser un número");
            double volumenFormateado = parsearADouble(volumen, "El volumen debe ser un número");
            double alcoholFormateado = parsearADouble(alcohol, "La graduación del alcohol debe ser un número");

            ItemMenu bebida = new Bebida(id,nombre, descripcion, precioFormateado, aptoVegano, pesoFormateado, volumenFormateado, alcoholFormateado, vendedorId);
            itemMenuDAO.editarItemMenu(bebida);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private double parsearADouble(String valor, String mensajeError) throws Exception {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new Exception(mensajeError);
        }
    }

    public List<ItemMenu> listarItemsMenu() {
        return itemMenuDAO.listarItemMenus();
    }

    public ItemMenu buscarItemMenuPorId(int id) {
        return itemMenuDAO.buscarItemMenuPorId(id);
    }

}
