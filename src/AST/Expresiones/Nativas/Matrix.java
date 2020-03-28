/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Matrix extends Expresion{

    Expresion dataExpresion, filasExpresion, columnasExpresion;
    LinkedList<LinkedList<Object>> data;
    
    public Matrix(Expresion dataExpresion, Expresion filaExpresion, Expresion columnasExpresion, Entorno entorno, int fila, int columna){
        this.dataExpresion = dataExpresion;
        this.filasExpresion = filaExpresion;
        this.columnasExpresion = columnasExpresion;
        this.fila = fila;
        this.columna = columna;
        this.data = new LinkedList<>();
        
        instanciarData(entorno);
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
    
    public Matrix(){
        this.data = new LinkedList<>();
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        return this;
    }
    
    public LinkedList<Object> getMapeo(){
        LinkedList<Object> mapeo = new LinkedList<>();
        int col = getNCol();
        for(int i = 0; i<col; i++){
            for(LinkedList list : this.data){
                mapeo.add(list.get(i));
            }
        }
        
        return mapeo;
    }
    
    public void instanciarData(Entorno entorno){
        //valido los parámetros
        Object valorFilas = filasExpresion.getValor(entorno);
        Object tipoFilas = Primitivo.getTipoDato(valorFilas);
        Object valorColumnas = columnasExpresion.getValor(entorno);
        Object tipoColumnas = Primitivo.getTipoDato(valorColumnas);
        
        if(!tipoFilas.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
            entorno.addError(new Token("Vector["+tipoFilas+"]", "No se puede utilizar como Expresion de Filas este tipo de vector", fila, columna));
            return;
        }
        if(!tipoColumnas.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
            entorno.addError(new Token("Vector["+tipoColumnas+"]", "No se puede utilizar como Expresion de Columnas este tipo de vector", fila, columna));
            return;
        }
        
        Object valores = dataExpresion.getValor(entorno);
        int filas = (int)valorFilas;
        int columnas = (int)valorColumnas;
        
        //le creo las linkedList de filas
        for(int i=0; i<filas; i++){
            this.data.add(new LinkedList<>());
        }
        
        //VERIFICAR AQUÍ QUE SEA UN CLON EL QUE ESTOY PASANDO Y NO LA LISTA COMO TAL
        //hago el llenado por columnas
        int element = 0;
        if(valores instanceof LinkedList){
            LinkedList<Object> elements = (LinkedList)valores;
            for(int i=0; i<columnas; i++){
                for (int j = 0; j < filas; j++) {
                    //el index del valor a obtener
                    if(element>=elements.size()){
                        element = 0;
                    }
                    data.get(j).add(elements.get(element++));
                }
            }
        }else{
            for(int i=0; i<columnas; i++){
                for (int j = 0; j < filas; j++) {
                    data.get(j).add(valores);
                }
            }
        }
    }
    
    public int getNRow(){
        return this.data.size();
    }
    
    public int getNCol(){
        return this.data.isEmpty()?0:this.data.get(0).size();
    }

    @Override
    public String toString() {
        String ret = "";
        for(LinkedList<Object> row : this.data){
            ret += "[";
            for(Object dato : row){
                ret += dato+", ";
            }
            ret = ret.substring(0, ret.length()-2);
            ret += "]";
            ret += "\n";
        }
        return ret;
    }
    
    

}
