/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Nativas.Matrix;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class Acceso extends Expresion{

    Expresion subAcceso;
    Expresion Ex,Ey;
    TIPO_ACCESO tipoAcceso;
    
    public Acceso(Expresion subAcceso, Expresion x, Expresion y, TIPO_ACCESO tipoAcceso){
        this.Ex = x;
        this.Ey = y;
        this.subAcceso = subAcceso;
        this.tipoAcceso = tipoAcceso;
        this.fila = x.fila;
        this.columna = x.columna;
    }
    
    public enum TIPO_ACCESO{
        SIMPLE, DOBLE, XY, X, Y
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        //empiezo a retornar el valor
        int x = -1;
        int y = -1;
        if(Ex != null){
            Object Ox = Ex.getValor(entorno);
            try {
                x = (int)Ox;
            } catch (Exception e) {
                entorno.addError(new Token("Acceso-TipoX","No se puede castear a Entero: "+Ox,fila,columna));
                return 0;
            }
        }
        if(Ey != null){
            Object Oy = Ey.getValor(entorno);
            try {
                y = (int)Oy;
            } catch (Exception e) {
                entorno.addError(new Token("Acceso-TipoY","No se puede castear a Entero: "+Oy,fila,columna));
                return 0;
            }
        }
        
        
        Object Osub = this.subAcceso.getValor(entorno);
        if(Osub instanceof LinkedList){
            if(tipoAcceso.equals(TIPO_ACCESO.SIMPLE)){
                return ((new Primitivo(Osub, Primitivo.getTipoDato(Osub), fila, columna))).getPos(x, entorno);
            }else{
                entorno.addError(new Token("Acceso-No soportado acceso", "Se quizo acceder a vector con acceso: "+this.tipoAcceso, fila, columna));
                return 0;
            }
        }
        
        entorno.addError(new Token("Acceso-No soportado", "No soportado subacceso tipo: "+this.subAcceso.getClass(), fila, columna));
        return 0;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "ACCESO");
        
        return cont_raiz;
    }

}
