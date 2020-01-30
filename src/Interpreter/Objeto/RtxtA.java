/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import javafx.scene.control.TextArea;

public class RtxtA extends Objeto {

    private TextArea textarea;

    public RtxtA() {
        super(Tipo.RTXTA);
    }

    public void nuevoTextarea() {
        textarea = new TextArea();
    }

    @Override
    public Object getValue() {
        return this.textarea;
    }

    @Override
    public void setValue(Object value) {
        this.textarea = (TextArea) value;
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
        return value.getTipo() == Tipo.RTXTA;
    }

}
