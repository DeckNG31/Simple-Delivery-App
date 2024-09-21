/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package isi.deso.tp;

import exceptions.ItemNoEncontradoException;
import isi.deso.tp.menu.Bebida;
import isi.deso.tp.menu.ItemPedido;
import isi.deso.tp.menu.Plato;
import isi.deso.tp.usuarios.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deck
 */
public class Tp {

    public static void main(String[] args) {
        
     //etapa1Pruebas();
      
     etapa3Pruebas();
    }
    
    //metodos de prueba de etapas
    
    public static void etapa1Pruebas(){
          Vendedor v1 = new Vendedor(0,"Toreto","Chaco 12",new Coordenada(-60.7413545,-60.5405618));
        Vendedor v2 = new Vendedor(1,"Evaristo","California 2445",new Coordenada(-31.7413545,-60.5405618));
        Vendedor v3 = new Vendedor(2,"Rodney Sacks","New York 324",new Coordenada(-37.7413945,-10.5405818));
        Vendedor v4 = new Vendedor(3,"Evaristo","Zapallito 352",new Coordenada(-55.7413945,-10.5405818));
       //System.out.println(v1 instanceof Vendedor);
        //System.out.println(v2.getNombre());
       // System.out.println(v3.getCoord().getLat() + " " + v3.getCoord().getLng());
    
        List<Vendedor> v = new ArrayList<Vendedor>();
        v.add(v1);
        v.add(v2);
        v.add(v3);
        v.add(v4);
      
        
     //  List<Vendedor> vendors = Vendedor.buscarVendedor(0,v);
   //   System.out.println(  vendors.getFirst().toString());
    //   List<Vendedor> vendor2 = Vendedor.buscarVendedor("Evaristo",v);
    //   for(int i = 0 ; i < vendor2.size() ; i++){
      //    System.out.println(vendor2.get(i).toString());
    //   }
    //  v.remove(0);
   //  v.forEach(element->{System.out.println(element.toString());});
      
      
     Cliente c1 = new Cliente(0,"20-43427317-0" , "benshapozzi@gmail.com" , "Suecia 1120" , new Coordenada(-53,-23) , "Benjamin");
     Cliente c2 = new Cliente(1,"20-43113753-5" , "marianitocapox@outlook.net" , "Calle 123" , new Coordenada(134.5 , 242.7) , "Mariano");
     Cliente c3 = new Cliente(2 , "20-42272167-6" , "dardosanchez2000@gmail.com" , "San Martin 1560" , new Coordenada(60,20) , "Dardo");
      
     List<Cliente>clientes = new ArrayList<Cliente>();
     clientes.add(c1);
     clientes.add(c2);
     clientes.add(c3);
     
     List<Cliente> nombresClientes = Cliente.buscarCliente("Benjamin", clientes);
     List<Cliente> idClientes = Cliente.buscarCliente(1, clientes);
     
     System.out.println(nombresClientes.getFirst().toString());
     System.out.println(idClientes.getFirst().toString());
    // System.out.println("---------------------------------------"); 
     clientes.remove(0);
     System.out.println("------------------------------------");
     clientes.forEach(element->{System.out.println(element.toString());});
     
   
    System.out.println(v1.distancia(c1));
    }
    
    
    public static void etapa2Pruebas(){
         // Crear una bebida con alcohol
        Bebida bebidaAlcoholica = new Bebida(500.0, 5.0, 1, "Cerveza", "Cerveza artesanal", 150.0, false, 0.5);
        // Crear una bebida sin alcohol
        Bebida bebidaSinAlcohol = new Bebida(330.0, 0.0, 2, "Refresco", "Refresco de cola", 100.0, true, 0.33);

        // Crear un plato apto para veganos y celiacos
        Plato platoVeganoCeliaco = new Plato(250.0, true, 3, "Ensalada", "Ensalada mixta", 200.0, true, 0.4);
        // Crear un plato no apto para veganos ni celiacos
        Plato platoNoVeganoNoCeliaco = new Plato(500.0, false, 4, "Pizza", "Pizza de queso", 300.0, false, 0.8);

        // Pruebas para las bebidas
        System.out.println("Probando Bebidas:");
        System.out.println("¿La bebida 'Cerveza' es alcohol? " + bebidaAlcoholica.esAlcohol()); // true
        System.out.println("¿La bebida 'Refresco' es alcohol? " + bebidaSinAlcohol.esAlcohol()); // false
        System.out.println("Peso calculado de 'Cerveza': " + bebidaAlcoholica.peso()); // peso calculado con alcohol
        System.out.println("Peso calculado de 'Refresco': " + bebidaSinAlcohol.peso()); // peso calculado sin alcohol
        System.out.println("¿'Cerveza' es comida? " + bebidaAlcoholica.esComida()); // false
        System.out.println("¿'Cerveza' es bebida? " + bebidaAlcoholica.esBebida()); // true
        System.out.println("¿'Refresco' es vegano? " + bebidaSinAlcohol.aptoVegano()); // true

        // Pruebas para los platos
        System.out.println("\nProbando Platos:");
        System.out.println("¿El plato 'Ensalada' es vegano? " + platoVeganoCeliaco.aptoVegano()); // true
        System.out.println("¿El plato 'Pizza' es apto para celiacos? " + platoNoVeganoNoCeliaco.isAptoCeliaco()); // false
        System.out.println("Peso calculado de 'Ensalada': " + platoVeganoCeliaco.peso()); // peso ajustado
        System.out.println("Peso calculado de 'Pizza': " + platoNoVeganoNoCeliaco.peso()); // peso ajustado
        System.out.println("Calorías del plato 'Pizza': " + platoNoVeganoNoCeliaco.getCalorias()); // 500.0
    }
   
    
    
    public static void etapa3Pruebas(){
        
        List<ItemPedido> lista = null;
         ItemPedidoMemory gestionItems = new ItemPedidoMemory(lista);

        // Agregar algunos ítems de ejemplo
        gestionItems.itemsPedido.add(new ItemPedido("Pizza", 12.5, "Restaurante A"));
        gestionItems.itemsPedido.add(new ItemPedido("Hamburguesa", 8.0, "Restaurante B"));
        List<ItemPedido> todosItems = gestionItems.getTodosItems();
        System.out.println(todosItems.toString());
        // Buscar por rango de precios
        try {
            List<ItemPedido> items = gestionItems.buscarPorRangoDePrecios(5, 15);
            items.forEach(item -> System.out.println(item.getNombre() + " - " + item.getPrecio()));
        } catch (ItemNoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        
    }
   
}
