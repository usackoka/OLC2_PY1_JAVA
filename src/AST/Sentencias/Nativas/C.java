/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import AST.Expresiones.TipoList;
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
        Primitivo primitivo = null;
        for (int i = 0; i < this.expresiones.size(); i++) {
            Expresion expresion = this.expresiones.get(i);
            Object value = expresion.getValor(entorno);
            if(primitivo==null){
                primitivo = new Primitivo(value, Primitivo.getTipoDato(value), fila, columna);
                continue;
            }
            primitivo.addValue(value, Primitivo.getTipoDato(value), entorno);
        }
        return primitivo.castearA(getMAX(entorno));
    }
    
    //PRIORIDADES
    public Object getMAX(Entorno entorno){
        Object TIPOMAX = null;
        for (Expresion expresion : expresiones) {
            Object TIPO = expresion.getTipo(entorno);
            
            if(TIPOMAX==null){
                TIPOMAX=TIPO;
            }else{
                if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN)){
                    if(TIPO.equals(Expresion.TIPO_PRIMITIVO.INTEGER) | TIPO.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)|TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)|TIPO instanceof TipoList){
                        TIPOMAX = TIPO;
                    }
                }
                else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
                    if(TIPO.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)|TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)|TIPO instanceof TipoList){
                        TIPOMAX = TIPO;
                    }
                }else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)){
                    if(TIPO.equals(Expresion.TIPO_PRIMITIVO.STRING)|TIPO instanceof TipoList){
                        TIPOMAX = TIPO;
                    }
                }else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                    if(TIPO instanceof TipoList){
                        TIPOMAX = TIPO;
                    }
                }
            }
        }
        return TIPOMAX;
    }

}
