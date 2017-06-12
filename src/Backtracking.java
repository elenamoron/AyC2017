/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class Backtracking {
    
    Voraz vo = new Voraz();
    int alfombrasllenas = 0;
    int [][] soluciones = new int[20][20];
    int numSolucion = 0;
    
    public Regalo[] actualizarRegalos(Regalo regalo, Regalo[] regalos){
        for(int i=0; i< regalos.length; i++){
            if(regalo.getNumero() == regalos[i].getNumero()){
                regalos[i].setPeso(regalo.getPeso());
                regalos[i].setAlegria(regalo.getAlegria());
            }
        }
        return regalos;
    }
    
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C){
        Alfombra[] alfombras = {A,B,C};
        Alfombra alfombraMayor = vo.mayorCapacidad(alfombras); //se utiliza el mismo método que en los algoritmos voraces
        int [] solucion = new int[regalos.length];
        int i=0;
        while(alfombraMayor.getPeso()>0){
           if(regalos[i].getPeso()<=alfombraMayor.getPeso()){
                alfombraMayor.setRegalo(regalos[i], alfombraMayor.getRegalosIntro());
                alfombraMayor.setPeso(regalos[i].getPeso()-regalos[i].getPeso());
                regalos[i].setPeso(0);
                regalos[i].setAlegria(0);
                alfombraMayor.setRegalosIntro(alfombraMayor.getRegalosIntro());
            }
           i++;
        }
        
        for(int j=0; j<alfombraMayor.getRegalos().length;j++){
            if(alfombraMayor.getRegalos()[j]!=null){
                solucion[j] = alfombraMayor.getRegalos()[j].getNumero(); 
            }
        }
        soluciones[numSolucion]= solucion;
        numSolucion++;
        return alfombras;
    }
    
}
