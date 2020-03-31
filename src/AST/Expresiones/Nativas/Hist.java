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
import GraficasArit.Graf_Hist;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Hist extends Sentencia{

    Expresion v, main, xlab;
    
    public Hist(Expresion v, Expresion xlab, Expresion main, int fila, int columna){
        this.v = v;
        this.main = main;
        this.xlab = xlab;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        
        //Obtengo el LinkedList<Double> para graficar los datos
        LinkedList<Double> datos = new LinkedList<>();
        Object Odatos = v.getValor(entorno);
        //creo el linkedList de double
        if(Odatos instanceof VectorArit){
            LinkedList<Object> temp = (VectorArit)Odatos;
            for(Object element : temp){
                Object tipo = Primitivo.getTipoDato(element);
                if(!(tipo.equals(Expresion.TIPO_PRIMITIVO.integer) || tipo.equals(Expresion.TIPO_PRIMITIVO.numeric))){
                    entorno.addError(new Token("Hist-TipoDeDato Parameter(x)", "Se esperaba un vector int o double, viene: "+tipo, fila, columna));
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
                entorno.addError(new Token("Hist-TipoDeDato Parameter(x)", "Se esperaba un vector int o double, viene: "+tipo, fila, columna));
                return null;
            }
            try {
                datos.add(Integer.parseInt(Odatos.toString())+0.0);
            } catch (Exception e) {
                datos.add(Double.parseDouble(Odatos.toString()));
            }
        }
        
        Graf_Hist hist = new Graf_Hist(datos, xlab.getValor(entorno).toString(), main.getValor(entorno).toString());
        hist.generarImagen(entorno);
        
        return null;
    }

}
