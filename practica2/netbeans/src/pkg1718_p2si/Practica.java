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
    private int porcentajeEntrenamiento = 60;
    private int totalImagenes = 1000;
    private int cantidadEntrenamiento = totalImagenes * porcentajeEntrenamiento / 100;

    private ArrayList cantidad = new ArrayList();
    private ArrayList aprendizaje = new ArrayList ();
    private ArrayList testeo = new ArrayList ();
    
    private ArrayList<ArrayList> correctos = new ArrayList<>();
    
    private MNISTLoader ml = new MNISTLoader ();
    
    Practica()
    {
        ml.loadDBFromPath ( "./mnist_1000" );
        
        System.out.println ( "[Practica] Imágenes a cargar: " + totalImagenes );
        System.out.println ( "[Practica] Porcentaje a entrenar: " + porcentajeEntrenamiento );
        System.out.println ( "[Practica] Imagenes a entrenar: " + cantidadEntrenamiento + "\n");
        
        cantidad();
        correcto();
        separarImagenes();
    }

    // CANTIDAD DE IMÁGENES POR DÍGITO
    private void cantidad ()
    {
        System.out.println("[Practica] Obtenida cantidad imágenes por dígito...");
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = ml.getImageDatabaseForDigit(i);
            int size = imgs.size();
            cantidad.add ( size );
        }
    }

    // OBTENER LOS VECTORES CORRECTOS DE CADA DÍGITO
    private void correcto ()
    {
        System.out.println("[Practica] Array bidimensional con clasificación correcta generado...");
        int inicio = 0;
        int fin = (int) cantidad.get(0) - 1;
        
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList resultado = new ArrayList();
            
            for ( int j = 0; j < getTotalImagenes(); ++j )
            {
                boolean rango = (j >= inicio) && (j<=fin);
                if ( rango ) 
                    resultado.add ( true );
                else
                    resultado.add ( false );
            }
            
            inicio = fin + 1;
            if ( i != 9)
                fin += (int) cantidad.get ( i+1 );
            
            correctos.add ( resultado );
        }
    }
    
    //SEPRAR IMÁGENES TESTEO Y ENTRENAMIENTO
    private void separarImagenes()
    {
        System.out.println("[Practica] Imagenes separadas en testeo y entrenamiento...");
        for ( int i = 0; i < 10; ++i )
        {
            ArrayList imgs = ml.getImageDatabaseForDigit(i);
            
            int digitos = imgs.size();
            for ( int j = 0; j < digitos; ++j )
            {
                Imagen img = (Imagen) imgs.get(j);

                if ( j < cantidadEntrenamiento )
                    aprendizaje.add ( img );
                else
                    testeo.add ( img );
            }
        }
    }
    
    /**
     *
     * @return
     */
    public int getPorcentajeEntrenamiento() {
        return porcentajeEntrenamiento;
    }

    /**
     *
     * @return
     */
    public int getTotalImagenes() {
        return totalImagenes;
    }

    /**
     *
     * @return
     */
    public int getCantidadEntrenamiento() {
        return cantidadEntrenamiento;
    }

    /**
     *
     * @return
     */
    public ArrayList getCantidad() {
        return cantidad;
    }
    
    /**
     *
     * @return
     */
    public MNISTLoader getMl() {
        return ml;
    }

    /**
     *
     * @param ml
     */
    public void setMl(MNISTLoader ml) {
        this.ml = ml;
    }

    /**
     *
     * @param porcentajeEntrenamiento
     */
    public void setPorcentajeEntrenamiento(int porcentajeEntrenamiento) {
        this.porcentajeEntrenamiento = porcentajeEntrenamiento;
    }

    /**
     *
     * @param totalImagenes
     */
    public void setTotalImagenes(int totalImagenes) {
        this.totalImagenes = totalImagenes;
    }

    /**
     *
     * @param cantidadEntrenamiento
     */
    public void setCantidadEntrenamiento(int cantidadEntrenamiento) {
        this.cantidadEntrenamiento = cantidadEntrenamiento;
    }

    /**
     *
     * @param cantidad
     */
    public void setCantidad(ArrayList cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     *
     * @param aprendizaje
     */
    public void setAprendizaje(ArrayList aprendizaje) {
        this.aprendizaje = aprendizaje;
    }

    /**
     *
     * @param testeo
     */
    public void setTesteo(ArrayList testeo) {
        this.testeo = testeo;
    }

    /**
     *
     * @param correctos
     */
    public void setCorrectos(ArrayList<ArrayList> correctos) {
        this.correctos = correctos;
    }

    /**
     *
     * @return
     */
    public ArrayList getAprendizaje() {
        return aprendizaje;
    }

    /**
     *
     * @return
     */
    public ArrayList getTesteo() {
        return testeo;
    }

    /**
     *
     * @return
     */
    public ArrayList<ArrayList> getCorrectos() {
        return correctos;
    }
    
}
