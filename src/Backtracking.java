
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
                regalos[i].setPeso(0);
                regalos[i].setAlegria(0);
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
    public Alfombra mejorSolucion(Alfombra alfombra, int[][] soluciones, Regalo[] regalos){
        int alegria = 0;
        int solucion = 0;
        for(int i=0;i<soluciones.length;i++){
            for (int j=0;j<soluciones.length;j++){
                if(j==0){
                    alfombra.setRegalo(regalos[soluciones[solucion][j]], j, regalos[soluciones[solucion][j]].getNumero());
                } else if (soluciones[i][j]!=0){
                    alfombra.setRegalo(regalos[soluciones[solucion][j]], j, regalos[soluciones[solucion][j]].getNumero());
                }
            }
            if(alegria < alfombra.sumarAlegria(alfombra.getRegalos())){
                alegria = alfombra.sumarAlegria(alfombra.getRegalos());
                solucion = i;
                alfombra.setRegalos(new Regalo[regalos.length]);
                alfombra.setRegalosIntro(0);
                
            }
        }
        alfombra.setRegalos(new Regalo[regalos.length]);
        alfombra.setRegalosIntro(0);
        for (int j=0;j<soluciones.length;j++){
            if(j==0){
               alfombra.setRegalo(regalos[soluciones[solucion][j]], j, regalos[soluciones[solucion][j]].getNumero());
               alfombra.setRegalosIntro(alfombra.getRegalosIntro());
            } else if (soluciones[solucion][j]!=0){
                alfombra.setRegalo(regalos[soluciones[solucion][j]], j, regalos[soluciones[solucion][j]].getNumero());
                alfombra.setRegalosIntro(alfombra.getRegalosIntro());
            }
        }
        return alfombra;
    }
    public Alfombra algoritmo(Regalo [] regalos, Alfombra alfombra,int nivel, int mejorBeneficio, int[] solucion, int pesoAlfombra,
            Regalo[] regaloAux, File fis, Alfombra A, Alfombra B, Alfombra C) throws IOException{
        int i=numSolucion;
        while(numSolucion < regalos.length){
            while(alfombra.getPeso()>0 && i< regalos.length){
               if(nivel == 0 || nivel != i){
                    if(regalos[i].getPeso()<=alfombra.getPeso() && regalos[i].getPeso()!=0){
                        if(alfombra.getRegalosIntro()!= 0){
                            if(alfombra.getRegalos()[alfombra.getRegalosIntro()-1]!=null && alfombra.getRegalos()[alfombra.getRegalosIntro()-1].getNumero() != regalos[i].getNumero()){
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
                solucion = new int[regalos.length];
                algoritmo(regalos, alfombra,nivel,mejorBeneficio,solucion,pesoAlfombra,regaloAux,fis,A,B,C);


            }
            numSolucion++;
            solucionesPorRegalo=0;
            alfombra.setRegalos(new Regalo[regalos.length]);
            alfombra.setPeso(pesoAlfombra);
            alfombra.setRegalosIntro(0);
            if(alfombrasllenas == 0){
                regaloAux = m.leerFichero(fis);
            } else {
                regaloAux = m.leerFichero(fis);
                if (alfombra.getNombre()!= A.getNombre()){
                    for(int j=0; j< A.getNumRegalos(A.getRegalos());j++){
                        if(A.getRegalos()[j]!=null){
                            regaloAux = actualizarRegalos(A.getRegalos()[j],regaloAux);
                        }
                    }
                }
                if (alfombra.getNombre()!= B.getNombre()){
                    for(int j=0; j< B.getNumRegalos(B.getRegalos());j++){
                        if(B.getRegalos()[j]!=null){
                            regaloAux = actualizarRegalos(B.getRegalos()[j],regaloAux);
                        }
                    }
                    
                }
                if (alfombra.getNombre()!= C.getNombre()){
                    if(C.getRegalosIntro()> 0){
                        for(int j=0; j< C.getNumRegalos(C.getRegalos());j++){
                            if(C.getRegalos()[j]!=null){
                                regaloAux = actualizarRegalos(C.getRegalos()[j],regaloAux);
                            }
                        }
                    }
                }
            }
            if(nivel!=0){
                nivel--;
            }
            algoritmo(regaloAux, alfombra,nivel,mejorBeneficio,solucion,pesoAlfombra,regaloAux,fis,A,B,C);
        }
        if(alfombrasllenas == 0){
            regaloAux = m.leerFichero(fis);
        }
        alfombra = mejorSolucion(alfombra,soluciones,regaloAux);
        alfombra.setPeso(0);
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
            numSolucion = 0;
            alfombraMayor = algoritmo(regalos,alfombraMayor,nivel,mejorBeneficio,solucion, pesoAlfombra,regalosAux,fichero,A,B,C);
            
            regalos = m.leerFichero(fichero);
            
            for(int j=0; j< alfombras.length;j++){
                if(alfombras[j].getPeso()== 0){
                    for(int i=0; i< alfombras[j].getNumRegalos(alfombras[j].getRegalos());i++){
                        regalos = actualizarRegalos(alfombras[j].getRegalos()[i],regalos);
                    }
                }
            }
            switch(alfombraMayor.getNombre()){
                case "A":
                    A.setRegalos(alfombraMayor.getRegalos());
                    A.setPeso(alfombraMayor.getPeso());
                    
                    break;
                case "B":
                    B.setRegalos(alfombraMayor.getRegalos());
                    B.setPeso(alfombraMayor.getPeso());
                    
                    break;
                case "C":
                    C.setRegalos(alfombraMayor.getRegalos());
                    C.setPeso(alfombraMayor.getPeso());
                    
                    break;
            }
            alfombrasllenas++;
            
        }
        
        return alfombras;
    }
    
}
