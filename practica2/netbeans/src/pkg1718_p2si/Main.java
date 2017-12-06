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
        Practica p = new Practica();
        
        System.out.println("");
        
       AdaBoost adaboost = new AdaBoost( 20, 800 );
        
       Fuerte f;
       for ( int i = 0; i < 10; ++i )
       {
            f = adaboost.algoritmo( p.getAprendizaje(), p.getCorrectos().get(i) );  
       }
    }
}
