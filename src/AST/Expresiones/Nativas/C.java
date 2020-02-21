/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

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
        LinkedList<Object> tipos = new LinkedList<>();
        Primitivo primitivo = null;
        for (int i = 0; i < this.expresiones.size(); i++) {
            Expresion expresion = this.expresiones.get(i);
            Object value = expresion.getValor(entorno);
            Object tipoDato = Primitivo.getTipoDato(value);
            tipos.add(tipoDato);
            if(primitivo==null){
                primitivo = new Primitivo(value, tipoDato, fila, columna);
                continue;
            }
            primitivo.addValue(value, tipoDato, entorno);
        }
        return primitivo.castearA(getMAX(entorno,tipos));
    }
    
    //PRIORIDADES
    public Object getMAX(Entorno entorno, LinkedList<Object> tipos){
        Object TIPOMAX = null;
        for (Object tipo : tipos) {
            if(TIPOMAX==null){
                TIPOMAX=tipo;
            }else{
                if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.BOOLEAN)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.INTEGER) | tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)|tipo.equals(Expresion.TIPO_PRIMITIVO.STRING)|tipo instanceof TipoList){
                        TIPOMAX = tipo;
                    }
                }
                else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.INTEGER)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)|tipo.equals(Expresion.TIPO_PRIMITIVO.STRING)|tipo instanceof TipoList){
                        TIPOMAX = tipo;
                    }
                }else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.DOUBLE)){
                    if(tipo.equals(Expresion.TIPO_PRIMITIVO.STRING)|tipo instanceof TipoList){
                        TIPOMAX = tipo;
                    }
                }else if(TIPOMAX.equals(Expresion.TIPO_PRIMITIVO.STRING)){
                    if(tipo instanceof TipoList){
                        TIPOMAX = tipo;
                    }
                }
            }
        }
        return TIPOMAX;
    }

}
