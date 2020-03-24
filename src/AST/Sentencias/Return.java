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

public class Return extends Sentencia{

    Expresion expresion;
    
    public Return(Expresion expresion, int fila, int columna){
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        return this.expresion==null?null:expresion.getValor(entorno);
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "RETURN");
        
        if(this.expresion!=null){
            int cont_hijo = this.expresion.Recorrido(arbol);
            arbol.addRelacion(cont_raiz,cont_hijo);
        }
        return cont_raiz;
    }

}
