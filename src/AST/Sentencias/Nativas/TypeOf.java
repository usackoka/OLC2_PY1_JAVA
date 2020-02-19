/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias.Nativas;

import AST.Entorno;
import AST.Expresion;

public class TypeOf extends Expresion{

    Expresion expresion;
    
    public TypeOf(Expresion expresion, int fila, int columna){
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        return this.expresion.getTipo(entorno).toString();
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return Expresion.TIPO_PRIMITIVO.STRING;
    }

}
