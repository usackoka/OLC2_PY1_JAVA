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

public class While implements Instruction{

    private final int row, column;
    private final Operation condicion;
    private final LinkedList<Instruction> instructions;

    public While(int row, int column, Operation condicion, LinkedList<Instruction> instructions) {
        this.row = row;
        this.column = column;
        this.condicion = condicion;
        this.instructions = instructions;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Objeto value = this.condicion.Ejecutar(env);
        while(value.getTipo() == Objeto.Tipo.BOOLEAN && (boolean)value.getValue()){
            Enviorement nuevo = new Enviorement(env);
            for(Instruction ins : instructions){
                Object obj = ins.ejecutar(nuevo);
                if(obj instanceof Errorr){
                    Singleton.getInstance().addError(this.row,this.column,"Error recuperado");
                    return null;
                }
                else if(obj instanceof Objeto || obj instanceof Return)
                    return obj;
                else if(obj instanceof Break)
                    return null;
                else if(obj instanceof Continue)
                    break;
            }
            value = this.condicion.Ejecutar(env);
        }
        
        if(value.getTipo() == Objeto.Tipo.NULL){
            Singleton.getInstance().addError(new Errorr(this.row, this.column, "NullPointerException: la condicion fue nula"));
        }
        else if(value.getTipo() != Objeto.Tipo.BOOLEAN){
            Singleton.getInstance().addError(this.row,this.column,"TypeException: El tipo de la condicion debe ser booleano y se encontro " + value.getTipo());
        }
        return null;
    }
    
}
