
package pkg1718_p2si;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Fuerte {
    
    private final ArrayList<Debil> _debiles;

    public Fuerte ()
    {
        _debiles = new ArrayList ();
    }
    

    public float H ( Imagen imagen )
    {
        float H = 0.0f;
        boolean h;
        float conf;
        
        for ( int i = 0; i < _debiles.size(); ++i )
        {
            h = _debiles.get(i).h(imagen);
            conf = _debiles.get(i).getConfianza();
            if ( h )
                H += conf;
            else
                H -= conf;
        }
        return H;
    }

    
    public ArrayList<Debil> getDebiles ()
    {
        return _debiles;
    }
    public void addDebil ( Debil debil )
    {
        _debiles.add(debil);
    }
 }
