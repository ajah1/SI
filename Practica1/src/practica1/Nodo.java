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
public class Nodo {
    int f;
    int c;
    float h;
    float g;
    
    public Nodo()
    {
        this.f = 0;
        this.c = 0;
        h = 0.0f;
        g = 0.0f;
    }
    
    public Nodo(int _f, int _c)
    {
        this.f = _f;
        this.c = _c;
    }
    
    public boolean equals(Nodo _nodo)
    {
        if ( (f == _nodo.f) && (c == _nodo.c) )
            return true;
        else
            return false;
    }
    
}
