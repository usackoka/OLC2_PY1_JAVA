/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Instruction;

import Interpreter.Enviorement;
import Interpreter.Objeto.Arreglo;
import Interpreter.Objeto.Objeto;
import Interpreter.Objeto.Primitivo;
import Interpreter.Operation.Constant;
import java.util.LinkedList;

public class FunctionGui implements Instruction {

    private final int row, column;
    private final String id;
    private final String tipo;
    private final LinkedList<Instruction> instructions;
    private String nombreArchivo;

    public FunctionGui(String id, String tipo, LinkedList<Instruction> instructions, int row, int column) {
        this.row = row;
        this.column = column;
        this.id = id;
        this.tipo = tipo;
        this.instructions = instructions;
    }

    @Override
    public Object ejecutar(Enviorement env) {
        if (this.tipo.equalsIgnoreCase("iniciar_ventana")) {
            String nombreMetodo = "iniciar_ventana_" + nombreArchivo;
            generarVariable(env);
            LinkedList<Parameter> list = new LinkedList<>();
            Function f = new Function(nombreMetodo, list, instructions, null, 0, 0, true);
            env.AddFunction(f, nombreMetodo);
        } else if (this.tipo.equalsIgnoreCase("al_dar_click")) {
            String nombreMetodo = "__al_dar_click_" + id;
            LinkedList<Parameter> list = new LinkedList<>();
            Function f = new Function(nombreMetodo, list, instructions, null, 0, 0, true);
            env.AddFunction(f, nombreMetodo);
        }
        return null;
    }

    private void generarVariable(Enviorement env) {
        String nombreMetodo = "iniciar_ventana_" + nombreArchivo;
        LinkedList<String> lid = new LinkedList<>();
        lid.add(nombreArchivo);
        Constant cadena = new Constant(Objeto.Tipo.STRING, nombreMetodo, row, column);
        Constant tam = new Constant(Objeto.Tipo.INT, nombreMetodo.length(), row, column);
        Arreglo arreglo = new Arreglo(null, tam);
        arreglo.setTipoarreglo(new Primitivo(Objeto.Tipo.CHAR, null));
        VarDef vd = new VarDef(lid, cadena, arreglo, row, column);
        vd.ejecutar(env);
    }

    public void setNombre(String nombre) {
        this.nombreArchivo = nombre;
    }

    public String getNombre() {
        return this.nombreArchivo;
    }
}
