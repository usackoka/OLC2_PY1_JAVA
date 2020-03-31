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

public class nCol extends Expresion{
    Expresion expresion;
    
    public nCol(Expresion expresion, int fila, int columna){
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

        if(!tipo.equals(Expresion.TIPO_PRIMITIVO.matrix)){
            entorno.addError(new Token("Found: "+tipo, "No se puede realizar nCol de este tipo de dato", fila, columna));
            return 0;
        }
        
        return ((Matrix)valor).getNCol();
    }
}
