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
    // confianza del clasificadores
    private float _confianza = 0.0f;


    
    public Debil ()
    {
        System.out.println("[Debil] Clasificador debil inicializado");
        int posicion = (int)(Math.random() * 784);
        int umbral = (int)(Math.random() * 255) - 128;
        int direc = (int)(Math.random() * 2);
        
        _umbral = umbral;
        _pixel = posicion;
        
        if ( direc == 1 )
            _direccion = 1;
        else
            _direccion = -1;
    }

    
    // calcular la h del debil
    public int h ( Imagen imagen )
    {
        int resultado = 0;
        
        if ( _direccion == 1 )
        {
            if ( _umbral >= imagen.getImageData()[_pixel] )
                resultado = 1;
            else
                resultado = -1;
        }
        else if ( _direccion == -1 )
        {
            if ( _umbral <= imagen.getImageData()[_pixel] )
                resultado = 1;
            else
                resultado = -1;
        }
        
        return resultado;
    }
    
    // resultado_clasificación = aplicarClasificadorDebil (clasificador, datos )
    public ArrayList aplicarClasificadorDebil ( ArrayList entrenamiento )
    {
        System.out.println("[Debil] Aplicando clasificador debil...");
        ArrayList<Boolean> clasificacion = new ArrayList();
        boolean aux;
        
        for ( int i = 0; i < entrenamiento.size(); i++ ) 
        {
            Imagen img = (Imagen)entrenamiento.get(i);
            byte imageData[] = img.getImageData();
            
            int umbralimagen = imageData[this._pixel];
            //System.out.println ( "umbral de la imagen: " + umbralimagen );
            
            if(this._direccion == 1)
                if(this._umbral >= umbralimagen)
                    aux = true;
                else
                     aux = false;
            else if(this._umbral < umbralimagen)
                aux = true;
            else
                aux = false;
                            
            clasificacion.add(aux);
        }
        
        return clasificacion;
    }
    
    // comprar vector generado en la función anterior con
    // el vector correcto generado
    public void ErrorClasificador ( ArrayList<Imagen> aprendizaje, ArrayList correcto )
    {
        System.out.println("[Debil] Calcular error del clasificador...");
        
        ArrayList clasificacion =  this.aplicarClasificadorDebil ( aprendizaje );
        
        for ( int i = 0; i < correcto.size(); ++i ) 
        {
          if ( !clasificacion.get( i ).equals( aprendizaje.get( i ) ) ) 
              _error = _error + aprendizaje.get(i).getPeso();
        }
        _confianza = 0.5f * (float)Math.log10(1 - _error ) / _error;
    }

    // 
    
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
    
    public float getConfianza() {
        return _confianza;
    }

    public void setConfianza(float _confianza) {
        this._confianza = _confianza;
    }
}
