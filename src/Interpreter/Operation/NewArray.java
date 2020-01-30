/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.Arreglo;
import Interpreter.Objeto.Objeto;
import java.util.LinkedList;

public class NewArray extends Operation{

    private final LinkedList<Operation> values;
    
    public NewArray(int row, int column, LinkedList<Operation> values){
        super(row,column);
        this.values = values;
    }
    
    @Override
    public Objeto Ejecutar(Enviorement env) {
        Objeto obj = values.get(0).Ejecutar(env);
        if(obj.isError())
            return obj;
        Arreglo arreglo = new Arreglo(obj);
        arreglo.newArray(values.size());
        arreglo.addValue(obj, 0);
        for(int i = 1; i < values.size(); i++){
            Objeto actual = values.get(i).Ejecutar(env);
            if(actual.isError())
                return actual;
            arreglo.addValue(actual, i);
        }
        return arreglo;
    }
}
