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
    public static String convertir(String f, double x){
        String S="";
        String q="("+Double.toString(x)+")";
        for(int i=0; i<f.length(); i++){
            if(f.charAt(i)=='x'){
                S+=q;
            }else
                S+=f.charAt(i);
        }
        return S;
    }

    public static String convertir(String T, double x,int y){
        String q="("+Double.toString(x)+")";
        String P="x"+(char)(y+(int)'1');
        return T.replace(P, q);
    }
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

    public double eval(String expresion, double x){
        expresion=convertir(expresion, x);
// Evalua la expresion, si es posible se convertirá en un valor numérico. 
        jep.parseExpression(expresion);
// Obtiene el valor de la "expresión" con la función "getValue()". 
        return jep.getValue(); 
    }
    public double eval(String expresion, double X[], int n){
        for(int i=0; i<n; i++){
            expresion=convertir(expresion, X[i],i);
        }
        jep.parseExpression(expresion);
        return jep.getValue();
    }
    
}
