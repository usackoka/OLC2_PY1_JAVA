/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Nativas.Matrix;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Unaria extends Expresion{

    Expresion unaria;
    TIPO_OPERACION TipoOperacion;
    
    public Unaria(Expresion unaria, TIPO_OPERACION TipoOperacion, int fila, int columna){
        this.unaria = unaria;
        this.fila = fila;
        this.columna = columna;
        this.TipoOperacion = TipoOperacion;
    }
    
    public enum TIPO_OPERACION{
        MENOS,NOT
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object def = 0;
        Object val = unaria.getValor(entorno);
        Object tipo = Primitivo.getTipoDato(val);
        
        //========== VALIDO OPERACIONES ENTRE VECTORES
            if(val instanceof LinkedList){
                LinkedList<Object> temp = (LinkedList)val;
                LinkedList<Object> tempNew = new LinkedList<>();
                for(int i = 0; i<temp.size(); i++){
                    Primitivo element = new Primitivo(temp.get(i), Primitivo.getTipoDato(temp.get(i)),fila,columna);
                    Unaria operacion = new Unaria(element,TipoOperacion, fila, columna);
                    tempNew.add(operacion.getValor(entorno));
                }
                return tempNew;
            }
            else if(val instanceof Matrix){
                Matrix tempLeft = (Matrix)val;
                Matrix tempNew = new Matrix(tempLeft.getNRow(),tempLeft.getNCol());
                for(int i=0; i<tempLeft.getNRow(); i++){
                    for(int j=0; j<tempLeft.getNCol(); j++){
                        Primitivo pleft = new Primitivo(tempLeft.getValorIndex(i+1, j+1, entorno), Primitivo.getTipoDato(tempLeft.getValorIndex(i+1, j+1, entorno)),fila,columna);
                        Unaria operacion = new Unaria(pleft,TipoOperacion,fila, columna);
                        tempNew.setValor(i+1,j+1,operacion.getValor(entorno),entorno);
                    }
                }
                return tempNew;
            }
        
        switch (TipoOperacion) {
            case MENOS:
                if (tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                {
                    return - Integer.parseInt(val.toString());
                }
                else if (tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                {
                    return - Double.parseDouble(val.toString());
                }
            case NOT:
                if (tipo.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN))
                {
                    return !Boolean.parseBoolean(val.toString());
                }
                def = false;
        }
        
        entorno.addError(new Token("Unaria", "No soportado" + TipoOperacion + " con: "+tipo, fila, columna));
        return def;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, this.TipoOperacion.toString());
        
        int cont_hijo = this.unaria.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        return cont_raiz;
    }
}
