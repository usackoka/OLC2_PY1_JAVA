/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;

public class Unaria extends Expresion{

    Expresion unaria;
    TIPO_OPERACION tipoOperacion;
    
    public Unaria(Expresion unaria, TIPO_OPERACION tipoOperacion, int fila, int columna){
        this.unaria = unaria;
        this.fila = fila;
        this.columna = columna;
        this.tipoOperacion = tipoOperacion;
    }
    
    public enum TIPO_OPERACION{
        MENOS,NOT
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object def = 0;
        Object tipo = unaria.getTipo(entorno);
        switch (tipoOperacion) {
            case MENOS:
                if (tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                {
                    return - Integer.parseInt(unaria.getValor(entorno).toString());
                }
                else if (tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                {
                    return - Double.parseDouble(unaria.getValor(entorno).toString());
                }
            case NOT:
                if (tipo.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN))
                {
                    return !Boolean.parseBoolean(unaria.getValor(entorno).toString());
                }
                def = false;
        }
        
        entorno.addError(new Token("Unaria", "No soportado" + tipoOperacion + " con: "+tipo, fila, columna));
        return def;
    }

    @Override
    public Object getTipo(Entorno entorno) {
        Object tipo = unaria.getTipo(entorno);
        switch (tipoOperacion) {
            case MENOS:
                if (tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER))
                {
                    return Expresion.TIPO_PRIMITIVO.INTEGER;
                }
                else if (tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))
                {
                    return Expresion.TIPO_PRIMITIVO.DOUBLE;
                }
            case NOT:
                if (tipo.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN))
                {
                    return Expresion.TIPO_PRIMITIVO.BOOLEAN;
                }
        }
        
        entorno.addError(new Token("Unaria-getTipo", "No soportado" + tipoOperacion + " con: "+tipo, fila, columna));
        return Expresion.TIPO_PRIMITIVO.STRING;
    }

}
