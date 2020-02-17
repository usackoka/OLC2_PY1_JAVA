/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;

public class Unaria extends Expresion{

    Expresion unaria;
    
    public Unaria(Expresion unaria, int fila, int columna){
        this.unaria = unaria;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        return null;
    }

    @Override
    public Object getTipo(Entorno entorno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
