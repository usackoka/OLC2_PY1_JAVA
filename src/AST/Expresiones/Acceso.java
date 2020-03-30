/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Nativas.Array;
import AST.Expresiones.Nativas.Matrix;
import AST.Expresiones.Nativas.VectorArit;
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
        this.fila = x==null?y.fila:x.fila;
        this.columna = x==null?y.columna:x.columna;
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
        if(Osub instanceof ListArit){
            if(tipoAcceso.equals(TIPO_ACCESO.SIMPLE)){
                return (new ListArit(((ListArit)Osub).getData(),x,entorno,fila,columna));
            }else if(tipoAcceso.equals(TIPO_ACCESO.DOBLE)){
                LinkedList data = ((ListArit)Osub).getData();
                return ((new Primitivo(data, Primitivo.getTipoDato(data), fila, columna))).getPos(x, entorno);
            }else{
                entorno.addError(new Token("Acceso-No soportado acceso", "Se quizo acceder a una List con acceso: "+this.tipoAcceso, fila, columna));
                return 0;
            }
        }else if(Osub instanceof Matrix){
            if(tipoAcceso.equals(TIPO_ACCESO.XY)){
                return ((Matrix)Osub).getValorIndex(x,y,null,entorno);
            }else if(tipoAcceso.equals(TIPO_ACCESO.X)){
                return ((Matrix)Osub).getValorIndex(x,null,null,entorno);
            }else if(tipoAcceso.equals(TIPO_ACCESO.Y)){
                return ((Matrix)Osub).getValorIndex(null,y,null,entorno);
            }else if(tipoAcceso.equals(TIPO_ACCESO.SIMPLE)){
                return ((Matrix)Osub).getValorIndex(null,null,x,entorno);
            }else{
                entorno.addError(new Token("Acceso-No soportado acceso", "Se quizo acceder a una Matriz con acceso: "+this.tipoAcceso, fila, columna));
                return 0;
            }
        }else if(Osub instanceof Array){
            if(tipoAcceso.equals(TIPO_ACCESO.SIMPLE)){
                return ((Array)Osub).subArray(x,entorno,fila,columna);
            }else{
                entorno.addError(new Token("Acceso-No soportado acceso", "Se quizo acceder a un Array con acceso: "+this.tipoAcceso, fila, columna));
                return 0;
            }
        }else{
            //aquí entra linkedList y valores puntuales
            if(tipoAcceso.equals(TIPO_ACCESO.SIMPLE)){
                return ((new Primitivo(Osub, Primitivo.getTipoDato(Osub), fila, columna))).getPos(x, entorno);
            }else{
                entorno.addError(new Token("Acceso-No soportado acceso", "Se quizo acceder a vector con acceso: "+this.tipoAcceso, fila, columna));
                return 0;
            }
        }
    }
    
    public Object setValor(Entorno entorno, Object valor, boolean first){
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
        
        if(this.subAcceso instanceof Primitivo){
            //lo mas al fondo, variable
            Variable var = ((Primitivo)this.subAcceso).getVariable(entorno);
            Object Osub = var.value;
            
            if(first){
                if(Osub instanceof ListArit){
                    
                }else if(Osub instanceof Matrix){
                }else if(Osub instanceof Array){
                }else if(Osub instanceof VectorArit){
                    if(tipoAcceso.equals(TIPO_ACCESO.SIMPLE)){
                        ((VectorArit)Osub).setValue(valor, x, entorno, fila, columna);
                    }else{
                        entorno.addError(new Token("Acceso-No soportado acceso", "Se quizo acceder a vector con acceso: "+this.tipoAcceso, fila, columna));
                    }
                }else{
                    if(tipoAcceso.equals(TIPO_ACCESO.SIMPLE)){
                        //valores puntuales
                        VectorArit vec = new VectorArit();
                        vec.add(Osub);
                        vec.setValue(valor, x, entorno, fila, columna);
                        var.setValue(vec);
                    }else{
                        entorno.addError(new Token("Acceso-No soportado acceso", "Se quizo acceder a vector con acceso: "+this.tipoAcceso, fila, columna));
                    }
                }
            }else{
            }
        }else{
            //acceso
            Object Osub = ((Acceso)this.subAcceso).setValor(entorno, null, false);
        }
        
        
        return 0;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, "ACCESO");
        
        return cont_raiz;
    }

}
