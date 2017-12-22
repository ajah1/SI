
package pkg1718_p2si;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Crea un objeto "Practica" que contiene la información a operar, un objeto 
 * "AdaBoost" que contiene el algoritmo.
 */
public class AdaBoost 
{
    public static void main ( String[] args ) throws IOException 
    {
        int porcentajeEntrenamiento = 92;
        Practica p = new Practica( porcentajeEntrenamiento );
        
        // 108
        int numeroPruebas = 1008;
        int numeroDebiles = 100;
        System.out.println("Numero de pruebas: " + numeroPruebas );
        System.out.println("Numero de debiles: " + numeroDebiles );
        System.out.println("Porcentaje entrenamiento: " + porcentajeEntrenamiento +"%");
        
        Algoritmo adaboost = new Algoritmo( numeroDebiles, numeroPruebas );
        
        ArrayList<Imagen> imagenCorrecta;
        ArrayList<Imagen> imagenCargada;
        ArrayList<Fuerte> fuerte;
        ArrayList<Integer> mejoresH;

        int numAciertos = 0;
        if (args.length == 0)
        {
            int imagen = 3;
            System.out.println("\n[POR DEFECTO]: Nuestro mejor fuerte sobre una imagen");
            fuerte = p.leerPipo("nuestroMejorFuerte");
            
            imagenCargada = p._ml.getImageDatabaseForDigit(imagen);
            mejoresH = p.aplicarFuertes( imagenCargada, fuerte);
            
            numAciertos = mejoresH.stream().filter((H) -> 
                    ( H == imagen)).map((_item) -> 1).reduce(
                            numAciertos, 
                            Integer::sum);
            
            System.out.println("\nClasificación dada por el fuerte:");
            System.out.println(mejoresH);
            
            System.out.println("\n** Resultado fuerte sobre las imagenes del "+imagen);
            System.out.println( "Aciertos:  " + numAciertos );
            System.out.println( "Fallos:    " + (imagenCargada.size() - numAciertos) );
            System.out.println("Porcentaje: " + (numAciertos*100/mejoresH.size()) +"%");
        }
        else if ( args[0].equals("-t") )
        {
            System.out.println("\n[OPCION 1]: Entrenando para todos los dígitos");
            
            fuerte = p.obtenerFuerte(p, adaboost);
            p.guardarPipo(fuerte,args[1]);
            
            mejoresH = p.aplicarFuertes ( p.getTesteo() , fuerte );
            numAciertos = p.imagenesAcertadas ( 
                    p.getTesteo(),
                    mejoresH,
                    p.getCorrectoTesteo() );
            
            System.out.println(p.getCorrectoTesteo() );
            System.out.println(mejoresH);
            
            System.out.println("\n** Resultados imagenes de testeo: ");
            System.out.println( "Aciertos:  " + numAciertos );
            System.out.println( "Fallos:    " + (p.getTesteo().size() - numAciertos) );
            System.out.println("Porcentaje: " + (numAciertos*100/mejoresH.size()) +"%");
        }
        else
        {
            System.out.println("\n[OPCION 2]: Cargar el fuerte pasado y ejecutar sobre la imagen pasada");
            
            System.out.println("\nFuerte a leer: " + args[0]);
            System.out.println("Imagen a pasar: "+ args[1]);
            fuerte = p.leerPipo(args[0]);
            
            int imagen = Integer.valueOf(args[1]);
            imagenCargada = p._ml.getImageDatabaseForDigit(imagen);
            mejoresH = p.aplicarFuertes( imagenCargada, fuerte);
            
            numAciertos = mejoresH.stream().filter((H) -> 
                    ( H == imagen)).map((_item) -> 1).reduce(
                            numAciertos, 
                            Integer::sum);
            
            System.out.println("Clasificacion dada por el fuerte: ");
            System.out.println(mejoresH);
            
            System.out.println("\n** Resultados **");
            System.out.println( "Aciertos:  " + numAciertos );
            System.out.println( "Fallos:    " + (imagenCargada.size() - numAciertos) );
            System.out.println("Porcentaje: " + (numAciertos*100/mejoresH.size()) +"%");
        }
    }
}
