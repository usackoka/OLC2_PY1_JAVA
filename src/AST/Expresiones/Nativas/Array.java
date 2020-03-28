/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.ListArit;
import AST.Expresiones.Primitivo;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Array extends Expresion{

    Expresion expresion;
    Expresion vector;
    LinkedList<Object> data;
    
    public Array(Expresion expresion, Expresion vector, Entorno entorno, int fila, int columna){
        this.expresion = expresion;
        this.vector = vector;
        this.fila = fila;
        this.columna = columna;
        this.data = new LinkedList<>();
        //valores a ingresar
        this.valoresIngresar = new LinkedList<>();
        
        instanciarData(entorno);
    }
    
    public Array(){
        this.data = new LinkedList<>();
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        return this;
    }
    
    public void instanciarData(Entorno entorno){
        Object valorExpresion = expresion.getValor(entorno);
        Object valorVector = vector.getValor(entorno);
        
        //valido que el parámetro sea un vector de integer
        if(valorVector instanceof Matrix || valorVector instanceof ListArit || valorVector instanceof Array){
            entorno.addError(new Token("Instancia Array-"+Primitivo.getTipoDato(valorVector).toString(), "No se puede utilizar como vector este tipo de dato", fila, columna));
            return;
        }
        
        //saco las dimensiones en una lista
        LinkedList<Integer> dimensiones = new LinkedList<>();
        if(valorVector instanceof LinkedList){
            LinkedList elements = (LinkedList)valorVector;
            for(Object element : elements){
                try {
                    dimensiones.add((int)element);
                } catch (Exception e) {
                    entorno.addError(new Token("Instancia Array-"+Primitivo.getTipoDato(valorVector).toString(), "Se esperaba un valor integer para las dimensiones", fila, columna));
                    return;
                }
            }
        }else{
            try {
                dimensiones.add((int)valorVector);
            } catch (Exception e) {
                entorno.addError(new Token("Instancia Array-"+Primitivo.getTipoDato(valorVector).toString(), "Se esperaba un valor integer para las dimensiones", fila, columna));
                return;
            }
        }
        
        if(valorExpresion instanceof LinkedList){
            LinkedList elements = (LinkedList)valorExpresion;
            for(Object element : elements){
                valoresIngresar.add(element);
            }
        }else if(valorExpresion instanceof ListArit){
            ListArit elements = (ListArit)valorExpresion;
            for(Object element : elements.getData()){
                valoresIngresar.add(element);
            }
        }else{
            valoresIngresar.add(valorExpresion);
        }
        
        fillData(this.data, dimensiones.size()-1, dimensiones);
    }
    
    public LinkedList<Object> fillData(LinkedList<Object> lista, int indexDim, LinkedList<Integer> dimensiones){
        int limite = dimensiones.get(indexDim);
        if(indexDim==0){//ultima iteracion
            for(int i = 0; i<limite; i++){
                lista.add(getDato());
            }
        }else{
            //debo crear listas
            for(int i = 0; i<limite; i++){
                lista.add(fillData(new LinkedList<>(), indexDim-1, dimensiones));
            }
        }
        return lista;
    }
    
    LinkedList<Object> valoresIngresar;
    int cont = 0;
    public Object getDato(){
        if(cont>=valoresIngresar.size()){
            cont = 0;
        }
        return valoresIngresar.get(cont++);
    }
    
    public LinkedList<Object> getData(){
        return this.data;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
}
