/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica1;

import java.util.ArrayList;
import javax.vecmath.Vector3d;

/**
 *
 * @author mireia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        // TODO code application logic here
        if(args.length!=1)
        {
            System.err.println("ERROR: Número de parámetros incorrecto.");
            System.out.println("Uso: Practica1SI2012 fichero_mundo.txt");
            System.out.println("Debes introducir como parámetro un nombre de fichero con la configuración del entorno.");
        }
        else{
            
            Practica1 practica1 = new Practica1();
            practica1.start(args[0]);
            
            
        }
        */
        
        Practica1 p1 = new Practica1();
        p1.start(args[0]);
        
        Vector3d v = new  Vector3d();
        
       //Vector3d position, String name, Practica1 practica1
       MiRobot r = new MiRobot( v, "hola", p1);
       /*
        for (int i = 0; i < 20; ++i )
            {
                for (int j = 0; j < 20; ++j)
                {
                    System.out.print((int)r.mundo[i][j]);
                }
                System.out.println();
            }
        
       */
       
       
      // System.out.println("RESULTADO: " + r.AEstrella());
       
       Nodo n = new Nodo(3, 2);
       
       ArrayList lf = new ArrayList();
       
       ArrayList li = new ArrayList();
       li.add(n);
       
      
       
       r.obtenerHijos(lf, n, li);
       
    }

}
