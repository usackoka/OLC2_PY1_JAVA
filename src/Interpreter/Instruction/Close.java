/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Principal.Singleton;

public class Close implements Instruction{

    private final int row, column;

    public Close(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Singleton.getInstance().closeBuffer(row, column);
        return null;
    }
    
}
