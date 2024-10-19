/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gui;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Sandro Amancio de Sá
 */
public class ClienteEjb {
    
    public int somar(int a, int b){
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state","com.sun.cobra.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.COBRA.ORBInitalHost","localhost");
        props.setProperty("org.omg.COBRA.ORBInitalPort","3700");
        props.setProperty("org.sun.cobra.ee.transport.ORBWaitForResponseTimeOut","5000");
        props.setProperty("org.sun.cobra.ee.transport.ORBTCPConnectTimeouts","100:500:100:500");
        props.setProperty("org.sun.cobra.ee.transport.ORBTCPTimeouts","500:2000:50:1000");
        
        InitialContext ejbRemoteContext;
        try{
            ejbRemoteContext = new InitialContext(props);
            
        } catch (NamingException ex){
            System.out.println("Erro");
            System.out.println(ex.getMessage());
            return -1;
        }
        
        bri.ICalculadora beanRemote;
        try {
            beanRemote = (bri.ICalculadora) ejbRemoteContext.lookup("java:global/Aula4Pratica3Web-1.0-SNAPSHOT/EjbCalculadora");
            int x = beanRemote.somar(a, b);
            return x;
        } catch (NamingException ex) {
            System.out.println("Erro");
            System.out.println(ex.getMessage());
            return -2;
        }
    }
}
