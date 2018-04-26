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
public class TipoViaje {
    private String nomServicio;
    private double base;
    private double costoXKm;
    private double minima;

    public TipoViaje(String nom, double base, double costoXKm, double minima) {
        this.nomServicio=nom;
        this.base = base;
        this.costoXKm = costoXKm;
        this.minima = minima;

    }
    

    public double getBase() {
        return base;
    }

    public double getCostoXKm() {
        return costoXKm;
    }

    public double getMinima() {
        return minima;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setCostoXKm(double costoXKm) {
        this.costoXKm = costoXKm;
    }

    public void setMinima(double minima) {
        this.minima = minima;
    }

    public String getNomServicio() {
        return nomServicio;
    }

    public void setNomServicio(String nomServicio) {
        this.nomServicio = nomServicio;
    }
    
    
    
    
}
