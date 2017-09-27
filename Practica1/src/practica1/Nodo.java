/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

/**
 *
 * @author alihyder
 */
public class Nodo 
{
    int f;
    int c;
    
    float fn;
    float h;
    int g;
  
    Nodo padre;
    
    public Nodo()
    {   
        this.f = 0;
        this.c = 0;
        
        this.h = 0.0f;
        this.g = 0;
    }
    
    public Nodo(int _f, int _c)
    {
        this.f = _f;
        this.c = _c;
    }
    
        public Nodo(int _f, int _c, int _g)
    {
        this.f = _f;
        this.c = _c;
        this.g = _g;
    }
    
    
    public boolean equals(Nodo _nodo)
    {
        if ( (f == _nodo.f) && (c == _nodo.c) )
            return true;
     
        return false;
    }
    
}
