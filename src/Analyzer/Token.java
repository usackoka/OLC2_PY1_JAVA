/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Analyzer;

public class Token {
    public String lexema, componenteLexico, descripcion, columna, fila, archivo;
    
    public Token(){
    }

    public Token(String lexema, String componenteLexico, String descripcion, String fila, String columna) {
        this.lexema = lexema;
        this.componenteLexico = componenteLexico;
        this.descripcion = descripcion;
        this.columna = columna;
        this.fila = fila;
        this.archivo = "";
    }
    
    
}
