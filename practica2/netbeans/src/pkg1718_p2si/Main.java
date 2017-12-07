/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.util.ArrayList;

/**
 * Crea un objeto "Practica" que contiene la información a operar, un objeto 
 * "AdaBoost" que contiene el algoritmo.
 */
public class Main 
{
    public static void main ( String[] args ) 
    {
        // Porcentaje de imagenes destinadas al entrenamiento
        int porcentajeEntrenamiento = 80;
        Practica p = new Practica( porcentajeEntrenamiento );
        System.out.println("");
        
        
        // numero de pruebas a realizar y la cantidad
        // de clasificadores débiles a entrenar en el algoritmo adaboost
        int numeroPruebas = 100;
        int numeroDebiles = 50;
        AdaBoost adaboost = new AdaBoost( 50, 100 );

        
        // llamada a adaBoost para cada dígito, este recibe la clasificación
        // correcta de las imágenes correspondiente al dígito a calcular
        for ( int i = 0; i < 10; ++i )
        {
            System.out.println("---------------------------> " + i);
            adaboost.algoritmo( p.getAprendizaje(), p.getCorrectos().get(i));
        }

        
    }
}
