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

public class Remove extends Expresion{
    LinkedList<Expresion> expresiones;
    
    public Remove(LinkedList<Expresion> expresiones, int fila, int columna){
        this.expresiones = expresiones;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        
        Object defecto = "";
        Object valor = expresiones.get(0).getValor(entorno);
        Object tipo = expresiones.get(0).getTipo(entorno);
        Object valor2 = expresiones.get(1).getValor(entorno);
        Object tipo2 = expresiones.get(1).getTipo(entorno);
        
        if(valor instanceof LinkedList){
            entorno.addError(new Token("Vector1["+tipo+"]:"+((LinkedList<Object>)valor).size(), "No se puede afectar el vector con mas de un valor", fila, columna));
            return defecto;
        }
        if(valor2 instanceof LinkedList){
            entorno.addError(new Token("Vector2["+tipo2+"]:"+((LinkedList<Object>)valor2).size(), "No se puede afectar el vector con mas de un valor", fila, columna));
            return defecto;
        }
        
        String cadena1 = valor.toString();
        return cadena1.replace(cadena1, valor2.toString());
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return Expresion.TIPO_PRIMITIVO.STRING;
    }
}