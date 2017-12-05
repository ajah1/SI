/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public final class Practica 
{
    private int _porcentajeEntrenamiento = 80;
    private int _totalImagenes = 1000;
    private int _cantidadEntrenamiento = _totalImagenes * _porcentajeEntrenamiento / 100;

    private ArrayList _cantidad = new ArrayList();
    private ArrayList _aprendizaje = new ArrayList ();
    private ArrayList _testeo = new ArrayList ();
    
    private ArrayList<ArrayList> _correctos = new ArrayList<>();
    
    private float [] _pesosImagenes = new float [ _cantidadEntrenamiento ];
    
    private MNISTLoader _ml = new MNISTLoader ();
    
    Practica()
    {
        _ml.loadDBFromPath ( "./mnist_1000" );
        
        cantidad();
        correcto();
        separarImagenes();
        
        System.out.println ("[Practica] Imágenes a cargar: " + _totalImagenes );
        System.out.println ("[Practica] Porcentaje a entrenar: " + _porcentajeEntrenamiento );
        System.out.println ("[Practica] Imagenes a entrenar: " + _aprendizaje.size() );
        System.out.println ("[Practica] Imagenes a testear: " + _testeo.size() );
    }
    
    // CANTIDAD DE IMÁGENES POR DÍGITO
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

    // OBTENER LOS VECTORES CORRECTOS DE CADA DÍGITO
    private void correcto ()
    {
        System.out.println("[Practica] Array bidimensional con clasificación correcta generado...");
        int inicio = 0;
        int fin = (int) _cantidad.get(0) - 1;
        
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList resultado = new ArrayList();
            
            for ( int j = 0; j < _cantidadEntrenamiento; ++j )
            {
                boolean rango = (j >= inicio) && (j<=fin);
                if ( rango ) 
                    resultado.add ( true );
                else
                    resultado.add ( false );
            }
            
            inicio = fin + 1;
            if ( i != 9)
                fin += (int) _cantidad.get ( i+1 );
            
            _correctos.add ( resultado );
        }
    }
    
    //SEPRAR IMÁGENES TESTEO Y ENTRENAMIENTO
    private void separarImagenes()
    {
        System.out.println("[Practica] Imagenes separadas en testeo y entrenamiento...");
        int separarImagenes = 0;
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = _ml.getImageDatabaseForDigit(i);
            
            int digitos = imgs.size();
            for ( int j = 0; j < digitos; ++j )
            {
                Imagen img = (Imagen) imgs.get(j);

                separarImagenes = _porcentajeEntrenamiento*digitos / 100;
                if ( j <  separarImagenes )
                    _aprendizaje.add ( img );
                else
                    _testeo.add ( img );
            }
        }
    }
    
    /**
     *
     * @return
     */
    public int getPorcentajeEntrenamiento() {
        return _porcentajeEntrenamiento;
    }

    /**
     *
     * @return
     */
    public int getTotalImagenes() {
        return _totalImagenes;
    }

    /**
     *
     * @return
     */
    public int getCantidadEntrenamiento() {
        return _cantidadEntrenamiento;
    }

    /**
     *
     * @return
     */
    public ArrayList getCantidad() {
        return _cantidad;
    }
    
    /**
     *
     * @return
     */
    public MNISTLoader getMl() {
        return _ml;
    }

    /**
     *
     * @param ml
     */
    public void setMl(MNISTLoader ml) {
        this._ml = ml;
    }

    /**
     *
     * @param porcentajeEntrenamiento
     */
    public void setPorcentajeEntrenamiento(int porcentajeEntrenamiento) {
        this._porcentajeEntrenamiento = porcentajeEntrenamiento;
    }

    /**
     *
     * @param totalImagenes
     */
    public void setTotalImagenes(int totalImagenes) {
        this._totalImagenes = totalImagenes;
    }

    /**
     *
     * @param cantidadEntrenamiento
     */
    public void setCantidadEntrenamiento(int cantidadEntrenamiento) {
        this._cantidadEntrenamiento = cantidadEntrenamiento;
    }

    /**
     *
     * @param cantidad
     */
    public void setCantidad(ArrayList cantidad) {
        this._cantidad = cantidad;
    }
    
    /**
     *
     * @param aprendizaje
     */
    public void setAprendizaje(ArrayList aprendizaje) {
        this._aprendizaje = aprendizaje;
    }

    /**
     *
     * @param testeo
     */
    public void setTesteo(ArrayList testeo) {
        this._testeo = testeo;
    }

    /**
     *
     * @param correctos
     */
    public void setCorrectos(ArrayList<ArrayList> correctos) {
        this._correctos = correctos;
    }

    /**
     *
     * @return
     */
    public ArrayList getAprendizaje() {
        return _aprendizaje;
    }

    /**
     *
     * @return
     */
    public ArrayList getTesteo() {
        return _testeo;
    }

    /**
     *
     * @return
     */
    public ArrayList<ArrayList> getCorrectos() {
        return _correctos;
    }

    public void setPesosImagenes(float[] pesosImagenes) {
        this._pesosImagenes = pesosImagenes;
    }

    public float[] getPesosImagenes() {
        return _pesosImagenes;
    }    
}
