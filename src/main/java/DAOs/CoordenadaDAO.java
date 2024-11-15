/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import isi.deso.tp.usuarios.Coordenada;

/**
 *
 * @author mariano
 */
public interface CoordenadaDAO {

    public void crearCoordenada(Coordenada p); // crea

    public void editarCoordenada(Coordenada p); // edita

    public Coordenada buscarCoordenadaPorId(int id); // busca
}
