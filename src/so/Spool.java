/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.awt.Color;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author GabrielRS
 */
public class Spool implements Runnable{

    private ConcurrentLinkedQueue<String> fila;
    private String emAndamento = "";
    private Thread printerT;
    
    
    public Spool() {
        this.fila = new <String>ConcurrentLinkedQueue();
    }
    
    public void run(){
        while(true){
                if(!fila.isEmpty()){
                    this.imprime();
                }
            }
        }
    
    public boolean adicionaNaFila(String str, String usr){
        fila.add(str);
        new Thread(()->{
                JOptionPane.showMessageDialog(null, "Adicionado na fila\nOrigem: "+usr+"\nPosição: "+fila.size(), usr, 1);
            }).start();
        return true;
    }
    
    public void removeDaFila(String str){
        try{
            fila.remove(str);
        }catch(Exception e){
            new Thread(()->{
                JOptionPane.showMessageDialog(null, "Processo não se encontra na fila");
            }).start();
        }
    }
    
    public void imprime(){
        String buff = this.fila.remove();
        emAndamento = buff;
        boolean imprimindo = true;
        Printer printer = new Printer(buff);
        this.printerT = new Thread(printer);
        this.printerT.start();
        while(this.printerT.isAlive()){
        }
        emAndamento = "";
    }

    public String[] converteFilaVector() {
        return this.converteVector(this.fila);
    }

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
        this.printerT.interrupt();
        this.emAndamento = "Impressão interrompida";
    }
}
