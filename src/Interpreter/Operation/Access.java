/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Instruction.FusionDef;
import Interpreter.Objeto.*;
import Interpreter.Symbol;
import Principal.Singleton;
import javafx.scene.control.Button;

public class Access extends Operation {

    private final Operation left;
    public final String id;

    public Access(int row, int column, Operation left, String id) {
        super(row, column);
        this.left = left;
        this.id = id;
    }

    @Override
    public Objeto Ejecutar(Enviorement env) {
        if (left == null) {
            Symbol val = env.getValue(id);
            if (val == null) {
                FusionDef fs = env.GetFusion(id);
                if (fs == null) {
                    return Singleton.getInstance().addError(getRow(), getColumn(), "ValuesException: no existe la variable / constante : " + this.id);
                }
                return new Primitivo(Objeto.Tipo.INT, fs.getAtributos().size());
            }
            if (val.getValue().getTipo() == Objeto.Tipo.RBTON && val.getValue() != null) {
                ((Rboton) val.getValue()).setNombreBoton(id);
            }
            return val.getValue();
        } else {
            Objeto anterior = left.Ejecutar(env);
            if (anterior.isError()) {
                return anterior;
            } else if (anterior.compare(Objeto.Tipo.FUSION)) {
                if (((Fusion) anterior).getTs() == null) {
                    return Singleton.getInstance().addError(new Errorr(getRow(), getColumn(), "NullPointerException"));
                }
                Symbol val = ((Fusion) anterior).getTs().getValue(id);
                if (val == null) {
                    return Singleton.getInstance().addError(new Errorr(getRow(), getColumn(), "ValuesException: no existe la variable / constante : " + this.id));
                }
                return val.getValue();
            }
        }
        return null;
    }

}
