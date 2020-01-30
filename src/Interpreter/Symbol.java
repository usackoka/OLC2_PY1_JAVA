package Interpreter;

import Interpreter.Objeto.Objeto;

public class Symbol {
    private String id;
    private Objeto value;

    public Symbol(String id, Objeto value) {
        this.id = id;
        this.value = value;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Objeto getValue() {
        return value;
    }

    public void setValue(Objeto value) {
        this.value = value;
    }
}
