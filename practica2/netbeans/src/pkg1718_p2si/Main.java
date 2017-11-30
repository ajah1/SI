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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        ArrayList aprendizaje = new ArrayList();
        ArrayList testeo = new ArrayList();

        MNISTLoader ml = new MNISTLoader();
        ml.loadDBFromPath("./mnist_1000");
        
        
        System.out.println("\n[?][images separadas en aprendizaje/testeo y obtenido el correcto]");
        // procentaje de imágenes para aprendizaje
        int porciento = 60;
        
        int porcentaje = 0;
        ArrayList<ArrayList> correctos = new ArrayList<>();
        // separar imagenes de aprendizaje y testeo
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = ml.getImageDatabaseForDigit(i);
            ArrayList resultado = new ArrayList();
            
            int digitos = imgs.size();
            porcentaje = imgs.size() * porciento / 100;
            for ( int j = 0; j < digitos; ++j )
            {
                Imagen img = (Imagen) imgs.get(j);
                //byte imageData[] = img.getImageData();

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
        
        System.out.println("[?] Imagenes para aprendizaje: " + aprendizaje.size() );
        System.out.println("[?] Imagenes para testeo: " + testeo.size() );
        System.out.println("[?] Creados " + correctos.size() + " vectores con conrrectos");
        
        aprendizaje.get(1);
        
        Debil d = new Debil();
        ArrayList<Boolean> r;
        r = d.aplicarClasificadorDebil(d, aprendizaje);
        System.out.println(r);
        

        
    }
    
}
