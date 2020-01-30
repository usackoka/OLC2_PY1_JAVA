package Interpreter.Instruction;

import Interpreter.Enviorement;

public interface Instruction {
    
    public abstract Object ejecutar(Enviorement env);
}
