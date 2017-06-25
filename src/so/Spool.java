/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GabrielRS
 */
public class Spool implements Runnable{

    private ConcurrentLinkedQueue<String> filaVector;
//    private Vector<String> emAndamento;
    private String emAndamento = "";
    private Thread printer;
//    private String[] filaStr = null;
    
    
    public Spool() {
        this.filaVector = new <String>ConcurrentLinkedQueue();
//        this.emAndamento = new Vector();
        
//        this.filaStr = {"vazio"};
    }
    
    public void run(){
        while(true){
                if(!filaVector.isEmpty()){
                    this.imprime();
                }
            }
        }
    
    public boolean adicionaNaFila(String str){
        filaVector.add(str);
        System.out.println(filaVector.toString());
        return true;
    }
    
    public void removeDaFila(int pos){
        try{
            filaVector.remove(pos);
        }catch(Exception e){
            System.out.println("já foi impresso");
        }
    }
    
    public void imprime(){
        System.out.println("Antes da impressão: "+this.filaVector.toString());
        String impresso = this.filaVector.remove();
        emAndamento = impresso;
//        emAndamento.add(impresso);
        boolean imprimindo = true;
        this.printer = new Thread(new Printer(impresso,imprimindo));
        this.printer.start();
        while(this.printer.isAlive()){
            try {
                Thread.sleep(1000);
                System.out.println("imprimindo - spool");
            } catch (InterruptedException ex) {
                Logger.getLogger(Spool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Após impressão: "+this.filaVector.toString());
//        emAndamento.remove(impresso);
        emAndamento = "";
    }

    public String[] converteFilaVector() {
        return this.converteVector(this.filaVector);
    }
    
//    public String[] converteEmAndamento(){
//        return this.converteVector(this.emAndamento);
//    }
    
    public String getEmAndamento(){
        synchronized(this.emAndamento){
            return this.emAndamento;
        }
    }
    
    public String[] converteVector(ConcurrentLinkedQueue vector){
        if(!vector.isEmpty()){
            String[] str = new String[vector.size()];
            vector.toArray(str);
            return str;
        }else
        {
            String[] str = {""};
            return str;
        }
    }
    
    public void cancelaAndamento(){
        this.printer.interrupt();
        System.out.println("Impressão interrompida");
        this.emAndamento = "Impressão suspensa";
    }

    
}
