/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JTextField;

/**
 *
 * @author TOSHIBA
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    Regalo[] regalos = null;
    Alfombra AlfombraA = null;
    Alfombra AlfombraB = null;
    Alfombra AlfombraC = null;
    Alfombra [] alfombras_voraz = null;
    Alfombra [] alfombras_dyv = null;
    Alfombra [] alfombras_dinamico = null;
    Alfombra [] alfombras_backtracking = null;
    int PesoA=0;
    int PesoB=0;
    int PesoC=0;
    public Main() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcbejemplo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaSolucion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Los reyes magos");

        jLabel3.setText("Regalos posibles:");

        jcbejemplo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ejemplo 1", "ejemplo 2", "ejemplo 3" }));
        jcbejemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbejemploActionPerformed(evt);
            }
        });

        jLabel4.setText("SOLUCIÓN");

        jButton1.setText("CALCULAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtaSolucion.setColumns(20);
        jtaSolucion.setRows(5);
        jScrollPane1.setViewportView(jtaSolucion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbejemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))))
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbejemplo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(64, 64, 64)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    public Regalo[] leerFichero (File archivo) throws FileNotFoundException, IOException{ 
        Regalo[] regalos = null;
        try{            
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int contador = 0;
            int numeroRegalos;
            boolean alfombra =true;
            while ((linea=br.readLine())!=null) {                                    
                if (alfombra){
                    switch(contador){
                            case 0:
                                PesoA = Integer.parseInt(linea);
                                AlfombraA = new Alfombra(PesoA,"A");
                                AlfombraA.setRegalosIntro(0);
                                break;
                            case 1:
                               PesoB = Integer.parseInt(linea);
                               AlfombraB = new Alfombra(PesoB,"B");
                               AlfombraB.setRegalosIntro(0);
                               break;
                            case 2:
                                PesoC = Integer.parseInt(linea);
                                AlfombraC = new Alfombra(PesoC,"C");
                                AlfombraC.setRegalosIntro(0);
                                alfombra = false;
                                contador=-2;
                                break;
                    }
                    contador ++;
                } else{
                    if(contador == -1){
                        /*
                        *   Linea que indica el número total de regalos
                        */
                        String [] array = linea.split("\t");
                        numeroRegalos = Integer.parseInt(array[0]);
                        AlfombraA.initRegalos(numeroRegalos);
                        AlfombraB.initRegalos(numeroRegalos);
                        AlfombraC.initRegalos(numeroRegalos);
                        regalos = new Regalo[numeroRegalos];
                        contador ++;
                    }else{
                        /*
                        * Separamos los valores de peso y alegria que viene en la misma linea con un tabulador
                        */
                        String [] array = linea.split("\t"); 
                        regalos[contador] = new Regalo(Integer.parseInt(array[0]),Integer.parseInt(array[1]),contador);
                        contador ++;
                    }
                    
                }
            } 
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return regalos;
    }
    
    public Regalo[] correrPosiciones(Regalo[] regalosOrdenados, Regalo regaloAux, int posicion, int inicio){
        Regalo regaloOrAux = null;
        Regalo tmp = null;
        if(inicio!=posicion){
            for(int j=inicio;j<posicion;j++){
                if(j==inicio){
                    regaloOrAux = new Regalo(regalosOrdenados[j].getPeso(),regalosOrdenados[j].getAlegria(),j);
                    regalosOrdenados[j].setPeso(regaloAux.getPeso());
                    regalosOrdenados[j].setAlegria(regaloAux.getAlegria());
                }else{
                    if(tmp == null){
                        tmp = new Regalo(regalosOrdenados[j].getPeso(),regalosOrdenados[j].getAlegria(),j);
                        regalosOrdenados[j].setPeso(regaloOrAux.getPeso());
                        regalosOrdenados[j].setAlegria(regaloOrAux.getAlegria());
                        if(regalosOrdenados[j+1] != null){
                            regaloOrAux.setPeso(regalosOrdenados[j+1].getPeso());
                            regaloOrAux.setAlegria(regalosOrdenados[j+1].getAlegria());
                        }
                    }else{
                        regalosOrdenados[j].setPeso(tmp.getPeso());
                        regalosOrdenados[j].setAlegria(tmp.getAlegria());
                        tmp.setPeso(regaloOrAux.getPeso());
                        tmp.setAlegria(regaloOrAux.getAlegria());
                        if(regalosOrdenados[j+1] != null){
                            regaloOrAux.setPeso(regalosOrdenados[j+1].getPeso());
                            regaloOrAux.setAlegria(regalosOrdenados[j+1].getAlegria());
                        }
                    }
                }

            }
            if(tmp!=null){
                regalosOrdenados[posicion]=new Regalo(tmp.getPeso(),tmp.getAlegria(),posicion);       
            }else{
                regalosOrdenados[posicion]=new Regalo(regaloOrAux.getPeso(),regaloOrAux.getAlegria(),posicion);       
            }
        }else{
            regalosOrdenados[posicion]=new Regalo(regaloAux.getPeso(),regaloAux.getAlegria(),posicion); 
        }
        return regalosOrdenados;
    } 
    public Regalo[] ordenarPorTasa(Regalo[] regalos, int longitud){
        Double [] tasas = new Double
        return regalos;
    }
    public Regalo[] ordenarRegalosDeMayorAlegriaAMenor(Regalo[] regalos, int longitud){
        int mayorAlegria = regalos[0].getAlegria();
        int pesoMayorAlegria = regalos[0].getPeso();
        Regalo [] regalosOrdenados = new Regalo[regalos.length];
        for(int i=0; i< longitud; i++){
            if(i==0){                
                /*
                * primera iteración
                */
                if (regalos[i+1].getAlegria() > mayorAlegria){
                    /*
                    * Si hubiese valor en regalosOrdenados
                    */
                    if(regalosOrdenados[i]!=null){
                        Regalo regaloAux = new Regalo(regalosOrdenados[i].getPeso(),regalosOrdenados[i].getAlegria(),i);
                        regalosOrdenados[i].setAlegria(regalos[i+1].getAlegria());
                        regalosOrdenados[i].setPeso(regalos[i+1].getPeso());
                        regalosOrdenados[i+1].setAlegria(regaloAux.getAlegria());
                        regalosOrdenados[i+1].setPeso(regaloAux.getPeso());
                        mayorAlegria = regalosOrdenados[i+1].getAlegria();
                        pesoMayorAlegria = regalosOrdenados[i+1].getPeso();
                        
                    }else{
                        regalosOrdenados[i]= new Regalo(regalos[i+1].getPeso(),regalos[i+1].getAlegria(),i);
                    }
                }else{
                    regalosOrdenados[i] = new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),i);
                }
            } else if (regalos[i].getAlegria() > mayorAlegria){
                /*
                 * Demás iteraciones
                 */
                if(regalosOrdenados[i]!=null){
                    Regalo regaloAux = new Regalo(regalosOrdenados[i].getPeso(),regalosOrdenados[i].getAlegria(),i);
                    regalosOrdenados[i].setAlegria(regalos[i].getAlegria());
                    regalosOrdenados[i].setPeso(regalos[i].getPeso());
                    regalosOrdenados[i+1].setAlegria(regaloAux.getAlegria());
                    regalosOrdenados[i+1].setPeso(regaloAux.getPeso());
                    mayorAlegria = regalosOrdenados[i].getAlegria();
                    pesoMayorAlegria = regalosOrdenados[i].getPeso();
                }else{
                    Regalo regaloAux = new Regalo(regalosOrdenados[0].getPeso(),regalosOrdenados[0].getAlegria(),i);
                    regalosOrdenados[0].setPeso(regalos[i].getPeso());
                    regalosOrdenados[0].setAlegria(regalos[i].getAlegria());
                    regalosOrdenados = correrPosiciones(regalosOrdenados,regaloAux,i,1);
                    mayorAlegria = regalos[i].getAlegria();
                    pesoMayorAlegria = regalos[i].getPeso();
                }
            } else if(regalos[i].getAlegria()== mayorAlegria){
                /*
                * Si las alegrias coinciden, se coloca el de menor peso primero
                */
                if(regalos[i].getPeso()<pesoMayorAlegria){
                    if(regalosOrdenados[i]!=null){
                        Regalo regaloAux = new Regalo(regalosOrdenados[i].getPeso(),regalosOrdenados[i].getAlegria(),i);
                        regalosOrdenados[i].setAlegria(regalos[i].getAlegria());
                        regalosOrdenados[i].setPeso(regalos[i].getPeso());
                        regalosOrdenados[i+1].setAlegria(regaloAux.getAlegria());
                        regalosOrdenados[i+1].setPeso(regaloAux.getPeso());
                        mayorAlegria = regalosOrdenados[i].getAlegria();
                        pesoMayorAlegria = regalosOrdenados[i].getPeso();
                        
                    }else{
                        regalosOrdenados[i] = new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),i);
                        mayorAlegria = regalosOrdenados[i].getAlegria();
                        pesoMayorAlegria = regalosOrdenados[i].getPeso();
                    }
                }else{
                    if(regalosOrdenados[i]!=null){
                        Regalo regaloAux = new Regalo(regalosOrdenados[i].getPeso(),regalosOrdenados[i].getAlegria(),i);
                        regalosOrdenados[i].setAlegria(mayorAlegria);
                        regalosOrdenados[i].setPeso(pesoMayorAlegria);
                        regalosOrdenados[i+1].setAlegria(regaloAux.getAlegria());
                        regalosOrdenados[i+1].setPeso(regaloAux.getPeso());
                        mayorAlegria = regalosOrdenados[i].getAlegria();
                        pesoMayorAlegria = regalosOrdenados[i].getPeso();
                        
                    }else{
                        if(regalosOrdenados[i-1].getAlegria()<regalos[i].getAlegria()){
                            Regalo regaloAux = new Regalo(regalosOrdenados[i-1].getPeso(),regalosOrdenados[i-1].getAlegria(),i-1);
                            regalosOrdenados[i-1].setAlegria(regalos[i].getAlegria());
                            regalosOrdenados[i-1].setPeso(regalos[i].getPeso());
                            regalosOrdenados[i] = new Regalo(regaloAux.getPeso(),regaloAux.getAlegria(),i);
                        }else{
                            regalosOrdenados[i] = new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),i);
                        }
                    }
                }
            } else {
                if(regalosOrdenados[0]!=null){
                    if(regalosOrdenados[i]!=null){
                            Regalo regaloAux = new Regalo(regalosOrdenados[i].getPeso(),regalosOrdenados[i].getAlegria(),i);
                            regalosOrdenados[i].setAlegria(mayorAlegria);
                            regalosOrdenados[i].setPeso(pesoMayorAlegria);
                            regalosOrdenados[i+1].setAlegria(regaloAux.getAlegria());
                            regalosOrdenados[i+1].setPeso(regaloAux.getPeso());
                            mayorAlegria = regalosOrdenados[i].getAlegria();
                            pesoMayorAlegria = regalosOrdenados[i].getPeso();

                    }else{
                        if(i!=0){
                            if(regalosOrdenados[i-1].getAlegria()< regalos[i].getAlegria()){
                                for (int k = 0; k < i; k++) {
                                    if (regalosOrdenados[k].getAlegria()<regalos[i].getAlegria()) {
                                        Regalo regaloAux = new Regalo(regalosOrdenados[k].getPeso(),
                                                                      regalosOrdenados[k].getAlegria(),k);
                                        regalosOrdenados[k].setPeso(regalos[i].getPeso());
                                        regalosOrdenados[k].setAlegria(regalos[i].getAlegria());
                                        regalosOrdenados = correrPosiciones(regalosOrdenados,regaloAux,i,k+1);
                                        //Para salir del bucle
                                        k=i;
                                    }
                                }
                            } else if(regalosOrdenados[i-1].getAlegria()== regalos[i].getAlegria()){
                                if(regalosOrdenados[i-1].getPeso()<= regalos[i].getPeso()){
                                    regalosOrdenados[i] = new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),i);
                                }else{
                                    Regalo regaloAux = new Regalo(regalosOrdenados[i-1].getPeso(),
                                                                  regalosOrdenados[i-1].getAlegria(),
                                                                  i);
                                    regalosOrdenados[i-1].setPeso(regalos[i].getPeso());
                                    regalosOrdenados[i-1].setAlegria(regalos[i].getAlegria());
                                    regalosOrdenados[i]= new Regalo(regaloAux.getPeso(),regaloAux.getAlegria(),i);
                                }
                            } else {
                                regalosOrdenados[i] = new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),i);
                            }
                        }else{
                            regalosOrdenados[i] = new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),i);
                        }
                    }
                }else{
                    regalosOrdenados[i] = new Regalo(regalos[i].getPeso(),regalos[i].getAlegria(),i);
                }
            }
        }
        return regalosOrdenados;
    }
    
    public void mostrarResultado(Alfombra[] alfombras, long duration){
        for (int i=0; i<alfombras.length;i++){
                alfombras[i].setAlegria(alfombras[i].sumarAlegria(alfombras[i].getRegalos()));
                jtaSolucion.append("Alegria de la alfombra "+
                        alfombras[i].getNombre()+"--"+
                        alfombras[i].getAlegria()+" lleva "+
                        alfombras[i].getNumRegalos(alfombras[i].getRegalos())+" número de regalos"+"\n");
            }
        jtaSolucion.append("Duracion del algoritmo en nanosegundos "+duration+"\n");
    }
    
    public void escribirFichero(Alfombra[] alfombras, PrintWriter pw, String salida ){
        try{
            pw.println("Alegria de la alfombra "+
                    alfombras[0].getNombre()+"--"+
                    alfombras[0].getAlegria()+" lleva "+
                    alfombras[0].getNumRegalos(alfombras[0].getRegalos())+" número de regalos"+"\n");
            pw.println("Alegria de la alfombra "+
                    alfombras[1].getNombre()+"--"+
                    alfombras[1].getAlegria()+" lleva "+
                    alfombras[1].getNumRegalos(alfombras[1].getRegalos())+" número de regalos"+"\n");
            pw.println("Alegria de la alfombra "+
                    alfombras[2].getNombre()+"--"+
                    alfombras[2].getAlegria()+" lleva "+
                    alfombras[2].getNumRegalos(alfombras[2].getRegalos())+" número de regalos"+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void resetVariables(){
        AlfombraA.setPeso(PesoA);
        AlfombraA.setRegalos(new Regalo[regalos.length]);
        AlfombraA.setAlegria(0);
        AlfombraA.setRegalosIntro(0);
        AlfombraB.setPeso(PesoB);
        AlfombraB.setRegalos(new Regalo[regalos.length]);
        AlfombraB.setAlegria(0);
        AlfombraB.setRegalosIntro(0);
        AlfombraC.setPeso(PesoC);
        AlfombraC.setRegalos(new Regalo[regalos.length]);
        AlfombraC.setAlegria(0);
        AlfombraC.setRegalosIntro(0);
            
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:      
        Voraz vo = new Voraz();
        DyV dyv = new DyV();
        Dinamico dinamico = new Dinamico();
        Backtracking backtracking = new Backtracking();
        File fis = new File("");
        String salida="";
        switch(jcbejemplo.getSelectedItem().toString()){
            case "ejemplo 1": {
                /*
                * Función System.getProperty("user.dir")---> Devuelve el directorio raíz donde se encuentra el proyecto,
                *   para así poder ejecutar desde cualquier ordenador sin problemas de rutas
                */
                fis = new File(System.getProperty("user.dir")+"/ej1.txt");
                salida="ej1Resultado.txt";
                break;
            }
                        
            case "ejemplo 2": {
                fis = new File(System.getProperty("user.dir")+"/ej2.txt");
                salida="ej2Resultado.txt";
                break;
            }
            
            case "ejemplo 3": {
                fis = new File(System.getProperty("user.dir")+"/ej3.txt");
                salida="ej3Resultado.txt";
                break;
            }
        
        }
        try {                   
            regalos = leerFichero(fis);
            Regalo [] regalosCopy = new Regalo[regalos.length]; 
            regalosCopy = leerFichero(fis);
            Regalo[] regalosOrdenados = new Regalo[regalos.length]; 
            Regalo[] regalosOrdenadosCopy = new Regalo[regalos.length]; 
            regalosOrdenados = ordenarRegalosDeMayorAlegriaAMenor(regalos,regalos.length);
            regalosOrdenadosCopy = ordenarRegalosDeMayorAlegriaAMenor(regalos,regalos.length);
            long startTime = System.nanoTime();
            alfombras_voraz = vo.RepartirRegalos(regalosOrdenados,AlfombraA,AlfombraB, AlfombraC);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            jtaSolucion.append("Resultado por voraz\n");
            FileWriter fichero = null;
            PrintWriter pw = null;
            fichero = new FileWriter(System.getProperty("user.dir")+"/"+salida);
            pw = new PrintWriter(fichero);
            pw.println("Resultado por voraz\n");
            mostrarResultado(alfombras_voraz, duration);
            escribirFichero(alfombras_voraz, pw, salida);
            jtaSolucion.append("Resultado por Divide y vencerás \n");
            resetVariables();
            startTime = System.nanoTime();
            alfombras_dyv = dyv.RepartirRegalos(regalosOrdenadosCopy,AlfombraA,AlfombraB, AlfombraC,regalosOrdenadosCopy);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            pw.println("Resultado por Divide y vencerás \n");
            mostrarResultado(alfombras_dyv,duration);
            escribirFichero(alfombras_dyv, pw, salida);
            resetVariables();
            jtaSolucion.append("Resultado por Programación dinámica \n");
            startTime = System.nanoTime();
            alfombras_dinamico = dinamico.RepartirRegalos(regalos,AlfombraA,AlfombraB, AlfombraC, regalos.length-1);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            pw.println("Resultado por Programación dinámica \n");
            mostrarResultado(alfombras_dinamico,duration);
            escribirFichero(alfombras_dinamico, pw, salida);
            resetVariables();
            jtaSolucion.append("Resultado por Backtraking \n");
            startTime = System.nanoTime();
            alfombras_backtracking = backtracking.RepartirRegalos(regalosCopy,AlfombraA,AlfombraB, AlfombraC);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            pw.println("Resultado por Backtraking \n");
            mostrarResultado(alfombras_backtracking,duration);
            escribirFichero(alfombras_backtracking, pw, salida);
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcbejemploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbejemploActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbejemploActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbejemplo;
    private javax.swing.JTextArea jtaSolucion;
    // End of variables declaration//GEN-END:variables
}
