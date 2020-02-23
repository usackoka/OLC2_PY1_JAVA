/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import java.util.LinkedList;

public class ListArit extends Expresion{
    
    LinkedList<Object> data;
    LinkedList<Expresion> expresiones;
    
    public ListArit(LinkedList<Expresion> expresiones, Entorno entorno, int fila, int columna){
        this.expresiones = expresiones;
        this.fila = fila;
        this.columna = columna;
        this.data = new LinkedList<>();
        instanciarData(entorno);
    }
    
    public ListArit(){
        this.data = new LinkedList<>();
    }
    
    public ListArit(LinkedList<Object> data){
        this.data = data;
    }
    
    public void instanciarData(Entorno entorno){
        for(Expresion dato : this.expresiones){
            this.data.add(dato.getValor(entorno));
        }
    }

    @Override
    public Object getValor(Entorno entorno) {
        return this;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "[";
        for(Object dato : data){
            ret += dato+", ";
        }
        ret = ret.substring(0,ret.length()-2);
        ret += "]";
        return ret;
    }
    
    
}
