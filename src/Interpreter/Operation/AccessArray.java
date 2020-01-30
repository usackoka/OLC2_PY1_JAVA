/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.Arreglo;
import Interpreter.Objeto.Objeto;
import Principal.Singleton;

public class AccessArray extends Operation{
    
    private final Operation left;
    private final Operation access;
    
    public AccessArray(int row, int column, Operation left, Operation access) {
        super(row, column);
        this.left = left;
        this.access = access;
    }

    @Override
    public Objeto Ejecutar(Enviorement env) {
        Objeto obj = left.Ejecutar(env);
        Objeto index = access.Ejecutar(env);
        if(obj.isError())
            return obj;
        if(index.isError())
            return index;
        if(!obj.compare(Objeto.Tipo.ARRAY))
            return Singleton.getInstance().addError(getRow(), getColumn(), "Acceso no aplicable para el tipo: " + obj.getTipo());
        if(!(index.compare(Objeto.Tipo.INT) || index.compare(Objeto.Tipo.CHAR)))
            return Singleton.getInstance().addError(getRow(), getColumn(), "Index incorrecto");
        Arreglo arr = (Arreglo)obj;
        return arr.getValue(Integer.parseInt(index.toString()), getRow(), getColumn());
    }
    
}
