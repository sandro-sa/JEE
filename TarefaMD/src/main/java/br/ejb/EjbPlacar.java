package br.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author Sandro Amancio de Sá
 */
@Stateless
public class EjbPlacar {

     private Map<String ,Integer> placar = new HashMap<>();
    
    public void atualizarPlacar(String nomeDoJogador,int pontos){
        placar.put(nomeDoJogador, placar.getOrDefault(nomeDoJogador, 0) + pontos);
    }
    
    public List<Map.Entry<String, Integer>> getPlacar(){
        List<Map.Entry<String, Integer>> placarOrdenado = new ArrayList<>(placar.entrySet());
        placarOrdenado.sort((a,b)->b.getValue().compareTo(a.getValue()));
        return placarOrdenado;
    }
}
