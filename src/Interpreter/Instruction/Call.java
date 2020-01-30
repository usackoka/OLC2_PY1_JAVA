/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Operation.Operation;

public class Call implements Instruction{

    private final Operation llamada;
    private final int row, column;

    public Call(Operation llamada, int row, int column) {
        this.llamada = llamada;
        this.row = row;
        this.column = column;
    }
    
    
    @Override
    public Object ejecutar(Enviorement env) {
        llamada.Ejecutar(env);
        return null;
    }
}
