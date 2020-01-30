package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.RLabel;
import Interpreter.Objeto.RString;
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

public class getTexto implements Instruction {

    private Object componente;

    public getTexto(Object componente) {
        this.componente = componente;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        if (componente instanceof RLabel) {
            RLabel rlbl = (RLabel) componente;
            Label l = (Label) rlbl.getValue();
            RString r = new RString(l.getText());
            return r;
        } else if (componente instanceof Rtxt) {
            Rtxt rtxt = (Rtxt) componente;
            TextField tf = (TextField) rtxt.getValue();
            RString r = new RString(tf.getText());
            return r;
        } else if (componente instanceof RtxtA) {
            RtxtA rtxtA = (RtxtA) componente;
            TextArea ta = (TextArea) rtxtA.getValue();
            RString r = new RString(ta.getText());
            return r;
        } else if (componente instanceof RtxtN) {
            RtxtN rtxtN = (RtxtN) componente;
            Spinner sp = (Spinner) rtxtN.getValue();
            RString r = new RString(sp.getValueFactory().getValue() + "");
            return r;
        } else if (componente instanceof RtxtP) {
            RtxtP rtxtP = (RtxtP) componente;
            PasswordField pf = (PasswordField) rtxtP.getValue();
            RString r = new RString(pf.getText());
            return r;
        } else if (componente instanceof Rboton) {
            Rboton rboton = (Rboton) componente;
            Button boton = (Button) rboton.getValue();
            RString r = new RString(boton.getText());
            return r;
        }
        return null;
    }
}
