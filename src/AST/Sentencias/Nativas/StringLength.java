/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias.Nativas;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;
import java.util.LinkedList;

public class StringLength extends Expresion{

    Expresion expresion;
    
    public StringLength(Expresion expresion, int fila, int columna){
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object defecto = 0;
        Object valor = expresion.getValor(entorno);
        Object tipo = expresion.getTipo(entorno);
        
        if(valor instanceof LinkedList){
            entorno.addError(new Token("Vector["+tipo+"]:"+((LinkedList<Object>)valor).size(), "No se puede afectar el vector con mas de un valor", fila, columna));
            return defecto;
        }
        
        return valor.toString().length();
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return Expresion.TIPO_PRIMITIVO.INTEGER;
    }

}
