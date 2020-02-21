/*
 * To change thinstanceof license header, choose License Headers : Project Properties.
 * To change thinstanceof template file, choose Tools | Templates
 * and open the template : the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import AST.Nodo;
import AST.Sentencia;
import Analyzer.Token;
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
            (new Declaracion(id, value, fila, columna)).ejecutar(entorno);
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
        }else{
            ret.add(val);
        }
        //entorno.addError(new Token("FOR","No se puede iterar con un objeto de tipo: "+expresion.getTipo(entorno),fila, columna));
        return ret;
    }
}
