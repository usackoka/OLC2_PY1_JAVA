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

public class If extends Sentencia{
    
    Expresion condicion;
    LinkedList<Nodo> instrucciones;
    Sentencia elseif;
    
    public If(Expresion condicion, LinkedList<Nodo> instrucciones, Sentencia elseif ,int fila, int columna){
        this.condicion = condicion;
        this.instrucciones = instrucciones;
        this.fila = fila;
        this.columna = columna;
        this.elseif = elseif;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        //si se cumple la condicion ejecuto las acciones
        
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
        
        if(valCondicion){
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
        }else{
            //si no se cumple pregunto si hay else
            if(elseif!=null){
                entorno = new Entorno(entorno);
                return elseif.ejecutar(entorno);
            }
        }
        return null;
    }

    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "If");
        
        for(Nodo nodo:this.instrucciones){
            int cont_hijo = nodo.Recorrido(arbol);
            arbol.addRelacion(cont_raiz,cont_hijo);
        }
        
        return cont_raiz;
    }

}
