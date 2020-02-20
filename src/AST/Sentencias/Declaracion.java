/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Primitivo;
import AST.Expresiones.Variable;
import AST.Sentencia;

public class Declaracion extends Sentencia{
    
    Expresion value;
    String id;
    public Declaracion(String id, Expresion value, int fila, int columna){
        this.id = id;
        this.value = value;
        this.fila = fila;
        this.columna = columna;
        this.valorDirecto = null;
    }
    
    //declaracion especial para cuando la creo desde código 
    Object valorDirecto, tipoDirecto;
    public Declaracion(String id, Object value, Object tipoDirecto, int fila, int columna){
        this.id = id;
        this.valorDirecto = value;
        this.tipoDirecto = tipoDirecto;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        Object valor = this.valorDirecto==null?value.getValor(entorno):this.valorDirecto;
        Object tipo = this.valorDirecto==null?Primitivo.getTipoDato(valor):this.tipoDirecto;
        Variable var = new Variable(valor,tipo,fila,columna);
        entorno.addVariable(id.toLowerCase(), var);
        return null;
    }

}
