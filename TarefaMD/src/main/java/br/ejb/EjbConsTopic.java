package br.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Sandro Amancio de Sá
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java/Topico"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class EjbConsTopic implements MessageListener{

    @Override
    public void onMessage(Message msg) {
        try{
            TextMessage tm = (TextMessage) msg;
            System.out.println(tm.getText());
        }catch(Exception e){
            
        }
    }
    
}