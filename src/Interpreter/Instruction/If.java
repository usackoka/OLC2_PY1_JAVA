/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Errorr;
import Interpreter.Objeto.Objeto;
import Interpreter.Operation.Operation;
import Principal.Singleton;
import java.util.LinkedList;

public class If implements Instruction{

    private final int fila, columna;
    private final Operation condicion;
    private final LinkedList<Instruction> instructions;

    public If(int fila, int columna, Operation condicion, LinkedList<Instruction> instructions) {
        this.fila = fila;
        this.columna = columna;
        this.condicion = condicion;
        this.instructions = instructions;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Objeto value = this.condicion.Ejecutar(env);
        if(value.getTipo() == Objeto.Tipo.BOOLEAN && (boolean)value.getValue()){
            Enviorement nuevo = new Enviorement(env);
            for(Instruction ins : instructions){
                Object obj = ins.ejecutar(nuevo);
                if(obj instanceof Errorr){
                    Singleton.getInstance().addError(this.fila,this.columna,"Error recuperado");
                    return null;
                }
                else if(obj != null)
                    return obj;
            }
        }
        if(value.getTipo() == Objeto.Tipo.NULL){
            Singleton.getInstance().addError(new Errorr(this.fila, this.columna, "NullPointerException: la condicion fue nula"));
        }
        else if(value.getTipo() != Objeto.Tipo.BOOLEAN){
            Singleton.getInstance().addError(this.fila,this.columna,"TypeException: El tipo de la condicion debe ser booleano y se encontro " + value.getTipo());
        }
        return null;
    }
}
