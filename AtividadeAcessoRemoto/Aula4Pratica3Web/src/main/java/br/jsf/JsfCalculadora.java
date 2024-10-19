/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import bri.ICalculadora;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Sandro Amancio de Sá
 */
@Named(value = "jsfCalculadora")
@RequestScoped
public class JsfCalculadora {

    @EJB
    private ICalculadora ejbCalculadora;

    /**
     * Creates a new instance of JsfCalculadora
     */
    public JsfCalculadora() {
    }
    
    public void  somar(){
        resultado = ejbCalculadora.somar(valorA, valorB);
    }
    @Setter @Getter
    private int valorA;
    @Setter @Getter
    private int valorB;
    @Getter
    private int resultado;
}
