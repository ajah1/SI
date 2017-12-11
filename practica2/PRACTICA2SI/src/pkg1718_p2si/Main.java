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
        int porcentajeEntrenamiento = 72;
        Practica p = new Practica( porcentajeEntrenamiento );
        System.out.println("");
        
        // numero de pruebas a realizar y la cantidad
        // de clasificadores débiles a entrenar en el algoritmo adaboost
        int numeroPruebas = 1000;
        int numeroDebiles = 150;
        System.out.println("[Main] Numero de pruebas: " + numeroPruebas );
        System.out.println("[Main] Numero de debiles: " + numeroDebiles + "\n");
        
        AdaBoost adaboost = new AdaBoost( numeroDebiles, numeroPruebas );

        
        // llamada a adaBoost para cada dígito, este recibe la clasificación
        // correcta de las imágenes correspondientes al dígito a calcular
        ArrayList<Fuerte> fuertes = new ArrayList();
        for ( int i = 0; i < 10; ++i )
        {
            System.out.println("--> " + "fuerte del digito " + i);
            fuertes.add( adaboost.algoritmo( p.getAprendizaje(), p.getCorrectos().get(i)) );
        }
        
        // aplicar clasificadores fuertes a las imágenes de testeo
        ArrayList mejoresH = p.aplicarFuertes ( fuertes );

        System.out.println( "\n[Main] Comparacion real con la generada" );
        System.out.println( mejoresH);
        System.out.println( p.getCorrectoTesteo() );
 

        // obtener el número de aciertos
        int numAciertos = p.imagenesAcertadas ( mejoresH );
        
        System.out.println( "ACIERTOS: " + numAciertos );
        System.out.println( "FALLOS: " + (p.getTesteo().size() - numAciertos) );
    }
}
