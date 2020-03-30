/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import GraficasArit.Graph_AST;

public class Variable extends Expresion{

    Object value;
    
    public Variable(Object value, Object TIPO, int fila, int columna){
        this.value = value;
        this.TIPO = TIPO;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        return this.value;
    }
    
    public void setValue(Object value){
        this.value = value;
    }
    
    public Object getTipo(Entorno entorno){
        return this.TIPO;
    }

    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
}
