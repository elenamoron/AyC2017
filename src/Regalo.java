/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class Regalo {
    
    int peso;
    int alegria;
    int numero;
    
    public Regalo(int peso, int alegria, int numero){
        this.peso = peso;
        this.alegria = alegria;
        this.numero = numero;
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
    
    public int getNumero(){
        return this.numero;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
    }
}
