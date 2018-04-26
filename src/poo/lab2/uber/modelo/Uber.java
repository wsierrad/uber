/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.modelo;

import becker.robots.City;
import becker.robots.Direction;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author willi_000
 */
public class Uber extends City {
    private final ArrayList<Vehiculo> ubers;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<TipoViaje> tipoViajes;
    

    public Uber(String string) {
        super(string);
        ubers = new ArrayList<>();
        clientes = new ArrayList<>();
        tipoViajes = new ArrayList<>();
    }
    
    public void pagoCondu(){
        for (Vehiculo v: ubers){
            double pago = v.getRecaudado()*0.7;
            v.addGanancias(pago);
        }
    }
    
    public String infoUbers(){
        String info="";
        for (Vehiculo v:ubers){
            info+=v.info();
            info="\n";
        }
        return info;
    }
    
    public double gananciasNetas(){
        double pago=0;
        for (Vehiculo v: ubers){
            pago += v.getRecaudado()*0.3;
        }
        return pago;
    }
    
    
    public boolean addUber(int i, int i1, Direction drctn){
        Vehiculo u = new Vehiculo(this, i, i1, drctn);
        return ubers.add(u);
    }
    
    public boolean addCliente(int i, int i1, int x, int y,TipoViaje tipo){
        Cliente c = new Cliente(this, i, i1, x, y,tipo);
        return clientes.add(c);
    }
    
    public boolean addTipoViajes(String nom,double base, double km,double min){
        TipoViaje t = new TipoViaje(nom, base, km, min);
        return tipoViajes.add(t);
    }
   
    public Vehiculo getUberNear(Cliente c){
        Vehiculo uberNear=null;
        try {
            synchronized(ubers){
                ArrayList <Vehiculo> ubersDisponibles = new ArrayList<>();
                for(Vehiculo v :ubers){
                    if (!v.isState())
                        ubersDisponibles.add(v);
                }
                if(ubersDisponibles.size()<2)
                    uberNear = ubersDisponibles.get(0);
                else{
                    int distance=c.distanceAt(ubersDisponibles.get(0).getAvenue(), 
                        ubersDisponibles.get(0).getStreet());
                    int temp;
                    for (Vehiculo v:ubersDisponibles){
                        temp = c.distanceAt(v.getAvenue(), v.getStreet());
                        if(distance > temp){
                            distance=temp;
                            uberNear=v;
                        }
                    }
                }
            }
        }catch(NullPointerException e){}
        return uberNear;
    }

    public ArrayList<Vehiculo> getUbers() {
        return ubers;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<TipoViaje> getTipoViajes() {
        return tipoViajes;
    }
    
    public String infoServicios(){
        String info=null;
        int size=this.tipoViajes.size();
        if (size>0)
            info="";
        for (int i=0; i<size;i++){
            info += i + this.tipoViajes.get(i).getNomServicio()+"\n";
        }
        return info;
    }
    
}
