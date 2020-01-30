/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import javafx.scene.control.Spinner;

public class RtxtN extends Objeto {

    private Spinner<Integer> spinner;

    public RtxtN() {
        super(Tipo.RTXTN);
    }

    public void nuevoSpinner() {
        spinner = new Spinner<>(-10000000,10000000,0);
        spinner.setEditable(true);
    }

    @Override
    public Object getValue() {
        return this.spinner;
    }

    @Override
    public void setValue(Object value) {
        this.spinner = (Spinner) value;
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
        return value.getTipo() == Tipo.RTXTN;
    }

}
