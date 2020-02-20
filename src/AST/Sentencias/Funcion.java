/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Parametro;
import AST.Expresiones.Primitivo;
import AST.Nodo;
import AST.Sentencia;
import Analyzer.Token;
import java.util.LinkedList;

public class Funcion extends Sentencia{

    public String id;
    LinkedList<Parametro> parametros;
    LinkedList<Nodo> instrucciones;
    public LinkedList<Object> valoresParametros;
    
    public Funcion(String id, LinkedList<Parametro> parametros, LinkedList<Nodo> instrucciones, int fila, int columna){
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.fila = fila;
        this.columna = columna;
        this.valoresParametros = new LinkedList<>();
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        //creo los parametros
        crearParametros(entorno);
        
        for (Nodo nodo : this.instrucciones)
        {
            if (nodo instanceof Sentencia)
            {
                Object val = ((Sentencia)nodo).ejecutar(new Entorno(entorno));
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
        return null;
    }
    
    public void crearParametros(Entorno entorno){
        int i = 0;
        for(Parametro parametro : parametros){
            Object value = valoresParametros.get(i++);
            Object tipo = Primitivo.getTipoDato(value);
            //si mandan un default
            if(value.equals(Expresion.TIPO_PRIMITIVO.DEFAULT)){
                //preegunto si hay un valor por defecto
                if(parametro.id != null){
                    //si el id es null es porque viene un id
                    (new Declaracion(parametro.id, value, tipo, fila, columna)).ejecutar(entorno);
                }else{
                    entorno.addError(new Token("Parametro-Default-Funcion("+this.id+")", "No se puede enviar un valor default en este parámetro: "+i, fila, columna));
                }
            }else{
                (new Declaracion(((Primitivo)parametro.expresion).getID(entorno), value, tipo, fila, columna)).ejecutar(entorno);
            }
        }
    }
    
    public int getConteoParametros(){
        return this.parametros.size();
    }

}
