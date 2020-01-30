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

public class For implements Instruction{

    private final int row, column;
    private final Instruction initialization,update;
    private final Operation condicion;
    private final LinkedList<Instruction> instructions;

    public For(int row, int column, Instruction initialization, Instruction update, Operation condicion, LinkedList<Instruction> instructions) {
        this.row = row;
        this.column = column;
        this.initialization = initialization;
        this.update = update;
        this.condicion = condicion;
        this.instructions = instructions;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Enviorement nuevo = new Enviorement(env);
        Object obj = initialization.ejecutar(nuevo);
        if(obj instanceof Errorr){
            Singleton.getInstance().addError(row,column,"Error recuperado");
            return null;
        }
        Objeto value = condicion.Ejecutar(nuevo);
        
        while(value.getTipo() == Objeto.Tipo.BOOLEAN && (boolean)value.getValue()){
            Enviorement interno = new Enviorement(nuevo);
            for(Instruction ins : instructions){
                obj = ins.ejecutar(interno);
                if(obj instanceof Errorr){
                    Singleton.getInstance().addError(this.row,this.column,"Error recuperado");
                    return null;
                }
                else if(obj instanceof Return || obj instanceof Objeto)
                    return obj;
                else if(obj instanceof Break)
                    return null;
                else if(obj instanceof Continue)
                    break;
            }
            obj = update.ejecutar(nuevo);
            if(obj instanceof Errorr){
                Singleton.getInstance().addError(row,column,"Error recuperado");
                return null;
            }
            value = this.condicion.Ejecutar(nuevo);
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
