/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.LeerDatos;
import data.Patron;
import java.util.*;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 *
 * @author wendo
 */
public class PatronRepresentativo
{
    private String clase;
    private String claseResultante;
    public double[] vectorC;

    /*---Método Constructor--*/
    // Vector que unicamnete recibe solamnete a los atributos y su clase 
     public PatronRepresentativo(double[] vectorC, String clase) 
    {
        this.vectorC = vectorC;
        this.clase = clase;
    }

    /*Método formula para sumatoria (Sumas las instancias)*/   
    public void sumatoria(double[] s)
    {
        for(int k =0; k<vectorC.length; k++)
        {
            vectorC[k] = vectorC[k] + s[k];
        }
    }
     /*Método formula para calcularMedia (media de cada uno de los atributos de cada las clases)*/ 
     public void calcularMedia(int m)
    {
        for(int w =0; w<vectorC.length; w++)
        {   
            vectorC[w] = vectorC[w] / m; // m = numero de inatancias de cada clase hay 
        }
    }
/*--- MÉTODOS GET AND SET ---*/    
    public String getClase()
    {
        return clase;
    }
    public double[] getVectorC()
    {
        return vectorC;
    }
    
/*--- Método para obtener largo del ArrayList  ---*/
    public static int obtenerSize(ArrayList<Patron> instancias) 
    {
        int tam=instancias.size();
        return  tam;     
    }
}    
