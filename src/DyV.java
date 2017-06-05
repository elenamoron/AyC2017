/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class DyV {
    Voraz vo = new Voraz();
    int alfombrasllenas=0;
    
    public Regalo[] actualizarRegalos(Regalo regalo, Regalo[] regalos){
        for(int i=0; i< regalos.length; i++){
            if(regalo.getNumero() == regalos[i].getNumero()){
                regalos[i].setPeso(regalo.getPeso());
                regalos[i].setAlegria(regalo.getAlegria());
            }
        }
        return regalos;
    }
    
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C, Regalo[] regaloAux){
        Alfombra[] alfombras = {A,B,C};
        Alfombra alfombraMayor = vo.mayorCapacidad(alfombras); //se utiliza el mismo método que en los algoritmos voraces
        int pesoDisponible = alfombraMayor.getPeso();
        int numRegalos = regalos.length;
        if(regalos.length > 1){
            Regalo[] part1 = new Regalo[numRegalos/2];
            Regalo[] part2 = new Regalo[numRegalos/2];
            /*
             * Dividir nuestro array para aplicar el algoritmo
             */
            System.arraycopy(regalos, 0           , part1, 0     , part1.length);
            System.arraycopy(regalos, part1.length, part2, 0     , part2.length);
            if(pesoDisponible>0){
                if(part1[0].getPeso()>0){
                    if(part1[0].getPeso() <= pesoDisponible && (numRegalos/2) > 1){
                        RepartirRegalos(part1,A,B,C, regaloAux);
                    } else if(part1[0].getPeso() <= pesoDisponible && (numRegalos/2) < 2){
                        alfombraMayor.setRegalo(part1[0],alfombraMayor.getRegalosIntro()+1);
                        alfombraMayor.setRegalosIntro(1);
                        pesoDisponible -= part1[0].getPeso();
                        part1[0].setAlegria(0);
                        part1[0].setPeso(0);
                        regaloAux = actualizarRegalos(part1[0],regaloAux);
                        alfombraMayor.setPeso(pesoDisponible);
                        RepartirRegalos(part2,A,B,C,regaloAux);
                    }
                } else {
                    RepartirRegalos(part2,A,B,C,regaloAux );
                }
            } else {
                alfombrasllenas++;
                alfombras = vo.actualizarAlfombra(alfombraMayor,alfombras);
                alfombraMayor = vo.mayorCapacidad(alfombras);
                pesoDisponible = alfombraMayor.getPeso();
                alfombraMayor.setRegalosIntro(0);
                RepartirRegalos(regaloAux,A,B,C,regaloAux);
            }
        }else{
            if(regalos[0].getPeso() > 0){
                if(regalos[0].getPeso() <= pesoDisponible){
                    alfombraMayor.setRegalo(regalos[0],alfombraMayor.getRegalosIntro()+1);
                    alfombraMayor.setRegalosIntro(1);
                    pesoDisponible -= regalos[0].getPeso();
                    regalos[0].setAlegria(0);
                    regalos[0].setPeso(0);
                    regaloAux = actualizarRegalos(regalos[0],regaloAux);
                    alfombraMayor.setPeso(pesoDisponible);
                    RepartirRegalos(regaloAux,A,B,C,regaloAux);
                }
            } else {
                RepartirRegalos(regaloAux,A,B,C,regaloAux);
            }
        }
        return alfombras;
    }
    
}
