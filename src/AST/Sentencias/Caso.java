/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Nodo;
import AST.Sentencia;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Caso extends Sentencia{

    Expresion match;
    LinkedList<Nodo> instrucciones;
    
    public Caso(Expresion match, LinkedList<Nodo> instrucciones, int fila, int columna){
        this.match = match;
        this.instrucciones = instrucciones;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        entorno = new Entorno(entorno);
        for (Nodo nodo : this.instrucciones)
        {
            if (nodo instanceof Sentencia)
            {
                Object val = ((Sentencia)nodo).ejecutar(entorno);
                if (val != null)
                {
                    return val;
                }
            }
            else
            {
                ((Expresion)nodo).getValor(entorno);
            }
        }
        return null;
    }
    
    public boolean match(Object value,Entorno entorno){
        return this.match.getValor(entorno).equals(value);
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "Case");
        
        for(Nodo nodo:this.instrucciones){
            int cont_hijo = nodo.Recorrido(arbol);
            arbol.addRelacion(cont_raiz,cont_hijo);
        }
        
        return cont_raiz;
    }
    
}
