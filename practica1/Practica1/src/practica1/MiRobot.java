/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica1;

import java.util.ArrayList;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import simbad.sim.*;
import javax.vecmath.Vector3d;
import javax.vecmath.Matrix3d;


/**
 *
 * @author mireia
 */

public class MiRobot extends Agent{
    
        //Variables utilizadas para el controlador difuso
        RangeSensorBelt sonars;
        FuzzyController controller;
        
        //Variables generales
        int mundo[][]; //Datos del entorno
        int origen; //Punto de partida del robot. Será la columna 1 y esta fila
        int destino; //Punto de destino del robot. Será la columna tamaño-1 y esta fila
        char camino[][]; //Camino que debe seguir el robot. Será el resultado del A*
        int expandidos[][]; //Orden de los nodos expandidos. Será el resultado del A*
        int tamaño; //Tamaño del mundo

        

        public MiRobot(Vector3d position, String name, Practica1 practica1) {
            super(position, name);

            //Prepara las variables
            tamaño = practica1.tamaño_mundo;
            mundo = new int[tamaño][tamaño];
            camino = new char[tamaño][tamaño];
            expandidos = new int[tamaño][tamaño];
            origen = practica1.origen;
            destino = practica1.destino;
            mundo = practica1.mundo;
            
            //Inicializa las variables camino y expandidos donde el A* debe incluir el resultado
            for(int i=0;i<tamaño;i++)
                for(int j=0;j<tamaño;j++){
                    camino[i][j] = '.';
                    expandidos[i][j] = -1;
                }

            // Añade sonars
            sonars = RobotFactory.addSonarBeltSensor(this); // de 0 a 1.5m
        }
        
        // calcular heurística
        public float calcularh ( Nodo _no, Nodo _nd )
        {
            
            float dx = Math.abs(_no.c - _nd.c);
            float dy = Math.abs(_no.f - _nd.f);

            //return (dx + dy ) + Math.min(dx, dy);
            return (Math.abs(_no.f - _nd.f) + Math.abs(_no.c - _nd.c));
           // return (float)Math.sqrt(dx*dx + dy*dy);
            
        }
        
        // nodo frontera con menor f(n)
        public Nodo menorFrontera ( ArrayList _lf, Nodo _nd )
        {
            
            Nodo menorNodo = (Nodo)_lf.get(0);
            float hm = calcularh ( menorNodo, _nd );
            float fm = menorNodo.g + hm;
            
            float f = 0.0f;
            float h = 0.0f;
            
            for ( Object nodo : _lf )
            {
                Nodo n = (Nodo)nodo;
                
                f = n.g + calcularh ( n, _nd );
                h = calcularh ( n, _nd );
                
                if ( h < hm )
                {
                    hm = h;
                    menorNodo = n;
                }
                else if ( h == hm)
                {
                    if ( (_nd.c - n.c) < (_nd.c - menorNodo.c) )
                    {
                        hm = h;
                        menorNodo = n;
                    }
                    else if (( _nd.c - n.c) == (_nd.c - menorNodo.c))
                    {
                        if ((_nd.f - n.f) < (_nd.f - menorNodo.f))
                        {
                            hm = h;
                            menorNodo = n;
                        }
                    }
                }
            }
            return menorNodo;
        }
        
        // Obtener los nodos hijos que no estén en lista interior [ok]
        public void obtenerHijos( ArrayList _lf, Nodo _n, ArrayList _li )
        {   
            
            Nodo naux;
            if ( mundo[_n.f][_n.c + 1] == 0
                    && !esInterior(new Nodo(_n.f, _n.c + 1), _li))
            {
                naux = new Nodo(_n.f, _n.c+1, _n.g + 1);
                naux.padre = _n;
                _lf.add(naux);
            }
            if ( mundo[_n.f + 1][_n.c] == 0
                    && !esInterior(new Nodo(_n.f + 1, _n.c), _li))
            {
                naux = new Nodo(_n.f+1, _n.c, _n.g + 1);
                naux.padre = _n;
                _lf.add(naux);
            }
            if ( mundo[_n.f][_n.c - 1] == 0
                    && !esInterior(new Nodo(_n.f , _n.c - 1), _li))
            {
                naux = new Nodo(_n.f, _n.c-1, _n.g + 1);
                naux.padre = _n;
                _lf.add(naux);
            }      
            if ( mundo[_n.f - 1][_n.c] == 0 
                    && !esInterior(new Nodo(_n.f - 1, _n.c), _li) )
            {
                naux = new Nodo(_n.f-1, _n.c, _n.g + 1);
                naux.padre = _n;
               _lf.add(naux);
            }
        }
        
        // busca el _nodo en la lista interior
        public boolean esInterior( Nodo _n, ArrayList _li )
        {   
            for ( Object nodo : _li)
            {
                Nodo n = (Nodo)nodo;
                
                if ( _n.equals(n) )
                    return true;
            }
            return false;
        }
        
        // busca el _nodo en lista frontera
        public boolean esfrontera ( ArrayList _lf, Nodo _n )
        {
            for ( Object nodo : _lf )
            {
                Nodo n = (Nodo)nodo;
                
                if ( _n.equals(n) )
                    return true;
            }
            
            return false;
        }
        
        public void expand()
        {
            for (int i = 0; i < 20; ++i )
            {
                for (int j = 0; j < 20; ++j)
                {
                    System.out.print(this.expandidos[i][j] +  " ");
                }
                System.out.println();
            }
        }
        
        
        // genera el camino obtenido
        public void camino(Nodo _last)
        {
            Nodo aux;
            
            for (int i = 0; i < 20; ++i )
            {
                for (int j = 0; j < 20; ++j)
                {
                    camino[i][j] = '.';
                }
            }

            aux = _last;
            
            while ( aux != null )
            {
                camino[aux.f][aux.c] = 'X';
                aux = aux.padre;
            }
            
            
            for (int i = 0; i < 20; ++i )
            {
                for (int j = 0; j < 20; ++j)
                {
                    System.out.print(camino[i][j]);
                }
                System.out.println(" ");
            }
            
        }
        
        //Calcula el A*
        public int AEstrella()
        {        
            /*
                HAcer contador par+a saber el orden en el que 
                expandes los nodos            
            */
            int expandido = 0;
   
            Nodo nodometa = new Nodo( destino, tamaño - 2 );
            Nodo nodoorigen = new Nodo ( origen, 1, 0 );
            
            this.expandidos[nodometa.f][nodometa.c] = expandido;
            expandido++;
            
            Nodo n = new Nodo();
            
        //listaInterior = vacio
            ArrayList listaInterior = new ArrayList();
            listaInterior.add( nodoorigen );
        //listaFrontera = inicio
            ArrayList listaFrontera = new ArrayList();
        // inicializar listaFrontera
            this.obtenerHijos ( 
                    listaFrontera, 
                    nodoorigen, 
                    listaInterior );
            
        //mientras listaFrontera no esté vacía
            while ( !listaFrontera.isEmpty() )
            {
            //n = obtener nodo de listaFrontera con menor f(n) = g(n) + h(n)
                n = this.menorFrontera( listaFrontera, nodometa );
                this.expandidos[n.f][n.c] = expandido;
                expandido++;
                
            //listaFrontera.del(n)
                listaFrontera.remove( n );
            //listaInterior.add(n)
                listaInterior.add( n );
                
            //si listaFrontera = vacía
                if ( listaFrontera.isEmpty() )
                {
                     //Error, no se encuentra solución
                    return 1;
                }
            //sino si n es meta
                else if ( nodometa.equals( n ) )
                {
                //devolver
                //reconstruir camino desde la meta al inicio siguiendo los punteros
                    this.camino(n);
                    this.expand();
                    return 0;
                }
            //fsi
            
                ArrayList hijosn = new ArrayList();
                this.obtenerHijos(hijosn, n, listaInterior);
                
                //para cada hijo m de n que no esté en lista interior
                for ( Object nodo : hijosn )
                {
                    Nodo m = (Nodo)nodo;
                    
                    //g’(m) = n.g + c(n, m)
                    m.g = n.g + 1;
                    
                    //si m no está en listaFrontera
                    if ( !this.esfrontera( listaFrontera, m ) )
                    {
                        //almacenar la f, g y h del nodo en (m.f, m.g, m.h)
                        m.h = calcularh ( m, nodometa );
                        //m.padre = n
                        m.padre = n;
                        
                        //listaFrontera.add(m)
                        listaFrontera.add ( m );
                    }        
                    //sino  si  g’(m)  es  mejor  que  m.g //Verificamos  si  el nuevo camino es mejor
                    // comprarar g del padre actual con n
                    else if ( n.g < m.padre.g )
                    {
                        //m.padre = n
                        m.padre = n;
                        //recalcular f y g del nodo m
                        m.g = n.g + 1;
                    }//fsi
                    
                }//fpara
            } //fmientras
            
            return 0;
        }

        //Función utilizada para la parte de lógica difusa donde se le indica el siguiente punto al que debe ir el robot.
        //Busca cual es el punto más cercano.
        public Point3d puntoMasCercano(Point3d posicion){
            int inicio;
            Point3d punto = new Point3d(posicion);
            double distancia;
            double cerca = 100;

            inicio = (int) (tamaño-(posicion.z+(tamaño/2)));
            
            for(int i=0; i<tamaño; i++)
                for(int j=inicio+1; j<tamaño; j++){
                    if(camino[i][j]=='X'){
                        distancia = Math.abs(posicion.x+(tamaño/2)-i) + Math.abs(tamaño-(posicion.z+(tamaño/2))-j);
                        if(distancia < cerca){
                            punto.x=i;
                            punto.z=j;
                            cerca = distancia;
                        }
                    }
                }

            return punto;
        }

        /** This method is called by the simulator engine on reset. */
    @Override
        public void initBehavior() {

            System.out.println("Entra en initBehavior");
            //Calcula A*
            int a = AEstrella();

            if(a!=0){
                System.err.println("Error en el A*");
            }else{
                // init controller
                controller = new FuzzyController();
            }
        }

        /** This method is call cyclically (20 times per second)  by the simulator engine. */
    @Override
        public void performBehavior() {

            double angulo;
            int giro;

            //Ponemos las lecturas de los sonares al controlador difuso
            //System.out.println("Fuzzy Controller Input:");
            float[] sonar = new float[9];
            for(int i=0; i<9; i++){
                if(sonars.getMeasurement(i)==Float.POSITIVE_INFINITY){
                    sonar[i] = sonars.getMaxRange();
                } else {
                    sonar[i] = (float) sonars.getMeasurement(i);
                }

                //System.out.println("    > S"+ i +": " + sonar[i]);
            }

     
            //Calcula ángulo del robot
            Transform3D rotTrans = new Transform3D();
            this.rotationGroup.getTransform(rotTrans); //Obtiene la transformada de rotación

            //Debe calcular el ángulo a partir de la matriz de transformación
            //Nos quedamos con la matriz 3x3 superior
            Matrix3d m1 = new Matrix3d();
            rotTrans.get(m1);

            //Calcula el ángulo sobre el eje y
            angulo = -java.lang.Math.asin(m1.getElement(2,0));

            if(angulo<0.0)
                angulo += 2*Math.PI;
            assert(angulo>=0.0 && angulo<=2*Math.PI);

            //Calcula la dirección
            if(m1.getElement(0, 0)<0)
                angulo = -angulo;
            angulo = angulo*180/Math.PI;            
            if(angulo<0 && angulo>-90)
                angulo += 180;
            if(angulo<-270 && angulo>-360)
                angulo += 180+360;


            //Calcula el siguiente punto al que debe ir del A*
            Point3d coord = new Point3d();
            this.getCoords(coord);

            Point3d punto = puntoMasCercano(coord);
            coord.x = coord.x+(tamaño/2);
            coord.z = tamaño-(coord.z+tamaño/2);
            coord.x = (int)coord.x;
            coord.z = (int)coord.z;
            
            
            //Calcula distancia y ángulo del vector, creado desde el punto que se encuentra el robot,
            //hasta el punt que se desea ir
            double distan = Math.sqrt(Math.pow(coord.z-punto.z, 2)+Math.pow(coord.x-punto.x, 2));    
            double phi= Math.atan2((punto.z-coord.z),(punto.x - coord.x));
            phi = phi*180/Math.PI;
            
            //Calcula el giro que debe realizar el robot. Este valor es el que se le pasa al controlador difuso.
            double rot = phi-angulo;
            if(rot<-180)
                rot += 360;
            if(rot>180)
                rot -=360;
            
            //System.out.println("Angulo de giro: "+rot);
            
            //Ejecuto el controlador
            controller.step(sonar, rot);

            //Obtengo las velocidades calculadas y las aplico al robot
            setTranslationalVelocity(controller.getVel());
            setRotationalVelocity(controller.getRot());

            //Para mostrar los valores del controlador
            //System.out.println("Fuzzy Controller Output:");
            //System.out.println("    >vel: "+ controller.getVel());
            //System.out.println("    >rot: "+ controller.getRot());
        }

}
