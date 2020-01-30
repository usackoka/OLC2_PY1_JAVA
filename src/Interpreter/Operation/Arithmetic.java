/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.*;
import Principal.Singleton;

public class Arithmetic extends Operation {

    private final Operation left, right;
    private final char tipo;

    public Arithmetic(int row, int column, Operation left, Operation right, char tipo) {
        super(row, column);
        this.left = left;
        this.right = right;
        this.tipo = tipo;
    }

    @Override
    public Objeto Ejecutar(Enviorement env) {
        Objeto leftV = this.left.Ejecutar(env);
        Objeto rightV = this.right.Ejecutar(env);
        if (leftV.isError()) {
            return leftV;
        } else if (rightV.isError()) {
            return rightV;
        }
        switch (this.tipo) {
            case '+':
                return plus(leftV, rightV);
            case '-':
                return minus(leftV, rightV);
            case '*':
                return times(leftV, rightV);
            case '/':
                return div(leftV, rightV);
            case '^':
                return pot(leftV, rightV);
            default:
                return mod(leftV, rightV);
        }
    }

    private Objeto plus(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            switch(left.getTipo()){
                case DECIMAL:{
                    double value = (double)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value += (int)right.getValue();
                            break;
                        case CHAR:
                            value += (char)right.getValue();
                            break;
                        default:
                            value += (double)right.getValue();
                    }
                    return new Primitivo(Objeto.Tipo.DECIMAL,value);
                }
                case INT:{
                    int value = (int)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value += (int)right.getValue();
                            break;
                        case CHAR:
                            value += (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,(double)right.getValue() + value);
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);   
                }
                case CHAR:{
                    int value = (char)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value += (int)right.getValue();
                            break;
                        case CHAR:
                            value += (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,(double)right.getValue() + value);
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);
                }
            }
        }
        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede sumar " + left.getTipo() + " con " + right.getTipo()));
    }

    private Objeto minus(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            switch(left.getTipo()){
                case DECIMAL:{
                    double value = (double)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value -= (int)right.getValue();
                            break;
                        case CHAR:
                            value -= (char)right.getValue();
                            break;
                        default:
                            value -= (double)right.getValue();
                    }
                    return new Primitivo(Objeto.Tipo.DECIMAL,value);
                }
                case INT:{
                    int value = (int)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value -= (int)right.getValue();
                            break;
                        case CHAR:
                            value -= (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,value - (double)right.getValue());
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);   
                }
                case CHAR:{
                    int value = (char)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value -= (int)right.getValue();
                            break;
                        case CHAR:
                            value -= (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,value - (double)right.getValue());
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);
                }
            }
        }
        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede restar " + left.getTipo() + " con " + right.getTipo()));
    }

    private Objeto times(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            switch(left.getTipo()){
                case DECIMAL:{
                    double value = (double)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value *= (int)right.getValue();
                            break;
                        case CHAR:
                            value *= (char)right.getValue();
                            break;
                        default:
                            value *= (double)right.getValue();
                    }
                    return new Primitivo(Objeto.Tipo.DECIMAL,value);
                }
                case INT:{
                    int value = (int)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value *= (int)right.getValue();
                            break;
                        case CHAR:
                            value *= (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,(double)right.getValue() * value);
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);   
                }
                case CHAR:{
                    int value = (char)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value *= (int)right.getValue();
                            break;
                        case CHAR:
                            value *= (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,(double)right.getValue() * value);
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);
                }
            }
        }
        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede multiplicar " + left.getTipo() + " con " + right.getTipo()));
    }

    private Objeto div(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            switch(left.getTipo()){
                case DECIMAL:{
                    double value = (double)left.getValue();
                    double divisor = 0;
                    switch(right.getTipo()){
                        case INT:
                            divisor = (int)right.getValue();
                            break;
                        case CHAR:
                            divisor = (char)right.getValue();
                            break;
                        default:
                            divisor = (double)right.getValue();
                    }
                    if(divisor == 0)
                        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede dividir entre 0"));                    
                    return new Primitivo(Objeto.Tipo.DECIMAL,value / divisor);
                }
                case INT:{
                    int value = (int)left.getValue();
                    int divisor;
                    switch(right.getTipo()){
                        case INT:
                            divisor = (int)right.getValue();
                            break;
                        case CHAR:
                            divisor = (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,value / (double)right.getValue());
                    }
                    if(divisor == 0)
                        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede dividir entre 0"));
                    return new Primitivo(Objeto.Tipo.INT,Math.round(value/divisor));   
                }
                case CHAR:{
                    int value = (char)left.getValue();
                    int divisor;
                    switch(right.getTipo()){
                        case INT:
                            divisor = (int)right.getValue();
                            break;
                        case CHAR:
                            divisor = (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,value / (double)right.getValue());
                    }
                    if(divisor == 0)
                        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede dividir entre 0"));                    
                    return new Primitivo(Objeto.Tipo.INT,Math.round(value/divisor));   
                }
            }
        }
        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede restar " + left.getTipo() + " con " + right.getTipo()));
    }

    private Objeto pot(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            switch(left.getTipo()){
                case DECIMAL:{
                    double value = (double)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value = Math.pow(value, (int)right.getValue());
                            break;
                        case CHAR:
                            value = Math.pow(value, (char)right.getValue());
                            break;
                        default:
                            value = Math.pow(value,(double)right.getValue());
                    }
                    return new Primitivo(Objeto.Tipo.DECIMAL,value);
                }
                case INT:{
                    int value = (int)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value = (int)Math.round(Math.pow(value, (int)right.getValue()));
                            break;
                        case CHAR:
                            value = (int)Math.round(Math.pow(value, (char)right.getValue()));
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,Math.pow(value,(double)right.getValue() + value));
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);   
                }
                case CHAR:{
                    int value = (char)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value = (int)Math.round(Math.pow(value, (int)right.getValue()));
                            break;
                        case CHAR:
                            value = (int)Math.round(Math.pow(value, (char)right.getValue()));
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,Math.pow(value,(double)right.getValue() + value));
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);
                }
            }
        }
        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede elevar " + left.getTipo() + " con " + right.getTipo()));
    }

    private Objeto mod(Objeto left, Objeto right)   {
        if (left.isNumber() && right.isNumber()) {
            switch(left.getTipo()){
                case DECIMAL:{
                    double value = (double)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value %= (int)right.getValue();
                            break;
                        case CHAR:
                            value %= (char)right.getValue();
                            break;
                        default:
                            value %= (double)right.getValue();
                    }
                    return new Primitivo(Objeto.Tipo.DECIMAL,value);
                }
                case INT:{
                    int value = (int)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value %= (int)right.getValue();
                            break;
                        case CHAR:
                            value %= (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,value % (double)right.getValue());
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);   
                }
                case CHAR:{
                    int value = (char)left.getValue();
                    switch(right.getTipo()){
                        case INT:
                            value += (int)right.getValue();
                            break;
                        case CHAR:
                            value += (char)right.getValue();
                            break;
                        default:
                            return new Primitivo(Objeto.Tipo.DECIMAL,value % (double)right.getValue());
                    }
                    return new Primitivo(Objeto.Tipo.INT,value);
                }
            }
        }
        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: No se puede modular " + left.getTipo() + " con " + right.getTipo()));
    }
}
