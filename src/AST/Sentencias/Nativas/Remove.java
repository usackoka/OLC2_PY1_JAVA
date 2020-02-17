/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias.Nativas;

import AST.Entorno;
import AST.Expresion;
import java.util.LinkedList;

public class Remove extends Expresion{
    LinkedList<Expresion> expresiones;
    
    public Remove(LinkedList<Expresion> expresiones, int fila, int columna){
        this.expresiones = expresiones;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        String cadena1 = this.expresiones.get(0).getValor(entorno).toString();
        return cadena1.replace(cadena1, this.expresiones.get(1).getValor(entorno).toString());
    }

    @Override
    public Object getTipo(Entorno entorno) {
        return Expresion.TIPO_PRIMITIVO.STRING;
    }
}
