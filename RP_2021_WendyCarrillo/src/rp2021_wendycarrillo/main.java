/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2021_wendycarrillo;

import java.util.*;
import data.*;
import clasificadores.MinimaDistancia;
import interfaces.*;
 /*
 * @author wendo
 */
public class main 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        ArrayList<Patron> instancias=LeerDatos.tokenizarDataSet();
                
        System.out.println("Numero de instancias: "+PatronRepresentativo.obtenerSize(instancias));
        
        MinimaDistancia md = new MinimaDistancia();
        
        ArrayList<Patron> Patron2 = md.implementacionEntrenar(instancias);
        
        System.err.println(md.eficiencia(Patron2));
       
        for(Patron print: instancias)
        {
            System.out.println(" Clase: "+print.getClase()+" su clase resultante es: "+ print.getClaseResultante());
        }
        
    } 
    
}
