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
    private Intersection destino;
    
    public Cliente(City city, int i, int i1, int x, int y) {
        super(city, i, i1);
        destino = new Intersection(city, x, y);
    }

    public Intersection getDestino() {
        return destino;
    }

    public void setDestino(Intersection destino) {
        this.destino = destino;
    }
    
    
    
}
