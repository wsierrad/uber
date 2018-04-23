/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.vista;

import becker.robots.*;
import java.util.ArrayList;
import poo.lab2.uber.modelo.*;

/**
 *
 * @author ESTUDIANTE
 */
public class Principal {
        public static Uber city;
        
	public static void main (String[] args){
            //Declarar la creacion de la ciudad
            city = new Uber(0,0,20,40);
            ViajeDisponible vd =new ViajeDisponible(null,null,false);
            city.addUber(0, 1, Direction.SOUTH,vd);
            city.addUber(0, 5, Direction.NORTH,vd);
            city.addUber(7, 1, Direction.EAST,vd);
            city.addUber(20, 1, Direction.WEST,vd);
            city.addUber(18, 30, Direction.SOUTH,vd);
            
	    city.showThingCounts(true);
            
            city.genClientes(5, 20, 40);
            
            ArrayList<Vehiculo> ubers = city.getUbers();
            ArrayList<Thread> hilos = new ArrayList<>();
            ArrayList<Cliente> clientes = city.getClientes();
            for(Vehiculo v: ubers){
                Thread thread = new Thread(v);
                thread.start();
                hilos.add(thread);
            }
            Vehiculo uber;
            for(Cliente c : clientes){
                vd.setViaje(c.getViaje());
                vd.setDisponible(true);
                vd.setState("Esperando conductor");
                uber = city.llamarUber(vd, c);
                uber.goTo(c.getViaje().getOrigen().getAvenue(), c.getViaje().getOrigen().getStreet());
            }
        }
}
