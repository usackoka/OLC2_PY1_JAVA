/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;
import Interpreter.Operation.Operation;
import Principal.Singleton;
import java.util.LinkedList;

public class Switch implements Instruction{

    private final int row,column;
    private final Operation condicion;
    private final LinkedList<Instruction> caselist;
    private final Instruction defaultcase;

    public Switch(int row, int column, Operation condicion, LinkedList<Instruction> caselist, Instruction defaultcase) {
        this.row = row;
        this.column = column;
        this.condicion = condicion;
        this.caselist = caselist;
        this.defaultcase = defaultcase;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Objeto condicion = this.condicion.Ejecutar(env);
        boolean execdefault = true;
        for(Instruction ins : caselist ){
            Case caso = (Case)ins;
            Objeto condicioncase = condicion.Equals(caso.getCondicion().Ejecutar(env),row,column);
            if(condicioncase.isError()){
                Singleton.getInstance().addError(row, column, "Error recuperado");
                return null;                
            }
            else if(condicion.compare(Objeto.Tipo.BOOLEAN) && (boolean)condicion.getValue()){
                Object obj = caso.ejecutar(env);
                if(obj instanceof Objeto || obj instanceof Continue || obj instanceof Return)
                    return obj;
                else if(obj instanceof Break)
                    return null;
                execdefault = false;
            }
        }
        if(execdefault && defaultcase != null){
            return defaultcase.ejecutar(env);
        }
        return null;
    }
    
}
