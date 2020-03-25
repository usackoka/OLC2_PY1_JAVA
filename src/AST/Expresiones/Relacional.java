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

public class Relacional extends Expresion{

    Expresion left, right;
    TIPO_OPERACION TipoOperacion;
    
    public Relacional(Expresion left,Expresion right, TIPO_OPERACION TipoOperacion, int fila, int columna){
        this.left = left;
        this.right = right;
        this.fila = fila;
        this.columna = columna;
        this.TipoOperacion = TipoOperacion;
    }
    
    public enum TIPO_OPERACION{
        MENOR, MENORIGUAL, MAYOR, MAYORIGUAL, IGUALIGUAL, DIFERENTE
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object valLeft = left.getValor(entorno);
        Object valRight = right.getValor(entorno);
            
        try {
            switch (TipoOperacion)
            {
                case IGUALIGUAL:
                    return valLeft.equals(valRight);
                case DIFERENTE:
                    return !valLeft.equals(valRight);
                case MENOR:
                    if(valLeft instanceof String && valRight instanceof String){
                        return valLeft.toString().codePoints().sum() < valRight.toString().codePoints().sum();
                    }
                    else if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) < Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (valLeft instanceof Integer && valRight instanceof Integer)
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) < Integer.parseInt(String.valueOf(valRight));
                    }
                case MENORIGUAL:
                    if(valLeft instanceof String && valRight instanceof String){
                        return valLeft.toString().codePoints().sum() <= valRight.toString().codePoints().sum();
                    }
                    else if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) <= Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (valLeft instanceof Integer && valRight instanceof Integer)
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) <= Integer.parseInt(String.valueOf(valRight));
                    }
                case MAYOR:
                    if(valLeft instanceof String && valRight instanceof String){
                        return valLeft.toString().codePoints().sum() > valRight.toString().codePoints().sum();
                    }
                    else if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) > Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (valLeft instanceof Integer && valRight instanceof Integer)
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) > Integer.parseInt(String.valueOf(valRight));
                    }
                case MAYORIGUAL:
                    if(valLeft instanceof String && valRight instanceof String){
                        return valLeft.toString().codePoints().sum() >= valRight.toString().codePoints().sum();
                    }
                    else if (valLeft instanceof Double || valRight instanceof Double)
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) >= Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (valLeft instanceof Integer && valRight instanceof Integer)
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) >= Integer.parseInt(String.valueOf(valRight));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Relacional", "No soportado: "+TipoOperacion+" tipos:" + Primitivo.getTipoDato(valLeft) + " y " + Primitivo.getTipoDato(valRight), fila, columna));
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
