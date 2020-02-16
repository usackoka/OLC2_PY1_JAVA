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
        Object tipIzq = left.getTipo(entorno);
        Object tipDer = right.getTipo(entorno);
            
        try {
            switch (TipoOperacion)
            {
                case MAS:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) || tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING))
                    {
                        return left.getValor(entorno) + "" + right.getValor(entorno);
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        Object value = Double.parseDouble(String.valueOf(left.getValor(entorno))) + Double.parseDouble(String.valueOf(right.getValor(entorno)));
                        return value;
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(left.getValor(entorno))) + Integer.parseInt(String.valueOf(right.getValor(entorno)));
                    }
                case MENOS:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        Object value = Double.parseDouble(String.valueOf(left.getValor(entorno))) - Double.parseDouble(String.valueOf(right.getValor(entorno)));
                        return value;
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        Object value = Integer.parseInt(String.valueOf(left.getValor(entorno))) - Integer.parseInt(String.valueOf(right.getValor(entorno)));
                        return value;
                    }
                case POR:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(left.getValor(entorno))) * Double.parseDouble(String.valueOf(right.getValor(entorno)));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(left.getValor(entorno))) * Integer.parseInt(String.valueOf(right.getValor(entorno)));
                    }
                case MOD:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(left.getValor(entorno))) % Double.parseDouble(String.valueOf(right.getValor(entorno)));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(left.getValor(entorno))) % Integer.parseInt(String.valueOf(right.getValor(entorno)));
                    }
                case DIV:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(left.getValor(entorno))) / Double.parseDouble(String.valueOf(right.getValor(entorno)));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(left.getValor(entorno))) / Integer.parseInt(String.valueOf(right.getValor(entorno)));
                    }
                case POT:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Math.pow(Double.parseDouble(String.valueOf(left.getValor(entorno))), Double.parseDouble(String.valueOf(right.getValor(entorno))));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) || tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Math.pow(Double.parseDouble(String.valueOf(left.getValor(entorno))), Double.parseDouble(String.valueOf(right.getValor(entorno))));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Aritmetica", "No soportado: "+TipoOperacion+" tipos:" + tipIzq + " y " + tipDer, fila, columna));
        return "";
    }

    @Override
    public Object getTipo(Entorno entorno) {
        Object izq = left.getTipo(entorno);
        Object der = right.getTipo(entorno);
        
        try {
            switch (TipoOperacion) {
                case MAS:
                    if (izq.equals(Expresion.TIPO_PRIMITIVO.STRING) || der.equals(Expresion.TIPO_PRIMITIVO.STRING))
                    {
                        return Expresion.TIPO_PRIMITIVO.STRING;
                    }
                    else if (izq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || der.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Expresion.TIPO_PRIMITIVO.DOUBLE;
                    }
                    else if (izq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && der.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Expresion.TIPO_PRIMITIVO.INTEGER;
                    }
                case MENOS:
                case POR:
                case MOD:
                case DIV:
                    if (izq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || der.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Expresion.TIPO_PRIMITIVO.DOUBLE;
                    }
                    else if (izq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && der.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Expresion.TIPO_PRIMITIVO.INTEGER;
                    }
                case POT:
                    if (izq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || der.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Expresion.TIPO_PRIMITIVO.DOUBLE;
                    }
                    else if (izq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) || der.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Expresion.TIPO_PRIMITIVO.DOUBLE;
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Aritmetica-getTipo", "No soportado: "+TipoOperacion+" tipos: " + izq + " y " + der, fila, columna));
        return Expresion.TIPO_PRIMITIVO.STRING;
    }

}
