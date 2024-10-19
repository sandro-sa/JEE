# Criando um EJB com Acesso Remoto

## Objetivos
- [x] Desenvolver um EJB com acesso remoto para somar dois números.
- [x] Criar uma aplicação que sirva como biblioteca.
- [x] Implementar uma interface cliente com JavaServer Faces (JSF).
- [x] Criar uma interface cliente GUI.

## Passos do Projeto

### 1. Criar um Projeto Java Application como Biblioteca

- **Nome do Projeto**: `Aula4Pratica3Lib`
- **Pacote**: `bri`

#### 1.1 Criar a Interface
```java
package bri;

import javax.ejb.Remote;

/**
 * Interface para operações de cálculo.
 * @author Sandro Amancio de Sá
 */
@Remote
public interface ICalculadora {
    int somar(int a, int b);
}
```
- Compile o projeto (Clean and Build).

### 2. Criar um Projeto Web Application

- **Nome do Projeto**: `Aula4Pratica3Web`
- **Pacote**: `br`
- **Servidor**: Payara Server
- **Versão do Java EE**: Jakarta EE 8 Web

#### 2.1 Adicionar a Dependência da Biblioteca `Aula4Pratica3Lib`

#### 2.2 Criar um Session Bean
- **Nome do EJB**: `EjbCalculadora`
- **Pacote**: `br.ejb`
- Implementa `bri.ICalculadora` e sobrescreve o método `somar`.

```java
package br.ejb;

import javax.ejb.Stateless;

/**
 * EJB para operações de soma.
 * @author Sandro Amancio de Sá
 */
@Stateless
public class EjbCalculadora implements bri.ICalculadora {
    @Override
    public int somar(int a, int b) {
        return a + b;
    }
}
```

#### 2.3 Adicionar o Framework JavaServer Faces

#### 2.4 Executar o Projeto
**Observação**: O Java Application deve estar compilado.

#### 2.5 Implementar o Cliente JSF CDI Bean
- **Nome da Classe**: `JsfCalculadora`
- **Pacote**: `br.jsf`
- **Escopo**: `request`

```java
package br.jsf;

import bri.ICalculadora;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean para integração com a interface JSF.
 * @author Sandro Amancio de Sá
 */
@Named(value = "jsfCalculadora")
@RequestScoped
public class JsfCalculadora {
    @EJB
    private ICalculadora ejbCalculadora;

    @Setter @Getter
    private int valorA;
    @Setter @Getter
    private int valorB;
    @Getter
    private int resultado;

    public void somar() {
        resultado = ejbCalculadora.somar(valorA, valorB);
    }
}
```
*Os Setters e Getters são criados pelo Lombok.*

#### 2.6 Adicionar Lombok ao Web Application
- No `pom.xml`, adicione a dependência:
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.22</version>
    <scope>provided</scope>
</dependency>
```
- Compile o projeto (Clean and Build).

#### 2.7 Editar o `index.xhtml` para Implementar o Formulário
```xhtml
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://xmlns.jcp.org/jsf/html">
    <h:head>
        <title>UTFPR EJB</title>
    </h:head>
    <h:body>
        <h1>Desenvolvendo um EJB com Acesso Remoto para Somar Dois Números</h1>
        <h:form>
            Valor A: <h:inputText value="#{jsfCalculadora.valorA}"/><br/>
            Valor B: <h:inputText value="#{jsfCalculadora.valorB}"/><br/>
            <h:commandButton value="Somar" action="#{jsfCalculadora.somar()}"/><br/>
            Resultado: <h:outputText value="#{jsfCalculadora.resultado}"/>
        </h:form>
    </h:body>
</html>
```
- Compile o projeto (Clean and Build) e execute.

### 3. Criar uma Java Application para o GUI

#### 3.1 Criar uma Nova Aplicação Java
- **Nome do Projeto**: `Aula4Pratica4gui`
- **Pacote**: `br.gui`

#### 3.2 Adicionar a Dependência da Biblioteca `Aula4Pratica3Lib`

#### 3.3 Adicionar Dependência para Utilizar o Servidor no `pom.xml`
```xml
<dependency>
    <groupId>fish.payara.extras</groupId>
    <artifactId>payara-embedded-all</artifactId>
    <version>5.192</version>
</dependency>
```
- Compile o projeto (Clean and Build).

#### 3.4 Criar Classe para Comunicar o GUI com o EJB
```java
package br.gui;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Classe cliente para acesso ao EJB.
 * @author Sandro Amancio de Sá
 */
public class ClienteEjb {
    public int somar(int a, int b) {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.cobra.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.COBRA.ORBInitalHost", "localhost");
        props.setProperty("org.omg.COBRA.ORBInitalPort", "3700");

        try {
            InitialContext ejbRemoteContext = new InitialContext(props);
            bri.ICalculadora beanRemote = (bri.ICalculadora) ejbRemoteContext.lookup("java:global/Aula4Pratica3Web-1.0-SNAPSHOT/EjbCalculadora");
            return beanRemote.somar(a, b);
        } catch (NamingException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return -1;
        }
    }
}
```

#### 3.5 Criar a interface grafica
- new jFrame Form
- Class Name: Principal
- Package: br.gui
    - adicione os labels, inpust e um botão pa invorca o EJB

````java
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        
        int valorA, valorB, result;
        valorA = Integer.parseInt(jTextField1.getText());
        valorB = Integer.parseInt(jTextField2.getText());
        
        ClienteEjb cejb = new ClienteEjb();
        result = cejb.somar(valorA, valorB);
        jLabel3.setText("Resultado: "+ result);
    }

````
