/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import AST.Expresiones.ListArit;
import Analyzer.Token;
import java.util.LinkedList;

public class C extends Expresion{
    LinkedList<Expresion> expresiones;
    
    public C(LinkedList<Expresion> expresiones, int fila, int columna){
        this.expresiones = expresiones;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        LinkedList<Object> tipos = new LinkedList<>();
        LinkedList<Object> data = new LinkedList<>();
        for (int i = 0; i < this.expresiones.size(); i++) {
            Expresion expresion = this.expresiones.get(i);
            Object value = expresion.getValor(entorno);
            Object tipoDato = Primitivo.getTipoDato(value);
            tipos.add(tipoDato);
            data.add(value);
        }
        return castearA(getMAX(entorno,tipos),data);
    }
    
    //PRIORIDADES
    public Object getMAX(Entorno entorno, LinkedList<Object> tipos){
        Object TIPOMAX = null;
        for (Object tipo : tipos) {
            if(TIPOMAX==null){
                TIPOMAX=tipo;
            }else{
                if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER) | tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)|tipo.equals(Expresion.TIPO_PRIMITIVO.STRING)|tipo.equals(Expresion.TIPO_PRIMITIVO.LIST)){
                        TIPOMAX = tipo;
                    }
                }
                else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)|tipo.equals(Expresion.TIPO_PRIMITIVO.STRING)|tipo.equals(Expresion.TIPO_PRIMITIVO.LIST)){
                        TIPOMAX = tipo;
                    }
                }else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)){
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
    
    //castear el vector
    public Object castearA(Object TIPO, LinkedList<Object> data){
        LinkedList<Object> values = new LinkedList<>();
        
        if(TIPO.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN)){
            values = data;
        }
        else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
            for(Object element : data){
                if(element instanceof LinkedList){
                    LinkedList elementList = (LinkedList)element;
                    for(Object obj : elementList){
                        values.add(obj);
                    }
                }else{
                    int value = 0;
                    try {
                        value = Integer.parseInt(String.valueOf(element));
                    } catch (Exception e) {
                        value = element.toString().toLowerCase().equals("true")?1:0;
                    }
                    values.add(value);
                }
            }
        }else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)){
            for(Object element : data){
                if(element instanceof LinkedList){
                    LinkedList elementList = (LinkedList)element;
                    for(Object obj : elementList){
                        values.add(obj);
                    }
                }else{
                    double value = 0.0;
                    try {
                        value = Double.parseDouble(String.valueOf(element));
                    } catch (Exception e) {
                        value = element.toString().toLowerCase().equals("true")?1.0:0.0;
                    }
                    values.add(value);
                }
            }
        }else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)){
            for(Object element : data){
                if(element instanceof LinkedList){
                    LinkedList elementList = (LinkedList)element;
                    for(Object obj : elementList){
                        values.add(obj);
                    }
                }else{
                    values.add(element.toString());
                }
            }
        }else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.LIST)){
            for(Object element : data){
                if(element instanceof LinkedList){
                    LinkedList elementList = (LinkedList)element;
                    for(Object obj : elementList){
                        values.add(obj);
                    }
                }else if(element instanceof ListArit){
                    ListArit elementList = (ListArit)element;
                    for(Object obj : elementList.getData()){
                        values.add(obj);
                    }
                }else{
                    values.add(element);
                }
            }
            return new ListArit(values);
        }
        
        return values;
    }
}
