/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elena
 */
public class Alfombra {
    
    int peso;
    int alegria;
    Regalo[] regalos;
    String nombre;
    
    public Alfombra(int peso, String nombre){
        this.peso = peso;
        this.nombre = nombre;
    }
    
    public int getPeso(){
        return this.peso;
    }
    
    public void setPeso(int peso){
        this.peso = peso;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public Regalo[] getRegalos(){
        return this.regalos;
    }
    
    public void setRegalo(Regalo regalo,int posicion){
        this.regalos[posicion] = new Regalo(regalo.getPeso(),regalo.getAlegria());
    }
    
    public int getAlegria(){
        return this.alegria;
    }
    
    public void setAlegria(int alegria){
        this.alegria = alegria;
    }
    
    public void initRegalos(int numRegalos){
        this.regalos = new Regalo[numRegalos];
    }
    
    public int sumarAlegria(Regalo[] regalos){
        int alegria=0;
        for(int i=0; i< regalos.length; i++){
            if (regalos[i]!=null){
                alegria += regalos[i].getAlegria();
            }
        }
        return alegria;
    }
    
    public int getNumRegalos(Regalo[] regalos){
        int num =0;
        for(int i=0; i< regalos.length; i++){
            if (regalos[i]!=null){
                num++;
            }
        }
        return num;
    }
}
