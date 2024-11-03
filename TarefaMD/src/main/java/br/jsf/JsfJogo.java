/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.ejb.EjbParticipanteJogar;
import br.ejb.EjbPlacar;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 *
 * @author Sandro Amancio de Sá
 */
@Named(value = "jsfJogo")
@SessionScoped
public class JsfJogo implements Serializable {

    @EJB
    private EjbPlacar ejbPlacar;

    @EJB
    private EjbParticipanteJogar ejbParticipanteJogar;
    
    private int somaDoJogador;
    
    private String mensagem;
    
    
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "java/Topico")
    private Topic topico;

    public void setNomeDoJogador(String nomeDoJogador){
        ejbParticipanteJogar.setNomeDoJogador(nomeDoJogador);
    }
    
    public String getNomeDoJogador(){
        return ejbParticipanteJogar.getNomeDoJogador();
    }

    public int getSomaDoJogador() {
        return somaDoJogador;
    }

    public void setSomaDoJogador(int somaDoJogador) {
        this.somaDoJogador = somaDoJogador;
    }
    
    public int getNumero1(){
        return ejbParticipanteJogar.getNumero1();
    }
    
    public int getNumero2(){
        return ejbParticipanteJogar.getNumero2();
    }

    public String getMensagem() {
        return mensagem;
    }
   
    public String verificarSoma() {
        final String NOME_VAZIO_MSG = "Opsss! Digite seu nome!!!";
        final String CORRETO_MSG = "Correto! Você ganhou um ponto";
        final String TENTE_NOVAMENTE_MSG = "Opsss! Tente novamente";
        
        String nomeJogador = ejbParticipanteJogar.getNomeDoJogador();

        if (nomeJogador == null || nomeJogador.isEmpty()) {
            mensagem = NOME_VAZIO_MSG;
            return null;
        }

        boolean correto = ejbParticipanteJogar.verificarSoma(somaDoJogador);
        ejbPlacar.atualizarPlacar(nomeJogador, ejbParticipanteJogar.getPontos());

        if (correto) {
            mensagem = CORRETO_MSG;
            send();
            return "success"; 
        } else {
            mensagem = TENTE_NOVAMENTE_MSG;
            return null; 
        }
    }

    public int getPontos(){
        return ejbParticipanteJogar.getPontos();
    }
    
    public List<Map.Entry<String, Integer>> getPlacar(){
        return ejbPlacar.getPlacar();
    }
    
   private void send() {
    try {
        
        List<Map.Entry<String, Integer>> placarList = new ArrayList<>(ejbPlacar.getPlacar());
        
        placarList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
        JMSContext context = connectionFactory.createContext();
      
        int posicao = 1;
        for (Map.Entry<String, Integer> entry : placarList) {
            String mensagem = entry.getKey() + ": " + entry.getValue() + " Posição: "+posicao; 
            context.createProducer().send(topico, mensagem);
            posicao++;
        }
     
    } catch (Exception e) {
        System.out.println("Erro");
        System.out.println(e.getMessage());
    }
}

    
}
