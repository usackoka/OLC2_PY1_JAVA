/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import AST.Sentencia;
import Analyzer.Token;
import GraficasArit.Graf_Pie;
import java.util.LinkedList;
import org.jfree.chart.ChartPanel;

public class Pie extends Sentencia{
    
    Expresion Ex,Elabels,Emain;
    
    public Pie(Expresion x, Expresion labels, Expresion main, int fila, int columna){
        this.Ex = x;
        this.Elabels = labels;
        this.Emain = main;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        
        //Obtengo el LinkedList<Double> para graficar los datos
        LinkedList<Double> datos = new LinkedList<>();
        Object Odatos = Ex.getValor(entorno);
        //creo el linkedList de double
        if(Odatos instanceof LinkedList){
            LinkedList<Object> temp = (LinkedList)Odatos;
            for(Object element : temp){
                Object tipo = Primitivo.getTipoDato(element);
                if(!(tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER) || tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))){
                    entorno.addError(new Token("Pie-TipoDeDato Parameter(x)", "Se esperaba un vector int o double, viene: "+tipo, fila, columna));
                    return null;
                }
                try {
                    datos.add(Integer.parseInt(element.toString())+0.0);
                } catch (Exception e) {
                    datos.add(Double.parseDouble(element.toString()));
                }
            }
        }else{
            Object tipo = Primitivo.getTipoDato(Odatos);
            if(!(tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER) || tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE))){
                entorno.addError(new Token("Pie-TipoDeDato Parameter(x)", "Se esperaba un vector int o double, viene: "+tipo, fila, columna));
                return null;
            }
            try {
                datos.add(Integer.parseInt(Odatos.toString())+0.0);
            } catch (Exception e) {
                datos.add(Double.parseDouble(Odatos.toString()));
            }
        }
        
        //creo el linkedList<String> de labels
        LinkedList<String> labels = new LinkedList<>();
        Object Olabels = Elabels.getValor(entorno);
        //creo el linkedList de string
        if(Olabels instanceof LinkedList){
            LinkedList<Object> temp = (LinkedList)Olabels;
            for(Object element :temp){
                labels.add(element.toString());
            }
        }else{
            labels.add(Olabels.toString());
        }
        
        Graf_Pie graf0 = new Graf_Pie(datos,labels,Emain.getValor(entorno).toString()); 
        graf0.CreatePane();
        
        return null;
    }
}
