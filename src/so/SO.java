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
public class SO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Spool spool = new Spool();
        new Thread(spool).start();

        for(int x=0; x < 5; x++){
            new Thread(new Usuario("u"+x, spool, "u"+x, x)).start();
        }


        
        
    }
    
}
