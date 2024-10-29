/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.ejb.EjbProduto;
import br.model.Produto;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author javaebj
 */
@Named(value = "jsfProduto")
@RequestScoped
public class JsfProduto {

    @EJB
    private EjbProduto ejbProduto;

    /**
     * Creates a new instance of JsfProduto
     */
    public JsfProduto() {
    }
    
    public ArrayList<Produto> getAll(){
        return ejbProduto.getAll();
    }
    
}
