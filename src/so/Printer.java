/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.awt.Color;

/**
 *
 * @author GabrielRS
 */
public class Printer implements Runnable{

    private String render;
    private TelaImpressao telaImpressao;

    public Printer(String render) {
        this.render = render;
    }
    
    public void run(){
        try{
            this.telaImpressao = new TelaImpressao();
            this.telaImpressao.setVisible(true);
            this.telaImpressao.getProgressoBar().setMaximum(10);
            this.telaImpressao.getProgressoBar().setMinimum(0);

            for(int x=0; x < this.render.length(); x++){
                char c = this.render.charAt(x);
                telaImpressao.getRenderLabel().setText(telaImpressao.getRenderLabel().getText()+Character.toString(c));
                float percent = (float)x/this.render.length();
                this.telaImpressao.getProgressoBar().setValue(Math.round(percent*10));;
                Thread.sleep(500);
            }

            this.telaImpressao.dispose();
            this.telaImpressao = null;
            Thread.currentThread().interrupt();
        }catch(Exception e){
            telaImpressao.getRenderLabel().setText("Impressão cancelada!");
            telaImpressao.getRenderLabel().setForeground(Color.red);
            this.telaImpressao.dispose();
            this.telaImpressao = null;
        }
    }

    public TelaImpressao getTelaImpressao() {
        return telaImpressao;
    }
    
}
