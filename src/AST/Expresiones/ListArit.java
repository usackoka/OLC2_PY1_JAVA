/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public final class ListArit extends Expresion{
    
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
    
    //PARA CUANDO LA CREO DESDE ACCESOS
    public ListArit(LinkedList<Object> data, int x, Entorno entorno, int fila, int columna){
        x = x - 1;
        if(x>=data.size() || x<0){
            entorno.addError(new Token("ListArit","IndexOutOfBounds size:"+data.size()+" index:"+(x+1),fila,columna));
            return;
        }
        this.fila = fila;
        this.columna = columna;
        this.data = new LinkedList<>();
        this.data.add(data.get(x));
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

    public LinkedList<Object> getData(){
        return this.data;
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
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
    
}
