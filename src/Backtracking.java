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
    
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C, int nivel){
        Alfombra[] alfombras = {A,B,C};
        Alfombra alfombraMayor = vo.mayorCapacidad(alfombras); //se utiliza el mismo método que en los algoritmos voraces
        int [] solucion = new int[regalos.length];
        int i=0;
        while(alfombraMayor.getPeso()>0 && i< regalos.length){
           if(nivel == 0 || nivel != i){
                if(regalos[i].getPeso()<=alfombraMayor.getPeso() && regalos[i].getPeso()!=0){
                     alfombraMayor.setRegalo(regalos[i], alfombraMayor.getRegalosIntro(),regalos[i].getNumero());
                     alfombraMayor.setPeso(alfombraMayor.getPeso()-regalos[i].getPeso());
                     alfombraMayor.setRegalosIntro(alfombraMayor.getRegalosIntro());
                 }
                i++;
           }else{
               if(regalos[i+1].getPeso()<=alfombraMayor.getPeso() && regalos[i+1].getPeso()!=0){
                     alfombraMayor.setRegalo(regalos[i+1], alfombraMayor.getRegalosIntro(), regalos[i+1].getNumero());
                     alfombraMayor.setPeso(alfombraMayor.getPeso()-regalos[i+1].getPeso());
                     alfombraMayor.setRegalosIntro(alfombraMayor.getRegalosIntro());
                 }
                i++;
           }
        }
        
        for(int j=0; j<alfombraMayor.getRegalos().length;j++){
            if(alfombraMayor.getRegalos()[j]!=null){
                solucion[j] = alfombraMayor.getRegalos()[j].getNumero(); 
            }
        }
        soluciones[numSolucion]= solucion;
        numSolucion++;
        nivel = alfombraMayor.getRegalosIntro();
        if(i!=0){
            regalos[alfombraMayor.getRegalos()[alfombraMayor.getRegalosIntro()-1].getNumero()].setAlegria(0);
            regalos[alfombraMayor.getRegalos()[alfombraMayor.getRegalosIntro()-1].getNumero()].setPeso(0);
        }
        for(int j=0; i<alfombras.length;j++){
            if(alfombraMayor.getNombre() == alfombras[i].getNombre()){
                alfombraMayor.setPeso(alfombras[i].getPeso());
            }
        }
        
        alfombraMayor.setRegalos(new Regalo[regalos.length]);
        alfombraMayor.setRegalosIntro(0);
        RepartirRegalos(regalos, A, B, C,nivel-1);
        return alfombras;
    }
    
}
