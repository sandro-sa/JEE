/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.model;

import lombok.Data;

/**
 *
 * @author javaebj
 */
@Data
public class Produto implements java.io.Serializable{
    private int codigo;
    private String desricao;

    public Produto(int codigo, String desricao) {
        this.codigo = codigo;
        this.desricao = desricao;
    }
    
    
}
