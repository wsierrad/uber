/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.vista;

import becker.robots.*;
import java.util.ArrayList;
import java.util.Scanner;
import poo.lab2.uber.modelo.*;

/**
 *
 * @author ESTUDIANTE
 */
public class Principal {
        public static Uber city;
        public static Scanner teclado;
        
	public static void main (String[] args) {
            //Declarar la creacion de la ciudad
            city = new Uber("UberCity.txt");
            city.showThingCounts(true);
            
            city.addTipoViajes("UberX",2200, 549.91, 4600);
            city.addTipoViajes("Uber",3000, 950.08, 6000);
            
            
            int opcion;
            teclado = new Scanner(System.in);
            boolean menu;
            String temp;
            
            city.addUber(0, 1, Direction.SOUTH);
            city.addUber(0, 5, Direction.NORTH);
            city.addUber(7, 1, Direction.EAST);
            city.addUber(20, 1, Direction.WEST);
            city.addUber(18, 30, Direction.SOUTH);
            
            ArrayList<TipoViaje> tipos = city.getTipoViajes();
            
            city.addCliente(15,14, 20, 23, tipos.get(0));
            city.addCliente(5,4, 12, 23, tipos.get(0));
            city.addCliente(5,14, 2, 23, tipos.get(0));
            city.addCliente(1,14, 20, 3, tipos.get(0));
            city.addCliente(15,4, 0, 23, tipos.get(0));
            
            do{
                System.out.println("Si desea cambiar algun parametro presione c, para continuar presione cualquier tecla");
                temp=teclado.next();
                menu = !temp.equalsIgnoreCase("c");
                
            while(!menu){
                System.out.println("Bienvenido a la simulación de Uber");
                System.out.println("Seleccione una opción: " +
                        "\n1.Ingresar cliente nuevo\n2.Ingresar nuevo conductor" +
                        "\n3.Agregar un nuevo tipo de servicio\n4.Realizar pago a conductores"+
                        "\n5.Ver ganacias netas app\n6.Salir");
                opcion=teclado.nextInt();
                if(opcion==4)
                    break;
                menu=menu(opcion);
                if(menu){
                System.out.println("Desea agregar mas clientes, conductores o servicios: (S/N)");
                temp=teclado.next();
                if(temp.equalsIgnoreCase("s"))
                    menu=false;
                }else System.out.println("Opción invalida"); 
            }

            ArrayList<Vehiculo> ubers = city.getUbers();
            ArrayList<Cliente> clientes = city.getClientes();
            int i=0;
            for(Vehiculo v: ubers){
                v.setLabel("uber: " + Integer.toString(i));
                Thread thread = new Thread(v);
                thread.start();
                i++;
            }
            
            Vehiculo uber;
            Viaje viaje;
                    
            for(Cliente c : clientes){
                if(!c.getViaje().isState()){
                    System.out.println(c.getViaje().isState());
                    uber = city.getUberNear(c);
                    if(uber!=null){
                    viaje= c.getViaje();
                    uber.setVD(viaje);
                    uber.setState(true);
                    System.out.println("el precio del viaje es: " 
                            + viaje.getPrecio());
                    }else System.out.println("No hay conductores disponibles");
                    
                }
            } 
            }while(true);
        }
        
        public static boolean menu(int opcion){
            int xo,yo,xf,yf,i;
            TipoViaje tipo;
            Direction dir;
            String temp;
            double base, km, min;
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la posición incial del cliente: (x y)");
                    xo=teclado.nextInt();
                    yo=teclado.nextInt();
                    System.out.println("Ingrese el destino del cliente: (x y)");
                    xf=teclado.nextInt();
                    yf=teclado.nextInt();
                    System.out.println("Seleccione el tipo de servicio: ");
                    System.out.println(city.infoServicios());
                    i=teclado.nextInt();
                    tipo = city.getTipoViajes().get(i);
                    return city.addCliente(yo, xo, yf, xf, tipo);
                case 2:
                    System.out.println("Ingrese la posición incial del conductor: (x y)");
                    xo=teclado.nextInt();
                    yo=teclado.nextInt();
                    System.out.println("Ingrese la dirección incial del conductor: (NORTH, EAST, WEST, SOUTH)");
                    temp=teclado.next();
                    temp = temp.toUpperCase();
                    dir= Direction.valueOf(temp);
                    return city.addUber(yo, xo, dir);
                case 3:
                    System.out.println("Ingrese el nombre del servicio a agregar:");
                    temp=teclado.next();
                    System.out.println("Ingrese el valor base de la carrera: ");
                    base=teclado.nextDouble();
                    System.out.println("Ingrese el costo por km de la carrera: ");
                    km=teclado.nextDouble();
                    System.out.println("Ingrese el costo minimo por carrera: ");
                    min = teclado.nextDouble();
                    return city.addTipoViajes(temp, base, km, min);
                case 4:
                    city.pagoCondu();
                    System.out.println("Pagos realizados");
                    city.infoUbers();
                case 5:
                    System.out.println("Las ganancias netas fueron de: "+city.gananciasNetas());
                default:
                    return false;
            }
        }
}
