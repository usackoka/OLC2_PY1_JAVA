/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Sentencia;

public class Return extends Sentencia{

    Expresion expresion;
    
    public Return(Expresion expresion, int fila, int columna){
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        return this.expresion==null?null:expresion.getValor(entorno);
    }

}
