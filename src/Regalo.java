/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elena
 */
public class Regalo {
    
    int peso;
    int alegria;
    
    public Regalo(int peso, int alegria){
        this.peso = peso;
        this.alegria = alegria;
    }
    
    public int getPeso(){
        return this.peso;
    }
    
    public void setPeso(int peso){
        this.peso = peso;
    }
    
    public int getAlegria(){
        return this.alegria;
    }
    
    public void setAlegria(int alegria){
        this.alegria = alegria;
    }
}