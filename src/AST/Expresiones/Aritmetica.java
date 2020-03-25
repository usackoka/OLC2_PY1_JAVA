/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Aritmetica extends Expresion{
    
    Expresion left, right;
    TIPO_OPERACION TipoOperacion;
    
    public Aritmetica(Expresion left,Expresion right, TIPO_OPERACION TipoOperacion, int fila, int columna){
        this.left = left;
        this.right = right;
        this.fila = fila;
        this.columna = columna;
        this.TipoOperacion = TipoOperacion;
    }
    
    public enum TIPO_OPERACION{
        MAS, MENOS, POR, DIV, POT, MOD
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object valLeft = left.getValor(entorno);
        Object valRight = right.getValor(entorno);
            
        try {
            //========== VALIDO OPERACIONES ENTRE VECTORES
            if(valLeft instanceof LinkedList && valRight instanceof LinkedList){
                LinkedList<Object> tempLeft = (LinkedList)valLeft;
                LinkedList<Object> tempRight = (LinkedList)valRight;
                LinkedList<Object> tempNew = new LinkedList<>();
                if(tempLeft.size()!=tempRight.size()){
                    entorno.addError(new Token("OPERACION-"+TipoOperacion+" entre vectores", "Los vectores no poseen el mismo tamaño", fila, columna));
                    return new LinkedList<>();
                }
                for(int i=0;i<tempLeft.size();i++){
                    Primitivo pleft = new Primitivo(tempLeft.get(i), Primitivo.getTipoDato(tempLeft.get(i)),fila,columna);
                    Primitivo pright = new Primitivo(tempRight.get(i), Primitivo.getTipoDato(tempRight.get(i)),fila,columna);
                    Aritmetica operacion = new Aritmetica(pleft,pright,TipoOperacion,fila, columna);
                    tempNew.add(operacion.getValor(entorno));
                }
                return tempNew;
            }
            else if(valLeft instanceof LinkedList){
                LinkedList<Object> temp = (LinkedList)valLeft;
                LinkedList<Object> tempNew = new LinkedList<>();
                for(int i = 0; i<temp.size(); i++){
                    Primitivo element = new Primitivo(temp.get(i), Primitivo.getTipoDato(temp.get(i)),fila,columna);
                    Primitivo pright = new Primitivo(valRight, Primitivo.getTipoDato(valRight), fila, columna);
                    Aritmetica operacion = new Aritmetica(element,pright,TipoOperacion, fila, columna);
                    tempNew.add(operacion.getValor(entorno));
                }
                return tempNew;
            }
            else if(valRight instanceof LinkedList){
                LinkedList<Object> temp = (LinkedList)valRight;
                LinkedList<Object> tempNew = new LinkedList<>();
                for(int i = 0; i<temp.size(); i++){
                    Primitivo element = new Primitivo(temp.get(i), Primitivo.getTipoDato(temp.get(i)),fila,columna);
                    Primitivo pleft = new Primitivo(valLeft, Primitivo.getTipoDato(valLeft), fila, columna);
                    Aritmetica operacion = new Aritmetica(pleft,element,TipoOperacion, fila, columna);
                    tempNew.add(operacion.getValor(entorno));
                }
                return tempNew;
            }
            
            switch (TipoOperacion)
            {
                case MAS:
                    if (valLeft instanceof String || valRight instanceof String)
                    {
                        return valLeft + "" + valRight;
                    }
                    else if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        Object value = Double.parseDouble(String.valueOf(valLeft)) + Double.parseDouble(String.valueOf(valRight));
                        return value;
                    }
                    else if ((valLeft instanceof Integer) && (valRight instanceof Integer))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) + Integer.parseInt(String.valueOf(valRight));
                    }
                case MENOS:
                    if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        Object value = Double.parseDouble(String.valueOf(valLeft)) - Double.parseDouble(String.valueOf(valRight));
                        return value;
                    }
                    else if (valLeft instanceof Integer && valRight instanceof Integer)
                    {
                        Object value = Integer.parseInt(String.valueOf(valLeft)) - Integer.parseInt(String.valueOf(valRight));
                        return value;
                    }
                case POR:
                    if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) * Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (valLeft instanceof Integer && valRight instanceof Integer)
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) * Integer.parseInt(String.valueOf(valRight));
                    }
                case MOD:
                    if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) % Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (valLeft instanceof Integer && valRight instanceof Integer)
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) % Integer.parseInt(String.valueOf(valRight));
                    }
                case DIV:
                    if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) / Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (valLeft instanceof Integer && valRight instanceof Integer)
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) / Integer.parseInt(String.valueOf(valRight));
                    }
                case POT:
                    if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        return Math.pow(Double.parseDouble(String.valueOf(valLeft)), Double.parseDouble(String.valueOf(valRight)));
                    }
                    else if (valLeft instanceof Integer || valRight instanceof Integer)
                    {
                        return (int)Math.pow(Integer.parseInt(String.valueOf(valLeft)), Integer.parseInt(String.valueOf(valRight)));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Aritmetica", "No soportado: "+TipoOperacion+" tipos:" + Primitivo.getTipoDato(valLeft) + " y " + Primitivo.getTipoDato(valRight), fila, columna));
        return "";
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, TipoOperacion.toString());
        
        int cont_hijo = left.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        cont_hijo = right.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        return cont_raiz;
    }
}
