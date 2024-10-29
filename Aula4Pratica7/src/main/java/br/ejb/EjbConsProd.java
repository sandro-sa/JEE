/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ejb;

import br.model.Produto;
import java.util.ArrayList;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author javaebj
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java/Topico"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class EjbConsProd implements MessageListener{

    @Override
    public void onMessage(Message msg) {
         System.out.println("Mensagem recebida pelo consumidor topico: "+ this.getClass().getSimpleName());
        try{
            ObjectMessage tm = (ObjectMessage) msg;
            System.out.println("Fazendo cast");
            ArrayList<Produto> lprod = new ArrayList();
            lprod = (ArrayList<Produto>) tm.getObject();
            for(Produto produto: lprod){
                System.out.println("Produto => "+ produto.getCodigo()+" : "+ produto.getDesricao());
            }
        }catch(Exception e){
            System.out.println("Erro");
            System.out.println(e.getMessage());
        }
    }
    
}
