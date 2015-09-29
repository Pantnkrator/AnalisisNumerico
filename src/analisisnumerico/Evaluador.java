/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisnumerico;

import org.nfunk.jep.*; 
import org.nfunk.jep.type.*; 

import java.io.*; 
import java.util.Scanner; 

/**
 *
 * @author toto
 */
public class Evaluador {
    private JEP jep; 
    private double valor; 
    
    public Evaluador(){ 
        jep=new JEP(); 
// Permite utilizar las constantes "pi" y "e". 
        jep.addStandardConstants(); 
// Permite utilizar las funciones básicas como las Trigonometricas, 
// Logarítmicas, Exponenciales. 
        jep.addStandardFunctions(); 
// Permite 2cos(45) en vez de 2*cos(45) 
        jep.setImplicitMul(true); 
} 

    public double eval(String expresion){ 
// Evalua la expresion, si es posible se convertirá en un valor numérico. 
        jep.parseExpression(expresion); 
// Obtiene el valor de la "expresión" con la función "getValue()". 
    return jep.getValue(); 
    } 
}
