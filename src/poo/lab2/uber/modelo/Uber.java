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
    
    public Uber(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        ubers = new ArrayList<>();
        clientes = new ArrayList<>();
    }
    
    public boolean addUber(int i, int i1, Direction drctn){
        Vehiculo u = new Vehiculo(this, i, i1, drctn);
        return ubers.add(u);
    }
    
    public boolean addCliente(int i, int i1, int x, int y){
        Cliente c = new Cliente(this, i, i1, x, y);
        return clientes.add(c);
    }
    
    /*public Vehiculo llamarUber(){
        for (Vehiculo v: ubers){
            
        }
    }*/

    public ArrayList<Vehiculo> getUbers() {
        return ubers;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    
}
