/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public class nRow extends Expresion{

    Expresion expresion;
    
    public nRow(Expresion expresion, int fila, int columna){
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object valor = expresion.getValor(entorno);
        Object tipo = Primitivo.getTipoDato(valor);

        if(!tipo.equals(Expresion.TIPO_PRIMITIVO.MATRIX)){
            entorno.addError(new Token("Found: "+tipo, "No se puede realizar nRow de este tipo de dato", fila, columna));
            return 0;
        }
        
        return ((Matrix)valor).getNRow();
    }

}
