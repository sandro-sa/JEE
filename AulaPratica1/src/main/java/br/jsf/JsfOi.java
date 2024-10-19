/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.ejb.EjbOlaMundo;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author javaebj
 */
@Named(value = "jsfOi")
@RequestScoped
public class JsfOi {

    @EJB
    private EjbOlaMundo ejbOlaMundo;

    /**
     * Creates a new instance of JsfOi
     */
    public JsfOi() {
    }
    
    public String getOi(){
        return ejbOlaMundo.getOi();
    }
    
}
