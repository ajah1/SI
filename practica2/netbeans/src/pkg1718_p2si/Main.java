/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;


/**
 *
 * @author fidel
 */
public class Main 
{

    public static void main ( String[] args ) 
    {
        // inicializar los vectores / atributos a operar
        Practica p = new Practica();
        
        
        
        // INICIALIZAR LOS PESOS DE LAS IMÁGENES
        int size = p.getAprendizaje().size();
        float [] pesosImagenes = new float [ size ];
        
        int aux = pesosImagenes.length;
        for ( int i = 0; i < aux; ++i )
            pesosImagenes [i] = 1.0f / size;
        
        System.out.print("[?] Pesos de las imágenes inicialziadas con el valor: ");
        System.out.println( pesosImagenes [ size - 1 ] );
        
        //Debil d = new Debil();
        //ArrayList<Boolean> r;
        //r = d.aplicarClasificadorDebil(d, p.getAprendizaje());
        //System.out.println(r);
        
    }
    
}
