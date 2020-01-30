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

public class Nativa_eqls implements Instruction{

    @Override
    public Object ejecutar(Enviorement env) {
        Arreglo arr1 = (Arreglo)env.getValue("arg0").getValue();
        Arreglo arr2 = (Arreglo)env.getValue("arg1").getValue();
        if(arr1.getSize() == arr2.getSize()){
            return new Primitivo(Objeto.Tipo.BOOLEAN,arr1.comparar(arr2));
        }
        return new Primitivo(Objeto.Tipo.BOOLEAN,false);
    }
}
