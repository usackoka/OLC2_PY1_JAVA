/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;
import Principal.Singleton;
import java.util.LinkedList;

public class Function implements Instruction{

    private final String id;
    private final LinkedList<Instruction> instruction;
    private final LinkedList<Parameter> parameters;
    private final int row,column;
    private final boolean ismetodo;
    private final Objeto tipo;

    public Function(String id, LinkedList<Parameter> parameters, LinkedList<Instruction> instruction, Objeto tipo, int row, int column,boolean ismetodo) {
        this.id = id;
        this.instruction = instruction;
        this.row = row;
        this.column = column;
        this.parameters = parameters;
        this.ismetodo = ismetodo;
        this.tipo = tipo;
    }
    
      public String getStringParametros(){
          String cad ="(";
        for (Parameter par : this.parameters) {
            try {
                cad+=par.getId()+":"+par.getTipo().getTipo()+" , ";
            }
            catch (Exception e){
            }
            cad+="";
        }
        return cad+")";
    }
    

    public String getId() {
        return id;
    }
    
    public Objeto getTipo(){
        return this.tipo;
    }

    public LinkedList<Instruction> getInstruction() {
        return instruction;
    }

    public LinkedList<Parameter> getParameters() {
        return parameters;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isIsmetodo() {
        return ismetodo;
    }
    
    public boolean compare(Function f){
        if(this.parameters.size() == f.parameters.size()){
            if(this.id.equals("_pesode") && this.parameters.size() == 1)
                return false;
            for(int i = 0; i < this.parameters.size(); i++){
                Parameter param1 = this.parameters.get(i);
                if(!param1.compare(f.parameters.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean compare(LinkedList<Objeto> params){
        if(this.parameters.size() == params.size()){
            if(this.parameters.size() == 1 && this.id.equals("_pesode"))
                return true;
            for(int i = 0; i < this.parameters.size(); i++){
                Parameter param1 = this.parameters.get(i);
                if(!param1.compare(params.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
        public boolean compareSobre(LinkedList<Objeto> params){
        if(this.parameters.size() == params.size()){
            if(this.parameters.size() == 1 && this.id.equals("_pesode"))
                return true;
            for(int i = 0; i < this.parameters.size(); i++){
                Parameter param1 = this.parameters.get(i);
                if(!param1.compareSobre(params.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        if(!env.AddFunction(this,this.id)){
            Singleton.getInstance().addError(row, column, "Ya existe la sobrecarga de : " + this.id);
        }
        return null;
    }    
}
