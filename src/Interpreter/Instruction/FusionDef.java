/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Principal.Singleton;
import java.util.LinkedList;

public class FusionDef implements Instruction{
    
    private final String id;
    private final LinkedList<Parameter> atributos;
    private final int row, column;

    public FusionDef(String id, LinkedList<Parameter> atributos, int row, int column) {
        this.atributos = atributos;
        this.id = id;
        this.row = row;
        this.column = column;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        if(!env.AddFusion(this, id)){
            Singleton.getInstance().addError(row, column, "FusionException: Ya existe la fusion con el identificador: " + this.id);
        }
        return null;
    }
    
    public LinkedList<Parameter> getAtributos() {
        return atributos;
    }
}
