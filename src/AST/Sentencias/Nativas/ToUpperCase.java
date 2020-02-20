/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import Analyzer.Token;
import java.util.LinkedList;

public class ToUpperCase extends Expresion{

    Expresion expresion;
    public ToUpperCase(Expresion expresion, int fila, int columna){
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object defecto = "";
        Object valor = expresion.getValor(entorno);
        Object tipo = Primitivo.getTipoDato(valor);
        
        if(valor instanceof LinkedList){
            entorno.addError(new Token("Vector["+tipo+"]:"+((LinkedList<Object>)valor).size(), "No se puede afectar el vector con mas de un valor", fila, columna));
            return defecto;
        }
        
        return valor.toString().toUpperCase();
    }
    
}
