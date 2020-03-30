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
import AST.Expresiones.Nativas.VectorArit;
import Analyzer.Token;
import GraficasArit.Graph_AST;

//En este caso, primitivo hace referencia a un vector de tipo primitivo
public final class Primitivo extends Expresion{
    
    //por ser vector el mas primitivo se maneja un conteo de valores
    VectorArit values;
    
    public Primitivo(Object value, Object TIPO, int fila, int columna){
        this.TIPO = TIPO;
        this.values = new VectorArit();
        
        if(value instanceof VectorArit){
            this.values = (VectorArit)value;
        }else{
            if(this.TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                value = getEscapes(value.toString());
            }
            this.values.add(value);
        }
        
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
        }
        return this.values;
    }
    
    public Variable getVariable(Entorno entorno){
        if(values.size()==1){
            //pregunto si es una variable
            if(this.TIPO.equals(Expresion.TIPO_PRIMITIVO.VARIABLE)){
                return entorno.getVariable(this.values.get(0).toString().toLowerCase(), fila, columna);
            }
        }
        entorno.addError(new Token("Primitivo-getVariable", "No se encontró la variable: "+this.values.get(0).toString(), fila, columna));
        return new Variable(null, TIPO, fila, columna);
    }
    
    public Object getPos(int index, Entorno entorno){
        index = index - 1;
        
        VectorArit values = new VectorArit();
        
        if(this.TIPO.equals(Expresion.TIPO_PRIMITIVO.VARIABLE)){
            Object Ovalues = entorno.getValorVariable(this.values.get(0).toString().toLowerCase(), fila, columna);
            if(Ovalues instanceof VectorArit){
                values = (VectorArit)Ovalues;
            }else{
                values.add(Ovalues);
            }
        }else{
            values = this.values;
        }
        
        if(index>=values.size() || index<0){
            entorno.addError(new Token("Vector-Primitivo","IndexOutOfBounds size:"+values.size()+" index:"+(index+1),fila,columna));
            return 0;
        }
        
        return values.get(index);
    }
    
    public static Object getTipoDato(Object obj){
        if(obj instanceof String){
            return Expresion.TIPO_PRIMITIVO.STRING;
        }else if(obj instanceof Double){
            return Expresion.TIPO_PRIMITIVO.NUMERIC;
        }else if(obj instanceof Boolean){
            return Expresion.TIPO_PRIMITIVO.BOOLEAN;
        }else if(obj instanceof Integer){
            return Expresion.TIPO_PRIMITIVO.INTEGER;
        }else if(obj instanceof VectorArit){
            return getTipoDato(((VectorArit)obj).get(0));
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