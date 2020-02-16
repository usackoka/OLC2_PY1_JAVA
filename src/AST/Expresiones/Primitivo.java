/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import java.util.LinkedList;

//En este caso, primitivo hace referencia a un vector de tipo primitivo
public class Primitivo extends Expresion{
    
    //por ser vector el mas primitivo se maneja un conteo de valores
    LinkedList<Object> values;
    
    public Primitivo(Object value, Expresion.TIPO_PRIMITIVO TIPO, int fila, int columna){
        this.values = new LinkedList<>();
        this.values.add(value);
        this.fila = fila;
        this.columna = columna;
        this.TIPO = TIPO;
    }

    @Override
    public Object getValor(Entorno entorno) {
        return this.values;
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return this.TIPO;
    }
}