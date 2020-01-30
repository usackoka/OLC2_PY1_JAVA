/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.New;
import Interpreter.Objeto.Objeto;

public class Nativa_reservar implements Instruction{

    @Override
    public Object ejecutar(Enviorement env) {
        Objeto obj = env.getValue("arg0").getValue();
        return new New(Integer.parseInt(obj.toString()));
    }
}
