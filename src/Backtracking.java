
import java.io.File;
import java.io.IOException;


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
    Main m = new Main();
    
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
    
    public Alfombra algoritmo(Regalo [] regalos, Alfombra alfombra,int nivel, int mejorBeneficio, int[] solucion, int pesoAlfombra, Regalo[] regaloAux, File fis) throws IOException{
        int i=numSolucion;
        
        while(alfombra.getPeso()>0 && i< regalos.length){
           if(nivel == 0 || nivel != i){
                if(regalos[i].getPeso()<=alfombra.getPeso() && regalos[i].getPeso()!=0){
                    if(alfombra.getRegalosIntro()!= 0){
                        if(alfombra.getRegalos()[alfombra.getRegalosIntro()-1].getNumero() != regalos[i].getNumero()){
                            alfombra.setRegalo(regalos[i], alfombra.getRegalosIntro(),regalos[i].getNumero());
                            alfombra.setPeso(alfombra.getPeso()-regalos[i].getPeso());
                            alfombra.setRegalosIntro(alfombra.getRegalosIntro());
                        }
                    }else{
                        alfombra.setRegalo(regalos[i], alfombra.getRegalosIntro(),regalos[i].getNumero());
                        alfombra.setPeso(alfombra.getPeso()-regalos[i].getPeso());
                        alfombra.setRegalosIntro(alfombra.getRegalosIntro());
                    }
                }
                i++;
           }else{
               if(regalos[i+1].getPeso()<=alfombra.getPeso() && regalos[i+1].getPeso()!=0){
                     alfombra.setRegalo(regalos[i+1], alfombra.getRegalosIntro(), regalos[i+1].getNumero());
                     alfombra.setPeso(alfombra.getPeso()-regalos[i+1].getPeso());
                     alfombra.setRegalosIntro(alfombra.getRegalosIntro());
                 }
                i++;
           }
        }
        
        for(int j=0; j<alfombra.getRegalos().length;j++){
            if(alfombra.getRegalos()[j]!=null){
                solucion[j] = alfombra.getRegalos()[j].getNumero(); 
            }
        }
        
        int beneficioEncontrado = beneficio(solucion,regalos);
        if(beneficioEncontrado>mejorBeneficio){
            soluciones[numSolucion]= solucion;
            mejorBeneficio = beneficioEncontrado;
        }
        
        nivel = alfombra.getRegalosIntro();
        if(alfombra.getRegalosIntro()-1 >0 ){
            regalos[alfombra.getRegalos()[alfombra.getRegalosIntro()-1].getNumero()].setAlegria(0);
            regalos[alfombra.getRegalos()[alfombra.getRegalosIntro()-1].getNumero()].setPeso(0);
        }
        while(solucionesPorRegalo < regalos.length){
            alfombra.setPeso(pesoAlfombra);
            alfombra.setRegalos(new Regalo[regalos.length]);
            solucionesPorRegalo++;    
            alfombra.setRegalosIntro(0);
            if(nivel!=0){
                nivel--;
            }
            algoritmo(regalos, alfombra,nivel,mejorBeneficio,solucion,pesoAlfombra,regaloAux,fis);
            
            
        }
        numSolucion++;
        solucionesPorRegalo=0;
        alfombra.setRegalos(new Regalo[regalos.length]);
        alfombra.setPeso(pesoAlfombra);
        alfombra.setRegalosIntro(0);
        regaloAux = m.leerFichero(fis);
        if(nivel!=0){
            nivel--;
        }
        algoritmo(regaloAux, alfombra,nivel,mejorBeneficio,solucion,pesoAlfombra,regaloAux,fis);
    
        
        return alfombra;
    }
    public Alfombra[] RepartirRegalos(Regalo[] regalos, Alfombra A, Alfombra B, Alfombra C, File fichero) throws IOException{
        Alfombra[] alfombras = {A,B,C};  
        int nivel = 0;
        int mejorBeneficio = 0;
        Regalo[] regalosAux = new Regalo[regalos.length];
        for(int i=0;i<regalos.length;i++){
            regalosAux[i]= new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),regalos[i].getNumero());
        }
        
      
        while(alfombrasllenas<3){
            Alfombra alfombraMayor = vo.mayorCapacidad(alfombras); //se utiliza el mismo método que en los algoritmos voraces
            int pesoAlfombra = alfombraMayor.getPeso(); 
            int [] solucion = new int[regalos.length];
            alfombraMayor = algoritmo(regalos,alfombraMayor,nivel,mejorBeneficio,solucion, pesoAlfombra,regalosAux,fichero);
            alfombrasllenas++;
        }
        
        return alfombras;
    }
    
}
