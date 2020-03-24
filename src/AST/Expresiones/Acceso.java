/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import GraficasArit.Graph_AST;

public class Acceso extends Expresion{

    Expresion subAcceso;
    Expresion x,y;
    TIPO_ACCESO tipoAcceso;
    
    public Acceso(Expresion subAcceso, Expresion x, Expresion y, TIPO_ACCESO tipoAcceso){
        this.x = x;
        this.y = y;
        this.subAcceso = subAcceso;
        this.tipoAcceso = tipoAcceso;
        this.fila = x.fila;
        this.columna = x.columna;
    }
    
    public enum TIPO_ACCESO{
        SIMPLE, DOBLE, XY, X, Y
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        //empiezo a retornar el valor
        return null;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "ACCESO");
        
        return cont_raiz;
    }

}
