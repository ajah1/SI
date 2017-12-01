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
        // CARGAS LAS IMÁGENES A USAR
        ArrayList aprendizaje = new ArrayList ();
        ArrayList testeo = new ArrayList ();

        MNISTLoader ml = new MNISTLoader ();
        ml.loadDBFromPath ( "./mnist_1000" );
        
        
        // SEPARA IMÁGENES Y OBTENER VECTORES REALES
        int porciento = 60;
        int porcentaje = 0;
        ArrayList<ArrayList> correctos = new ArrayList<>();
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = ml.getImageDatabaseForDigit(i);
            ArrayList resultado = new ArrayList();
            
            int digitos = imgs.size();
            porcentaje = imgs.size() * porciento / 100;
            for ( int j = 0; j < digitos; ++j )
            {
                Imagen img = (Imagen) imgs.get(j);

                if ( j < porcentaje )
                {
                    aprendizaje.add ( img );
                    resultado.add(true);
                }
                else
                {
                    testeo.add ( img );
                    resultado.add(false);
                }
            }
            correctos.add ( resultado );
        }
        
        System.out.println ( "" );
        System.out.println ( "[?] Imagenes para aprendizaje: " + aprendizaje.size() );
        System.out.println ( "[?] Imagenes para testeo: " + testeo.size() );
        System.out.println ( "[?] Creados " + correctos.size() + " vectores con conrrectos" );
        
        
        // INICIALIZAR LOS PESOS DE LAS IMÁGENES
        int size = aprendizaje.size();
        float [] pesosImagenes = new float [ size ];
        
        int aux = pesosImagenes.length;
        for ( int i = 0; i < aux; ++i )
            pesosImagenes [i] = (float )1 / size;
        
        System.out.print("[?] Pesos de las imágenes inicialziadas con el valor: ");
        System.out.println( pesosImagenes [ size - 1 ] );
        
        Debil d = new Debil();
        ArrayList<Boolean> r;
        //r = d.aplicarClasificadorDebil(d, aprendizaje);
        //System.out.println(r);
    }
    
}
