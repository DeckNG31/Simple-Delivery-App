/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package isi.deso.tp;

import isi.deso.tp.usuarios.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deck
 */
public class Tp {

    public static void main(String[] args) {
        
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
   
   
}
