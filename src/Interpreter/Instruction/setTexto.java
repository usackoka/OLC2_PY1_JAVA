package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.RLabel;
import Interpreter.Objeto.Rboton;
import Interpreter.Objeto.Rtxt;
import Interpreter.Objeto.RtxtA;
import Interpreter.Objeto.RtxtN;
import Interpreter.Objeto.RtxtP;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class setTexto implements Instruction {

    private Objeto texto;
    private Object componente;

    public setTexto(Object componente, Objeto texto) {
        this.componente = componente;
        this.texto = texto;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        Object param1 = texto.getValue();
        if (componente instanceof RLabel) {
            RLabel rlbl = (RLabel) componente;
            Label l = (Label) rlbl.getValue();
            l.setText(param1.toString());
        } else if (componente instanceof Rtxt) {
            Rtxt rtxt = (Rtxt) componente;
            TextField tf = (TextField) rtxt.getValue();
            tf.setText(param1.toString());
        } else if (componente instanceof RtxtA) {
            RtxtA rtxtA = (RtxtA) componente;
            TextArea ta = (TextArea) rtxtA.getValue();
            ta.setText(param1.toString());
        } else if (componente instanceof RtxtN) {
            RtxtN rtxtN = (RtxtN) componente;
            Spinner sp = (Spinner) rtxtN.getValue();
            try {
                sp.getValueFactory().setValue(Integer.parseInt(param1.toString()));
            } catch (Exception e) {
                sp.getValueFactory().setValue(0);
            }
        } else if (componente instanceof RtxtP) {
            RtxtP rtxtP = (RtxtP) componente;
            PasswordField pf = (PasswordField) rtxtP.getValue();
            pf.setText(param1.toString());
        } else if (componente instanceof Rboton) {
            Rboton boton = (Rboton) componente;
            Button bt = (Button) boton.getValue();
            bt.setText(param1.toString());
        }
        return null;
    }
}
