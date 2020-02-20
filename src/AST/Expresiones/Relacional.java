/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;

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
        Object tipIzq = Primitivo.getTipoDato(valLeft);
        Object tipDer = Primitivo.getTipoDato(valRight);
            
        try {
            switch (TipoOperacion)
            {
                case IGUALIGUAL:
                    return valLeft.equals(valRight);
                case DIFERENTE:
                    return !valLeft.equals(valRight);
                case MENOR:
                    if(tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) && tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                        return valLeft.toString().codePoints().sum() < valRight.toString().codePoints().sum();
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) < Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) < Integer.parseInt(String.valueOf(valRight));
                    }
                case MENORIGUAL:
                    if(tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) && tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                        return valLeft.toString().codePoints().sum() <= valRight.toString().codePoints().sum();
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) <= Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) <= Integer.parseInt(String.valueOf(valRight));
                    }
                case MAYOR:
                    if(tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) && tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                        return valLeft.toString().codePoints().sum() > valRight.toString().codePoints().sum();
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) > Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) > Integer.parseInt(String.valueOf(valRight));
                    }
                case MAYORIGUAL:
                    if(tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) && tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                        return valLeft.toString().codePoints().sum() >= valRight.toString().codePoints().sum();
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(valLeft)) >= Double.parseDouble(String.valueOf(valRight));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(valLeft)) >= Integer.parseInt(String.valueOf(valRight));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Relacional", "No soportado: "+TipoOperacion+" tipos:" + tipIzq + " y " + tipDer, fila, columna));
        return false;
    }
}
