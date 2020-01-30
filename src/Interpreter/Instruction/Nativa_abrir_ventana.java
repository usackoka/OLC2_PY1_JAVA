package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Arreglo;
import Interpreter.Objeto.Frame;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.Primitivo;
import Interpreter.Operation.AccessFunction;
import java.util.LinkedList;
import javafx.scene.Scene;
import javax.swing.JDialog;

public class Nativa_abrir_ventana implements Instruction {

    @Override
    public Object ejecutar(Enviorement env) {
        Arreglo arr1 = (Arreglo) env.getValue("arg0").getValue();
        String nombreFuncion = "";
        Object valores = arr1.getValue();
        Objeto[] chars = (Objeto[]) valores;

        for (Objeto char1 : chars) {
            Primitivo p = (Primitivo) char1;
            nombreFuncion += p.getValue();
        }
        Frame frame = new Frame(nombreFuncion);
        env.getGlobal().setFrame(frame);

        AccessFunction af = new AccessFunction(0, 0, null, nombreFuncion, new LinkedList<>());
        JDialog window = new JDialog();
        af.Ejecutar(env);
        env.getGlobal().getFrame().getVentana().setStyle("-fx-background-color:red;");
        Scene s = new Scene(env.getGlobal().getFrame().getVentana());
        env.getGlobal().getFrame().getJfxPanel().setScene(s);
        window.add(env.getGlobal().getFrame().getJfxPanel());
        window.pack();
        window.setModal(true);
        window.setVisible(true);
        return null;
    }
}
