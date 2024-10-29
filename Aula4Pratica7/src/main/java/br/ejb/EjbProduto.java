/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ejb;

import br.model.CrudProduto;
import br.model.Produto;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author javaebj
 */
@Stateless
public class EjbProduto {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public ArrayList<Produto> getAll(){
        return new CrudProduto().getAll();
    }
}
