/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

public class Errorr extends Objeto{

    private final int row,column;
    private final String message;
    
    public Errorr(int row, int column, String message) {
        super(Tipo.ERROR);
        this.row = row;
        this.column = column;
        this.message = message;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Objeto Equals(Objeto compare, int fila, int columna) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Objeto NotEquals(Objeto compare, int fila, int columna) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public String toString(){
        return this.message + " - " + this.row + " - " + this.column + "\n";
    }

    @Override
    public boolean compare(Objeto value) {
        return false;
    }
}
