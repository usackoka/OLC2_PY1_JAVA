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
import java.util.LinkedList;

public class Default extends Sentencia{

    LinkedList<Nodo> instrucciones;
    
    public Default(LinkedList<Nodo> instrucciones, int fila, int columna){
        this.instrucciones = instrucciones;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        for (Nodo nodo : this.instrucciones)
        {
            if (nodo instanceof Sentencia)
            {
                Object val = ((Sentencia)nodo).ejecutar(new Entorno(entorno));
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
        return null;
    }

}
