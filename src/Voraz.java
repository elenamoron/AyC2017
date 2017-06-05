/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class Voraz {
   
    public Alfombra mayorCapacidad(Alfombra[] alfombras){
        Alfombra maxCapacidad = alfombras[0];
        for(int i=1; i< alfombras.length; i++){
            if(alfombras[i].getPeso()>maxCapacidad.getPeso()){
                maxCapacidad = alfombras[i];
            }
        }
        return maxCapacidad;
    }
    
    public Alfombra[] actualizarAlfombra(Alfombra actualizacion, Alfombra[] alfombras){
        for(int i=0; i< alfombras.length; i++){
            if(actualizacion.getNombre().equals(alfombras[i].getNombre())){
                alfombras[i].setPeso(actualizacion.getPeso());
            }
        }
        return alfombras;
    }
    
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C){
        Alfombra[] alfombras = {A,B,C};
        Alfombra alfombraMayor = mayorCapacidad(alfombras);
        int pesoDisponible = alfombraMayor.getPeso();
        int regalosIntro=0;
        int alfombrasllenas=0;
        for (int i=0; i < regalos.length; i++){
            if(pesoDisponible>0){
                if(regalos[i].getPeso()>0){
                    if(regalos[i].getPeso() <= pesoDisponible ){
                        alfombraMayor.setRegalo(regalos[i],regalosIntro);
                        regalosIntro++;
                        pesoDisponible -= regalos[i].getPeso();
                        regalos[i].setAlegria(0);
                        regalos[i].setPeso(0);
                        alfombraMayor.setPeso(pesoDisponible);
                    }
                }
            } else{
                alfombrasllenas++;
                alfombras = actualizarAlfombra(alfombraMayor,alfombras);
                alfombraMayor = mayorCapacidad(alfombras);
                pesoDisponible = alfombraMayor.getPeso();
                regalosIntro=0;
                if(alfombrasllenas < alfombras.length){
                    i=0;
                }
                        
            }
        }
        return alfombras; 
    }
}