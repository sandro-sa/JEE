package br.data.jsf;

import br.data.ejb.EjbParticipanteJogar;
import br.data.ejb.EjbPlacar;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;

/**
 *
 * @author Sandro Amancio de Sá
 */
@Named(value = "jsfJogo")
@ManagedBean
@SessionScoped
public class JsfJogo implements Serializable {

    @EJB
    private EjbPlacar ejbPlacar;

    @EJB
    private EjbParticipanteJogar ejbParticipanteJogar;
    
    private int somaDoJogador;
    private String mensagem;

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
}
