/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Nativas.VectorArit;
import Analyzer.Token;
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
        //valido la condición
        boolean valCondicion = false;
        Object err = this.condicion.getValor(entorno);
        try {
            //validacion para cuando viene un vector de varios booleans
            if(err instanceof VectorArit){
                err = ((VectorArit)err).get(0);
            }
            valCondicion = (Boolean)err;
        } catch (Exception e) {
            entorno.addError(new Token("Condicion-IF","Error al convertir en boolean: "+err.getClass(), fila, columna));
        }
        
        return (valCondicion)?verdadera.getValor(entorno):falsa.getValor(entorno);
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
