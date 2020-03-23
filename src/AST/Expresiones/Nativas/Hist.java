/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Sentencia;

public class Hist extends Sentencia{

    Expresion v, main, xlab, xlim, ylim;
    
    public Hist(Expresion v, Expresion main, Expresion xlab, int fila, int columna){
        this.v = v;
        this.main = main;
        this.xlab = xlab;
        this.xlim = xlim;
        this.ylim = ylim;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
