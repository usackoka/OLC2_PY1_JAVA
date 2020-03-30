/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.ListArit;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Length extends Expresion{
    Expresion e;
    
    public Length(Expresion e, Entorno entorno, int fila, int columna){
        this.e = e;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object getValor(Entorno entorno) {
        Object val = e.getValor(entorno);
        if(val instanceof Matrix){
            return ((Matrix)val).getMapeo().size();
        }else if(val instanceof ListArit){
            return ((ListArit)val).getData().size();
        }else if(val instanceof Array){
            int conteo = 1;
            for (Integer element : ((Array)val).dimensiones) {
                conteo = conteo * element;
            }
            return conteo;
        }else if(val instanceof LinkedList){
            return ((LinkedList)val).size();
        }else{
            return 1;
        }
    }

    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
    
}
