/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraficasArit;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Graph_AST {
    //===========variables para la grafica del arbol=====================
    public int contGraph;
    public String recorridoArbol;
    
    public Graph_AST(){
        this.recorridoArbol = "";
        this.contGraph = 0;
    }
    
    public int getNextContGraph(){
        return contGraph++;
    }

    public void addNodoGraph(int nodo1, String label) {
        recorridoArbol += "id_"+nodo1+"[label=\""+label.replace("\"", "")+"\"]"+"\n";
    }
    
    public void addRelacion(int nodo1, int nodo2){
        recorridoArbol += "id_"+nodo1+" -> "+"id_"+nodo2+"\n";
    }
    
    public void graficarAST(String syte){
        FileWriter fichero = null;
        PrintWriter pw = null;
        String nombreArchivo = "AST_"+syte;
        String nombre = nombreArchivo;
        String archivo = "Graficas\\" + nombre + ".dot";
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println("digraph G {node[shape=box, style=filled, color=Gray95]; edge[color=blue];rankdir=UD \n");
            pw.println(recorridoArbol);
            pw.println("\n}");
            fichero.close();
        } catch (Exception e) {
            System.out.println("catch graficar");
            System.out.println(e);
        }
        try {
            String cmd = "cd "+(new File (".").getAbsolutePath ())+"\n";
            cmd += "cd Graficas"+"\n";
            cmd += "dot -Tjpg " + nombre + ".dot -o " + nombre + ".jpg";
            Runtime.getRuntime().exec(cmd);
            Desktop.getDesktop().open(new File(archivo));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        recorridoArbol = "";
    }
}
