/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

public class Primitivo extends Objeto{
    private Object value;

    public Primitivo(Objeto.Tipo tipo,Object value){
        super(tipo);
        this.value = value;
    }
    
    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Objeto Equals(Objeto compare, int fila, int columna) {
        boolean cond;
        if(this.isNumber() && compare.isNumber()){
            cond = this.value.toString().equals(compare.toString());
            return new Primitivo(Tipo.BOOLEAN,cond);            
        }
        else if(this.compare(Tipo.BOOLEAN) && compare.compare(Tipo.BOOLEAN)){
            cond = this.value.toString().equals(compare.toString());
            return new Primitivo(Tipo.BOOLEAN,cond);
        }
        return new Errorr(fila,columna,"Tipos diferentes");
    }

    @Override
    public Objeto NotEquals(Objeto compare, int fila, int columna) {
        boolean cond;
        if(this.isNumber() && compare.isNumber()){
            cond = this.value.toString().equals(compare.toString());
            return new Primitivo(Tipo.BOOLEAN,!cond);            
        }
        else if(this.compare(Tipo.BOOLEAN) && compare.compare(Tipo.BOOLEAN)){
            cond = this.value.toString().equals(compare.toString());
            return new Primitivo(Tipo.BOOLEAN,!cond);
        }
        return new Errorr(fila,columna,"Tipos diferentes");
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
    
    @Override
    public String toString(){
        return this.value.toString();
    }

    @Override
    public boolean compare(Objeto value) {
        if(value.getTipo() == this.getTipo())
            return true;
        switch(this.getTipo()){
            case INT:
                if(value.getTipo() == Tipo.DECIMAL){
                    value.setValue((int)Math.round((double)value.getValue()));
                    value.setTipo(Tipo.INT);
                    return true;
                }
                else if(value.getTipo() == Tipo.CHAR){
                    value.setValue((char)value.getValue() + 0);
                    value.setTipo(Tipo.INT);
                    return true;
                }
                return false;
            case CHAR:
                if(value.getTipo() == Tipo.INT){
                    value.setValue((char)((int)value.getValue() + '\0'));
                    value.setTipo(Tipo.CHAR);
                    return true;
                }
                return false;
            case DECIMAL:
                if(value.getTipo() == Tipo.INT){
                    value.setValue(Double.parseDouble(value.getValue().toString()));
                    value.setTipo(Tipo.DECIMAL);
                    return true;
                }
                else if(value.getTipo() == Tipo.CHAR){
                    value.setValue((char)value.getValue() + 0.0);
                    value.setTipo(Tipo.DECIMAL);
                    return true;
                }
                return false;
            default:
                if(value.getTipo() == Tipo.INT){
                    value.setValue((int)value.getValue() == 0);
                    value.setTipo(Tipo.BOOLEAN);
                    return true;
                }
                else if(value.getTipo() == Tipo.CHAR){
                    value.setValue((char)value.getValue() == '0');
                    value.setTipo(Tipo.BOOLEAN);
                    return true;
                }
        }
        return false;
    }
    
    public Objeto nuevo(){
        switch(this.getTipo()){
            case INT:
            {
                int val = (int)this.value;
                return new Primitivo(Tipo.INT,val);
            }
            case DECIMAL:
            {
                double val = (double)this.value;
                return new Primitivo(Tipo.DECIMAL,val);
            }
            case CHAR:
            {
                char val = (char)this.value;
                return new Primitivo(Tipo.CHAR,val);
            }
            default:
            {
                boolean val = (boolean)this.value;
                return new Primitivo(Tipo.BOOLEAN,val);
            }
        }
    }
}
