/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Sentencias;

import AST.Entorno;
import AST.*;

public class Print extends Sentencia{

    public Expresion expresion;
    
    public Print(Expresion expresion){
        this.expresion = expresion;
    }
    
    @Override
    public Object ejecutar(Entorno entorno) {
        entorno.print(this.expresion.getValor(entorno).toString());
        return null;
    }

}
