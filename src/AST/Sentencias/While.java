/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Nodo;
import AST.Sentencia;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class While extends Sentencia{
    
    Expresion condicion;
    LinkedList<Nodo> instrucciones;
    
    public While(Expresion condicion, LinkedList<Nodo> instrucciones, int fila, int columna){
        this.instrucciones = instrucciones;
        this.condicion = condicion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        
        //valido la condición
        boolean valCondicion = false;
        Object err = this.condicion.getValor(entorno);
        try {
            //validacion para cuando viene un vector de varios booleans
            if(err instanceof LinkedList){
                err = ((LinkedList)err).get(0);
            }
            valCondicion = (Boolean)err;
        } catch (Exception e) {
            entorno.addError(new Token("Condicion-IF","Error al convertir en boolean: "+err.getClass(), fila, columna));
        }
        
        while (valCondicion)
        {
            entorno = new Entorno(entorno);
            for (Nodo nodo : this.instrucciones)
            {
                if (nodo instanceof Sentencia)
                {
                    Object val = ((Sentencia)nodo).ejecutar(entorno);
                    if (val != null)
                    {
                        //pregunto si es un continue
                        if (val.equals(Corte.TIPO_CORTE.CONTINUE))
                        {
                            break;
                        }
                        else if (val.equals(Corte.TIPO_CORTE.BREAK))
                        {
                            return null;
                        }
                        return val;
                    }
                }
                else
                {
                    ((Expresion)nodo).getValor(entorno);
                }
            }
            
            //vuelvo a ejecutar la condicion
            valCondicion = false;
            err = this.condicion.getValor(entorno);
            try {
                valCondicion = (Boolean)err;
            } catch (Exception e) {
                entorno.addError(new Token("Condicion-IF","Error al convertir en boolean: "+err.getClass(), fila, columna));
            }
        }
        return null;
    }

    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "While");
        
        for(Nodo nodo:this.instrucciones){
            int cont_hijo = nodo.Recorrido(arbol);
            arbol.addRelacion(cont_raiz,cont_hijo);
        }
        
        return cont_raiz;
    }
}
