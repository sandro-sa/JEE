/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author javaebj
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java/Fila"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EjbConsQueue implements MessageListener{

    @Override
    public void onMessage(Message msg) {
        System.out.println("Mensagem recebida pelo consumidor fila: "+this.getClass().getSimpleName());
        try{
            TextMessage tm = (TextMessage) msg;
            System.out.println(tm.getText());
        }catch(Exception e){
            
        }
    }
    
}
