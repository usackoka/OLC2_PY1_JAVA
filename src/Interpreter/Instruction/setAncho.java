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

public class setAncho implements Instruction {

    private Objeto ancho;
    private Object componente;

    public setAncho(Object componente, Objeto ancho) {
        this.componente = componente;
        this.ancho = ancho;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        Object param1 = ancho.getValue();
        if (componente instanceof RLabel
                || componente instanceof Rtxt
                || componente instanceof RtxtA
                || componente instanceof RtxtN
                || componente instanceof RtxtP
                || componente instanceof Rboton) {
            Control control = (Control) ((Objeto) componente).getValue();
            control.setPrefWidth(Integer.parseInt(param1.toString()));
        }
        return null;
    }
}
