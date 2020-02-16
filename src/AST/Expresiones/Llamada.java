/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
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
        int PARAMETROS = 0;
        switch(match){
            //hago el match de las nativas
            case "print":
                PARAMETROS = 1;
                if(parametros.size()==PARAMETROS){
                    return new Print(parametros.get(0)).ejecutar(entorno);
                }
                entorno.addError(new Token(this.id, "Semántico", "la función "+this.id+" recibe "+PARAMETROS+" parametros exclusivamente", fila+"", columna+""));
                return null;
            //busco entre las declaradas en el archivo
            default:
                return null;
        }
    }

    @Override
    public Object getTipo(Entorno entorno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
