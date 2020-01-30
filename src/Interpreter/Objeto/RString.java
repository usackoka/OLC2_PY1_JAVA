/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

public class RString extends Objeto{

    private String cadena;
    
    public RString(String cadena){
        super(Tipo.RSTRING);
        this.cadena = cadena;
    }
    
    @Override
    public Object getValue() {
        return cadena;
    }

    @Override
    public void setValue(Object value) {
        this.cadena = value.toString();
    }

    @Override
    public Objeto Equals(Objeto compare, int fila, int columna) {
        return new Primitivo(Tipo.BOOLEAN,compare.toString().equals(cadena));
    }

    @Override
    public Objeto NotEquals(Objeto compare, int fila, int columna) {
        return new Primitivo(Tipo.BOOLEAN,!compare.toString().equals(cadena));
    }

    @Override
    public boolean compare(Objeto value) {
        if(value.getTipo() == this.getTipo()){
            this.cadena = value.toString();
            return true;
        }
        else if(value.getTipo() == Tipo.STRING){
            this.cadena = value.toString();
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return cadena;
    }
}
