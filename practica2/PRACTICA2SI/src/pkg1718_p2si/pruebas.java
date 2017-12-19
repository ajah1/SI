/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author alex
 */
public class pruebas {
    public static void main ( String [] args ) throws IOException
    {
    
ArrayList<Fuerte> fuertes = new ArrayList(10);
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        // leer mientras no se llegue a [FIN]
        
            // mientras no encuentra [FUERTE]
                
                // leer y añadir debil
                
                
        try
        {
            archivo = new File ( "fuerte.out" );
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            
            String linea = br.readLine();
            while ( linea != null )
            {
                if ( linea.equals("[FUERTE]") )
                {
                   Debil aux = new Debil();
                   aux.setUmbral( Integer.valueOf(br.readLine()) );
                   aux.setError( Float.valueOf(br.readLine()) );
                   aux.setPixel( Integer.valueOf(br.readLine()) );
                   aux.setPixel( Integer.valueOf(br.readLine()) );
                   aux.setConfianza( Float.valueOf(br.readLine()) );
                }
                
                linea = br.readLine();
            }
        }
        catch (FileNotFoundException e )
        {
            System.out.println("ERROR: al leer fuerte");
            System.out.println(e.toString());
        }
        finally{ if (null!=fr) fr.close(); }
        
    }
}
