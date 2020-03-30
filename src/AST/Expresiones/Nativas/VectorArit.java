/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.ListArit;
import AST.Expresiones.Primitivo;
import java.util.LinkedList;

public class VectorArit extends LinkedList<Object>{
    public VectorArit(){
        super();
    }
    
    public void setValue(Object value){
    }
    
    //castear el vector
    public Object castearA(Entorno entorno){
        LinkedList<Object> temp = new LinkedList<>();
        temp.addAll(this);
        Object TIPO = getMAX(entorno);
        
        this.clear();
        if(TIPO.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN)){
            for(Object element : temp){
                this.add(element);
            }
        }
        else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
            for(Object element : temp){
                int value = 0;
                try {
                    value = Integer.parseInt(String.valueOf(element));
                } catch (Exception e) {
                    value = element.toString().toLowerCase().equals("true")?1:0;
                }
                this.add(value);
            }
        }else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.NUMERIC)){
            for(Object element : temp){
                double value = 0.0;
                try {
                    value = Double.parseDouble(String.valueOf(element));
                } catch (Exception e) {
                    value = element.toString().toLowerCase().equals("true")?1.0:0.0;
                }
                this.add(value);
            }
        }else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)){
            for(Object element : temp){
                this.add(element.toString());
            }
        }else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.LIST)){
            for(Object element : temp){
                if(element instanceof ListArit){
                    ListArit elementList = (ListArit)element;
                    for(Object obj : elementList.getData()){
                        this.add(obj);
                    }
                }else{
                    this.add(element);
                }
            }
            return new ListArit(this);
        }
        
        return this.size()==1?this.get(0):this;
    }
    
    //PRIORIDADES
    public Object getMAX(Entorno entorno){
        LinkedList<Object> tipos = getTipos();
        Object TIPOMAX = null;
        for (Object tipo : tipos) {
            if(TIPOMAX==null){
                TIPOMAX=tipo;
            }else{
                if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER) | tipo.equals(Expresion.TIPO_PRIMITIVO.NUMERIC)|tipo.equals(Expresion.TIPO_PRIMITIVO.STRING)|tipo.equals(Expresion.TIPO_PRIMITIVO.LIST)){
                        TIPOMAX = tipo;
                    }
                }
                else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.NUMERIC)|tipo.equals(Expresion.TIPO_PRIMITIVO.STRING)|tipo.equals(Expresion.TIPO_PRIMITIVO.LIST)){
                        TIPOMAX = tipo;
                    }
                }else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.NUMERIC)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.STRING)|tipo.equals(Expresion.TIPO_PRIMITIVO.LIST)){
                        TIPOMAX = tipo;
                    }
                }else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.LIST)){
                        TIPOMAX = tipo;
                    }
                }
            }
        }
        return TIPOMAX;
    }
    
    public LinkedList<Object> getTipos(){
        LinkedList<Object> tipos = new LinkedList<>();
        for(Object obj : this){
            tipos.add(Primitivo.getTipoDato(obj));
        }
        return tipos;
    }
}