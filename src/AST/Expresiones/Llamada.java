/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Nativas.*;
import AST.Nodo;
import AST.Sentencias.Print;
import Analyzer.Token;
import GraficasArit.Graph_AST;
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
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, id+"()");
        
        for(Nodo nodo:this.parametros){
            int cont_hijo = nodo.Recorrido(arbol);
            arbol.addRelacion(cont_raiz,cont_hijo);
        }
        
        return cont_raiz;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        String match = this.id.toLowerCase();
        int PARAMETROS = 1;
        switch(match){
            //hago el match de las nativas
            case "pie":
                PARAMETROS = 3;
                if(parametros.size()==PARAMETROS){
                    return new Pie(this.parametros.get(0),this.parametros.get(1),this.parametros.get(2), fila, columna).ejecutar(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return null;
            case "barplot":
                PARAMETROS = 5;
                if(parametros.size()==PARAMETROS){
                    return new BarPlot(this.parametros.get(0),this.parametros.get(1),this.parametros.get(2), this.parametros.get(3), this.parametros.get(4), fila, columna).ejecutar(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return null;
            case "plot":
                PARAMETROS = 5;
                if(parametros.size()==PARAMETROS){
                    return new Plot(this.parametros.get(0),this.parametros.get(1),this.parametros.get(2), this.parametros.get(3), this.parametros.get(4), fila, columna).ejecutar(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return null;
            case "hist":
                PARAMETROS = 3;
                if(parametros.size()==PARAMETROS){
                    return new Hist(this.parametros.get(0),this.parametros.get(1),this.parametros.get(2), fila, columna).ejecutar(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return null;
            case "c":
                if(parametros.size()>=PARAMETROS){
                    return new C(this.parametros, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametro al menos", fila, columna));
                return "";
            case "matrix":
                PARAMETROS = 3;
                if(parametros.size()==PARAMETROS){
                    return new Matrix(this.parametros.get(0), this.parametros.get(1), this.parametros.get(2), entorno, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return new Matrix();
            case "list":
                if(parametros.size()>=PARAMETROS){
                    return new ListArit(this.parametros, entorno, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametro al menos", fila, columna));
                return new ListArit();
            case "array":
                PARAMETROS = 2;
                if(parametros.size()==PARAMETROS){
                    return new Array(this.parametros.get(0),this.parametros.get(1), entorno, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return new Array();
            case "length":
                if(parametros.size()==PARAMETROS){
                    return new Length(this.parametros.get(0), entorno, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
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
            case "nrow":
                if(parametros.size()==PARAMETROS){
                    return new nRow(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return 0;
            case "ncol":
                if(parametros.size()==PARAMETROS){
                    return new nCol(this.parametros.get(0), fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return 0;
            case "remove":
                PARAMETROS = 2;
                if(parametros.size()==PARAMETROS){
                    return new Remove(this.parametros, fila, columna).getValor(entorno);
                }
                entorno.addError(new Token(this.id, "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila, columna));
                return "";
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
                return "";
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

}
