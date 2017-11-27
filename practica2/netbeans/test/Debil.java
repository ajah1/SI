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
    
    // umbral
    private float _umbral = 0.0f;
    // error
    private float _error = 0.0f;
    
    public Debil ()
    {}
    
    public Debil ( float umbral, float error)
    {
        _umbral = umbral;
        _error = error;
    }

    // clasificador = generarClasificadorAzar ( dimesión de los datos )
    public int clasificador ( int p_dimesion )
    {
        return 0;
    }
    
    // resultado_clasificación = aplicarClasificadorDebil (clasificador, datos )
    public int aplicarClasificadorDebil ( Debil p_debil, byte [] p_datos )
    {
        return 0;
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

    public void setUmbral ( float _umbral ) {
        this._umbral = _umbral;
    }

    public void setError ( float _error ) {
        this._error = _error;
    }
    
    
}
