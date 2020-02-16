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
        Object tipIzq = left.getTipo(entorno);
        Object tipDer = right.getTipo(entorno);
            
        try {
            switch (TipoOperacion)
            {
                case IGUALIGUAL:
                    return left.getValor(entorno).equals(right.getValor(entorno));
                case DIFERENTE:
                    return !left.getValor(entorno).equals(right.getValor(entorno));
                case MENOR:
                    if(tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) && tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                        return left.getValor(entorno).toString().codePoints().sum() < right.getValor(entorno).toString().codePoints().sum();
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(left.getValor(entorno))) < Double.parseDouble(String.valueOf(right.getValor(entorno)));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(left.getValor(entorno))) < Integer.parseInt(String.valueOf(right.getValor(entorno)));
                    }
                case MENORIGUAL:
                    if(tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) && tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                        return left.getValor(entorno).toString().codePoints().sum() <= right.getValor(entorno).toString().codePoints().sum();
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(left.getValor(entorno))) <= Double.parseDouble(String.valueOf(right.getValor(entorno)));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(left.getValor(entorno))) <= Integer.parseInt(String.valueOf(right.getValor(entorno)));
                    }
                case MAYOR:
                    if(tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) && tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                        return left.getValor(entorno).toString().codePoints().sum() > right.getValor(entorno).toString().codePoints().sum();
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(left.getValor(entorno))) > Double.parseDouble(String.valueOf(right.getValor(entorno)));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(left.getValor(entorno))) > Integer.parseInt(String.valueOf(right.getValor(entorno)));
                    }
                case MAYORIGUAL:
                    if(tipIzq.equals(Expresion.TIPO_PRIMITIVO.STRING) && tipDer.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                        return left.getValor(entorno).toString().codePoints().sum() >= right.getValor(entorno).toString().codePoints().sum();
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.DOUBLE) || tipDer.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                    {
                        return Double.parseDouble(String.valueOf(left.getValor(entorno))) >= Double.parseDouble(String.valueOf(right.getValor(entorno)));
                    }
                    else if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.INTEGER) && tipDer.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                    {
                        return Integer.parseInt(String.valueOf(left.getValor(entorno))) >= Integer.parseInt(String.valueOf(right.getValor(entorno)));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Relacional", "No soportado: "+TipoOperacion+" tipos:" + tipIzq + " y " + tipDer, fila, columna));
        return false;
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return Expresion.TIPO_PRIMITIVO.BOOLEAN;
    }

}
