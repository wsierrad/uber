/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.vista;

import becker.robots.*;
import com.sun.javafx.image.impl.IntArgb;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.lab2.uber.modelo.*;

/**
 *
 * @author ESTUDIANTE
 */
public class Principal {
        public static Uber city;
        
	public static void main (String[] args) {
            //Declarar la creacion de la ciudad
            city = new Uber(0,0,20,40);
            city.addUber(0, 1, Direction.SOUTH);
            city.addUber(0, 5, Direction.NORTH);
            city.addUber(7, 1, Direction.EAST);
            city.addUber(20, 1, Direction.WEST);
            city.addUber(18, 30, Direction.SOUTH);
            
	    city.showThingCounts(true);
            
            city.addTipoViajes(3200, 600, 4600);
            ArrayList<TipoViaje> tipos = city.getTipoViajes();
            
            boolean addc;
            addc = city.addCliente(15,14, 20, 23, tipos.get(0));
            System.out.println(addc);
            addc = city.addCliente(5,4, 12, 23, tipos.get(0));
            System.out.println(addc);
            addc = city.addCliente(5,14, 2, 23, tipos.get(0));
            System.out.println(addc);
            addc = city.addCliente(1,14, 20, 3, tipos.get(0));
            System.out.println(addc);
            addc = city.addCliente(15,4, 0, 23, tipos.get(0));
            System.out.println(addc);
            
            ArrayList<Vehiculo> ubers = city.getUbers();
            ArrayList<Thread> hilos = new ArrayList<>();
            ArrayList<Cliente> clientes = city.getClientes();
            int i=0;
            for(Vehiculo v: ubers){
                v.setLabel("uber: " + Integer.toString(i));
                Thread thread = new Thread(v);
                thread.start();
                hilos.add(thread);
                i++;
            }
            
            Vehiculo uber;
            Viaje viaje;
            /*for(i =0; i<clientes.size();i++){
            uber = city.getUberNear(clientes.get(i));
            uber.setLabel("este es: " + i);
            viaje= clientes.get(i).getViaje();
            uber.setVD(viaje);
            uber.setState(true);
            System.out.println(uber.getAvenue() + " " + uber.getStreet());
            }*/
            
              
            
            for(Cliente c : clientes){
                uber = city.getUberNear(c);
                viaje= c.getViaje();
                uber.setVD(viaje);
                uber.setState(true);
                System.out.println("el precio del viaje es: " + viaje.getPrecio());
                //System.out.println(uber.getAvenue() + " " + uber.getStreet());
            }
        }
}
