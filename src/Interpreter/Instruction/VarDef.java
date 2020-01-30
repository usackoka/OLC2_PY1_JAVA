/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.*;
import Interpreter.Operation.Operation;
import Principal.Singleton;
import java.util.LinkedList;

public class VarDef implements Instruction {

    private final LinkedList<String> lid;
    private final Operation value;
    private final Objeto tipo;
    private final int row, column;

    public VarDef(LinkedList<String> lid, Operation value, Objeto tipo, int row, int column) {
        this.lid = lid;
        this.value = value;
        this.tipo = tipo;
        this.row = row;
        this.column = column;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        Objeto obj = getValue(env);
        if (this.value == null) {
            lid.stream().filter((id) -> (!env.add(id, obj))).forEachOrdered((id) -> {
                Singleton.getInstance().addError(row, column, "ValuesException: El identificador " + id + " ya esta utilizado en este ambito");
            });
        } else {
            Objeto retorno = value.Ejecutar(env);
            if (retorno == null || retorno.compare(Objeto.Tipo.ERROR)) {
                Singleton.getInstance().addError(row, column, "Error recuperado");
                return null;
            }
            
            if(retorno instanceof Primitivo){
                retorno = ((Primitivo)retorno).nuevo();
            }
            else if(retorno instanceof Fusion){
                Fusion f = (Fusion)retorno;
                retorno = new Fusion(f.getId(),f.getTs());
            }     
            
            if (obj.compare(retorno)) {
                for(String id : lid){
                    if(!env.add(id, obj.compare(Objeto.Tipo.ARRAY) || obj.compare(Objeto.Tipo.RSTRING) ? obj : retorno)){
                        Singleton.getInstance().addError(row, column, "ValuesException: El identificador " + id + " ya esta utilizado en este ambito");    
                    }
                }
            }
            else if(obj.compare(Objeto.Tipo.FUSION) && retorno.compare(Objeto.Tipo.NUEVO)){
                Fusion f = (Fusion)obj;
                New n = (New)retorno;
                FusionDef fd = env.GetFusion(f.getId());
                if(fd == null){
                    Singleton.getInstance().addError(row, column, "FusionException: No existe la fusion " + f.getId());
                    return null;
                }
                if(fd.getAtributos().size() == n.getSize()){
                    Enviorement envFs = new Enviorement();
                    fd.getAtributos().stream().filter((param) -> (!envFs.add(param.getId(), param.getTipo().nuevoFusion(env)))).forEachOrdered((_item) -> {
                        Singleton.getInstance().addError(row, column, "Atributos repetidos");
                    });
                    lid.stream().filter((id) -> (!env.add(id, new Fusion(f.getId(),envFs)))).forEachOrdered((id) -> {
                        Singleton.getInstance().addError(row, column, "ValuesException: El identificador " + id + " ya esta utilizado en este ambito" );
                    });
                }
                else{
                    Singleton.getInstance().addError(row, column, "FusionException: tamaño de memoria insuficiente para " + f.getId());
                }
            }
            else if(obj.compare(Objeto.Tipo.FUSION) && retorno.compare(Objeto.Tipo.NULL)){
                Fusion f = (Fusion)obj;
                FusionDef fd = env.GetFusion(f.getId());
                if(fd == null){
                    Singleton.getInstance().addError(row, column, "FusionException: No existe la fusion " + f.getId());
                    return null;
                }
                lid.stream().filter((id) -> (!env.add(id, new Fusion(f.getId(),null)))).forEachOrdered((id) -> {
                    Singleton.getInstance().addError(row, column, "ValuesException: El identificador " + id + " ya esta utilizado en este ambito" );
                });
            }
            else{
                Singleton.getInstance().addError(row, column, "Tipos de datos diferentes");
            }
        }
        return null;
    }

    private Objeto getValue(Enviorement env){
        switch(this.tipo.getTipo()){
            case INT:
                return new Primitivo(Objeto.Tipo.INT,0);
            case DECIMAL:
                return new Primitivo(Objeto.Tipo.DECIMAL,0.0);
            case CHAR:
                return new Primitivo(Objeto.Tipo.CHAR,'\0');
            case STRING:
                return new Cadena("");
            case BOOLEAN:
                return new Primitivo(Objeto.Tipo.BOOLEAN,false);
            case FUSION:
                Fusion f = (Fusion)this.tipo;
                return new Fusion(f.getId(),null);
            case ARRAY:
                Arreglo arr = (Arreglo)this.tipo;
                return arr.ejecutar(env);
            default:
                return this.tipo;
        }
    }
}
