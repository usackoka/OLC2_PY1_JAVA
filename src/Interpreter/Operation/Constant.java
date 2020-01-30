/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Operation;

import Interpreter.Enviorement;
import Interpreter.Objeto.*;
import Interpreter.Objeto.Objeto.Tipo;

public class Constant extends Operation{

    private final Object value;
    private final Tipo tipo;

    public Constant(Tipo tipo, Object value, int row, int column) {
        super(row, column);
        this.value = value;
        this.tipo = tipo;
    }
    
    @Override
    public Objeto Ejecutar(Enviorement env) {
        switch(tipo){
            case INT:
            case BOOLEAN:
            case DECIMAL:
                return new Primitivo(tipo,value);
            case NULL:
                return new Null();
            case CHAR:
            {
                String cadena = value.toString();
                if(cadena.length() == 3)
                    return new Primitivo(tipo,cadena.charAt(1));
                else{
                    switch(cadena.charAt(2)){
                        case '\'':
                            return new Primitivo(tipo,'\'');
                        case '"':
                            return new Primitivo(tipo,'\"');
                        case '\\':
                            return new Primitivo(tipo,'\\');
                        case 'n':
                            return new Primitivo(tipo,'\n');
                        default:
                            return new Primitivo(tipo,cadena.charAt(2));
                    }
                }
            }
            default:
            {
                String cadena = value.toString();
                String aux = "";
                for(int i = 0; i < cadena.length(); i++){
                    if(cadena.charAt(i) == '\\'){
                        switch(cadena.charAt(++i)){
                            case '0': 
                                aux += '\0';
                                break;
                            case 'n': 
                                aux += '\n';
                                break;
                            case 'r':
                                aux += '\r';
                                break;
                            case 't': 
                                aux += '\t';
                                break;
                            case '%': 
                                aux += "\\%";
                                break;
                            case '\'':
                                aux += "'";
                                break;
                            case '"':
                                aux += "\"";
                                break;
                            case '\\':
                                aux += "\\";
                                break;
                            case '\n':
                                aux += "\n";
                                break;
                            default :
                                aux += cadena.charAt(i);
                                break;
                        }
                    }
                    else{
                        aux += cadena.charAt(i);
                    }
                }
                return new Cadena(aux);
            }
        }
    }
    
}
