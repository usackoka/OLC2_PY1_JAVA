/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.*;
import Principal.Singleton;

public class UnExpr extends Operation {

    private final char tipo;
    private final Operation left;

    public UnExpr(int row, int column, Operation left, char tipo) {
        super(row, column);
        this.left = left;
        this.tipo = tipo;
    }

    @Override
    public Objeto Ejecutar(Enviorement env) {
        Objeto leftV = this.left.Ejecutar(env);
        if (leftV.isError()) {
            return leftV;
        }
        switch (this.tipo) {
            case '+':
                return plus(leftV);
            case '-':
                return minus(leftV);
            default:
                return not(leftV);
        }
    }
    
    private Objeto not(Objeto left){
        if(left.getTipo() == Objeto.Tipo.BOOLEAN){
            return new Primitivo(Objeto.Tipo.BOOLEAN,!(boolean)left.getValue());
        }
        return Singleton.getInstance().addError(new Errorr(getRow(),getColumn(),"LogicalException: No se puede not de " + left.getTipo()));
    }
    
    private Objeto plus(Objeto left){
        switch(left.getTipo()){
            case INT:{
                int value = (int)left.getValue();
                return new Primitivo(Objeto.Tipo.INT,value);
            }
            case DECIMAL:
            {
                double value = (double)left.getValue();
                return new Primitivo(Objeto.Tipo.DECIMAL,value);
            }
            case CHAR:
            {
                int value = (char)left.getValue();
                return new Primitivo(Objeto.Tipo.INT,value);
            }
            default:
                return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede unario mas de " + left.getTipo()));
        }
    }
    
    private Objeto minus(Objeto left){
        switch(left.getTipo()){
            case INT:{
                int value = -(int)left.getValue();
                return new Primitivo(Objeto.Tipo.INT,value);
            }
            case DECIMAL:
            {
                double value = -(double)left.getValue();
                return new Primitivo(Objeto.Tipo.DECIMAL,value);
            }
            case CHAR:
            {
                int value = -(char)left.getValue();
                return new Primitivo(Objeto.Tipo.INT,value);
            }
            default:
                return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede unario menos de " + left.getTipo()));

        }
    }
}
