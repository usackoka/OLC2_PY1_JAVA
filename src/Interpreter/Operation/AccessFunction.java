/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Instruction.*;
import Interpreter.Objeto.*;
import Interpreter.Symbol;
import Principal.Singleton;
import java.util.LinkedList;

public class AccessFunction extends Operation {

    private final Operation anterior;
    private final String id;
    private final LinkedList<Operation> params;

    public AccessFunction(int row, int column, Operation anterior, String id, LinkedList<Operation> params) {
        super(row, column);
        this.anterior = anterior;
        this.id = id;
        this.params = params;
    }

    @Override
    public Objeto Ejecutar(Enviorement env) {
        if (anterior == null) {
            LinkedList<Objeto> list = new LinkedList<>();
            params.forEach((op) -> {
                list.add(op.Ejecutar(env));
            });
            Function f = env.getFunction(id, list);
            if (f == null) {
                return Singleton.getInstance().addError(new Errorr(getRow(), getColumn(), "FunctionException: la sobrecarga " + this.id + " no existe"));
            }

            Enviorement nueva = new Enviorement(env.getGlobal());
            for (int i = 0; i < list.size(); i++) {
                Parameter actual = f.getParameters().get(i);
                Objeto actualobj = list.get(i);
                if (actualobj instanceof Primitivo) {
                    actualobj = ((Primitivo) actualobj).nuevo();
                } else if (actualobj.compare(Objeto.Tipo.STRING)) {
                    actualobj = ((Cadena) actualobj).nuevo();
                }

                if (!nueva.add(actual.getId(), actualobj)) {
                    return Singleton.getInstance().addError(new Errorr(getRow(), getColumn(), "ValuesException: ya existe el identificador " + actual.getId()));
                }
            }

            for (Instruction ins : f.getInstruction()) {
                Object obj = ins.ejecutar(nueva);
                if (obj != null) {
                    if (obj instanceof Errorr) {
                        return (Errorr) obj;
                    } else if (obj instanceof Return) {
                        return new Null();
                    } else if (obj instanceof Objeto) {
                        //TODO... comprobar tipos
                        return (Objeto) obj;
                    } else {
                        return Singleton.getInstance().addError(new Errorr(getRow(), getColumn(), ""));
                    }
                }
            }
        } else {
            Symbol simbolo = env.getValue(((Access) anterior).id);
            Object componente = null;
            if (simbolo.getValue().getTipo() == Objeto.Tipo.RLABEL
                    || simbolo.getValue().getTipo() == Objeto.Tipo.RTXT
                    || simbolo.getValue().getTipo() == Objeto.Tipo.RTXTA
                    || simbolo.getValue().getTipo() == Objeto.Tipo.RTXTP
                    || simbolo.getValue().getTipo() == Objeto.Tipo.RTXTN
                    || simbolo.getValue().getTipo() == Objeto.Tipo.RBTON) {
                componente = simbolo.getValue();
            }
            if (id.equalsIgnoreCase("setancho")) {
                Objeto param = params.get(0).Ejecutar(env);
                setAncho sa = new setAncho(componente, param);
                sa.ejecutar(env);
            } else if (id.equalsIgnoreCase("setalto")) {
                Objeto param = params.get(0).Ejecutar(env);
                setAlto sa = new setAlto(componente, param);
                sa.ejecutar(env);
            } else if (id.equalsIgnoreCase("settexto")) {
                Objeto param = params.get(0).Ejecutar(env);
                setTexto st = new setTexto(componente, param);
                st.ejecutar(env);
            } else if (id.equalsIgnoreCase("setpos")) {
                Objeto param = params.get(0).Ejecutar(env);
                Objeto param1 = params.get(1).Ejecutar(env);
                setPos sp = new setPos(componente, param, param1);
                sp.ejecutar(env);
            } else if (id.equalsIgnoreCase("getancho")) {
                getAncho ga = new getAncho(componente);
                return (Objeto) ga.ejecutar(env);
            } else if (id.equalsIgnoreCase("getalto")) {
                getAlto ga = new getAlto(componente);
                return (Objeto) ga.ejecutar(env);
            } else if (id.equalsIgnoreCase("gettexto")) {
                getTexto gt = new getTexto(componente);
                return (Objeto) gt.ejecutar(env);
            } else if (id.equalsIgnoreCase("getpos")) {
                getPos gp = new getPos(componente);
                return (Objeto) gp.ejecutar(env);
            }
        }
        return null;
    }
}
