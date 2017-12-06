/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.util.ArrayList;

/**
 *
 * @author fidel
 */
public class Main 
{

    public static void main ( String[] args ) 
    {
        Practica p = new Practica();
        
        System.out.println("");
        
       AdaBoost adaboost = new AdaBoost( 50, 200 );
        
       Fuerte f;
       ArrayList resultado = new ArrayList ( 10 );
       for ( int i = 0; i < 10; ++i )
       {
           System.out.println("---------------------------> " + i);
           adaboost.algoritmo( p.getAprendizaje(), p.getCorrectos().get(i));
       }
      // resultado.add( adaboost.algoritmo( p.getAprendizaje(), p.getCorrectos().get(8) ) );
    }
}
