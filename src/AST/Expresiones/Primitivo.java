/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Nativas.Array;
import AST.Expresiones.Nativas.Matrix;
import AST.Nodo;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

//En este caso, primitivo hace referencia a un vector de tipo primitivo
public class Primitivo extends Expresion{
    
    //por ser vector el mas primitivo se maneja un conteo de valores
    LinkedList<Object> values;
    
    public Primitivo(Object value, Object TIPO, int fila, int columna){
        this.TIPO = TIPO;
        if(this.TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)){
            value = getEscapes(value.toString());
        }
        this.values = new LinkedList<>();
        this.values.add(value);
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object getValor(Entorno entorno) {
        if(values.size()==1){
            //pregunto si es una variable
            if(this.TIPO.equals(Expresion.TIPO_PRIMITIVO.VARIABLE)){
                return entorno.getValorVariable(this.values.get(0).toString().toLowerCase(), fila, columna);
            }
            return values.get(0);
        }else{
            return this.values;
        }
    }
    
    public static Object getTipoDato(Object obj){
        if(obj instanceof String){
            return Expresion.TIPO_PRIMITIVO.STRING;
        }else if(obj instanceof Double){
            return Expresion.TIPO_PRIMITIVO.DOUBLE;
        }else if(obj instanceof Boolean){
            return Expresion.TIPO_PRIMITIVO.BOOLEAN;
        }else if(obj instanceof Integer){
            return Expresion.TIPO_PRIMITIVO.INTEGER;
        }else if(obj instanceof LinkedList){
            return getTipoDato(((LinkedList)obj).get(0));
        }else if(obj instanceof Matrix){
            return Expresion.TIPO_PRIMITIVO.MATRIX;
        }else if(obj instanceof ListArit){
            return Expresion.TIPO_PRIMITIVO.LIST;
        }else if(obj instanceof Array){
            return Expresion.TIPO_PRIMITIVO.ARRAY;
        }else{
            return obj;
        }
    }
    
    public String getID(Entorno entorno){
        if(TIPO.equals(Expresion.TIPO_PRIMITIVO.VARIABLE)){
            return this.values.get(0).toString();
        }else{
            entorno.addError(new Token(TIPO.toString(), "Se esperaba un ID", fila, columna));
            return "--errorID";
        }
    }
    
    public String getEscapes(String cadena){
        return cadena.replace("\\n", "\n").replace("\\\\", "\\").replace("\\\"","\"").replace("\\t", "\t").replace("\\r", "\r");
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, this.values.get(0).toString());
        
        return cont_raiz;
    }
}