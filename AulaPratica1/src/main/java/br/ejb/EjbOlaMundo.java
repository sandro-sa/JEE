/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ejb;

import javax.ejb.Stateless;

/**
 *
 * @author javaebj
 */
@Stateless
public class EjbOlaMundo {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String getOi(){
        return "Olá mundo EJB";
    }
}
