/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.*;
import Principal.Singleton;

public class PostExpr extends Operation {

    private final Operation left;
    private final char tipo;

    public PostExpr(int row, int column, Operation left, char tipo) {
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
        if (tipo == '+') {
            return plusPlus(leftV);
        } else {
            return minusMinus(leftV);
        }
    }

    private Objeto plusPlus(Objeto left) {
        if (left.isNumber()) {
            Objeto ret = new Primitivo(left.getTipo(), left.getValue());
            switch(left.getTipo()){
                case INT:
                    left.setValue(Integer.parseInt(left.getValue().toString()) + 1);
                    break;
                case CHAR   :
                    left.setValue((char)(Integer.parseInt(left.getValue().toString()) + 1));
                    break;
                default:
                    left.setValue(Double.parseDouble(left.getValue().toString()) + 1);
                    break;
            }
            return ret;
        }
        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: ++ no disponible para " + left.getTipo()));
    }

    private Objeto minusMinus(Objeto left) {
        if (left.isNumber()) {
            Objeto ret = new Primitivo(left.getTipo(), left.getValue());
            switch(left.getTipo()){
                case INT:
                    left.setValue(Integer.parseInt(left.getValue().toString()) - 1);
                    break;
                case CHAR   :
                    left.setValue((char)(Integer.parseInt(left.getValue().toString()) - 1));
                    break;
                default:
                    left.setValue(Double.parseDouble(left.getValue().toString()) - 1);
                    break;
            }
            return ret;
        }
        return Singleton.getInstance().addError( new Errorr(getRow(),getColumn(),"ArithmeticException: -- no disponible para " + left.getTipo()));
    }
}
/*main(){
 for(ent i = 0; i < 10; i++){
  _imp(i);
 }
 ent i = 0;
 for(i = 10; i > 0; i--){
  _imp(i);
 }
}*/
