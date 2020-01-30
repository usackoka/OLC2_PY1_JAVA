/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.*;
import Interpreter.Symbol;

public class Nativa_aent implements Instruction {

    @Override
    public Object ejecutar(Enviorement env) {
        Symbol sym = env.getValue("arg0");
        if (sym != null) {
            Objeto ob = sym.getValue();
            if (ob.compare(Objeto.Tipo.STRING)) {
                try {
                    return new Primitivo(Objeto.Tipo.INT, Integer.parseInt(ob.toString()));
                } catch (Exception ex) {
                    return new Primitivo(Objeto.Tipo.INT, 0);
                }
            } else if (ob.compare(Objeto.Tipo.ARRAY)) {
                Arreglo arr1 = (Arreglo) ob;
                String num = "";
                Object valores = arr1.getValue();
                Objeto[] chars = (Objeto[]) valores;

                for (Objeto char1 : chars) {
                    Primitivo p = (Primitivo) char1;
                    num += p.getValue();
                }
                try {
                    return new Primitivo(Objeto.Tipo.INT, Integer.parseInt(num));
                } catch (Exception ex) {
                    return new Primitivo(Objeto.Tipo.INT, 0);
                }
            } else {

            }
        }
        return null;
    }

}
