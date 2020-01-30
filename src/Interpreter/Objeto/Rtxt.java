/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import javafx.scene.control.TextField;

public class Rtxt extends Objeto {

    private TextField textfield;

    public Rtxt() {
        super(Tipo.RTXT);
    }

    public void nuevoTextfield() {
        textfield = new TextField();
    }

    @Override
    public Object getValue() {
        return this.textfield;
    }

    @Override
    public void setValue(Object value) {
        this.textfield = (TextField) value;
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
        return value.getTipo() == Tipo.RTXT;
    }

}
