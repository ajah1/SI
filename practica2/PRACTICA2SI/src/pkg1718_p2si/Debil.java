package pkg1718_p2si;

import java.util.ArrayList;

/**
 * @author Alejandro Jesús Aliaga Hyder 48765284V
 */
public class Debil {
    
    // umbral [-128,128]
    private int _umbral = 0;
    // error [0,1]
    private float _error = 0.0f;
    // pixel [0,783]
    private int _pixel = 0;
    // dirección +-1
    private int _direccion = 0;
    // confianza del clasificador
    private float _confianza = 0.0f;

    /**
     *  Constructor por defecto
     */
    public Debil ()
    {
        _pixel = (int)(Math.random() * 784);
        _umbral = (int)(Math.random() * 256)-128;
        _direccion = 1;
        
        int direc = (int)(Math.random() * 2);
        if ( direc == 0 )
            _direccion = -1;
    }
    
    
    public boolean h ( Imagen imagen )
    {
        boolean resultado = false;
        
        if ( _direccion == 1 )
        {
            if ( _umbral < imagen.getImageData()[_pixel] )
                resultado = true;
        }
        else if ( _umbral >= imagen.getImageData()[_pixel] )
                resultado = true;
        
        return resultado;
    }
    
 
    /**
     * Genera el arrayList necesario para poder calcular el error del clasificador
     * este es usado en el método que calcula el error.
     * @param entrenamiento
     * @return 
     */
    public ArrayList aplicarClasificadorDebil ( ArrayList entrenamiento )
    {
        ArrayList<Boolean> clasificacion = new ArrayList();
        
        boolean aux;
        int umbralimagen;
        for ( int i = 0; i < entrenamiento.size(); i++ ) 
        {
            Imagen img = (Imagen)entrenamiento.get(i);
           
            aux = h (img);
             
            clasificacion.add(aux);
        }
        return clasificacion;
    }
    

    /**
     * Genera el error del clasificador debil a partir de la lista de imágenes de
     * entrenamiento y el vector con la clasficación correcto de las imágenes.
     * @param aprendizaje
     * @param correcto 
     */
    public void ErrorClasificador ( ArrayList<Imagen> aprendizaje, ArrayList correcto )
    {
        ArrayList clasificacion =  this.aplicarClasificadorDebil ( aprendizaje );
        
        for ( int i = 0; i < clasificacion.size(); ++i ) 
        {
            if ( clasificacion.get(i).equals(correcto.get(i)) )  
                _error += aprendizaje.get(i).getPeso();
        }
        _confianza = 0.5f * (float)Math.log10(1.0f - _error ) / _error;
    }
    
    /**
     * Getters y Setters
     * @return 
     */
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
