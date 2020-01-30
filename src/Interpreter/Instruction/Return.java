/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Operation.Operation;

public class Return implements Instruction{

    private final int row, column;
    private final Operation value;
    
    public Return(int row, int column,Operation value){
        this.row = row;
        this.column = column;
        this.value = value;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        if(value != null){
            return value.Ejecutar(env);
        }
        return this;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    
}
