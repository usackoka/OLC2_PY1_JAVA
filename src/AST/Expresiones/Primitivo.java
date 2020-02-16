/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;
import java.util.LinkedList;

//En este caso, primitivo hace referencia a un vector de tipo primitivo
public class Primitivo extends Expresion{
    
    //por ser vector el mas primitivo se maneja un conteo de valores
    LinkedList<Object> values;
    
    public Primitivo(Object value, Object TIPO, int fila, int columna){
        this.TIPO = TIPO;
        if(this.TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)){
            value = getEscapes(value.toString());
        }
        this.values = new LinkedList<>();
        this.values.add(value);
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object getValor(Entorno entorno) {
        if(values.size()==1){
            return values.get(0);
        }else{
            return this.values;
        }
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return this.TIPO;
    }
    
    public void addValue(Object value, Object TIPO, Entorno entorno){
        this.values.add(value);
    }
    
    //castear el vector
    public Object castearA(Object TIPO){
        LinkedList<Object> values = new LinkedList<>();
        if(TIPO.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN)){
            values = this.values;
        }
        else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
            for(Object element : this.values){
                int value = 0;
                try {
                    value = Integer.parseInt(String.valueOf(element));
                } catch (Exception e) {
                    value = element.toString().toLowerCase().equals("true")?1:0;
                }
                values.add(value);
            }
        }else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)){
            for(Object element : this.values){
                double value = 0.0;
                try {
                    value = Double.parseDouble(String.valueOf(element));
                } catch (Exception e) {
                    value = element.toString().toLowerCase().equals("true")?1.0:0.0;
                }
                values.add(value);
            }
        }else if(TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)){
            for(Object element : this.values){
                values.add(element.toString());
            }
        }else if(TIPO instanceof TipoList){
        }
        
        this.values = values;
        this.TIPO = TIPO;
        return this.values;
    }
    
    public String getEscapes(String cadena){
        return cadena.replace("\\n", "\n").replace("\\\\", "\\").replace("\\\"","\"").replace("\\t", "\t").replace("\\r", "\r");
    }
}