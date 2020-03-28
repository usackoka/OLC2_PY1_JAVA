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
        
        //PREGUNTO SI ES ACCESO A VECTOR
        if(this.subAcceso instanceof Primitivo){
            Object Ox = Ex.getValor(entorno);
            int x = 0;
            try {
                x = (int)Ox;
            } catch (Exception e) {
                entorno.addError(new Token("Acceso-TipoX","No se puede castear a Entero: "+Ox,fila,columna));
                return new LinkedList<>();
            }
            
            return ((Primitivo)this.subAcceso).getPos(x,entorno);
        }
        //si trae mas subaccesos
        else if(this.subAcceso instanceof Acceso){
            Object Osub = this.subAcceso.getValor(entorno);
            Object Ox = Ex.getValor(entorno);
            int x = 0;
            try {
                x = (int)Ox;
            } catch (Exception e) {
                entorno.addError(new Token("Acceso-TipoX","No se puede castear a Entero: "+Ox,fila,columna));
                return new LinkedList<>();
            }
            return (new Primitivo(Osub, Primitivo.getTipoDato(Osub), fila, columna)).getPos(x, entorno);
        }
        
        entorno.addError(new Token("Acceso-No soportado", "No soportado subacceso: "+this.subAcceso.getClass(), fila, columna));
        return 0;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "ACCESO");
        
        return cont_raiz;
    }

}
