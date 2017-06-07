/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class Dinamico {
    
    Voraz vo = new Voraz();
    int alfombrasllenas=0;
    
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C, int numRegalos){
        Alfombra[] alfombras = {A,B,C};
        Alfombra alfombraMayor = vo.mayorCapacidad(alfombras); //se utiliza el mismo método que en los algoritmos voraces
        if(numRegalos > 0 && alfombrasllenas < 3){
            if(alfombraMayor.getPeso()>0){
                if(regalos[numRegalos].getPeso() > alfombraMayor.getPeso() && regalos[numRegalos].getPeso()!=0){
                    alfombraMayor.setAlegria(alfombraMayor.getAlegria());
                    RepartirRegalos(regalos,A,B,C,numRegalos-1);
                }else{
                    if(regalos[numRegalos-1].getPeso()<=alfombraMayor.getPeso() && regalos[numRegalos-1].getPeso()!=0){
                        if(alfombraMayor.getAlegria()+regalos[regalos.length-1].getAlegria()>alfombraMayor.getAlegria()){
                            alfombraMayor.setPeso(alfombraMayor.getPeso()-regalos[numRegalos-1].getPeso());
                            alfombraMayor.setAlegria(alfombraMayor.getAlegria()+regalos[numRegalos-1].getAlegria());
                            alfombraMayor.setRegalo(regalos[numRegalos-1],alfombraMayor.getRegalosIntro());
                            alfombraMayor.setRegalosIntro(alfombraMayor.getRegalosIntro());
                            regalos[numRegalos-1].setPeso(0);
                            regalos[numRegalos-1].setAlegria(0);
                            RepartirRegalos(regalos,A,B,C,numRegalos-1);
                        }else{
                            RepartirRegalos(regalos,A,B,C,numRegalos-1);
                        }
                    }else{
                        RepartirRegalos(regalos,A,B,C,numRegalos-1);
                    }
                }
            }else{
                alfombrasllenas++;
                RepartirRegalos(regalos,A,B,C,numRegalos);
            }
        }
        return alfombras;
    }
}
