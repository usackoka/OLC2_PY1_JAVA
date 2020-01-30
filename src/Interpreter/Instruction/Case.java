/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Operation.Operation;
import java.util.LinkedList;

public class Case implements Instruction{
    
    private final int row, column;
    private final Operation condicion;
    private final LinkedList<Instruction> instructions;

    public Case(int row, int column, Operation condicion, LinkedList<Instruction> instructions) {
        this.row = row;
        this.column = column;
        this.condicion = condicion;
        this.instructions = instructions;
    }

    public Operation getCondicion() {
        return condicion;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Enviorement nuevo = new Enviorement(env);
        for(Instruction ins : instructions){
            Object obj = ins.ejecutar(nuevo);
            if(obj != null)
                return obj;
        }
        return null;
    }
}
