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
import javafx.scene.text.Font;

public class setAlto implements Instruction {

    private Objeto ancho;
    private Object componente;

    public setAlto(Object componente, Objeto ancho) {
        this.componente = componente;
        this.ancho = ancho;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        Object param1 = ancho.getValue();
        if (componente instanceof RLabel) {
            RLabel rlbl = (RLabel) componente;
            Label l = (Label) rlbl.getValue();
            l.setPrefHeight(Integer.parseInt(param1.toString()));
            l.setFont(new Font("Calibri Light", l.getPrefHeight()));
        } else if (componente instanceof Rtxt) {
            Rtxt rtxt = (Rtxt) componente;
            TextField tf = (TextField) rtxt.getValue();
            tf.setPrefHeight(Integer.parseInt(param1.toString()));
        } else if (componente instanceof RtxtA) {
            RtxtA rtxtA = (RtxtA) componente;
            TextArea ta = (TextArea) rtxtA.getValue();
            ta.setPrefHeight(Integer.parseInt(param1.toString()));
        } else if (componente instanceof RtxtN) {
            RtxtN rtxtN = (RtxtN) componente;
            Spinner sp = (Spinner) rtxtN.getValue();
            sp.setPrefHeight(Integer.parseInt(param1.toString()));
        } else if (componente instanceof RtxtP) {
            RtxtP rtxtP = (RtxtP) componente;
            PasswordField pf = (PasswordField) rtxtP.getValue();
            pf.setPrefHeight(Integer.parseInt(param1.toString()));
        } else if (componente instanceof Rboton) {
            Rboton boton = (Rboton) componente;
            Button bt = (Button) boton.getValue();
            bt.setPrefHeight(Integer.parseInt(param1.toString()));
        }
        return null;
    }
}
