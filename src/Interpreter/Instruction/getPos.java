package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Arreglo;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.Primitivo;
import Interpreter.Objeto.RLabel;
import Interpreter.Objeto.Rboton;
import Interpreter.Objeto.Rtxt;
import Interpreter.Objeto.RtxtA;
import Interpreter.Objeto.RtxtN;
import Interpreter.Objeto.RtxtP;
import javafx.scene.control.Control;

public class getPos implements Instruction {

    private Object componente;

    public getPos(Object componente) {
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
            Arreglo arreglo = new Arreglo(new Primitivo(Objeto.Tipo.INT, null));
            arreglo.newArray(2);
            arreglo.addValue(new Primitivo(Objeto.Tipo.INT, (int) control.getLayoutX()), 0);
            arreglo.addValue(new Primitivo(Objeto.Tipo.INT, (int) control.getLayoutY()), 1);
            return arreglo;
        }
        return null;
    }
}
