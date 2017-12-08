/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        System.out.println("[AdaBoost]: inicializado");
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
        
        float numerador = 0.0f;
        
        // INICIALIZAR LA DISTRIBUCIÓN DE PESOS
        float pesoInicial = 1.0f / entrenamiento.size();
        entrenamiento.forEach((img) -> {
            img.setPeso ( pesoInicial );
        });
        
        
        // CLASIFICADORES A ENTRENAR
        for (int i = 0; i < _numclasificadores; ++i )
        {
            
            Debil debil = new Debil();
            debil.ErrorClasificador ( entrenamiento, real );
            
            // ENTRENAR CLASIFICADORES
            for ( int k = 0; k < _numPruebas; ++k )
            {
                Debil prueba = new Debil();
                prueba.ErrorClasificador ( entrenamiento, real );
                
                if ( debil.getError() > prueba.getError() )
                    debil = prueba;
            }

            System.out.println ( "Error: " + debil.getError() );
            
            fuerte.addDebil ( debil );
            ArrayList<Boolean> clasificados = debil.aplicarClasificadorDebil ( entrenamiento );
            
            
            // ACTUALIZAR PESOS IMÁGENES
            float Z = 0.0f;
            float peso;
            float confianzaDebil = debil.getConfianza();
            for ( int j = 0; j < entrenamiento.size(); ++j )
            {
                peso = entrenamiento.get(j).getPeso();
                if ( clasificados.get(j).equals ( real.get(j) ) )
                    numerador = peso * (float) Math.pow ( Math.E, -confianzaDebil );
                else
                    numerador = peso * (float) Math.pow ( Math.E, confianzaDebil );
                
                entrenamiento.get(j).setPeso( numerador );
                Z += entrenamiento.get(j).getPeso();
            }
            
            
            // NORMALIZAR LOS PESOS
            for ( int j = 0; j < entrenamiento.size(); ++j )
            {
                peso = entrenamiento.get(j).getPeso() / Z;
                entrenamiento.get(j).setPeso ( peso );
            }
            
            // ACTUALIZAR FUERTE
            
        }
        return fuerte;
    }
}
