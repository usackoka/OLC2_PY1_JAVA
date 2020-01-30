/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.*;
import Principal.Singleton;

public class Logical extends Operation {

    private final char tipo;
    private final Operation left, right;

    public Logical(int row, int column, Operation left, Operation right, char tipo) {
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
        if (tipo == '&') {
            return and(leftV, rightV);
        } else {
            return or(leftV, rightV);
        }
    }

    private Objeto and(Objeto left, Objeto right) {
        if (left.getTipo() == Objeto.Tipo.BOOLEAN && right.getTipo() == Objeto.Tipo.BOOLEAN) {
            boolean value = (boolean)left.getValue() && (boolean)right.getValue();
            return new Primitivo(Objeto.Tipo.BOOLEAN, value);
        }
        return Singleton.getInstance().addError( new Errorr(getRow(), getColumn(), "LogicalException: No se puede " + left.getTipo() + " && " + right.getTipo()));
    }

    private Objeto or(Objeto left, Objeto right) {
        if (left.getTipo() == Objeto.Tipo.BOOLEAN && right.getTipo() == Objeto.Tipo.BOOLEAN) {
            boolean value = (boolean)left.getValue() || (boolean)right.getValue();
            return new Primitivo(Objeto.Tipo.BOOLEAN, value);
        }
        return Singleton.getInstance().addError( new Errorr(getRow(), getColumn(), "LogicalException: No se puede " + left.getTipo() + " && " + right.getTipo()));
    }
}
