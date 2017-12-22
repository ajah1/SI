
package pkg1718_p2si;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Jesús Aliaga Hyder 48765284V
 */
public class Algoritmo 
{
    private int _numclasificadores = 0;
    private int _numPruebas = 0;
    /**
     * Constructor parametrizado con el número de clasificadores a obtener por
     * cada clasificador fuerte y el número de pruebas para obtener el debil
     * @param numclasificadores
     * @param numPruebas 
     */
    public Algoritmo ( int numclasificadores, int numPruebas )
    {
        _numclasificadores = numclasificadores;
        _numPruebas = numPruebas;
    }
    
    /**
     * Algortimo adaboost, devuelve el clasificador fuerte con la lista  de
     * débiles obtenidoss
     * @param entrenamiento
     * @param real
     * @return 
     */
     public Fuerte aplicarAlgoritmo ( ArrayList<Imagen> entrenamiento, 
             ArrayList<Boolean> real,
             int d) 
    {
        Fuerte fuerte = new Fuerte();
        
        float numerador;
        
        // inicializar la distribución de pesos
        float pesoInicial = 1.0f / entrenamiento.size();
        entrenamiento.forEach((img) -> {
            img.setPeso ( pesoInicial );
        });
        
        
        // clasificadores a entrenar
        for ( int i = 0; i < _numclasificadores; ++i )
        {
            
            Debil mejordebil = new Debil();
            mejordebil.ErrorClasificador ( entrenamiento, real );
            
            // entrenar clasificadores
            for ( int k = 0; k < _numPruebas; ++k )
            {
                Debil prueba = new Debil();
                prueba.ErrorClasificador ( entrenamiento, real );
                
                if ( prueba.getError() < mejordebil.getError() )
                    mejordebil = prueba;
            }
            
            
            // guardar en el fuerte el mejor clasificador debil
            // System.out.println("error: " + mejordebil.getError());
            fuerte.addDebil ( mejordebil );
            ArrayList<Boolean> clasificados = mejordebil.aplicarClasificadorDebil ( entrenamiento );
            
            
            // actualizar los pesos de las imágenes
            float Z = 0.0f;
            float peso;
            float confianzaDebil = mejordebil.getConfianza();
            for ( int j = 0; j < entrenamiento.size(); ++j )
            {
                peso = entrenamiento.get(j).getPeso();
                if ( clasificados.get(j).equals ( real.get(j) ) )
                    numerador = peso * (float) Math.pow ( Math.E, -confianzaDebil );
                else
                    numerador = peso * (float) Math.pow ( Math.E, confianzaDebil );
                
                entrenamiento.get(j).setPeso( numerador );
                Z += numerador;
            }
            
            
            // normalizar los pesos
            for ( int j = 0; j < entrenamiento.size(); ++j )
            {
                peso = entrenamiento.get(j).getPeso() / Z;
                entrenamiento.get(j).setPeso ( peso );
            }
        }
        return fuerte;
    }
}
