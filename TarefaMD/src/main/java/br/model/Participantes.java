package br.model;

import lombok.Data;

/**
 *
 * @author Sandro Amancio de Sá
 */
@Data
public class Participantes {
    private String nome;
    private int pontos;

    public Participantes(String nome, int pontos) {
        this.nome = nome;
        this.pontos = 0;
    }
    
    public void aadPontos(){
        this.pontos++;
    }
    
}