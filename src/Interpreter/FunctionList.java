/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter;

import Interpreter.Instruction.Function;
import Interpreter.Objeto.Objeto;
import java.util.LinkedList;

public class FunctionList {
    private final String id;
    private final LinkedList<Function> functionlist;

    public FunctionList(String id) {
        this.id = id;
        this.functionlist = new LinkedList<>();
    }    

    public LinkedList<Function> getFunctionlist() {
        return functionlist;
    }

    public String getId() {
        return id;
    }
    
    public void addFunction(Function f){
        functionlist.add(f);
    }
    
    public boolean sobreCarga(Function f){
        if (!functionlist.stream().noneMatch((actual) -> (actual.compare(f)))) {
            return false;
        }
        functionlist.add(f);
        return true;
    }
    
    public Function getFunction(LinkedList<Objeto> params){
        for(Function actual : functionlist){
            if(actual.compare(params)){
                return actual;
            }
        }
        for(Function actual : functionlist){
            if(actual.compareSobre(params))
                return actual;
        }
        return null;
    }
}
