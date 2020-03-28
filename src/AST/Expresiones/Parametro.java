/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import GraficasArit.Graph_AST;

public class Parametro extends Expresion{

    public String id;
    public Expresion expresion;
    
    public Parametro(String id, Expresion expresion, int fila, int columna){
        this.id = id;
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
}
