
package pkg1718_p2si;

import java.util.ArrayList;

/**
 * Esta clase se encarga de obtener toda la información necesaria para poder 
 * operar los clasificadores debiles y el algoritmo adaBoost.
 * Se obtiene en función del procentaje de imagenes a entrenar.
 */
public final class Practica 
{
    // porcentaje y numero de imágenes a entrenar
    private final int _porcentajeEntrenamiento;
    private final int _totalImagenes = 1000;
    private final int _cantidadEntrenamiento;
    
    // ArrayList con las imágenes separas en testeo y entrenamiento
    private final ArrayList _cantidad;
    private final ArrayList _aprendizaje = new ArrayList ();
    private final ArrayList _testeo = new ArrayList ();
    
    // array auxiliar
    private final ArrayList _tipo = new ArrayList();
    
    // ArrayList bidimensional con la clasificación correcta de las imagenes
    // de entrenamiento para cada dígito.
    private final ArrayList<ArrayList> _correctosEntrenamiento = new ArrayList<>();
    private final ArrayList _correctoTesteo = new ArrayList();

    // carga las imágenes a usar
    private final MNISTLoader _ml = new MNISTLoader ();
    
    /**
    * constructor por defecto, llama a las siguientes funciones
    * para inicializar los atributos necesarios para realizar el
    * adaboost
    * 
    * @param porcentajeEntrenamiento
    */
    public Practica( int porcentajeEntrenamiento )
    {
        this._cantidad = new ArrayList();
        
        _porcentajeEntrenamiento = porcentajeEntrenamiento;
        _cantidadEntrenamiento = _totalImagenes * _porcentajeEntrenamiento / 100;
        _ml.loadDBFromPath ( "./mnist_1000" );
        
        cantidadEntrenamiento();
        separarImagenes();
        correctoEntrenamiento();
    }
    
    /**
    * rellena el vector con la cantidad de imágenes de las que se
    * dispone para cada dígito.
    */
    private void cantidadEntrenamiento ()
    {
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = _ml.getImageDatabaseForDigit(i);
            int size = imgs.size();
            _cantidad.add ( size );
        }
    }

    /**
     * 
     * CORREGIR SIZE 
     * 
     * Rellena un arrayList bidimensional con las clasificación
     * correcta de las imágenes por dígito.
     */  
    private void correctoEntrenamiento ()
    {
        boolean rango;
        
        int inicio = 0;
        int fin = (int)_tipo.get(0);
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList resultado = new ArrayList();
            for ( int j = 0; j < _aprendizaje.size() ; ++j )
            {
                rango = (j>=inicio) && (j<fin);
                if ( rango )
                    resultado.add(true);
                else
                    resultado.add(false);
            }
            
            inicio = fin;
            if ( i != 9 )
                fin += (int)_tipo.get(i+1);
            else
                fin = _aprendizaje.size();
            _correctosEntrenamiento.add ( resultado );
        }
    }
    
    /**
    * Separa las imágenes a clasificar en las usadas para el entrenamiento
    * de los clasificadores débiles y las usadas para testear los debiles
    * entrenados.
    */
    private void separarImagenes()
    {
        int separarImagenes;
        int digitos;
        int tipo = 0;
   
        float sumaError = 0;
        float sde;
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = _ml.getImageDatabaseForDigit(i);
            digitos = imgs.size();
            
            separarImagenes =  _porcentajeEntrenamiento * digitos / 100;    
            sde = (float)digitos * _porcentajeEntrenamiento / 100.0f;
            sumaError += sde - (int)sde;

            tipo = 0;
            for ( int j = 0; j < digitos; ++j )
            {
                Imagen img = (Imagen) imgs.get(j);

                if ( j <  separarImagenes )
                {
                    _aprendizaje.add ( img );
                    tipo++;
                }
                
                else
                {
                    _testeo.add ( img );
                    _correctoTesteo.add(i);
                }
                
            }
            _tipo.add(tipo);
            
        }
        
        // corregir error al aplicar el porcentaje
        float dif = sumaError - (int)sumaError;
        if ( dif < 0.000001 )
        {
            for ( int i = 0; i < (int)sumaError; ++i )
            {
                ArrayList imgs = _ml.getImageDatabaseForDigit(i);
                _aprendizaje.add( imgs.get(i) );
            }
        }
        else if ( dif > 0.99997)
        {

            for ( int i = 0; i <= (int)sumaError; ++i )
            {
                ArrayList imgs = _ml.getImageDatabaseForDigit(i);
                _aprendizaje.add( imgs.get(i) );
            }
        }
    }

    
    // APLICAR LOS CLASIFICADORES FUERTES A LAS IMÁGENES DE TESTO
    public ArrayList aplicarFuertes ( ArrayList<Fuerte> fuertes )
    {
        ArrayList mejoresH = new ArrayList( _testeo.size() );
        float mejor;
        int pos = 0;
        for ( int i = 0; i < _testeo.size(); ++i )
        {
            Object img = _testeo.get(i);
            mejor = 0;
            for ( int j = 0; j < fuertes.size(); ++j )
            {
                Fuerte f = fuertes.get(j);
                if ( f.H( (Imagen)img) > mejor )
                {
                    mejor = f.H( (Imagen)img);
                    pos = j;
                }
            }
            mejoresH.add(pos);
        }
        
        return mejoresH;
    }
    
    // DEVUELVE LA CANTIDAD DE ACIERTOS
    public int imagenesAcertadas ( ArrayList mejoresH )
    {
        int aciertos = 0;
        
        for ( int i = 0; i < _testeo.size(); ++i )
        {
            if ( mejoresH.get(i) == _correctoTesteo.get(i) )
               aciertos++;
        }
        
        return aciertos;
    }
    
    
    public ArrayList getCorrectoTesteo() {
        return _correctoTesteo;
    }
    public ArrayList getAprendizaje() {
        return _aprendizaje;
    }
    public ArrayList getTesteo() {
        return _testeo;
    }
    public ArrayList<ArrayList> getCorrectos() {
        return _correctosEntrenamiento;
    }
}
