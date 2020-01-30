/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Objeto;
import Interpreter.Operation.Operation;
import Principal.Singleton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Apend implements Instruction{

    private final int row, column;
    private final Operation path;

    public Apend(int row, int column, Operation path) {
        this.row = row;
        this.column = column;
        this.path = path;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Objeto value = path.Ejecutar(env);
        String cadena = value.toString();
        try {
            File archivo = new File(cadena);
            if(!archivo.exists()){
                Singleton.getInstance().addError(row, column, "No existe el archivo: " + cadena);
                return null;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(cadena,true));
            Singleton.getInstance().setBuffer(writer,row,column);
        } catch (IOException ex) {
            Logger.getLogger(Write.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
