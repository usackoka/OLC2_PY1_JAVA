/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;

public class Ternaria extends Expresion{

    Expresion condicion, verdadera, falsa;
    
    public Ternaria(Expresion condicion, Expresion verdadera, Expresion falsa, int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.condicion = condicion;
        this.verdadera = verdadera;
        this.falsa = falsa;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        if(Boolean.parseBoolean(condicion.getValor(entorno).toString())){
            return verdadera.getValor(entorno);
        }
        return falsa.getValor(entorno);
    }
}
