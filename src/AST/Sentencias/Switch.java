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

public class Switch extends Sentencia{

    Expresion match;
    LinkedList<Caso> listaCasos;
    Default default_;
    
    public Switch(Expresion match, LinkedList<Caso> listaCasos, Default default_, int fila, int columna){
        this.match = match;
        this.listaCasos = listaCasos;
        this.default_ = default_;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        Object value = this.match.getValor(entorno);
        for(Caso caso : this.listaCasos){
            if(caso.match(value, entorno)){
                Object res = caso.ejecutar(entorno);
                if(res!=null){
                    if(res.equals(Corte.TIPO_CORTE.BREAK))
                        return null;
                    return res;
                }
            }
        }
        return default_!=null?default_.ejecutar(entorno):null;
    }

    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "Switch");
        
        for(Nodo nodo:this.listaCasos){
            int cont_hijo = nodo.Recorrido(arbol);
            arbol.addRelacion(cont_raiz,cont_hijo);
        }
        
        if(this.default_!=null){
            int cont_hijo = default_.Recorrido(arbol);
            arbol.addRelacion(cont_raiz, cont_hijo);
        }
        
        return cont_raiz;
    }
}
