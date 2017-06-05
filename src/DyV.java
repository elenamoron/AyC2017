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
    
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C){
        Alfombra[] alfombras = {A,B,C};
        Alfombra alfombraMayor = vo.mayorCapacidad(alfombras); //se utiliza el mismo método que en los algoritmos voraces
        int pesoDisponible = alfombraMayor.getPeso();
        int regalosIntro=0;
        int alfombrasllenas=0;
        int numRegalos = regalos.length;
        Regalo[] part1 = new Regalo[numRegalos/2];
        Regalo[] part2 = new Regalo[numRegalos/2];
        /*
         * Dividir nuestro array para aplicar el algoritmo
         */
        System.arraycopy(regalos, 0           , part1, 0     , part1.length);
        System.arraycopy(regalos, part1.length, part2, 0     , part2.length);
        if(pesoDisponible>0){
            if(part1[0].getPeso() <= pesoDisponible && (numRegalos/2) > 0){
                RepartirRegalos(part1,A,B,C);
            } else if(part1[0].getPeso() <= pesoDisponible && (numRegalos/2) < 1){
                alfombraMayor.setRegalo(regalos[0],regalosIntro);
                regalosIntro++;
                pesoDisponible -= regalos[0].getPeso();
                regalos[0].setAlegria(0);
                regalos[0].setPeso(0);
                alfombraMayor.setPeso(pesoDisponible);
            } else {
                RepartirRegalos(part2,A,B,C);
            }
        } else {
            alfombrasllenas++;
            alfombras = vo.actualizarAlfombra(alfombraMayor,alfombras);
            alfombraMayor = vo.mayorCapacidad(alfombras);
            pesoDisponible = alfombraMayor.getPeso();
            regalosIntro=0;
            RepartirRegalos(regalos,A,B,C);
        }
                
        return alfombras;
    }
    
}
