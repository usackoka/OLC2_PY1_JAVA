/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Objeto.*;

public class Parameter {
    private final String id;
    private final Objeto tipo;
    private final int row, column;
    
    public Parameter(String id, Objeto tipo, int row, int column){
        this.id = id;
        this.tipo = tipo;
        this.row = row;
        this.column = column;
    }

    public String getId() {
        return id;
    }

    public Objeto getTipo() {
        return tipo;
    }
    
    public boolean compare(Parameter param){
        return this.tipo.compareFunc(param.tipo);
    }
    
    public boolean compare(Objeto ob){
        if(this.tipo.compare(ob.getTipo())){
            if(this.tipo.compare(Objeto.Tipo.ARRAY) || this.tipo.compare(Objeto.Tipo.FUSION))
                return this.tipo.compare(ob);
            return true;
        }
        return false;
    }
    
    public boolean compareSobre(Objeto ob){
        return this.tipo.compare(ob);
    }
}
