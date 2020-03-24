/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import AST.Expresiones.Variable;
import AST.Nodo;
import AST.Sentencia;
import GraficasArit.Graph_AST;

public class Declaracion extends Sentencia{
    
    Expresion value;
    String id;
    public Declaracion(String id, Expresion value, int fila, int columna){
        this.id = id;
        this.value = value;
        this.fila = fila;
        this.columna = columna;
        this.valorDirecto = null;
    }
    
    //declaracion especial para cuando la creo desde código 
    Object valorDirecto;
    public Declaracion(String id, Object value, int fila, int columna){
        this.id = id;
        this.valorDirecto = value;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        Object valor = this.valorDirecto==null?value.getValor(entorno):this.valorDirecto;
        Object tipo = Primitivo.getTipoDato(valor);
        Variable var = new Variable(valor,tipo,fila,columna);
        entorno.addVariable(id.toLowerCase(), var);
        return null;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "ASIGNACION");
        
        int cont_hijo = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_hijo, id+"=");
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        cont_hijo = value.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        return cont_raiz;
    }

}
