/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.*;
import Principal.Singleton;

public class Relational extends Operation {

    private final String tipo;
    private final Operation left, right;

    public Relational(int row, int column, Operation left, Operation right, String tipo) {
        super(row, column);
        this.tipo = tipo;
        this.left = left;
        this.right = right;
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
            case "<":
                return less(leftV, rightV);
            case "<=":
                return lessEqual(leftV, rightV);
            case ">":
                return greater(leftV, rightV);
            case ">=":
                return greaterEqual(leftV, rightV);
            case "<>":
                return notEqual(leftV, rightV);
            default:
                return equal(leftV, rightV);
        }
    }

    private Objeto less(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            boolean value = Double.parseDouble(left.toString()) < Double.parseDouble(right.toString());
            return new Primitivo(Objeto.Tipo.BOOLEAN, value);
        }
        return Singleton.getInstance().addError( new Errorr(getRow(), getColumn(), "RelationalException: No se puede " + left.getTipo() + " < " + right.getTipo()));
    }

    private Objeto greater(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            boolean value = Double.parseDouble(left.toString()) > Double.parseDouble(right.toString());
            return new Primitivo(Objeto.Tipo.BOOLEAN, value);
        }
        return Singleton.getInstance().addError( new Errorr(getRow(), getColumn(), "RelationalException: No se puede " + left.getTipo() + " > " + right.getTipo()));
    }

    private Objeto lessEqual(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            boolean value = Double.parseDouble(left.toString()) <= Double.parseDouble(right.toString());
            return new Primitivo(Objeto.Tipo.BOOLEAN, value);
        }
        return Singleton.getInstance().addError( new Errorr(getRow(), getColumn(), "RelationalException: No se puede " + left.getTipo() + " <= " + right.getTipo()));
    }

    private Objeto greaterEqual(Objeto left, Objeto right) {
        if (left.isNumber() && right.isNumber()) {
            boolean value = Double.parseDouble(left.toString()) >= Double.parseDouble(right.toString());
            return new Primitivo(Objeto.Tipo.BOOLEAN, value);
        }
        return Singleton.getInstance().addError( new Errorr(getRow(), getColumn(), "RelationalException: No se puede " + left.getTipo() + " >= " + right.getTipo()));
    }

    private Objeto equal(Objeto left, Objeto right) {
        return left.Equals(right, getRow(), getColumn());
    }

    private Objeto notEqual(Objeto left, Objeto right) {
        return left.NotEquals(right, getRow(), getColumn());
    }
}
