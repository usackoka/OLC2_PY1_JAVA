/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Arreglo;
import Interpreter.Objeto.Errorr;
import Interpreter.Objeto.Frame;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.Primitivo;
import Interpreter.Objeto.Rboton;
import Interpreter.Operation.AccessFunction;
import Interpreter.Symbol;
import Principal.Singleton;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javax.swing.JDialog;

/**
 *
 * @author Pavel
 */
public class Nativa_agregarEvento implements Instruction {

    private String idBoton;

    public Nativa_agregarEvento(String idBoton) {
        this.idBoton = idBoton;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        LinkedList<Objeto> list = new LinkedList<>();
        String nombreMetodo = "__al_dar_click_" + idBoton;
        Function f = env.getFunction(nombreMetodo, list);
        if (f != null) {
            Symbol simbolo = env.getValue(idBoton);
            if (simbolo.getValue().getTipo() == Objeto.Tipo.RBTON) {
                Rboton rboton = ((Rboton) ((Objeto) simbolo.getValue()));
                Button bt = (Button) rboton.getValue();
                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Enviorement nueva = new Enviorement(env.getGlobal());
                        for (Instruction ins : f.getInstruction()) {
                            Object obj = ins.ejecutar(nueva);
                        }
                    }
                });
            }
        }
        return null;
    }
}
