/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.ejp.EjbLocalLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author javaebj
 */
@Named(value = "jsfLocal")
@RequestScoped
public class JsfLocal {

    @EJB
    private EjbLocalLocal ejbLocal;

    /**
     * Creates a new instance of JsfLocal
     */
    public JsfLocal() {
    }
    
    private int valor;
    private int resultado;
    
    public void dobrar(){
        resultado = ejbLocal.dobrar(valor);
    }

    public EjbLocalLocal getEjbLocal() {
        return ejbLocal;
    }

    public void setEjbLocal(EjbLocalLocal ejbLocal) {
        this.ejbLocal = ejbLocal;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
    
}
