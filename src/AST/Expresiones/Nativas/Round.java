/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Round extends Expresion{
    public Expresion expresion;
    
    public Round(Expresion expresion, int fila, int columna){
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        
        Object defecto = 0;
        Object valor = expresion.getValor(entorno);
        Object tipo = Primitivo.getTipoDato(valor);
        
        if(valor instanceof LinkedList){
            entorno.addError(new Token("Vector["+tipo+"]:"+((LinkedList<Object>)valor).size(), "No se puede hacer ROUND un vector con mas de un valor", fila, columna));
            return defecto;
        }
        if(!tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)){
            entorno.addError(new Token("Vector["+tipo+"]:"+((LinkedList<Object>)valor).size(), "No se puede hacer ROUND de este tipo de vector", fila, columna));
            return defecto;
        }
        
        return (int)valor;
    }
}
