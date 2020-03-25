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

public class Logica extends Expresion{
    Expresion left, right;
    TIPO_OPERACION TipoOperacion;
    
    public Logica(Expresion left,Expresion right, Logica.TIPO_OPERACION TipoOperacion, int fila, int columna){
        this.left = left;
        this.right = right;
        this.fila = fila;
        this.columna = columna;
        this.TipoOperacion = TipoOperacion;
    }
    
    public enum TIPO_OPERACION{
        AND,OR
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
                    Logica operacion = new Logica(pleft,pright,TipoOperacion,fila, columna);
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
                    Logica operacion = new Logica(element,pright,TipoOperacion, fila, columna);
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
                    Logica operacion = new Logica(pleft,element,TipoOperacion, fila, columna);
                    tempNew.add(operacion.getValor(entorno));
                }
                return tempNew;
            }
            
            switch (TipoOperacion)
            {
                case AND:
                    if (valLeft instanceof Boolean && valRight instanceof Boolean)
                    {
                        return Boolean.parseBoolean(String.valueOf(valLeft)) && Boolean.parseBoolean(String.valueOf(valRight));
                    }
                case OR:
                    if (valLeft instanceof Boolean && valRight instanceof Boolean)
                    {
                        return Boolean.parseBoolean(String.valueOf(valLeft)) || Boolean.parseBoolean(String.valueOf(valRight));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Logica", "No soportado: "+TipoOperacion+" tipos:" + Primitivo.getTipoDato(valLeft) + " y " + Primitivo.getTipoDato(valRight), fila, columna));
        return false;
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
