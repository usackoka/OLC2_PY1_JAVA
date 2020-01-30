/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Fusion;
import Interpreter.Objeto.New;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.Primitivo;
import Interpreter.Operation.Operation;
import Principal.Singleton;

public class Assignment implements Instruction{

    private final int row, column;
    private final Operation id;
    private final Operation value;

    public Assignment(int row, int column, Operation id, Operation value) {
        this.row = row;
        this.column = column;
        this.id = id;
        this.value = value;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Objeto access = id.Ejecutar(env);

        if(access.isError())
            return access;
        if(value == null)
            return null;

        Objeto valor = value.Ejecutar(env);        
        if(valor.isError())
            return valor;

        if(access.isIsconst()){
            Singleton.getInstance().addError(row,column,"ConstantException: No se puede modificar el valor de una constante");
            return null;
        }
        if(valor instanceof Primitivo)
            valor = ((Primitivo)valor).nuevo();
        
        if(access.compare(valor)){
            if(!access.compare(Objeto.Tipo.ARRAY))
                access.setValue(valor.getValue());
        }
        else if(access.compare(Objeto.Tipo.FUSION) && valor.compare(Objeto.Tipo.NUEVO)){
            Fusion f = (Fusion)access;
            New n = (New)valor;
            FusionDef fdef = env.GetFusion(f.getId());
            if(fdef == null){
                Singleton.getInstance().addError(row, column, "FusionException: no existe la fusion " + f.getId());
            }
            if(fdef.getAtributos().size() == n.getSize()){
                Enviorement envfs = new Enviorement();
                fdef.getAtributos().stream().filter((atr) -> (!envfs.add(atr.getId(), atr.getTipo().nuevoFusion(env)))).forEachOrdered((_item) -> {
                    Singleton.getInstance().addError(row, column, "Atributos repetidos");
                });
                f.setTs(envfs);
            }
        }
        else if(access.compare(Objeto.Tipo.FUSION) && valor.compare(Objeto.Tipo.NULL)){
            Fusion f = (Fusion)access;
            FusionDef fdef = env.GetFusion(f.getId());
            if(fdef == null){
                Singleton.getInstance().addError(row, column, "FusionException: no existe la fusion " + f.getId());
                return null;
            }
            f.setTs(null);
        }
        else
            Singleton.getInstance().addError(row, column, "TypeException: Tipos diferentes en asignacion");
        return null;
    }
}