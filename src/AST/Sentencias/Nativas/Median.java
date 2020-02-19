/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias.Nativas;

import AST.Entorno;
import AST.Expresion;
import Analyzer.Token;
import java.util.Comparator;
import java.util.LinkedList;

public class Median extends Expresion{

    LinkedList<Expresion> expresiones;
    
    public Median(LinkedList<Expresion> expresiones, int fila, int columna){
        this.expresiones = expresiones;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object getValor(Entorno entorno) {
        Object defecto = 0;
        Object valor = expresiones.get(0).getValor(entorno);
        Object tipo = expresiones.get(0).getTipo(entorno);
        Object valor2 = expresiones.size()==2?expresiones.get(1).getValor(entorno):null;
        Object tipo2 = expresiones.size()==2?expresiones.get(1).getTipo(entorno):null;
        
        //hago las validaciones de tipos en los parametros para estar seguro de las conversiones luego :p
        if(!tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
            entorno.addError(new Token("MEAN"+tipo.toString(),"Se esperaba tipo INT en el primer parametro", fila, columna));
            return defecto;
        }
        if(tipo2!=null && !tipo2.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
            entorno.addError(new Token("MEAN"+tipo.toString(),"Se esperaba tipo INT en el segundo parametro", fila, columna));
            return defecto;
        }
        
        //saco la lista de numeros
        LinkedList<Integer> numeros = new LinkedList<>();
        if(valor instanceof LinkedList){
            LinkedList<Object> temp = (LinkedList<Object>)valor;
            temp.forEach((num)->{
                numeros.add((int)num);
            });
        }else{
            numeros.add((int)valor);
        }
        
        //ordeno los numeros
        numeros.sort(Comparator.naturalOrder());
        
        //saco el index si es que hay
        int index = valor2!=null?(int)valor2:0;
        
        //calculando moda
        int maxModa = 0;
        int anterior = 0;
        int moda = 0;
        int resultado = 0;
        for(int numero : numeros){
            //si hay un index, pregunto si ese index es mayor o igual al numero que estoy comparando, si no hay, lo dejo pasar
            if(valor2!=null?index>=numero:true){
                if(anterior == numero){
                    moda++;
                }else{
                    if(moda>maxModa){
                        resultado = numero;
                    }else{
                        moda = 0;
                    }
                }
                anterior = numero;
            }
        }
        
        return resultado;
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return Expresion.TIPO_PRIMITIVO.DOUBLE;
    }
}
