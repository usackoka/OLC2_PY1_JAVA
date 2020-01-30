/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import javafx.scene.control.Label;

public class RLabel extends Objeto{

    private Label label;
    
    public RLabel(){
        super(Tipo.RLABEL);
    }
    
    public void nuevoLabel(){
        label = new Label();
    }
    
    @Override
    public Object getValue() {
        return this.label;
    }

    @Override
    public void setValue(Object value) {
        this.label = (Label)value;
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
    public boolean compare(Objeto value) {
        return value.getTipo() == Tipo.RLABEL;
    }
}
