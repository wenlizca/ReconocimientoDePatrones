/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import java.util.*;
import data.*;
import interfaces.*;
import java.util.ArrayList;
/**
 *
 * @author wendo
 */
public class MinimaDistancia
{
    ArrayList<PatronRepresentativo> representativos; // Es un ArrayList de tipo PatronRepresentativo llamado representativo 
    
    /* El metodo entrenar va a Calcular los Patrones Representativos de cada clase */
    public void entrenar(ArrayList<Patron> instancias) 
    {
        //Calcular los REPRESENTATIVOS
        representativos=new ArrayList<>();
        int c = 0; // Variable contador para indetificar la posicion en la que va la clase dentro del representativo
        int cantAtributos= 0; // Cunta los atributos que hay por cada clase
        for(int i=0; i<instancias.size();i++)
        {
            // Si el ArryLsit representativo esta vacio se le agregara Patron de la clase encontrada 
            if(representativos.size() == 0)  
            {
                representativos.add(new PatronRepresentativo(instancias.get(i).vectorC, instancias.get(i).getClase()));
                representativos.get(representativos.size()-1).sumatoria(instancias.get(i).getVectorC());
                cantAtributos++;
            }
            else // Si el ArryLsit representativo No esta vacio 
            {
                // Si la clase de isntancias en la posicion i = a la clase actual del ArrayList repserentativo 
                if(instancias.get(i).getClase().equals(representativos.get(c).getClase()))
                {
                   // Entonces e obtiene la longitud del ArryLits reprsentativo 
                   // y le aplicamos la sumatoria mandandole como paramentro el vector contenido en esa posicion 
                   representativos.get(representativos.size()-1).sumatoria(instancias.get(i).getVectorC());
                   cantAtributos++;
                } 
                else // Si la clase nueva y la clase actual son diferentes  
                {
                    // Se pasa directo a contabilizar la media 
                   representativos.get(c).calcularMedia(cantAtributos);
                   
                   cantAtributos = 0;  // Vuelve a inicializar la varible para calcular patrones de un nueva clase 
                   // Se agregara nuevo Patron de la clase encontrada 
                   representativos.add(new PatronRepresentativo(instancias.get(i).vectorC, instancias.get(i).getClase()));
                   c++;  // Pasa a la siguente posicion dentro del representativo en espera de una nueva clase 
                   representativos.get(representativos.size()-1).sumatoria(instancias.get(i).getVectorC());
                   cantAtributos++;
                }
            }
        }
        // Clacula unicamnete la media para la ultima clase reconocida 
        representativos.get(c).calcularMedia(cantAtributos);   
 
    }

     /* El metodo clasificar va a ver cual es la distancia mas corta  */   
    public Patron clasificar(Patron p) 
    {
        String[] vectorClases = new String[representativos.size()];
        double[] vectorDistancias = new double[representativos.size()]; 
        
        for(int x = 0; x<representativos.size(); x++)
        {
            // Se calculan las distancias en la posicion almacen la distancia calculada de la clase en la posicicion i 
            vectorDistancias[x] = p.calcularDistancia(new Patron(representativos.get(x).getClase(), "", representativos.get(x).getVectorC()));
            vectorClases[x] = representativos.get(x).getClase();
        }
        // Aqui se ordenan los vectores de mayor a menor en funcion al valor de su distancia 
        for(int k=0; k<vectorDistancias.length-1; k++)
        {
            for(int w=0; w<vectorDistancias.length-1; w++)
            {
                if (vectorDistancias[w] < vectorDistancias[w + 1])
                {
                    double aux = vectorDistancias[w+1];
                    vectorDistancias[w+1] = vectorDistancias[w];
                    vectorDistancias[w] = aux;
                    
                    String aux2 = vectorClases[w+1];
                    vectorClases[w+1] = vectorClases[w];
                    vectorClases[w] = aux2;
                }
            }
       }
        //Asignacion de la clase resultante 
        p.setClaseResultante(vectorClases[vectorClases.length-1]);
        // System.out.print(p);
        return p;
    }

    public String eficiencia(ArrayList<Patron> instancias)
    {
        double eficiencia=0;
        int c=0;
        for(int i=0;i<instancias.size();i++)
        {
            if(instancias.get(i).getClaseResultante().equals(instancias.get(i).getClase()))
            {
                c++;
            }
        }
        eficiencia=(c*100)/instancias.size();
        return "El porcentaje de eficiencia es: "+eficiencia+"%";
    }
    
    public ArrayList<Patron> implementacionEntrenar(ArrayList<Patron> instancias)
    {
        //llama al metodo entrenar para poder clasificar 
        entrenar(instancias);
        ArrayList<Patron> kw = new ArrayList<>();
        //System.out.print(instancias.size());
        for(int x = 0; x<instancias.size();x++)    
        {   
            kw.add(clasificar(instancias.get(x)));
        }
        return kw;
    }
    
}
