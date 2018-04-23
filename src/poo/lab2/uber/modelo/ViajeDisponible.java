/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.modelo;

/**
 *
 * @author willi_000
 */
public class ViajeDisponible {
    private Viaje viaje;
    private String state;
    private boolean disponible;

    public ViajeDisponible(Viaje viaje, String state, boolean disponible) {
        this.viaje = viaje;
        this.state = state;
        this.disponible = disponible;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
}
