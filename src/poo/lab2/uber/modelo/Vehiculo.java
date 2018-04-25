/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.lab2.uber.modelo;

import becker.robots.*;
import java.util.Random;

/**
 *
 * @author willi_000
 */
public class Vehiculo extends RobotSE implements Runnable {
    private boolean state;
    private Viaje viaje;
    private double ganacias;
    

    public Vehiculo(Uber city, int i, int i1, Direction drctn) {
        super(city, i, i1, drctn);
        state=false;
    }

    @Override
    public void run() {
        do{
            do{
            movAle();
        }while(!state);
        int xo,yo,xf,yf;
        //this.viaje.setState("Uber en camino");
        //System.out.println(viaje.getState());
        //Viaje viaje = viaje.getViaje();
        xo=viaje.getOrigen().getAvenue();
        yo=viaje.getOrigen().getStreet();
        xf=viaje.getDestino().getAvenue();
        yf=viaje.getDestino().getStreet();
        boolean  pp = this.goTo(xo, yo);
        System.out.println("pp: " + pp);
        if (pp){
            //viaje.setState("Uber en punto de partida: " + xo + " " + yo);
            //System.out.println(viaje.getState());
            pp=false;
            if(this.canPickThing()) {
                System.out.println("Recogiendo pasajero");
                this.pickThing();
                pp = this.goTo(xf,yf);
            }
        }
        if (pp){
            if(this.countThingsInBackpack()>0){
            this.putThing();
            //viaje.setState("Uber llego a su destino: " + + xf + " " + yf);
            //System.out.println(viaje.getState());
            this.state=false;
            }
        }
        } while(true);
    }
    
    public void movAle(){
        int ale,x,y;
        Direction dir;
        x = this.getAvenue();
        y = this.getStreet();
        dir=this.getDirection();
        ale = Vehiculo.numAleatorio(9);
        if (dir == Direction.WEST && x/ale==0)
            this.move(x%ale);
        else if (dir == Direction.NORTH && y/ale==0)
            this.move(y%ale);
        else if (dir == Direction.EAST && (x+ale)>40)
            this.move(40-x);
        else if (dir == Direction.SOUTH && (y+ale)>20)
            this.move(20-y);
        else this.move(ale);
        ale = Vehiculo.numAleatorio(5);
        switch(ale){
            case 1:
            case 2:
                this.turnRight();
                break;
            case 3:
            case 4:
                this.turnLeft();
                break;
            default:
                break;
        }
    }
    
    public static int numAleatorio(int i){
        int intAletorio;
        Random aleatorio;
        do{
        aleatorio = new Random(System.currentTimeMillis());
        // Producir nuevo int aleatorio entre 0 y i
        intAletorio = aleatorio.nextInt(i);  
        }while(intAletorio==0);
        return intAletorio;
    }
    
    public boolean goTo(int xf, int yf){
        int xo = this.getAvenue();
        int yo = this.getStreet();
        int difX =xf-xo, difY =yf-yo;
        Direction d = this.getDirection();
        if(difY>0){
            if(d!= Direction.SOUTH){
                switch (d){
                    case EAST:
                        this.turnRight();
                        break;
                    case NORTH:
                        this.turnAround();
                        break;
                    case WEST:
                        this.turnLeft();
                        break;
                }
                        
            }
            this.move(difY);
        }else {
            if(d!= Direction.NORTH){
                switch (d){
                    case WEST:
                        this.turnRight();
                        break;
                    case SOUTH:
                        this.turnAround();
                        break;
                    case EAST:
                        this.turnLeft();
                        break;
                }            
            }
            this.move(Math.abs(difY));
        }
        d = this.getDirection();
        if(difX>0){
            if(d!= Direction.EAST){
                switch (d){
                    case NORTH:
                        this.turnRight();
                        break;
                    case WEST:
                        this.turnAround();
                        break;
                    case SOUTH:
                        this.turnLeft();
                        break;
                }
                        
            }
            this.move(difX);
        }else {
            if(d!= Direction.WEST){
                switch (d){
                    case SOUTH:
                        this.turnRight();
                        break;
                    case EAST:
                        this.turnAround();
                        break;
                    case NORTH:
                        this.turnLeft();
                        break;
                }            
            }
            this.move(Math.abs(difX));
        }
        xo = this.getAvenue();
        yo = this.getStreet();
        return (xo==xf && yo==yf);
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Viaje getVD() {
        return viaje;
    }

    public void setVD(Viaje VD) {
        this.viaje = VD;
    }

    public double getGanacias() {
        return ganacias;
    }

    public void setGanacias(double ganacias) {
        this.ganacias = ganacias;
    }
    
}
