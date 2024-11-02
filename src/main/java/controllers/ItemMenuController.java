/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

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

    private double parsearADouble(String valor, String mensajeError) throws Exception {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new Exception(mensajeError);
        }
    }
}
