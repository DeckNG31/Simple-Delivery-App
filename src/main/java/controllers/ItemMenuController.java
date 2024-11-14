/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.ItemMenu;
import isi.deso.tp.menu.Plato;
import memories.ItemMenuMemory;

/**
 *
 * @author mariano
 */
public class ItemMenuController {

    ItemMenuMemory imm;

    //crear platos y bebidas
    public void crearPlato(String nombre, String descripcion, String precio, boolean aptoVegano, String peso, String calorias, boolean aptoCeliaco, Integer vendedorId) throws Exception {

        try {
            // Validación y conversión de precio, peso y calorías a Double
            double precioFormateado = parsearADouble(precio, "El precio debe ser un número");
            double pesoFormateado = parsearADouble(peso, "El peso debe ser un número");
            double caloriasFormateado = parsearADouble(calorias, "Las calorías deben ser un número");

            // Creación de un nuevo plato y almacenamiento en la memoria
            ItemMenu plato = new Plato(nombre, descripcion, precioFormateado, aptoVegano, pesoFormateado, caloriasFormateado, aptoCeliaco, vendedorId);
            ItemMenuMemory imm = ItemMenuMemory.getInstance();
            imm.agregarItemMenu(plato);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void editarPlato(Integer id, String nombre, String descripcion, String precio, boolean aptoVegano, String peso, String calorias, boolean aptoCeliaco, Integer vendedorId) throws Exception {

        try {
            // Validación y conversión de precio, peso y calorías a Double
            double precioFormateado = parsearADouble(precio, "El precio debe ser un número");
            double pesoFormateado = parsearADouble(peso, "El peso debe ser un número");
            double caloriasFormateado = parsearADouble(calorias, "Las calorías deben ser un número");

            // Creación de un nuevo plato y almacenamiento en la memoria
            ItemMenu plato = new Plato(nombre, descripcion, precioFormateado, aptoVegano, pesoFormateado, caloriasFormateado, aptoCeliaco, vendedorId);
            ItemMenuMemory imm = ItemMenuMemory.getInstance();
            imm.editarItemMenu(id, plato);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void eliminarItemMenu(Integer id) {
        ItemMenuMemory imm = ItemMenuMemory.getInstance();
        imm.eliminarItemMenu(id);
    }

    public void crearBebida(String nombre, String descripcion, String precio, boolean aptoVegano, String peso, String volumen, String alcohol, Integer vendedorId) throws Exception {

        try {
            // Validación y conversión de precio, peso y calorías a Double
            double precioFormateado = parsearADouble(precio, "El precio debe ser un número");
            double pesoFormateado = parsearADouble(peso, "El peso debe ser un número");
            double volumenFormateado = parsearADouble(volumen, "El volumen deben ser un número");
            double alcoholFormateado = parsearADouble(alcohol, "La graduacion del alcohol deben ser un número");

            // Creación de un nuevo plato y almacenamiento en la memoria
            ItemMenu bebida = new Bebida(nombre, descripcion, precioFormateado, aptoVegano, pesoFormateado, volumenFormateado, alcoholFormateado, vendedorId);
            ItemMenuMemory imm = ItemMenuMemory.getInstance();
            imm.agregarItemMenu(bebida);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
     public void editarBebida(Integer id, String nombre, String descripcion, String precio, boolean aptoVegano, String peso, String volumen, String alcohol, Integer vendedorId) throws Exception {

        try {
            // Validación y conversión de precio, peso y calorías a Double
            double precioFormateado = parsearADouble(precio, "El precio debe ser un número");
            double pesoFormateado = parsearADouble(peso, "El peso debe ser un número");
            double volumenFormateado = parsearADouble(volumen, "El volumen deben ser un número");
            double alcoholFormateado = parsearADouble(alcohol, "La graduacion del alcohol deben ser un número");

            // Creación de un nuevo plato y almacenamiento en la memoria
            ItemMenu bebida = new Bebida(nombre, descripcion, precioFormateado, aptoVegano, pesoFormateado, volumenFormateado, alcoholFormateado, vendedorId);
            ItemMenuMemory imm = ItemMenuMemory.getInstance();
            imm.editarItemMenu(id, bebida);

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
}
