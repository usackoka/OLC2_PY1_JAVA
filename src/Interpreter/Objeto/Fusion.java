/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import Interpreter.Enviorement;

public class Fusion extends Objeto{

    private final String id;
    private Enviorement ts;
    
    public Fusion(String id, Enviorement ts) {
        super(Tipo.FUSION);
        this.id = id;
        this.ts = ts;
    }

    @Override
    public Object getValue() {
        return this;
    }

    @Override
    public void setValue(Object value) {
        this.ts = ((Fusion)value).ts;
    }

    @Override
    public Objeto Equals(Objeto compare, int fila, int columna) {
        if(compare.compare(Tipo.NULL)){
            boolean value = this.ts == null;
            return new Primitivo(Tipo.BOOLEAN,value);            
        }
        else if(compare.compare(Tipo.FUSION)){
            boolean value = this.equals(compare);
            return new Primitivo(Tipo.BOOLEAN,value);
        }
        return new Errorr(fila,columna,"Tipos diferentes");
    }

    @Override
    public Objeto NotEquals(Objeto compare, int fila, int columna) {
        if(compare.compare(Tipo.NULL)){
            return new Primitivo(Tipo.BOOLEAN,this.ts != null);            
        }
        else if(compare.compare(Tipo.FUSION)){
            return new Primitivo(Tipo.BOOLEAN,!this.equals(compare));
        }
        return new Errorr(fila,columna,"Tipos diferentes");
    }

    @Override
    public boolean compare(Objeto value) {
        if(value.compare(Tipo.FUSION)){
            Fusion f = (Fusion)value;
            return this.id.equals(f.id);
        }
        return false;
    }

    public Enviorement getTs() {
        return ts;
    }

    public void setTs(Enviorement ts) {
        this.ts = ts;
    }

    public String getId() {
        return id;
    }
}
