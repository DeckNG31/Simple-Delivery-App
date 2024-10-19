/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.usuarios;

/**
 *
 * @author Deck
 */
public class Coordenada {
    private double lat;
    private double lng;

    
    //getter y setter para latitud
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    
    //getter y setter para longitud
    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Coordenada{" + "lat=" + lat + ", lng=" + lng + '}';
    }
    
    
    
    //constructores

    public Coordenada(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
    
    
}
