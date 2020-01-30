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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Read implements Instruction{
    private final int row, column;
    private final Operation path, value;

    public Read(int row, int column, Operation path, Operation value) {
        this.row = row;
        this.column = column;
        this.path = path;
        this.value = value;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        Objeto origen = path.Ejecutar(env);
        Objeto destino = value.Ejecutar(env);
        if(!destino.compare(Objeto.Tipo.RSTRING)){
            Singleton.getInstance().addError(row, column, "Solo tipo RString");
            return null;
        }
        String temp = "";
        File file = new File(origen.toString());
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            if(Singleton.getInstance().setBufferReder(bf, row, column)){
                String cadena;
                while((cadena = bf.readLine())!= null){
                    temp += cadena;
                }
                destino.setValue(temp);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
            Singleton.getInstance().addError(row, column, "Error al abrir archivo");
        }
        catch(IOException ex){
            Singleton.getInstance().addError(row, column, "Error al leer archivo");
        }
        return null;
    }
    
}
