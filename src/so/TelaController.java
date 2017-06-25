/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JList;

/**
 *
 * @author GabrielRS
 */
public class TelaController implements Runnable{
    
    private String nome;
    private Spool spool;
    private String[] listaAtual = null;
    private String[] listaEmAndamento = null;
    private TelaUsuario tela;

    public TelaController(String nome, Spool spool) {
        this.nome = nome;
        this.spool = spool;
        this.tela = new TelaUsuario();
    }
    
    public void run(){
        tela.setVisible(true);
        tela.setTitle(nome);
        while(true){
                this.atualizaAguardandoList();
                this.atualizaAndamentoList();
        }
    }

    public TelaUsuario getTela() {
        return tela;
    }
    
    public void atualizaAguardandoList(){
        if(!Arrays.equals(this.spool.converteFilaVector(),this.listaAtual)){
            tela.getAguardandoList().setListData(this.spool.converteFilaVector());
            this.listaAtual = this.spool.converteFilaVector();
        }
    }
    
    public void atualizaAndamentoList(){
        String[] str = {this.spool.getEmAndamento()};
        tela.getAndamentoList().setListData(str);
    }
    
//    public void atualizaAndamentoList(){
//        if(!Arrays.equals(this.spool.converteEmAndamento(),this.listaEmAndamento)){
//            tela.getAndamentoList().setListData(this.spool.converteEmAndamento());
//            this.listaEmAndamento = this.spool.converteEmAndamento();
//        }
//    }
    
}