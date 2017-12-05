/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

/**
 *
 * @author fidel
 */
public class Main 
{

    public static void main ( String[] args ) 
    {
        Practica practica = new Practica();
        
        System.out.println("");
        
        AdaBoost adaboost = new AdaBoost( 30, 2000 );
        
        adaboost.algoritmo( practica.getAprendizaje() );
    }
    
}
