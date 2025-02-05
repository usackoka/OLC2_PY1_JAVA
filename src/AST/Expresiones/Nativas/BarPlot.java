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
import GraficasArit.Graf_Bar;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class BarPlot extends Sentencia {
    
    Expresion Eh, Exlab, Eylab, Emain, Enames;
    
    public BarPlot(Expresion Eh, Expresion Exlab, Expresion Eylab, Expresion Emain, Expresion Enames, int fila, int columna){
        this.Eh = Eh;
        this.Exlab = Exlab;
        this.Eylab = Eylab;
        this.Emain = Emain;
        this.Enames = Enames;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        //Obtengo el LinkedList<Double> para graficar los datos
        LinkedList<Double> datos = new LinkedList<>();
        Object Odatos = Eh.getValor(entorno);
        //creo el linkedList de double
        if(Odatos instanceof VectorArit){
            LinkedList<Object> temp = (VectorArit)Odatos;
            for(Object element : temp){
                Object tipo = Primitivo.getTipoDato(element);
                if(!(tipo.equals(Expresion.TIPO_PRIMITIVO.integer) || tipo.equals(Expresion.TIPO_PRIMITIVO.numeric))){
                    entorno.addError(new Token("BarPlot-TipoDeDato Parameter(h)", "Se esperaba un vector int o double, viene: "+tipo, fila, columna));
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
            if(!(tipo.equals(Expresion.TIPO_PRIMITIVO.integer) || tipo.equals(Expresion.TIPO_PRIMITIVO.numeric))){
                entorno.addError(new Token("BarPlot-TipoDeDato Parameter(h)", "Se esperaba un vector int o double, viene: "+tipo, fila, columna));
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
        Object Olabels = Enames.getValor(entorno);
        //creo el linkedList de string
        if(Olabels instanceof VectorArit){
            LinkedList<Object> temp = (VectorArit)Olabels;
            for(Object element :temp){
                labels.add(element.toString());
            }
        }else{
            labels.add(Olabels.toString());
        }
        
        
       Graf_Bar graf01 = new Graf_Bar(datos,Exlab.getValor(entorno).toString(),Eylab.getValor(entorno).toString(),Emain.getValor(entorno).toString(),labels,fila,columna); 
       graf01.generarImagen(entorno);
        
        return null;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
}
