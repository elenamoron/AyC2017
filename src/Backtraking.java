/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class Backtraking {
    
    Voraz vo = new Voraz();
    int alfombrasllenas = 0;
    
    public Regalo[] actualizarRegalos(Regalo regalo, Regalo[] regalos){
        for(int i=0; i< regalos.length; i++){
            if(regalo.getNumero() == regalos[i].getNumero()){
                regalos[i].setPeso(regalo.getPeso());
                regalos[i].setAlegria(regalo.getAlegria());
            }
        }
        return regalos;
    }
    
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C,Regalo[] regalosAux){
        Alfombra[] alfombras = {A,B,C};
        Alfombra alfombraMayor = vo.mayorCapacidad(alfombras); //se utiliza el mismo método que en los algoritmos voraces
        int numParejas = regalos.length/2;
        Regalo [] mitad = new Regalo[numParejas];
        if(numParejas > 1){
            int i=0;
            while(i<regalos.length){                
                if(regalos[i].getAlegria()>regalos[i+1].getAlegria()){
                    mitad[i/2] = new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),regalos[i].getNumero());
                }else{
                    mitad[i/2] = new Regalo(regalos[i+1].getPeso(),regalos[i+1].getAlegria(),regalos[i+1].getNumero());
                }
                i+=2;
            }
            RepartirRegalos(mitad,A,B,C,regalosAux);
        }else{
            if(alfombraMayor.getPeso()!=0 && alfombrasllenas < 3){
                if(regalos[0].getPeso()!=0){
                    if(regalos[0].getAlegria()>regalos[1].getAlegria()){
                        if(regalos[0].getPeso() <= alfombraMayor.getPeso()){
                            alfombraMayor.setPeso(alfombraMayor.getPeso()-regalos[0].getPeso());
                            alfombraMayor.setAlegria(alfombraMayor.getAlegria()+regalos[0].getAlegria());
                            alfombraMayor.setRegalo(regalos[0],alfombraMayor.getRegalosIntro());
                            alfombraMayor.setRegalosIntro(alfombraMayor.getRegalosIntro());
                            regalos[0].setPeso(0);
                            regalos[0].setAlegria(0);
                            regalosAux = actualizarRegalos(regalos[0],regalosAux);
                            RepartirRegalos(regalosAux,A,B,C,regalosAux);

                        }else if(regalos[1].getPeso() <= alfombraMayor.getPeso()){
                            alfombraMayor.setPeso(alfombraMayor.getPeso()-regalos[1].getPeso());
                            alfombraMayor.setAlegria(alfombraMayor.getAlegria()+regalos[1].getAlegria());
                            alfombraMayor.setRegalo(regalos[1],alfombraMayor.getRegalosIntro());
                            alfombraMayor.setRegalosIntro(alfombraMayor.getRegalosIntro());
                            regalos[1].setPeso(0);
                            regalos[1].setAlegria(0);
                            regalosAux = actualizarRegalos(regalos[1],regalosAux);
                            RepartirRegalos(regalosAux,A,B,C,regalosAux);
                        } else{
                            RepartirRegalos(regalosAux,A,B,C,regalosAux);
                        }
                    }
                }else if(regalos[1].getPeso()!=0){
                    if(regalos[1].getPeso() <= alfombraMayor.getPeso()){
                        alfombraMayor.setPeso(alfombraMayor.getPeso()-regalos[1].getPeso());
                        alfombraMayor.setAlegria(alfombraMayor.getAlegria()+regalos[1].getAlegria());
                        alfombraMayor.setRegalo(regalos[0],alfombraMayor.getRegalosIntro());
                        alfombraMayor.setRegalosIntro(alfombraMayor.getRegalosIntro());
                        regalos[1].setPeso(0);
                        regalos[1].setAlegria(0);
                        regalosAux = actualizarRegalos(regalos[1],regalosAux);
                        RepartirRegalos(regalosAux,A,B,C,regalosAux);

                    }else if(regalos[0].getPeso() <= alfombraMayor.getPeso()){
                        alfombraMayor.setPeso(alfombraMayor.getPeso()-regalos[0].getPeso());
                        alfombraMayor.setAlegria(alfombraMayor.getAlegria()+regalos[0].getAlegria());
                        alfombraMayor.setRegalo(regalos[0],alfombraMayor.getRegalosIntro());
                        alfombraMayor.setRegalosIntro(alfombraMayor.getRegalosIntro());
                        regalos[0].setPeso(0);
                        regalos[0].setAlegria(0);
                        regalosAux = actualizarRegalos(regalos[0],regalosAux);
                        RepartirRegalos(regalosAux,A,B,C,regalosAux);
                    } else{
                        RepartirRegalos(regalosAux,A,B,C,regalosAux);
                    }
                }
            } else {
                alfombrasllenas++;
                RepartirRegalos(regalosAux,A,B,C,regalosAux);
            }
        }
        return alfombras;
    }
    
}
