package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;

public abstract class Operation {
    
    private int row,column;
    
    public Operation(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public abstract Objeto Ejecutar(Enviorement env);
}
