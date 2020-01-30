/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import javafx.scene.control.Button;

public class Rboton extends Objeto {

    private Button boton;
    private String nombre;

    public Rboton() {
        super(Tipo.RBTON);
    }

    public void nuevoBoton() {
        this.boton = new Button("boton");
    }

    public void setNombreBoton(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Object getValue() {
        return this.boton;
    }

    @Override
    public void setValue(Object value) {
        this.boton = (Button) value;
    }

    @Override
    public Objeto Equals(Objeto compare, int fila, int columna) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Objeto NotEquals(Objeto compare, int fila, int columna) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean compare(Objeto value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
