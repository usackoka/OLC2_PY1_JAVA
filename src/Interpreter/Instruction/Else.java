/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Errorr;
import Principal.Singleton;
import java.util.LinkedList;

public class Else implements Instruction{

    private final int row, column;
    private final LinkedList<Instruction> instructions;

    public Else(int row, int column, LinkedList<Instruction> instructions) {
        this.row = row;
        this.column = column;
        this.instructions = instructions;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Enviorement nuevo = new Enviorement(env);
        for(Instruction ins : instructions){
            Object obj = ins.ejecutar(nuevo);
            if(obj instanceof Errorr){
                Singleton.getInstance().addError(this.row,this.column,"Error recuperado");
                return null;
            }
            else if(obj != null)
                return obj;
        }
        return null;
    }
}
