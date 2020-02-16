/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Variable;
import AST.Sentencia;

public class Declaracion extends Sentencia{
    
    Expresion value;
    String id;
    public Declaracion(String id, Expresion value, int fila, int columna){
        this.id = id;
        this.value = value;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        Variable var = new Variable(value.getValor(entorno), value.getTipo(entorno),fila,columna);
        entorno.addVariable(id.toLowerCase(), var);
        return null;
    }

}
