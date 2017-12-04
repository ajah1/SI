/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.util.ArrayList;

/**
 *
 * @author fidel
 */
public class Main 
{

    public static void main ( String[] args ) 
    {
        
        // VARIABLES A PROBAR
        int porcentajeEntrenamiento = 80;
        int totalImagenes = 1000;
        int cantidadEntrenamiento = totalImagenes * porcentajeEntrenamiento / 100;
        
        // CARGAS LAS IMÁGENES A USAR
        MNISTLoader ml = new MNISTLoader ();
        ml.loadDBFromPath ( "./mnist_1000" );
        
        //  CANTIDAD DE IMÁGENES POR DÍGITO
        ArrayList cantidad = new ArrayList();
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = ml.getImageDatabaseForDigit(i);
            int size = imgs.size();
            cantidad.add ( size );
        }
        
        // OBTENER LOS VECTORES CORRECTOS DE CADA DÍGITO
        // arrray bidimensional, con los vectores correctos
        ArrayList<ArrayList> correctos = new ArrayList<>();

        int inicio = 0;
        int fin = (int) cantidad.get(0) - 1;
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList resultado = new ArrayList();
            
            for ( int j = 0; j < totalImagenes; ++j )
            {
                boolean rango = (j >= inicio) && (j<=fin);
                if ( rango ) 
                    resultado.add ( true );
                else
                    resultado.add ( false );
            }
            
            inicio = fin + 1;
            if ( i != 9)
                fin += (int) cantidad.get ( i+1 );
        }
            
        //SEGUIR.... SEGUIR >>>>>>>>>
        System.out.println(correctos.get(0));
        
        //SEPRAR IMÁGENES ENTRE TESTEO Y ENTRENAMIENTO
        ArrayList aprendizaje = new ArrayList ();
        ArrayList testeo = new ArrayList ();
        
        System.out.println ( "" );
        System.out.println ( "[?] Imagenes para aprendizaje: " + aprendizaje.size() );
        System.out.println ( "[?] Imagenes para testeo: " + testeo.size() );
        System.out.println ( "[?] Creados " + correctos.size() + " vectores con conrrectos" );
        
        /*
        // INICIALIZAR LOS PESOS DE LAS IMÁGENES
        int size = aprendizaje.size();
        float [] pesosImagenes = new float [ size ];
        
        int aux = pesosImagenes.length;
        for ( int i = 0; i < aux; ++i )
            pesosImagenes [i] = 1.0f / size;
        
        System.out.print("[?] Pesos de las imágenes inicialziadas con el valor: ");
        System.out.println( pesosImagenes [ size - 1 ] );
        
        Debil d = new Debil();
        ArrayList<Boolean> r;
        //r = d.aplicarClasificadorDebil(d, aprendizaje);
        //System.out.println(r);
        */
    }
    
}
