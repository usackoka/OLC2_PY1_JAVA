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
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wf implements Instruction{
    private final int row,column;
    private final LinkedList<Operation> print;

    public Wf(int row, int column, LinkedList<Operation> print) {
        this.row = row;
        this.column = column;
        this.print = print;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        BufferedWriter bf = Singleton.getInstance().getBuffer(row,column);
        if(bf == null){
            return null;
        }
        for(Operation op : print){
            Objeto obj = op.Ejecutar(env);
            try {
                bf.write(obj.toString());
            } catch (IOException ex) {
                Logger.getLogger(Wf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    
}
