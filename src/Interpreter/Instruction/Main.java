/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import java.util.LinkedList;

public class Main implements Instruction{

    private final int row,column;
    private final LinkedList<Instruction> instructions;

    public Main(int row, int column, LinkedList<Instruction> instructions) {
        this.row = row;
        this.column = column;
        this.instructions = instructions;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Enviorement nuevo = new Enviorement(env);
        instructions.forEach((ins) -> {
            ins.ejecutar(nuevo);
        });
        return null;
    }
    
}
