
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
    private int _porcentajeEntrenamiento;
    private final int _totalImagenes = 1000;
    private final int _cantidadEntrenamiento;

    // ArrayList con las imágenes separas en testeo y entrenamiento
    private final ArrayList _cantidad = new ArrayList();
    private final ArrayList _aprendizaje = new ArrayList ();
    private final ArrayList _testeo = new ArrayList ();
    
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
        
        _porcentajeEntrenamiento = porcentajeEntrenamiento;
        _cantidadEntrenamiento = _totalImagenes * _porcentajeEntrenamiento / 100;
        
        _ml.loadDBFromPath ( "./mnist_1000" );
        
        
        System.out.println ("[Practica] Imágenes a cargar:     " + _totalImagenes );
        System.out.println ("[Practica] Porcentaje a entrenar: " + _porcentajeEntrenamiento );
        System.out.println ("[Practica] Imagenes a entrenar:   " + _cantidadEntrenamiento);
        System.out.println("-------");
        
        cantidadEntrenamiento();
        separarImagenes();
        correctoEntrenamiento();
        
        System.out.println("--------");
        System.out.println ("[Practica] Size aprendizaje: " + _aprendizaje.size() );
        System.out.println ("[Practica] Size testeo:      " + _testeo.size() );     
        System.out.println ("[Practica] Size correcto:    " + _correctosEntrenamiento.size() );
 
    }
    
    /**
    * rellena el vector con la cantidad de imágenes de las que se
    * dispone para cada dígito.
    */
    private void cantidadEntrenamiento ()
    {
        System.out.println("[Practica] Obtenida cantidad imágenes por dígito...");
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
        System.out.println("[Practica] Array clasificación real generada...");
        int fin = (int) _cantidad.get(0) - 1;
        
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList resultado = new ArrayList();
            
            for ( int j = 0; j < _cantidadEntrenamiento ; ++j )
            {
                
                boolean rango =  ( j>=fin ) && ( j < (fin + (int)_cantidad.get(i)) );
                if ( rango )
                    resultado.add ( true );
                
                else
                    resultado.add ( false );
            }
            
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
        System.out.println("[Practica] Imagenes separadas en testeo y entrenamiento...");
        int separarImagenes;
        int digitos;
   
        float sumaError = 0;
        float sde;
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = _ml.getImageDatabaseForDigit(i);
            digitos = imgs.size();
            
            separarImagenes =  _porcentajeEntrenamiento * digitos / 100;    
            sde = (float)digitos * _porcentajeEntrenamiento / 100.0f;
            sumaError += sde - (int)sde;

            for ( int j = 0; j < digitos; ++j )
            {
                Imagen img = (Imagen) imgs.get(j);

                if ( j <  separarImagenes )
                    _aprendizaje.add ( img );
                
                else
                {
                    _testeo.add ( img );
                    _correctoTesteo.add(i);
                }
            }
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
        for ( Object img : _testeo )
        {
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
            if ( mejoresH.get(i) == _correctoTesteo.get(i))
               aciertos++;
        }
        
        return aciertos;
    }
    
    /**
     * getters y setters
     * @return 
     */
    public ArrayList getCorrectoTesteo() {
        return _correctoTesteo;
    }
    public int getPorcentajeEntrenamiento() {
        return _porcentajeEntrenamiento;
    }
    public int getTotalImagenes() {
        return _totalImagenes;
    }
    public int getCantidadEntrenamiento() {
        return _cantidadEntrenamiento;
    }
    public ArrayList getCantidad() {
        return _cantidad;
    }
    public MNISTLoader getMl() {
        return _ml;
    }
    public void setPorcentajeEntrenamiento(int porcentajeEntrenamiento) {
        this._porcentajeEntrenamiento = porcentajeEntrenamiento;
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
