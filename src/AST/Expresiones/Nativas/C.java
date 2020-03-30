/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class C extends Expresion{
    LinkedList<Expresion> expresiones;
    
    public C(LinkedList<Expresion> expresiones, int fila, int columna){
        this.expresiones = expresiones;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        VectorArit data = new VectorArit();
        for (int i = 0; i < this.expresiones.size(); i++) {
            Expresion expresion = this.expresiones.get(i);
            Object value = expresion.getValor(entorno);
            if(value instanceof VectorArit){
                for(Object element : (VectorArit)value){
                    data.add(element);
                }
                continue;
            }
            data.add(value);
        }
        return data.castearA(entorno);
    }
}
