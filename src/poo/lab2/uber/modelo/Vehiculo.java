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
    private ViajeDisponible VD;
    private double ganacias;
    

    public Vehiculo(Uber city, int i, int i1, Direction drctn, ViajeDisponible VD) {
        super(city, i, i1, drctn);
        state=false;
    }

    @Override
    public void run() {
        int ale,x,y;
        Direction dir;
        while(!state){
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
    
    public void goTo(int xf, int yf){
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
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public ViajeDisponible getVD() {
        return VD;
    }

    public void setVD(ViajeDisponible VD) {
        this.VD = VD;
    }

    public double getGanacias() {
        return ganacias;
    }

    public void setGanacias(double ganacias) {
        this.ganacias = ganacias;
    }
    
}
