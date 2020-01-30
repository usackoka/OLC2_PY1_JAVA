package Interpreter.Instruction;

import Interpreter.Enviorement;
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

public class Nativa_NuevoGui implements Instruction {

    private String nombreComponente;

    public Nativa_NuevoGui() {
    }

    private Nativa_NuevoGui(String nombreComponente) {
        this.nombreComponente = nombreComponente;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        Object param1 = env.getValue("arg0").getValue();
        if (param1 instanceof RLabel) {
            RLabel rlbl = (RLabel) param1;
            rlbl.nuevoLabel();
            Label l = (Label) rlbl.getValue();
            env.getGlobal().getFrame().getVentana().getChildren().add(l);
        } else if (param1 instanceof Rtxt) {
            Rtxt rtxt = (Rtxt) param1;
            rtxt.nuevoTextfield();
            TextField tf = (TextField) rtxt.getValue();
            env.getGlobal().getFrame().getVentana().getChildren().add(tf);
        } else if (param1 instanceof RtxtA) {
            RtxtA rtxtA = (RtxtA) param1;
            rtxtA.nuevoTextarea();
            TextArea ta = (TextArea) rtxtA.getValue();
            env.getGlobal().getFrame().getVentana().getChildren().add(ta);
        } else if (param1 instanceof RtxtN) {
            RtxtN rtxtN = (RtxtN) param1;
            rtxtN.nuevoSpinner();
            Spinner sp = (Spinner) rtxtN.getValue();
            env.getGlobal().getFrame().getVentana().getChildren().add(sp);
        } else if (param1 instanceof RtxtP) {
            RtxtP rtxtP = (RtxtP) param1;
            rtxtP.nuevoPassword();
            PasswordField pf = (PasswordField) rtxtP.getValue();
            env.getGlobal().getFrame().getVentana().getChildren().add(pf);
        } else if (param1 instanceof Rboton) {
            Rboton boton = (Rboton) param1;
            boton.nuevoBoton();
            Nativa_agregarEvento nae = new Nativa_agregarEvento(boton.getNombre());
            nae.ejecutar(env);
            Button bt = (Button) boton.getValue();
            env.getGlobal().getFrame().getVentana().getChildren().add(bt);
        }
        return null;
    }

}
