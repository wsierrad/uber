/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.modelo;

import becker.robots.*;

/**
 *
 * @author willi_000
 */
public class Cliente extends Thing {
    private Viaje viaje;
    
    
    public Cliente(Uber city, int i, int i1, int x, int y, TipoViaje tipo) {
        super(city, i, i1);
        Intersection o = new Intersection(city, i, i1);
        Intersection f = new Intersection(city, x, y);
        viaje = new Viaje(o, f, tipo);
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }
    
    public int distanceAt(int xf, int yf){
        int distance,x,y;
        x = this.getIntersection().getAvenue();
        y = this.getIntersection().getStreet();
        distance= Math.abs(xf-x + yf-x);
        return distance;
    }
    
    
}
