/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.modelo;

import becker.robots.Intersection;

/**
 *
 * @author willi_000
 */
public class Viaje {
    private final Intersection origen;
    private final Intersection destino;
    private double precio;
    private final TipoViaje tipo;

    public Viaje(Intersection origen, Intersection destino, TipoViaje tipo) {
        this.origen = origen;
        this.destino = destino;
        this.tipo = tipo;
        calcPrecio();
    }
    
    private void calcPrecio(){
        int km = Math.abs(origen.getAvenue()-destino.getAvenue())+ 
                Math.abs(origen.getStreet()-destino.getStreet());
        precio+= tipo.getBase();
        precio+= km * tipo.getCostoXKm();
        if(precio<tipo.getMinima())
            precio = tipo.getMinima();
    }

    public Intersection getOrigen() {
        return origen;
    }

    public Intersection getDestino() {
        return destino;
    }

    public double getPrecio() {
        return precio;
    }

    public TipoViaje getTipo() {
        return tipo;
    }
    
    
    
    
}
