/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Sentencias.Nativas.*;
import AST.Sentencias.Print;
import Analyzer.Token;
import java.util.LinkedList;

public class Llamada extends Expresion{

    String id;
    LinkedList<Expresion> parametros;
    
    public Llamada(String id, LinkedList<Expresion> parametros, int fila, int columna){
        this.id = id;
        this.parametros = parametros;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        String match = this.id.toLowerCase();
        int PARAMETROS = 1;
        switch(match){
            //hago el match de las nativas
            case "print":
                if(parametros.size()==PARAMETROS){
                    return new Print(this.parametros.get(0)).ejecutar(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return null;
            case "c":
                if(parametros.size()>=PARAMETROS){
                    return new C(this.parametros, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametro al menos", fila, columna));
                return new Primitivo("", TIPO_PRIMITIVO.STRING, fila, columna);
            case "typeof":
                if(parametros.size()==PARAMETROS){
                    return new TypeOf(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return new Primitivo("", TIPO_PRIMITIVO.STRING, fila, columna);
            //busco entre las declaradas en el archivo
            default:
                entorno.addError(new Token(this.id, "la función "+this.id+" no existe :c", fila, columna));
                return new Primitivo("", TIPO_PRIMITIVO.STRING, fila, columna);
        }
    }

    @Override
    public Object getTipo(Entorno entorno) {
        String match = this.id.toLowerCase();
        switch(match){
            case "c":
                return new C(this.parametros, fila, columna).getTipo(entorno);
            case "typeof":
                return Expresion.TIPO_PRIMITIVO.STRING;
            default:
                entorno.addError(new Token(this.id, "la función "+this.id+" no existe o no retorna un valor", fila, columna));
                return Expresion.TIPO_PRIMITIVO.STRING;
        }
    }

}
