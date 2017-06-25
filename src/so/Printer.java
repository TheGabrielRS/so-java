/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

/**
 *
 * @author GabrielRS
 */
public class Printer implements Runnable{

    private String render;
    private boolean imprimindo;

    public Printer(String render, boolean imprimindo) {
        this.imprimindo = imprimindo;
        this.render = render;
    }
    
    public void run(){
        try{
            System.out.println("Imprimindo");
            Thread.sleep(1000 * this.render.length());
            System.out.println("Impressão: "+this.render);
            Thread.currentThread().interrupt();
        }catch(Exception e){
            e.printStackTrace();
        }   

    }
    
}
