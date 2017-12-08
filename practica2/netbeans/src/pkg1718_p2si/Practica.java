/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    // para cada dígito.
    private final ArrayList<ArrayList> _correctos = new ArrayList<>();

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
        
        
        System.out.println ("[Practica] Imágenes a cargar: " + _totalImagenes );
        System.out.println ("[Practica] Porcentaje a entrenar: " + _porcentajeEntrenamiento );
        System.out.println ("[Practica] Imagenes a entrenar(valor): " + _cantidadEntrenamiento);
        System.out.println("-------");
        
        cantidad();
        separarImagenes();
        correcto();
        
        System.out.println("..........");
        System.out.println ("[Practica] Size aprendizaje: " + _aprendizaje.size() );
        System.out.println ("[Practica] Size testeo: " + _testeo.size() );     
        System.out.println ("[Practica] Size correcto: " + _correctos.size() );
 
                
    }
    
    /**
    * rellena el vector con la cantidad de imágenes de las que se
    * dispone para cada dígito.
    */
    private void cantidad ()
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
    private void correcto ()
    {
        System.out.println("[Practica] Array bidimensional con clasificación correcta generado...");
        int inicio = 0;
        int fin = (int) _cantidad.get(0) - 1;
        
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList resultado = new ArrayList();
            
            for ( int j = 0; j < _cantidadEntrenamiento ; ++j )
            {
                boolean rango = (j >= inicio) && (j<=fin);
                if ( rango ) 
                    resultado.add ( true );
                else
                    resultado.add ( false );
            }
            
            inicio = fin + 1;
            if ( i != 9)
                fin += (int) _cantidad.get ( i+1 ) - 1;
            
            _correctos.add ( resultado );
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
        float error = 0.0f;
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = _ml.getImageDatabaseForDigit(i);
            
            digitos = imgs.size();
            separarImagenes =  _porcentajeEntrenamiento * digitos / 100;
            // System.out.println(digitos);
            for ( int j = 0; j < digitos; ++j )
            {
                Imagen img = (Imagen) imgs.get(j);

                if ( j <  separarImagenes )
                    _aprendizaje.add ( img );
                else
                    _testeo.add ( img );
            }
        }
    }
    
    /**
     * getters y setters
     * @return 
     */
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
        return _correctos;
    } 
}
