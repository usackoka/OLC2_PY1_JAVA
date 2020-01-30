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

public class Nativa_atxt implements Instruction{
    
    @Override
    public Object ejecutar(Enviorement env) {
        Objeto obj = env.getValue("arg0").getValue();
        String value = obj.toString();
        Arreglo arr = new Arreglo(new Primitivo(Objeto.Tipo.CHAR,null));
        arr.newArray(value.length());
        for(int i = 0; i < value.length(); i++){
            arr.addValue(new Primitivo(Objeto.Tipo.CHAR,value.charAt(i)), i);
        }
        return arr;
    }
}
