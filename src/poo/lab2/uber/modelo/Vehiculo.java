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
    private String bicon;
    private double recaudado;
    
    public Vehiculo(Uber city, int i, int i1, Direction drctn) {
        super(city, i, i1, drctn);
        state=false;
        this.setBicon(state);
        setIcon(new Bicon(bicon));
        this.recaudado=0;
        this.ganacias=0;
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
        this.setBicon(state);
        xo=viaje.getOrigen().getAvenue();
        yo=viaje.getOrigen().getStreet();
        xf=viaje.getDestino().getAvenue();
        yf=viaje.getDestino().getStreet();
        boolean  pp = this.goTo(xo, yo);
        //System.out.println("pp: " + pp);
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
            System.out.println("Viaje completado");
            viaje.setState(true);
            //viaje.setState("Uber llego a su destino: " + + xf + " " + yf);
            //System.out.println(viaje.getState());
            this.state=false;
            this.setBicon(state);
            this.recaudado+=viaje.getPrecio();
            }
        }
        } while(true);
    }
    
    public void movAle(){
        int ale;
        ale = Vehiculo.numAleatorio(9);
        this.moveCant(ale);
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
    
    public void moveCant(int cant){
        for(int i=0;i<cant;i++){
            if(this.frontIsClear())
                this.move();
            else break;
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
            this.moveCant(Math.abs(difY));
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
            this.moveCant(Math.abs(difY));
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
            this.moveCant(Math.abs(difX));
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
            this.moveCant(Math.abs(difX));
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

    private void setBicon(boolean state){
        String path="image/";
        if(!state)
            path += "green";
        else path += "red";
        path+=".png";
        this.bicon = path;
        this.setIcon(new Bicon(bicon));
    }

    public double getRecaudado() {
        return recaudado;
    }

    public void setRecaudado(double recaudado) {
        this.recaudado = recaudado;
    }
    
    public void addGanancias(double pago){
        this.ganacias+=pago;
    }
    
    public String info(){
        String info="";
        info+= "El dinero total recaudado es: " + this.recaudado;
        info+= "\nEl dinero total en ganancias es: " + this.ganacias;
        return info;
    }
    
}
