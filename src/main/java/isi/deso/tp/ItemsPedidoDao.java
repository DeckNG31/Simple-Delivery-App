/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isi.deso.tp;

import exceptions.ItemNoEncontradoException;

/**
 *
 * @author marianoo
 */
public interface ItemsPedidoDao {

    public List<Pedidos> filtrado() throws ItemNoEncontradoException;

    //Criterio podria ser un enum
    public List<Pedidos> ordernarPorCriterio(Criterio criterio) throws ItemNoEncontradoException;

    public List<Pedidos> busquedaPorPrecio(Double max) throws ItemNoEncontradoException;

    public List<Pedidos> busquedaPorPrecio(Double min, Double max) throws ItemNoEncontradoException;

    public List<Pedidos> busquedaPorRestaurante(String restaurante) throws ItemNoEncontradoException;
}
