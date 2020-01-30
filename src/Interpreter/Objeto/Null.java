/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

public class Null extends Objeto{

    public Null() {
        super(Tipo.NULL);
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Objeto Equals(Objeto compare, int fila, int columna) {
        if(compare.compare(Tipo.NULL)){
            return new Primitivo(Tipo.BOOLEAN,true);            
        }
        else if(compare.compare(Tipo.FUSION)){
            Fusion f = (Fusion)compare;
            return new Primitivo(Tipo.BOOLEAN,f.getTs() == null);
        }
        return new Errorr(fila,columna,"Tipos diferentes");
    }

    @Override
    public Objeto NotEquals(Objeto compare, int fila, int columna) {
        if(compare.compare(Tipo.NULL)){
            return new Primitivo(Tipo.BOOLEAN,false);            
        }
        else if(compare.compare(Tipo.FUSION)){
            Fusion f = (Fusion)compare;
            return new Primitivo(Tipo.BOOLEAN,f.getTs() != null);
        }
        return new Errorr(fila,columna,"Tipos diferentes");
    }

    @Override
    public boolean compare(Objeto value) {
        return false;
    }
    
}
