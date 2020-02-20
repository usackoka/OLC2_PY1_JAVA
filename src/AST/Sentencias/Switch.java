/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Sentencia;
import java.util.LinkedList;

public class Switch extends Sentencia{

    Expresion match;
    LinkedList<Caso> listaCasos;
    Default default_;
    
    public Switch(Expresion match, LinkedList<Caso> listaCasos, Default default_, int fila, int columna){
        this.match = match;
        this.listaCasos = listaCasos;
        this.default_ = default_;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
