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
    int solucionesPorRegalo = 0;
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
    
    public int beneficio(int[] solucion, Regalo[] regalos){
        int beneficio = 0;
        for(int i=0; i< solucion.length; i++){
            if(i==0){
                beneficio+=regalos[solucion[i]].getAlegria();
            } else if(solucion[i]!=0){
                beneficio+=regalos[solucion[i]].getAlegria();
            }
        }
        return beneficio;
    }
    
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C, int nivel, int mejorBeneficio, Regalo[] regalosAux){
        Alfombra[] alfombras = {A,B,C};
        Alfombra alfombraMayor = vo.mayorCapacidad(alfombras); //se utiliza el mismo método que en los algoritmos voraces
        int [] solucion = new int[regalos.length];
        int i=numSolucion;
        int pesoAlfombra = alfombraMayor.getPeso(); 
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
        
        int beneficioEncontrado = beneficio(solucion,regalos);
        if(beneficioEncontrado>mejorBeneficio){
            soluciones[numSolucion]= solucion;
            mejorBeneficio = beneficioEncontrado;
        }
        
        nivel = alfombraMayor.getRegalosIntro();
        if(alfombraMayor.getRegalosIntro()-1 >0 ){
            regalos[alfombraMayor.getRegalos()[alfombraMayor.getRegalosIntro()-1].getNumero()].setAlegria(0);
            regalos[alfombraMayor.getRegalos()[alfombraMayor.getRegalosIntro()-1].getNumero()].setPeso(0);
        }
        while(solucionesPorRegalo < regalos.length){
            alfombraMayor.setPeso(pesoAlfombra);
            alfombraMayor.setRegalos(new Regalo[regalos.length]);
            alfombraMayor.setRegalosIntro(0);
            solucionesPorRegalo++;
            RepartirRegalos(regalos, A, B, C,nivel-1,mejorBeneficio,regalosAux);
        }
        numSolucion++;
        solucionesPorRegalo=0;
        alfombraMayor.setPeso(pesoAlfombra);
        RepartirRegalos(regalosAux, A, B, C,0,mejorBeneficio,regalosAux);
    
        return alfombras;
    }
    
}
