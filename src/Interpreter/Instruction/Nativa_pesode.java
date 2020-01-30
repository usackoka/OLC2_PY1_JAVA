/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Arreglo;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.Primitivo;
import Interpreter.Objeto.RString;

public class Nativa_pesode implements Instruction {

    @Override
    public Object ejecutar(Enviorement env) {
        Objeto obj = env.getValue("arg0").getValue();
        if (obj.compare(Objeto.Tipo.INT)) {
            return obj;
        } else if (obj.compare(Objeto.Tipo.RSTRING)) {
            return ((RString) obj).getValue().toString().length();
        } else if (obj.compare(Objeto.Tipo.ARRAY)) {
            return new Primitivo(Objeto.Tipo.INT, ((Arreglo) obj).getSize());
        } else {
            //TODO... retorna size
        }
        return null;
    }
}
