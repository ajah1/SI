
package pkg1718_p2si;

import java.util.ArrayList;

/**
 * Crea un objeto "Practica" que contiene la información a operar, un objeto 
 * "AdaBoost" que contiene el algoritmo.
 */
public class AdaBoost 
{
    public static void main ( String[] args ) 
    {
        
        String hola [] = new String[2];
        hola[0] = args[0];
        hola[1] = args[1];
        
        System.out.println(hola[0]);
        System.out.println(hola[1]);
        
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
        
        Algoritmo adaboost = new Algoritmo( numeroDebiles, numeroPruebas );
        
        ArrayList<Fuerte> fuertes = new ArrayList();
        

        int digito = 1;
        int numAciertos = 0;
        ArrayList mejoresH = null;
        if ( hola[0].equals("-t") )
        {
            // llamada al algoritmo para cada dígito, este recibe la clasificación
            // correcta de las imágenes correspondientes al dígito a calcular
            digito = 9;
            for ( int i = 0; i <= digito; ++i )
            {
                System.out.println("--> fuerte del digito " + i);
                fuertes.add( adaboost.aplicarAlgoritmo( p.getAprendizaje(), p.getCorrectos().get(i)) );
            }
        }
        else
        {
            // llamada al algoritmo para el dígito 1
            int num = Integer.valueOf(hola[1]);
            System.out.println("--> fuerte del digito " + num);
            fuertes.add( adaboost.aplicarAlgoritmo( p.getAprendizaje(), p.getCorrectos().get(num)) );
        }
        
        // aplicar clasificadores fuertes a las imágenes de testeo
        mejoresH = p.aplicarFuertes ( fuertes );
        // obtener el número de aciertos
        numAciertos = p.imagenesAcertadas ( mejoresH );
        
        System.out.println( "\nACIERTOS: " + numAciertos );
        System.out.println( "FALLOS: " + (p.getTesteo().size() - numAciertos) );
        System.out.println("PORCENTAJE: " + (numAciertos*100/mejoresH.size()) );

    }
}
