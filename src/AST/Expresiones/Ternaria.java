/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import GraficasArit.Graph_AST;

public class Ternaria extends Expresion{

    Expresion condicion, verdadera, falsa;
    
    public Ternaria(Expresion condicion, Expresion verdadera, Expresion falsa, int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.condicion = condicion;
        this.verdadera = verdadera;
        this.falsa = falsa;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        return ((Boolean)condicion.getValor(entorno))?verdadera.getValor(entorno):falsa.getValor(entorno);
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "TERNARIA");
        
        int cont_hijo = condicion.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        cont_hijo = verdadera.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        cont_hijo = falsa.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        return cont_raiz;
    }
}
