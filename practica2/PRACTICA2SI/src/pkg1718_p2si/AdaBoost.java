package pkg1718_p2si;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Jesús Aliaga Hyder 48765284V
 */
public class AdaBoost 
{
    private int _numclasificadores = 0;
    private int _numPruebas = 0;
    /**
     * Constructor parametrizado con el número de clasificadores a obtener por
     * cada clasificador fuerte y el número de pruebas para obtener el debil
     * @param numclasificadores
     * @param numPruebas 
     */
    public AdaBoost ( int numclasificadores, int numPruebas )
    {
        System.out.println("[AdaBoost]: Inicializado");
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
     public Fuerte algoritmo ( ArrayList<Imagen> entrenamiento, ArrayList<Boolean> real ) 
    {
        Fuerte fuerte = new Fuerte();
        
        float numerador;
        
        // INICIALIZAR LA DISTRIBUCIÓN DE PESOS
        float pesoInicial = 1.0f / entrenamiento.size();
        entrenamiento.forEach((img) -> {
            img.setPeso ( pesoInicial );
        });
        
        
        // CLASIFICADORES A ENTRENAR
        for ( int i = 0; i < _numclasificadores; ++i )
        {
            
            Debil mejordebil = new Debil();
            mejordebil.ErrorClasificador ( entrenamiento, real );
            
            // ENTRENAR CLASIFICADORES Y AÑADIR EL MEJOR AL FUERTE
            for ( int k = 0; k < _numPruebas; ++k )
            {
                Debil prueba = new Debil();
                prueba.ErrorClasificador ( entrenamiento, real );
                
                if ( mejordebil.getError() > prueba.getError() )
                    mejordebil = prueba;
            }

            // GUARDAMOS EN EL FUERTE EL MEJOR CLASIFICADOR DEBIL
            fuerte.addDebil ( mejordebil );
            ArrayList<Boolean> clasificados = mejordebil.aplicarClasificadorDebil ( entrenamiento );
            
            
            // ACTUALIZAR PESOS IMÁGENES
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
            
            
            // NORMALIZAR LOS PESOS
            for ( int j = 0; j < entrenamiento.size(); ++j )
            {
                peso = entrenamiento.get(j).getPeso() / Z;
                entrenamiento.get(j).setPeso ( peso );
            }
            
        }
        return fuerte;
    }
}
