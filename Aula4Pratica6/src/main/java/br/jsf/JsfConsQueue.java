/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author javaebj
 */
@Named(value = "jsfConsQueue")
@RequestScoped
public class JsfConsQueue {

    /**
     * Creates a new instance of JsfConsQueue
     */
    public JsfConsQueue() {
    }
    
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "java/Fila")
    private Queue fila;
    
    public void send(){
        try{
            JMSContext context = connectionFactory.createContext();
            for(int i = 0; i < quantidade; i++){
                context.createProducer().send(fila, mensagem +" ["+i+"]" );
            }
        }catch(Exception e){
            System.out.println("Erro");
            System.out.println(e.getMessage());
        }
    }
  
    @Getter @Setter
    private String mensagem;
    
    @Getter @Setter
    private int quantidade = 1;
    
}
