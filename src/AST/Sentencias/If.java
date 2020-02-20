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
        if(Boolean.parseBoolean(this.condicion.getValor(entorno).toString())){
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
        }else{
            //si no se cumple pregunto si hay else
            if(elseif!=null){
                return elseif.ejecutar(new Entorno(entorno));
            }
        }
        return null;
    }

}
