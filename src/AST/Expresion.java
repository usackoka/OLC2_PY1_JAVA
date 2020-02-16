/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

public abstract class Expresion extends Nodo{
    
    public enum TIPO_PRIMITIVO{
        INTEGER, STRING, DOUBLE, BOOLEAN
    }
    
    public Object TIPO;
    public abstract Object getValor(Entorno entorno);
    public abstract Object getTipo(Entorno entorno);
}
