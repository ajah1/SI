/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class AdaBoost 
{
    private int _numclasificadores = 0;
    private int _numPruebas = 0;
    
    public AdaBoost ( int numclasificadores, int numPruebas )
    {
        System.out.println("[AdaBoost]: inicializado");
        _numclasificadores = numclasificadores;
        _numPruebas = numPruebas;
    }
    
     public void algoritmo ( ArrayList<Imagen> entrenamiento ) 
    {
        // inicializar distribución de pesos
        int sizeEntrenamiento = entrenamiento.size();
        for ( int i = 0; i < sizeEntrenamiento; ++i )
            entrenamiento.get(i).setPeso(1.0f / sizeEntrenamiento) ;
        
        System.out.print("[adaBoost:algoritmo] Pesos de las imágenes inicialziadas con el valor: ");
        System.out.println( "1/"+sizeEntrenamiento + " = "+  1.0f / sizeEntrenamiento );
        
        // entrenar/buscar clasificadores debiles
        for (int i = 0; i < _numclasificadores; ++i )
        {
         
            // quedarse con la mejor prueba
            for ( int j = 0; j < _numPruebas; ++j )
            {
                // 1. entrenar el debil

                // quedarse con el mejor

                // añadir el mejor obtenido

                // 2. calcular/obtener confianza de este clasificador

                // 3. Actualizar D

                //peso del clasificador debil para esta i

                // 4. Actualizar el clasificador fuerte y coger el mejor
            }
           
        }
    }
}
