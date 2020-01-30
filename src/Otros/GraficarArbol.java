/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Koka
 */
public class GraficarArbol {
    
    /*
    public static String recorridoArbol(Nodo raiz) {
        String cuerpo = "";
        int num = raiz.hijos.size();
        //System.out.println("numero hijos raiz:"+ num+ "\nEtiqueta: "+raiz.getEtiqueta());
        try{
            for (int i = 0; i < num; i++) {
                Nodo hijo = raiz.hijos.get(i);
                cuerpo += "\"" + raiz.idNodo + "_" + raiz.etiqueta.replace("\"", "").replace("\n", "") + "=" + 
                        raiz.valor.replace("\"", "").replace("\n", "") + 
                        "\"->\"" + hijo.idNodo + "_" + hijo.etiqueta.replace("\"", "").replace("\n", "") + "=" + 
                        hijo.valor.replace("\"", "").replace("\n", "") + "\"";
                cuerpo += recorridoArbol(hijo);
            }
        }
        catch(Exception e){
            System.out.println("catch recorrido");
            System.out.println(e.toString());
        }
        //System.out.println("fin");
        return cuerpo;
    }
    
    public static void GraficarArbol(Nodo raiz, String nombreArchivo) {
        //System.out.println("escribiendo archivo");
        String cadena = recorridoArbol(raiz);
        FileWriter fichero = null;
        PrintWriter pw = null;
        String nombre = nombreArchivo;
        String archivo = nombre + ".dot";
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println("digraph G {node[shape=box, style=filled, color=Gray95]; edge[color=blue];rankdir=UD \n");
            pw.println(cadena);
            pw.println("\n}");
            fichero.close();
        } catch (Exception e) {
            System.out.println("catch graficar");
            System.out.println(e);
        }
        try {
            String cmd = "dot -Tjpg " + nombre + ".dot -o " + nombre + ".jpg";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
//        try {
//            Desktop.getDesktop().open(new File(archivo.replace(".dot",".jpg")));
//        } catch (IOException ex) {
//            Logger.getLogger(GraficarArbol.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

*/
}
