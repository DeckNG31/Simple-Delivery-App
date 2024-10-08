/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp;

import isi.deso.tp.usuarios.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author st
 */
public class PedidoObserver {

    private List<Cliente> clientesSuscriptos = new ArrayList();

    public void addCliente(Cliente c) {
        clientesSuscriptos.add(c);
    }

    public void removeCiente(Cliente c) {
        clientesSuscriptos.remove(c);
    }

    public void notificarClientes() {
        clientesSuscriptos.stream().forEach(c -> c.update());
    }
}
