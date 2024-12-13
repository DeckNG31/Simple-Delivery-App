/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import isi.deso.tp.Pedido;
import isi.deso.tp.menu.ItemMenu;
import java.util.List;

/**
 *
 * @author mariano
 */
public class PedidoItemMenuDTO {

    private List<Pedido> pedidos;
    private List<ItemMenu> itemMenus;

    public PedidoItemMenuDTO(List<Pedido> pedidos, List<ItemMenu> itemMenus) {
        this.pedidos = pedidos;
        this.itemMenus = itemMenus;
    }

    
    public Pedido getPedidoPorId(Integer id) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ItemMenu> getItemMenus() {
        return itemMenus;
    }

    public void setItemMenus(List<ItemMenu> itemMenus) {
        this.itemMenus = itemMenus;
    }
}
