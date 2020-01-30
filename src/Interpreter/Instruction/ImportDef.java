/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;

public class ImportDef implements Instruction{

    public final String ruta;
    private final int row,column;
    
    public ImportDef(String ruta, int row, int column){
        this.ruta = ruta;
        this.row = row;
        this.column = column;
    }
    
    @Override
    public Object ejecutar(Enviorement env) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
