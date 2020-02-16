/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

public abstract class Sentencia implements Nodo{
    public abstract Object ejecutar(Entorno entorno);
}
