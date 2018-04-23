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
            city.addUber(0, 1, Direction.SOUTH);
            city.addUber(0, 5, Direction.NORTH);
            city.addUber(7, 1, Direction.EAST);
            city.addUber(20, 1, Direction.WEST);
            city.addUber(18, 30, Direction.SOUTH);
            
	    city.showThingCounts(true);
            
            Vehiculo v1 = new Vehiculo(city, 30, 10, Direction.EAST);
            v1.goTo(0,0);

            ArrayList<Vehiculo> ubers = city.getUbers();
            ArrayList<Thread> hilos = new ArrayList<>();
            for(Vehiculo v: ubers){
                Thread thread = new Thread(v);
                thread.start();
                hilos.add(thread);
            }
        
            
            
            //Direction.NORTH, EAST, SOUTH, WEST
            //Definicion de la ubicacion del robot, Ciudad, posicion, Direccion, Numero things en el bolso.
            
            /*mover(1);
            do{
                
                //mover(1);
                int c=recoger();
                
                uber.turnLeft();
                mover(1);
                poner(c);
                giro(2);
                mover(c+1);
                giro(1);
                mover(1);
            }while(uber.canPickThing());
            */
        }
        
        public static int recoger(Vehiculo uber){
            int c=0;
            while(uber.canPickThing()){
                uber.pickThing();
                int cant = uber.countThingsInBackpack();
                uber.setLabel(Integer.toString(cant));
                c++;
            }
            return c;
        }
        
        public static void poner(int a, Vehiculo uber){
            for(int i=a;i>0;i--){
                uber.putThing();
                int cant = uber.countThingsInBackpack();
                uber.setLabel(Integer.toString(cant));
                uber.move();
            }
        }
        public static void mover(int pasos, Vehiculo uber){
            for (int i = 0; i < pasos; i++) 
                uber.move();
        }
        public static void giro(int giros, Vehiculo uber){
            for (int i = 0; i < giros; i++) 
                uber.turnLeft();
        }
}
