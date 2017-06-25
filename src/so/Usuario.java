/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author GabrielRS
 */
public class Usuario implements Runnable{
    
    private boolean naFila;
    private String nome;
    private Spool spool;
    private String impressao;
    private int fator;

    public Usuario(String nome, Spool spool, String impressao, int fator) {
        this.naFila = false;
        this.nome = nome;
        this.spool = spool;
        this.impressao = impressao;
        this.fator = fator;
    }
    
    
    
    public void run(){
//        TelaUsuario tela = new TelaUsuario();
//        tela.setVisible(true);
        TelaController tela = new TelaController(nome, spool);
        tela.getTela().getImprimirBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                impressao = tela.getTela().getTextoParaImprimirTextArea().getText();
                spool.adicionaNaFila(Usuario.this.impressao);
                System.out.println("adicionou na fila "+nome);
                tela.atualizaAguardandoList();
            }
        });
        tela.getTela().getEncerrarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spool.removeDaFila(tela.getTela().getAguardandoList().getSelectedIndex());
            }
        });
        tela.getTela().getSuspenderBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spool.cancelaAndamento();
            }
        });
        new Thread(tela).start();
        while(true){
//            tela.getAguardandoList().setListData(this.spool.getFilaVector());
//            try{
//                Thread.sleep(1000*this.fator);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
        }
    }

    @Override
    public String toString() {
        return impressao;
    }

    public void setNaFila(boolean naFila) {
        this.naFila = naFila;
    }
    
    
}
