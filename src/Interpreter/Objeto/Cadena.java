/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

public class Cadena extends Objeto{

    private String cadena;
    private boolean isarray = false;
    
    public Cadena(String cadena) {
        super(Tipo.STRING);
        this.cadena = cadena;
    }

    @Override
    public Object getValue() {
        return cadena;
    }

    public boolean getIsArray(){
        return this.isarray;
    }
    
    public void setIsArray(boolean isarray){
        this.isarray = isarray;
    }
    
    @Override
    public void setValue(Object value) {
        this.cadena = value.toString();
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
    public String toString(){
        return this.cadena;
    }

    @Override
    public boolean compare(Objeto value) {
        return value.getTipo() == Tipo.STRING;
    }
    
    public Objeto nuevo(){
        if(!this.isarray)
            return this;
        
        Arreglo arr = new Arreglo(new Primitivo(Objeto.Tipo.CHAR,null));
        arr.newArray(cadena.length());
        for(int i = 0; i < cadena.length(); i++){
            arr.addValue(new Primitivo(Objeto.Tipo.CHAR,cadena.charAt(i)), i);
        }
        return arr;
    }
}
