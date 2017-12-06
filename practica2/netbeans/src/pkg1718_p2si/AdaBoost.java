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
    
     public Fuerte algoritmo ( ArrayList<Imagen> entrenamiento, ArrayList<Boolean> real ) 
    {
        Fuerte fuerte = new Fuerte();
        
        boolean auxiliar;
        int comprobarIguales;
        float numerador = 0.0f;
        float Z = 0.0f;
        float resultadoFuerte = 0.0f;
        
        // inicializar distribución de pesos
        int sizeEntrenamiento = entrenamiento.size();
        for ( int i = 0; i < sizeEntrenamiento; ++i )
            entrenamiento.get(i).setPeso(1.0f / sizeEntrenamiento) ;
        
        // entrenar/buscar clasificadores debiles
        for (int i = 0; i < _numclasificadores; ++i )
        {
            Debil debil = new Debil();
            
            for ( int k = 0; k < _numPruebas; ++k )
            {
                Debil prueba = new Debil();
                ArrayList aplicado;
                
                aplicado = prueba.aplicarClasificadorDebil(entrenamiento);
                
                prueba.ErrorClasificador( entrenamiento, aplicado );
                
                
                if ( k == 0 )
                    debil = prueba;
                else if ( prueba.getError() < debil.getError() )
                    debil = prueba;
            }

            fuerte.addDebil(debil);
            
            float confianzaDebil = debil.getConfianza();
            
            for ( int j = 0; j < entrenamiento.size(); ++j )
            {
                if ( debil.h(entrenamiento.get(j)) == 1)
                    auxiliar = true;
                else
                    auxiliar = false;
                
                if ( real.get(j).equals(auxiliar) )
                    comprobarIguales = 1;
                else
                    comprobarIguales = -1;
                
                numerador = entrenamiento.get(j).getPeso() * (float) Math.pow(Math.E, -confianzaDebil * comprobarIguales);
                entrenamiento.get(j).setPeso(numerador);
            }
            
            Z = 0.0f;
            
            for ( int j = 0; j < entrenamiento.size(); ++j)
                Z += entrenamiento.get(j).getPeso();
            
            for ( int j = 0; j < entrenamiento.size(); ++j )
                entrenamiento.get(j).setPeso( entrenamiento.get(j).getPeso() / Z);
            
            resultadoFuerte = 0.0f;
            
            for ( int j = 0; j < entrenamiento.size(); ++j )
                resultadoFuerte += fuerte.H( entrenamiento.get(j) );
            
            
            if ( resultadoFuerte == 0.0f )
                return fuerte;
           
        }
        return fuerte;
    }
}
