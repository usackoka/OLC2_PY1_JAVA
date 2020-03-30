/*
 * To change thinstanceof license header, choose License Headers : Project Properties.
 * To change thinstanceof template file, choose Tools | Templates
 * and open the template : the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.ListArit;
import AST.Expresiones.Nativas.Array;
import AST.Expresiones.Nativas.Matrix;
import AST.Nodo;
import AST.Sentencia;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class For extends Sentencia{
    
    Expresion expresion;
    LinkedList<Nodo> instrucciones;
    String id;
    
    public For(String id, Expresion expresion, LinkedList<Nodo> instrucciones, int fila, int columna){
        this.id = id;
        this.instrucciones = instrucciones;
        this.fila = fila;
        this.columna = columna;
        this.expresion = expresion;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        entorno = new Entorno(entorno);
        LinkedList<Object> valores = getValores(entorno);
        for (Object value : valores)
        {
            //declaro la variable
            (new Declaracion(id, value, false, fila, columna)).ejecutar(entorno);
            for (Nodo nodo : this.instrucciones)
            {
                if (nodo instanceof Sentencia)
                {
                    Object val = ((Sentencia)nodo).ejecutar(entorno);
                    if (val != null)
                    {
                        return val;
                    }
                }
                else
                {
                    ((Expresion)nodo).getValor(entorno);
                }
            }
        }
        return null;
    }
    
    public LinkedList<Object> getValores(Entorno entorno){
        //obtengo el valor
        LinkedList<Object> ret = new LinkedList<>();
        Object val = expresion.getValor(entorno);
        if(val instanceof LinkedList){
            return (LinkedList<Object>)val;
        }else if(val instanceof ListArit){
            return ((ListArit)val).getData();
        }else if(val instanceof Matrix){
            return ((Matrix)val).getMapeo();
        }else if(val instanceof Array){
            return ((Array)val).getData();
        }else{
            ret.add(val);
        }
        //entorno.addError(new Token("FOR","No se puede iterar con un objeto de tipo: "+expresion.getTipo(entorno),fila, columna));
        return ret;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "For");
        
        for(Nodo nodo:this.instrucciones){
            int cont_hijo = nodo.Recorrido(arbol);
            arbol.addRelacion(cont_raiz,cont_hijo);
        }
        
        return cont_raiz;
    }
}
