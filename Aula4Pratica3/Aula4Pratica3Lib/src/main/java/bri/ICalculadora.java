/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bri;

import javax.ejb.Remote;

/**
 *
 * @author Sandro Amancio de Sá
 */
@Remote
public interface ICalculadora {
    public int somar(int a , int b);
}
