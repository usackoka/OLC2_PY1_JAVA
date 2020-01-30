package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;
import java.awt.Dimension;

public class Nativa_DimVentana implements Instruction {

    @Override
    public Object ejecutar(Enviorement env) {
        Objeto obj = env.getValue("arg0").getValue();
        Objeto obj1 = env.getValue("arg1").getValue();
        int param1 = Integer.parseInt(obj.toString());
        int param2 = Integer.parseInt(obj1.toString());
        env.getGlobal().getFrame().getJfxPanel().setPreferredSize(new Dimension(param2, param1));
        return null;
    }
}
