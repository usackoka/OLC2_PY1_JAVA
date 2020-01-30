package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.RLabel;
import Interpreter.Objeto.Rboton;
import Interpreter.Objeto.Rtxt;
import Interpreter.Objeto.RtxtA;
import Interpreter.Objeto.RtxtN;
import Interpreter.Objeto.RtxtP;
import javafx.scene.control.Control;

public class setPos implements Instruction {

    private Objeto x;
    private Objeto y;
    private Object componente;

    public setPos(Object componente, Objeto x, Objeto y) {
        this.componente = componente;
        this.x = x;
        this.y = y;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        Object param1 = x.getValue();
        Object param2 = y.getValue();
        if (componente instanceof RLabel
                || componente instanceof Rtxt
                || componente instanceof RtxtA
                || componente instanceof RtxtN
                || componente instanceof RtxtP
                || componente instanceof Rboton) {
            Control control = (Control) ((Objeto) componente).getValue();
            control.setLayoutX(Integer.parseInt(param1.toString()));
            control.setLayoutY(Integer.parseInt(param2.toString()));
        }
        return null;
    }
}
