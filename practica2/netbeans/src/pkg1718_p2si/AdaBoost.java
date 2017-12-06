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
        float pesoInicial = 1.0f / sizeEntrenamiento;
        for ( int i = 0; i < sizeEntrenamiento; ++i )
            entrenamiento.get(i).setPeso( pesoInicial ) ;
        
        
        //ArrayList aplicado;
        // entrenar/buscar clasificadores debiles
        for (int i = 0; i < _numclasificadores; ++i )
        {
            Debil debil = new Debil();
            debil.ErrorClasificador ( entrenamiento, real);
            
            for ( int k = 0; k < _numPruebas; ++k )
            {
                Debil prueba = new Debil();
                prueba.ErrorClasificador ( entrenamiento, real );
                
                if ( debil.getError() > prueba.getError() )
                    debil = prueba;
            }

            System.out.println("Error: " + debil.getError());
            fuerte.addDebil(debil);
            ArrayList<Boolean> clas = debil.aplicarClasificadorDebil(entrenamiento);
            
            float confianzaDebil = debil.getConfianza();
            
            Z = 0.0f;
            for ( int j = 0; j < entrenamiento.size(); ++j )
            {
                if ( debil.h(entrenamiento.get(j)) == 1)
                    auxiliar = true;
                else
                    auxiliar = false;
                
                //if ( real.get(j).equals(auxiliar) )
                if ( clas.get(j).equals(real.get(j)) )
                    numerador = entrenamiento.get(j).getPeso() * (float) Math.pow(Math.E, -confianzaDebil );
                else
                    numerador = entrenamiento.get(j).getPeso() * (float) Math.pow(Math.E, confianzaDebil );
                
                entrenamiento.get(j).setPeso( numerador);
                Z += entrenamiento.get(j).getPeso();
            }
            
            // NORMALIZAR LOS PESOS
            float p = 0.0f;
            for ( int j = 0; j < entrenamiento.size(); ++j )
            {
                entrenamiento.get(j).setPeso( entrenamiento.get(j).getPeso() / Z );
            }
           // System.out.print("Peso a asignar: ");
            //System.out.println(p);
            
            
           
        }
        return fuerte;
    }
}
