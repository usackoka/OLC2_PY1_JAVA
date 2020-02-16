/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;
import java.util.LinkedList;

//En este caso, primitivo hace referencia a un vector de tipo primitivo
public class Primitivo extends Expresion{
    
    //por ser vector el mas primitivo se maneja un conteo de valores
    LinkedList<Object> values;
    
    public Primitivo(Object value, Object TIPO, int fila, int columna){
        this.values = new LinkedList<>();
        this.values.add(value);
        this.fila = fila;
        this.columna = columna;
        this.TIPO = TIPO;
    }

    @Override
    public Object getValor(Entorno entorno) {
        if(values.size()==1){
            return values.get(0);
        }else{
            return this.values;
        }
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return this.TIPO;
    }
    
    public void addValue(Object value, Object TIPO, Entorno entorno){
        if(!this.TIPO.equals(TIPO)){
            entorno.addError(new Token(value.toString(), "No se puede agregar el tipo: "+TIPO+" a un vector de tipo: "+this.TIPO, fila, columna));
        }else{
            this.values.add(value);
        }
    }
}