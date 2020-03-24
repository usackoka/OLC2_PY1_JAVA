/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Nodo;
import AST.Sentencia;
import GraficasArit.Graph_AST;


public class Corte extends Sentencia{
    
    TIPO_CORTE tipoCorte;
    
    public Corte(TIPO_CORTE tipoCorte, int fila, int columna){
        this.tipoCorte = tipoCorte;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        return this.tipoCorte;
    }
    
    public enum TIPO_CORTE{
        BREAK, CONTINUE
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, this.tipoCorte.toString());
        return cont_raiz;
    }
}
