/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

import AST.Expresiones.Variable;
import Analyzer.Token;
import java.util.Hashtable;

public class Entorno {
    
    Entorno padre;
    Principal principal;
    Hashtable<String, Object> tbs;
    
    public Entorno(Entorno padre){
        this.padre = padre;
        this.tbs = new Hashtable<>();
    }
    
    public Entorno(Principal principal){
        this.padre = null;
        this.tbs = new Hashtable<>();
        this.principal = principal;
    }
    
    public void addError(Token token){
        if(this.padre==null){
            this.principal.addError(token);
        }else{
            getEntornoGlobal().addError(token);
        }
    }
    
    public void print(String cadena){
        if(this.padre==null){
            this.principal.print(cadena);
        }else{
            getEntornoGlobal().print(cadena);
        }
    }
    
    public Entorno getEntornoGlobal(){
        Entorno aux = this;
        while(aux.padre!=null){
            aux = aux.padre;
        }
        return aux;
    }
    
    public Object addVariable(String key, Variable value) {
        if (this.tbs.containsKey(key)) {
            //si ya la contiene se reemplaza
            ((Variable)this.tbs.get(key)).setValue(value);
            //addError(new Token("ObjectAlreadyExists", "Ya existe la variable con id:"+key+" declarada en este ámbito",fila,columna));
            return false;
        }
        this.tbs.put(key, value);
        return true;
    }

    public Object getTipoVariable(String id, int fila, int columna)
    {
        if (this.tbs.containsKey(id))
        {
            return ((Variable)this.tbs.get(id)).getTipo(this);
        }
        else
        {
            if (this.padre == null)
            {
                addError(new Token(id, "No se encontró la variable: " + id + " en ningún ambito (getTipoVariable)", fila, columna));
                return Expresion.TIPO_PRIMITIVO.STRING;
            }
            else
            {
                return this.padre.getTipoVariable(id, fila, columna);
            }
        }
    }

    public Object getValorVariable(String id, int fila, int columna) {
        if (this.tbs.containsKey(id))
        {
            return ((Variable)this.tbs.get(id)).getValor(this);
        }
        else {
            if (this.padre == null)
            {
                addError(new Token(id,"No se encontró la variable: "+id+" en ningún ambito (getValorVariable)",fila,columna));
                return "";
            }
            else {
                return this.padre.getValorVariable(id, fila, columna);
            }
        }
    }

    public Object getVariable(String id, int fila, int columna)
    {
        if (this.tbs.containsKey(id))
        {
            return (this.tbs.get(id));
        }
        else
        {
            if (this.padre == null)
            {
                addError(new Token(id, "No se encontró la variable: " + id + " en ningún ambito (getVariable)", fila, columna));
                return new Variable("", Expresion.TIPO_PRIMITIVO.STRING, fila, columna);
            }
            else
            {
                return this.padre.getVariable(id, fila, columna);
            }
        }
    }
}
