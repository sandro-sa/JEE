
package br.model;

import java.util.ArrayList;

/**
 *
 * @author javaebj
 */
public class CrudProduto {
    
    public ArrayList<Produto> getAll(){
        
        ArrayList<Produto> lprod = new ArrayList();
        
        lprod.add(new Produto(1,"Computador"));
        lprod.add(new Produto(2,"Notebook"));
        lprod.add(new Produto(3,"Mouse"));
        lprod.add(new Produto(4,"Teclado"));
        
        return lprod;
    }
}
