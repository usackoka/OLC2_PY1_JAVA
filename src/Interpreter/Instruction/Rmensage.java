/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Arreglo;
import Interpreter.Objeto.Objeto;
import Interpreter.Operation.Operation;
import Principal.Singleton;
import java.util.LinkedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Rmensage implements Instruction {

    private final int row, column;
    private final LinkedList<Operation> values;

    public Rmensage(int row, int column, LinkedList<Operation> values) {
        this.row = row;
        this.column = column;
        this.values = values;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        String mensajeSalida = "";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        Objeto op1 = values.get(0).Ejecutar(env);
        if (op1.compare(Objeto.Tipo.STRING)) {
            int contador = 1;
            String cadena = op1.toString();
            for (int i = 0; i < cadena.length(); i++) {
                char c = cadena.charAt(i);
                if (c == '\\' && cadena.charAt(i + 1) == '%') {
                    mensajeSalida += "\\";
                    mensajeSalida += "%";
                    i++;
                } else if (c == '%') {
                    if (values.size() < contador) {
                        continue;
                    }
                    op1 = values.get(contador++).Ejecutar(env);
                    c = cadena.charAt(++i);
                    switch (c) {
                        case 'd':
                            if (op1.compare(Objeto.Tipo.DECIMAL)) {
                                mensajeSalida += op1.toString();
                            } else {
                                Singleton.getInstance().addError(row, column, "Se esperaba decimal");
                            }
                            break;
                        case 'e':
                            if (op1.compare(Objeto.Tipo.INT)) {
                                mensajeSalida += op1.toString();
                            } else {
                                Singleton.getInstance().addError(row, column, "Se esperaba entero");
                            }
                            break;
                        case 'b':
                            if (op1.compare(Objeto.Tipo.BOOLEAN)) {
                                mensajeSalida += op1.toString();
                            } else {
                                Singleton.getInstance().addError(row, column, "Se esperaba booleano");
                            }
                            break;
                        case 'c':
                            if (op1.compare(Objeto.Tipo.CHAR)) {
                                mensajeSalida += op1.toString();
                            } else {
                                Singleton.getInstance().addError(row, column, "Se esperaba caracter");
                            }
                            break;
                        case 's':
                            if (op1.compare(Objeto.Tipo.STRING)) {
                                mensajeSalida += op1.toString();
                            } else if (op1.compare(Objeto.Tipo.ARRAY)) {
                                Arreglo arr = (Arreglo) op1;
                                if (arr.getTipoarreglo().compare(Objeto.Tipo.CHAR)) {
                                    arr.Print();
                                } else {
                                    Singleton.getInstance().addError(row, column, "Se esperaba arreglo de caracteres");
                                }
                            } else {
                                Singleton.getInstance().addError(row, column, "Se esperaba cadena");
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    mensajeSalida += c;
                }
            }
        }
        alert.setContentText(mensajeSalida);
        alert.showAndWait();
        return null;
    }
}
