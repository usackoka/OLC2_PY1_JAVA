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
import GraficasArit.Graf_Line;
import GraficasArit.Graf_Plot;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Plot extends Sentencia{

    Expresion Ev, Etype, Exlab, Eylab, Emain;
    
    public Plot(Expresion Ev, Expresion Etype, Expresion Exlab, Expresion Eylab, Expresion Emain, int fila, int columna){
        this.Ev = Ev;
        this.Etype = Etype;
        this.Exlab = Exlab;
        this.Eylab = Eylab;
        this.Emain = Emain;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        //Obtengo el LinkedList<LinkedList<Double>> para graficar los datos
        //o el vector
        LinkedList<Double> datos = new LinkedList<>();
        Object Odatos = Ev.getValor(entorno);
        //creo el linkedList de double
        if(Odatos instanceof Matrix || Odatos instanceof LinkedList){
            LinkedList<Object> temp;
            if(Odatos instanceof LinkedList){
                temp = (LinkedList)Odatos;
            }
            else{
                temp = ((Matrix)Odatos).getMapeo();
            }
            
            for(Object element : temp){
                Object tipo = Primitivo.getTipoDato(element);
                if(!(tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER) || tipo.equals(Expresion.TIPO_PRIMITIVO.NUMERIC))){
                    entorno.addError(new Token("Grafica-Plot-TipoDeDato Parameter(x)", "Se esperaba un vector int o double, viene: "+tipo, fila, columna));
                    return null;
                }
                try {
                    datos.add(Integer.parseInt(element.toString())+0.0);
                } catch (Exception e) {
                    datos.add(Double.parseDouble(element.toString()));
                }
            }
        }else{
            entorno.addError(new Token("Grafica - Plot", "Se esperaba una matriz o un vector en el primer parámetro viene: "+Primitivo.getTipoDato(Odatos), fila, columna));
        }
        
        //PREGUNTO POR EL ULTIMO PARÁMETRO PARA VER QUE GRAFICAR
        Object Omain = Emain.getValor(entorno);
        if(Omain instanceof LinkedList){
            //GRAFICA DE DISPERSION
            LinkedList<Object> temp = (LinkedList)Omain;
            LinkedList<Double> limitesY = new LinkedList<>();
            if(temp.size()!=2){
                entorno.addError(new Token("Grafica-Plot","Se esperaban unicamente 2 valores en el vector Ylim",fila,columna));
                return null;
            }
            for(Object element : temp){
                Object tipo = Primitivo.getTipoDato(element);
                if(!(tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER) || tipo.equals(Expresion.TIPO_PRIMITIVO.NUMERIC))){
                    entorno.addError(new Token("Grafica-Plot-TipoDeDato Parameter(Ylim)", "Se esperaba un vector int o double, viene: "+tipo, fila, columna));
                    return null;
                }
                try {
                    limitesY.add(Integer.parseInt(element.toString())+0.0);
                } catch (Exception e) {
                    limitesY.add(Double.parseDouble(element.toString()));
                }
            }
            
            Graf_Plot graf05 = new Graf_Plot(datos,Etype.getValor(entorno).toString(),
                    Exlab.getValor(entorno).toString(),Eylab.getValor(entorno).toString(),limitesY.get(0),limitesY.get(1),fila,columna); 
            graf05.generarImagen(entorno);
        }else{
            //GRAFICA LINEAL
            Graf_Line graf05 = new Graf_Line(datos,Etype.getValor(entorno).toString(),
                    Exlab.getValor(entorno).toString(),Eylab.getValor(entorno).toString(),Emain.getValor(entorno).toString(),fila,columna); 
            graf05.generarImagen(entorno);
        }
        
        return null;
    }
    
    
}
