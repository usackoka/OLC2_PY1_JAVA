package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.Primitivo;
import Interpreter.Objeto.RLabel;
import Interpreter.Objeto.Rboton;
import Interpreter.Objeto.Rtxt;
import Interpreter.Objeto.RtxtA;
import Interpreter.Objeto.RtxtN;
import Interpreter.Objeto.RtxtP;
import javafx.scene.control.Control;

public class getAlto implements Instruction {

    private Object componente;

    public getAlto(Object componente) {
        this.componente = componente;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        if (componente instanceof RLabel
                || componente instanceof Rtxt
                || componente instanceof RtxtA
                || componente instanceof RtxtN
                || componente instanceof RtxtP
                || componente instanceof Rboton) {
            Control control = (Control) ((Objeto) componente).getValue();
            return new Primitivo(Objeto.Tipo.INT, (int) control.getPrefHeight());
        }
        return null;
    }
}
