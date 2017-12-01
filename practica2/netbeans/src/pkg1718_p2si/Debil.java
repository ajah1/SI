package pkg1718_p2si;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex
 */
public class Debil {
    
    // umbral [0,254]
    private int _umbral = 0;
    // error
    private float _error = 0.0f;
    // pixel [0, 783]
    private int _pixel = 0;
    // dirección + -
    private int _direccion = 0;
    
    
    public Debil ()
    {
        int posicion = (int)(Math.random() * 784);
        int umbral = (int)(Math.random() * 255) - 128;
        int direc = (int)(Math.random() * 2);
       
        _umbral = umbral;
        _pixel = posicion;
        
        if ( direc == 1)
            _direccion = 1;
        else
            _direccion = -1;
    }
    
    // resultado_clasificación = aplicarClasificadorDebil (clasificador, datos )
    public ArrayList aplicarClasificadorDebil ( Debil p_debil, ArrayList entrenamiento )
    {
        ArrayList<Boolean> clasificacion = new ArrayList();
        boolean aux;
        
        for ( int i = 0; i < entrenamiento.size(); i++ ) 
        {
            Imagen img = (Imagen)entrenamiento.get(i);
            byte imageData[] = img.getImageData();
            
            int umbralimagen = imageData[p_debil._pixel];
            System.out.println ( "umbral de la imagen: " + umbralimagen );
            
            if(p_debil._direccion == 1)
                if(p_debil._umbral >= umbralimagen)
                    aux = true;
                else
                     aux = false;
            else
                if(p_debil._umbral < umbralimagen)
                    aux = true;
                else
                    aux = false;
                            
            clasificacion.add(aux);
        }
        
        return clasificacion;
    }
    
    // obtenerErrorClasificador ( clasificador, datos, D )
    public float obtenerErrorClasificador ( Debil p_debil, byte [] datos, float p_D )
    {
        return 0.0f;
    }

    // getters and setters
    public float getUmbral () {
        return _umbral;
    }

    public float getError () {
        return _error;
    }
    
    public float getPixel () {
        return _pixel;
    }
    
    public float getDireccion () {
        return _direccion;
    }

    public void setUmbral ( int umbral ) {
        this._umbral = umbral;
    }

    public void setError ( float error ) {
        this._error = error;
    }
    
    public void setPixel ( int pixel ) {
        this._pixel = pixel;
    }
    
    public void setDireccion ( int direccion ) {
        this._direccion = direccion;
    }
    
}
