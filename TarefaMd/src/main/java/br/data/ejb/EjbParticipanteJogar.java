
package br.data.ejb;

import java.util.Random;
import javax.ejb.Stateful;

/**
 *
 * @author Sandro Amancio de Sá
 */
@Stateful
public class EjbParticipanteJogar {

    private String nomeDoJogador;
    private int pontos = 0;
    private int numero1;
    private int numero2;
    
    public void gerarNumeros(){
        Random rand = new Random();
        this.numero1 = rand.nextInt(100);        
        this.numero2 = rand.nextInt(100);
    }
    
    public boolean verificarSoma(int somaDoJogador){
        if(somaDoJogador == (numero1 + numero2)){
            pontos++;
            gerarNumeros();
            return true;
        }
        return false;
    }

    public String getNomeDoJogador() {
        return nomeDoJogador;
    }

    public void setNomeDoJogador(String nomeDoUsuario) {
        this.nomeDoJogador = nomeDoUsuario;
        gerarNumeros();
    }

    public int getPontos() {
        return pontos;
    }

    public int getNumero1() {
        return numero1;
    }

    public int getNumero2() {
        return numero2;
    }

}
