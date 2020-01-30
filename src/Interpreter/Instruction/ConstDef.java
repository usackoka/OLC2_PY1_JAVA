/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;
import Interpreter.Operation.Operation;
import Principal.Singleton;

public class ConstDef implements Instruction{

    private final int row,column;
    private final String id;
    private final Operation value;

    public ConstDef(int row, int column, String id, Operation value) {
        this.row = row;
        this.column = column;
        this.id = id;
        this.value = value;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Objeto valor = this.value.Ejecutar(env);
        if(valor.getTipo() == Objeto.Tipo.NULL){
            Singleton.getInstance().addError(row, column, "NullPointerException: El valor de retorno fue nulo");
            return null;
        }
        else if(valor.getTipo() == Objeto.Tipo.ERROR){
            Singleton.getInstance().addError(row, column, "Error recuperado");
            return null;
        }
        valor.setIsconst(true);
        if(!env.add(id, valor)){
            Singleton.getInstance().addError(row,column,"DuplicateException: Ya existe una variable / constante con el identificador " + this.id);
        }
        return null;
    }
    
}
