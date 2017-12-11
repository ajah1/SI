/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Crea un objeto "Practica" que contiene la información a operar, un objeto 
 * "AdaBoost" que contiene el algoritmo.
 */
public class Main 
{
    public static void main ( String[] args ) 
    {
        // Porcentaje de imagenes destinadas al entrenamiento
        int porcentajeEntrenamiento = 62;
        Practica p = new Practica( porcentajeEntrenamiento );
        System.out.println("");
        
        
        // numero de pruebas a realizar y la cantidad
        // de clasificadores débiles a entrenar en el algoritmo adaboost
        int numeroPruebas = 100;
        int numeroDebiles = 150;
        AdaBoost adaboost = new AdaBoost( numeroDebiles, numeroPruebas );
        
        
        // llamada a adaBoost para cada dígito, este recibe la clasificación
        // correcta de las imágenes correspondiente al dígito a calcular
        ArrayList<Fuerte> fuertes = new ArrayList();
        for ( int i = 0; i < 10; ++i )
        {
            System.out.println("---------------------------> " + i);
            fuertes.add( adaboost.algoritmo( p.getAprendizaje(), p.getCorrectos().get(i)) );
        }
        
        // obtener la clasificación dada por los fuertes
        // de las imágenes de testeo
        ArrayList <Imagen> testear = p.getTesteo();
        ArrayList mejoresH = new ArrayList( testear.size() );
        float mejor;
        int pos = 0;
        for ( Object img : testear )
        {
            mejor = 0;
            for ( int j = 0; j < fuertes.size(); ++j )
            {
                Fuerte f = fuertes.get(j);
                if ( f.H( (Imagen)img) > mejor )
                {
                    mejor = f.H( (Imagen)img);
                    pos = j;
                }
            }
            mejoresH.add(pos);
        }
        
        System.out.println(mejoresH);
 
        // obtener el número de aciertos de los fuertes comparando
        // su clasificación con la real
        int aciertos = 0;
        for ( int i = 0; i < testear.size(); ++i )
        {
         //   if ( mejoresH.get(i) ==  )
            aciertos++;
        }
        
        System.out.println("ACIERTOS: " + aciertos);
    }
}
