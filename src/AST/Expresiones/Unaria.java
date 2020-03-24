/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Nodo;
import Analyzer.Token;
import GraficasArit.Graph_AST;

public class Unaria extends Expresion{

    Expresion unaria;
    TIPO_OPERACION tipoOperacion;
    
    public Unaria(Expresion unaria, TIPO_OPERACION tipoOperacion, int fila, int columna){
        this.unaria = unaria;
        this.fila = fila;
        this.columna = columna;
        this.tipoOperacion = tipoOperacion;
    }
    
    public enum TIPO_OPERACION{
        MENOS,NOT
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object def = 0;
        Object val = unaria.getValor(entorno);
        Object tipo = Primitivo.getTipoDato(val);
        switch (tipoOperacion) {
            case MENOS:
                if (tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                {
                    return - Integer.parseInt(val.toString());
                }
                else if (tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                {
                    return - Double.parseDouble(val.toString());
                }
            case NOT:
                if (tipo.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN))
                {
                    return !Boolean.parseBoolean(val.toString());
                }
                def = false;
        }
        
        entorno.addError(new Token("Unaria", "No soportado" + tipoOperacion + " con: "+tipo, fila, columna));
        return def;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, this.tipoOperacion.toString());
        
        int cont_hijo = this.unaria.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        return cont_raiz;
    }
}
