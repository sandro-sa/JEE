/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf;

import br.ejb.EjbProduto;
import br.model.Produto;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author javaebj
 */
@Named(value = "jsfProdutor")
@RequestScoped
public class JsfProdutor {

    @EJB
    private EjbProduto ejbProduto;

    /**
     * Creates a new instance of JsfProdutor
     */
    public JsfProdutor() {
    }
    
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    //Verifica se existe no servidor a estrutura e instancia
    @Resource(lookup ="java/Topico")
    private Topic topico;
    
    public void send(){
        
        try{
            ArrayList<Produto> lprod;
            lprod = ejbProduto.getAll();
            //Conexão para sistema de messagem java JMS
            Connection conexao = connectionFactory.createConnection();
            Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Obtem apartir da sessão a criação de um novo objeto de mensagem
            ObjectMessage om = session.createObjectMessage();
            
            //Seta o objeto que vai ser mandado para  o consumidor
            om.setObject(lprod);
            JMSContext context = connectionFactory.createContext();
            context.createProducer().send(topico, om);
            
        }catch(Exception e){
            System.out.println("Erro");
            System.out.println(e.getMessage());
        }
    }
    
}
