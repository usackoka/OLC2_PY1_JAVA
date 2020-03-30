/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Acceso;
import AST.Sentencia;
import GraficasArit.Graph_AST;

public class Modificacion extends Sentencia{

    Expresion expresion, acceso;
    
    public Modificacion(Expresion acceso, Expresion expresion, int fila, int columna){
        this.acceso = acceso;
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        ((Acceso)this.acceso).setValor(entorno,this.expresion.getValor(entorno),true);
        return null;
    }

    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "ACCESO");
        
        return cont_raiz;
    }

}
