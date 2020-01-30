package Interpreter.Objeto;

import Interpreter.Enviorement;

public abstract class Objeto {
    
    public enum Tipo{
        INT,
        DECIMAL,
        BOOLEAN,
        STRING,
        CHAR,
        ERROR,
        NULL,
        ARRAY,
        FUSION,
        RTXT,
        RTXTA,
        RTXTP,
        RTXTN,
        RBTON,
        RMENSAGE,
        RLABEL,
        NUEVO,
        RSTRING,
        VENTANA
    }
    
    Tipo tipo;
    private boolean isconst;
    
    public Objeto(Tipo tipo){
        this.tipo = tipo;
        this.isconst = false;
    }

    public Tipo getTipo() {
        return tipo;
    }
    
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean isIsconst() {
        return isconst;
    }

    public void setIsconst(boolean isconst) {
        this.isconst = isconst;
    }
    
    public abstract Object getValue();
    
    public abstract void setValue(Object value);
    
    public abstract Objeto Equals(Objeto compare, int fila, int columna);
    
    public abstract Objeto NotEquals(Objeto compare, int fila, int columna);
    
    public boolean isError(){
        return this.tipo == Tipo.ERROR;
    }
    
    public boolean isNumber(){
        return this.tipo == Tipo.CHAR || this.tipo == Tipo.INT || this.tipo == Tipo.DECIMAL;
    }
    
    public boolean compare(Tipo tipo){
        return this.tipo == tipo;
    }
    
    public abstract boolean compare(Objeto value);
    
    public boolean compareFunc(Objeto compare){
        if(this.tipo == compare.tipo){
            if(this.tipo == Tipo.FUSION){
                return this.compare(compare);
            }
            else if(this.tipo == Tipo.ARRAY){
                return this.compareFunc(compare);
            }
            return true;
        }
        return false;
    }
    
    public Objeto nuevoFusion(Enviorement env){
        switch(this.getTipo()){
            case INT:
                return new Primitivo(Tipo.INT,0);
            case DECIMAL:
                return new Primitivo(Tipo.DECIMAL,0.0);
            case CHAR:
                return new Primitivo(Tipo.CHAR,'\0');
            case BOOLEAN:
                return new Primitivo(Tipo.BOOLEAN,false);
            case FUSION:
                Fusion f = (Fusion)this;
                return new Fusion(f.getId(),null);
            case ARRAY:
                Arreglo a = (Arreglo)this;
                return a.ejecutar(env);
            case RSTRING:
                return new RString("");
        }
        return null;
    }
}
