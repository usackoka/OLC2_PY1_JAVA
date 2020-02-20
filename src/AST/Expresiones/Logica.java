/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;

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
        Object tipIzq = Primitivo.getTipoDato(valLeft);
        Object tipDer = Primitivo.getTipoDato(valRight);
            
        try {
            switch (TipoOperacion)
            {
                case AND:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN) && tipDer.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN))
                    {
                        return Boolean.parseBoolean(String.valueOf(valLeft)) && Boolean.parseBoolean(String.valueOf(valRight));
                    }
                case OR:
                    if (tipIzq.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN) && tipDer.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN))
                    {
                        return Boolean.parseBoolean(String.valueOf(valLeft)) || Boolean.parseBoolean(String.valueOf(valRight));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Logica", "No soportado: "+TipoOperacion+" tipos:" + tipIzq + " y " + tipDer, fila, columna));
        return false;
    }
}
