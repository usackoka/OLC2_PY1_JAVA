/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;

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
        Object tipIzq = Primitivo.getTipoDato(valLeft);
        Object tipDer = Primitivo.getTipoDato(valRight);
            
        try {
            switch (TipoOperacion)
            {
                case MAS:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) || tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING))
                    {
                        return valLeft + "" + valRight;
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        Object value = Double.parseDouble(String.valueOf(valLeft)) + Double.parseDouble(String.valueOf(valRight));
                        return value;
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) + Integer.parseInt(String.valueOf(valRight));
                    }
                case MENOS:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        Object value = Double.parseDouble(String.valueOf(valLeft)) - Double.parseDouble(String.valueOf(valRight));
                        return value;
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        Object value = Integer.parseInt(String.valueOf(valLeft)) - Integer.parseInt(String.valueOf(valRight));
                        return value;
                    }
                case POR:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) * Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) * Integer.parseInt(String.valueOf(valRight));
                    }
                case MOD:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) % Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) % Integer.parseInt(String.valueOf(valRight));
                    }
                case DIV:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) / Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) / Integer.parseInt(String.valueOf(valRight));
                    }
                case POT:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Math.pow(Double.parseDouble(String.valueOf(valLeft)), Double.parseDouble(String.valueOf(valRight)));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) || tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return (int)Math.pow(Integer.parseInt(String.valueOf(valLeft)), Integer.parseInt(String.valueOf(valRight)));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Aritmetica", "No soportado: "+TipoOperacion+" tipos:" + tipIzq + " y " + tipDer, fila, columna));
        return "";
    }
}
