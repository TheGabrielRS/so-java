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
    
    private String nome;
    private Spool spool;
    private String buffer;
    private int fator;

    public Usuario(String nome, Spool spool, String impressao, int fator) {
        this.nome = nome;
        this.spool = spool;
        this.buffer = impressao;
        this.fator = fator;
    }
    
    public void run(){
        TelaUsuarioController tela = new TelaUsuarioController(nome, spool);
        tela.getTela().getImprimirBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buffer = tela.getTela().getTextoParaImprimirTextArea().getText();
                spool.adicionaNaFila(Usuario.this.buffer, Usuario.this.nome);
            }
        });
        tela.getTela().getEncerrarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spool.removeDaFila(tela.getTela().getAguardandoList().getSelectedValue());
            }
        });
        tela.getTela().getSuspenderBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spool.cancelaAndamento();
            }
        });
        new Thread(tela).start();
    }

    @Override
    public String toString() {
        return buffer;
    }
}
