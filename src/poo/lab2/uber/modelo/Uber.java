/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.modelo;

import becker.robots.City;
import becker.robots.Direction;
import java.util.ArrayList;
/**
 *
 * @author willi_000
 */
public class Uber extends City {
    private ArrayList<Vehiculo> ubers;
    private ArrayList<Cliente> clientes;
    private ArrayList<TipoViaje> tipoViajes;
    
    public Uber(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        ubers = new ArrayList<>();
        clientes = new ArrayList<>();
        tipoViajes = new ArrayList<>();
    }
    
    public boolean addUber(int i, int i1, Direction drctn, ViajeDisponible vd){
        Vehiculo u = new Vehiculo(this, i, i1, drctn,vd);
        return ubers.add(u);
    }
    
    public boolean addCliente(int i, int i1, int x, int y,TipoViaje tipo){
        Cliente c = new Cliente(this, i, i1, x, y,tipo);
        return clientes.add(c);
    }
    
    /*public boolean updateState(){
        
    }*/
    
    public synchronized Vehiculo llamarUber(ViajeDisponible vd, Cliente c){
        Vehiculo uber=null;
        while (vd.isDisponible() == false){
                try{ 
                    wait();
                }catch (InterruptedException e){}
        }
        vd.setDisponible(false);
        int distance=c.distanceAt(this.ubers.get(0).getAvenue(), 
                this.ubers.get(0).getStreet());
        int temp;
        for (Vehiculo v: this.ubers){
            temp = c.distanceAt(v.getAvenue(), v.getStreet());
            if(distance > temp){
                distance=temp;
                uber=v;
            }
        }
        vd.setDisponible(true);
        notifyAll();
        return uber;
    }
    
    public void genClientes(int i,int x,int y){
        int j,a=0,b=0,c=0,d=0,e=0;
        for (j=0;j<i;j++){
            //e=Vehiculo.numAleatorio(tipoViajes.size());
            a=Vehiculo.numAleatorio(x);
            b=Vehiculo.numAleatorio(y);
            c=Vehiculo.numAleatorio(x);
            d=Vehiculo.numAleatorio(y);
            this.addCliente(a, b, c, d, null);
        }
    }

    public ArrayList<Vehiculo> getUbers() {
        return ubers;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    
}
