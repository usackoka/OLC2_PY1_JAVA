/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RtxtP extends Objeto {

    private PasswordField password;

    public RtxtP() {
        super(Tipo.RTXTP);
    }

    public void nuevoPassword() {
        password = new PasswordField();
    }

    @Override
    public Object getValue() {
        return this.password;
    }

    @Override
    public void setValue(Object value) {
        this.password = (PasswordField) value;
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
        return value.getTipo() == Tipo.RTXTP;
    }

}
