package Interpreter;

import Interpreter.Instruction.Function;
import Interpreter.Instruction.FusionDef;
import Interpreter.Objeto.Frame;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.Primitivo;
import Principal.Singleton;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Enviorement {
    private Enviorement anterior;
    private HashMap<String,Object> ts;
    private Frame frame;
    public Enviorement() {
        this.ts = new HashMap<>();
        this.anterior = null;
    }

    public Enviorement(Enviorement anterior) {
        this.anterior = anterior;
        this.ts = new HashMap<>();
        this.frame = anterior.frame;
    }

    public Enviorement getAnterior() {
        return anterior;
    }

    public void setAnterior(Enviorement anterior) {
        this.anterior = anterior;
    }

    public HashMap<String, Object> getTs() {
        return ts;
    }

    public void setTs(HashMap<String, Object> ts) {
        this.ts = ts;
    }
    
    public boolean add(String id, Objeto value){
        if(!ts.containsKey(id)){
            ts.put(id, new Symbol(id,value));
            return true;
        }
        return false;
    }
    
    public Symbol getValue(String id){
        Enviorement temp = this;
        while(temp != null){
            if(temp.ts.containsKey(id)){
                return (Symbol)temp.ts.get(id);
            }
            temp = temp.anterior;
        }
        return null;
    }
    
    public boolean AddFunction(Function f, String id){
        Enviorement global = getGlobal();
        if(global.ts.containsKey("$" + id)){
            FunctionList functionlist = (FunctionList)global.ts.get("$" + id);
            return functionlist.sobreCarga(f);
        }
        else{
            FunctionList func = new FunctionList(id);
            func.addFunction(f);
            global.ts.put("$" + id, func);
        }
        return true;
    }
    
    public boolean AddFusion(FusionDef f, String id){
        Enviorement temp = this.getGlobal();
        if(!temp.ts.containsKey("@" + id)){
            temp.ts.put("@" + id, f);
            return true;
        }
        return false;
    }
    
    public FusionDef GetFusion(String id){
        Enviorement temp = this.getGlobal();
        if(temp.ts.containsKey("@" + id)){
            return (FusionDef)temp.ts.get("@" + id);
        }
        return null;
    }
    
    public Enviorement getGlobal(){
        Enviorement temp = this;
        while(temp.anterior != null)
            temp = temp.anterior;
        return temp;
    }
    
    public Function getFunction(String id, LinkedList<Objeto> params){
        Enviorement global = getGlobal();
        if(global.ts.containsKey("$" + id)){
            FunctionList functionlist = (FunctionList)global.ts.get("$" + id);
            return functionlist.getFunction(params);
        }
        return null;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }
   
    public void ShowSymbolsHtml() {
          String cadHtml = "";
        
         Enviorement temp = this;
         int i=-1;
        while(temp != null){
            i++;
             for(Map.Entry <String,Object>  entry: temp.ts.entrySet()) {
                if (entry.getValue() instanceof Symbol){
                    Symbol s1 = (Symbol)entry.getValue();
                        try {
                              System.out.println(s1.getId()+"-Variable-"+s1.getValue().getTipo()+"-"+s1.getValue());             
                              cadHtml+="         <tr>";
                              cadHtml+="         <td>"+i+"</td>";
                              cadHtml+="         <td>"+s1.getId()+"</td>";
                              cadHtml+="         <td>Variable</td>";
                              cadHtml+="         <td>"+s1.getValue().getTipo()+"</td>";
                              cadHtml+="         <td>"+s1.getValue()+"</td>";
                              cadHtml+="         </tr>";
                         }catch (Exception E){
                         } 
                 }else 
                if (entry.getValue() instanceof FunctionList){
                    FunctionList s2 = (FunctionList)entry.getValue();
                  //  System.out.println(s2.getId()+"--");
                     for(Function actual : s2.getFunctionlist()){
                         try {
                             if(actual.getTipo()==null){
                              System.out.println(actual.getId()+"-Metodo-"+actual.getTipo());
                              cadHtml+="         <tr>";
                              cadHtml+="         <td>"+i+"</td>";
                              cadHtml+="         <td>"+actual.getId()+"</td>";
                              cadHtml+="         <td>Metodo</td>";
                              cadHtml+="         <td>Parametros:  "+actual.getStringParametros()+" ; Return: "+actual.getTipo()+"</td>";
                             
                              cadHtml+="         <td>--</td>";
                              cadHtml+="         </tr>";
                             }else{
                              System.out.println(actual.getId()+"-Funcion-"+actual.getTipo().getTipo());
                              cadHtml+="         <tr>";
                              cadHtml+="         <td>"+i+"</td>";
                              cadHtml+="         <td>"+actual.getId()+"</td>";
                              cadHtml+="         <td>Funcion</td>";
                              cadHtml+="         <td>Parametros: "+actual.getStringParametros()+" ; Return: "+actual.getTipo().getTipo()+"</td>";
                              cadHtml+="         <td>--</td>";
                              cadHtml+="         </tr>";
                             }
                         }catch (Exception E){
                         }
                        
                    }
                    
                    // System.out.println(entry.getKey()+"----------"+(((Objeto)entry.getValue()).getTipo()));
                }
                    
//                System.out.println(entry.getValue()); 
               }

            temp = temp.anterior;
        }
  
        Singleton.getInstance().crearTablaSimbolos(cadHtml);
    }

 
}
