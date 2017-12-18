
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
        int numeroDebiles = 100;
        System.out.println("Numero de pruebas: " + numeroPruebas );
        System.out.println("Numero de debiles: " + numeroDebiles );
        System.out.println("Porcentaje entrenamiento: " + porcentajeEntrenamiento  + "\n" );
        
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

        // obtener el número de aciertos
        int numAciertos = p.imagenesAcertadas ( mejoresH );
        
        System.out.println( "\nACIERTOS: " + numAciertos );
        System.out.println( "FALLOS: " + (p.getTesteo().size() - numAciertos) );
        System.out.println("PORCENTAJE: " + (numAciertos*100/mejoresH.size()) );

    }
}
