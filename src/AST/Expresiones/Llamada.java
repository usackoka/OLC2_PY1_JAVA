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
            case "c":
                if(parametros.size()>=PARAMETROS){
                    return new C(this.parametros, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametro al menos", fila, columna));
                return "";
            case "print":
                if(parametros.size()==PARAMETROS){
                    return new Print(this.parametros.get(0)).ejecutar(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return null;
            case "typeof":
                if(parametros.size()==PARAMETROS){
                    return new TypeOf(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return "";
            case "stringlength":
                if(parametros.size()==PARAMETROS){
                    return new StringLength(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return 0;
            case "remove":
                PARAMETROS = 2;
                if(parametros.size()==PARAMETROS){
                    return new Remove(this.parametros, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return new Primitivo("", TIPO_PRIMITIVO.STRING, fila, columna);
            case "tolowercase":
                if(parametros.size()==PARAMETROS){
                    return new ToLowerCase(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return "";
            case "touppercase":
                if(parametros.size()==PARAMETROS){
                    return new ToUpperCase(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return "";
            case "trunk":
                if(parametros.size()==PARAMETROS){
                    return new Trunk(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return new Primitivo("", TIPO_PRIMITIVO.STRING, fila, columna);
            case "round":
                if(parametros.size()==PARAMETROS){
                    return new Round(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return 0;
            case "mode":
                if(parametros.size()==1 || parametros.size()==2){
                    return new Mode(this.parametros, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe al menos 1 parametro y maximo 2 encontrados: "+parametros.size(), fila, columna));
                return 0;
            case "mean":
                if(parametros.size()==1 || parametros.size()==2){
                    return new Mean(this.parametros, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe al menos 1 parametro y maximo 2 encontrados: "+parametros.size(), fila, columna));
                return 0;
            case "median":
                if(parametros.size()==1 || parametros.size()==2){
                    return new Median(this.parametros, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe al menos 1 parametro y maximo 2 encontrados: "+parametros.size(), fila, columna));
                return 0;
            //busco entre las declaradas en el archivo
            default:
                return entorno.ejecutarFuncion(match, parametros, fila, columna);
        }
    }

    @Override
    public Object getTipo(Entorno entorno) {
        String match = this.id.toLowerCase();
        switch(match){
            case "c":
                return new C(this.parametros, fila, columna).getTipo(entorno);
            case "stringlength": case "trunk": case "round":
                return Expresion.TIPO_PRIMITIVO.INTEGER;
            case "remove": case "tolowercase": case "touppercase": case "typeof":
                return Expresion.TIPO_PRIMITIVO.STRING;
            case "median": case "mode": case "mean":
                return Expresion.TIPO_PRIMITIVO.DOUBLE;
            default:
                return Primitivo.getTipoDato(valorEjecutado, entorno, fila, columna);
        }
    }

}
