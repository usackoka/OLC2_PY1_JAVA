/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

import GraficasArit.Graph_AST;

public abstract class Nodo {
    public int fila;
    public int columna;
    
    public abstract int Recorrido(Graph_AST arbol);
}
