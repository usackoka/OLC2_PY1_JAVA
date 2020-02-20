/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

import AST.Expresiones.Parametro;
import AST.Expresiones.Variable;
import AST.Sentencias.Funcion;
import Analyzer.Token;
import java.util.Hashtable;
import java.util.LinkedList;

public class Entorno {
    
    Entorno padre;
    Principal principal;
    Hashtable<String, Variable> tbs;
    Hashtable<String, Funcion> funciones;
    
    public Entorno(Entorno padre){
        this.padre = padre;
        this.tbs = new Hashtable<>();
        this.funciones = new Hashtable<>();
    }
    
    public Entorno(Principal principal){
        this.padre = null;
        this.tbs = new Hashtable<>();
        this.funciones = new Hashtable<>();
        this.principal = principal;
    }
    
    public Object ejecutarFuncion(String id, LinkedList<Expresion> parametros, int fila, int columna){
        Entorno global = getEntornoGlobal();
        if(global.funciones.containsKey(id.toLowerCase())){
            Funcion f = global.funciones.get(id.toLowerCase());
            LinkedList<Object> valoresParametros = new LinkedList<>();
            for(Expresion parametro : parametros){
                valoresParametros.add(parametro.getValor(this));
            }
            f.valoresParametros = valoresParametros;
            return f.ejecutar(new Entorno(this));
        }else{
            addError(new Token("Error llamada a: "+id,"No existe la función",fila,columna));
            return null;
        }
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
        if (existeVariable(key)) {
            //si ya la contiene se reemplaza
            getVariable(key, 0, 0).setValue(value.getValor(this));
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
            return this.tbs.get(id).getValor(this);
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

    public Variable getVariable(String id, int fila, int columna)
    {
        if (this.tbs.containsKey(id))
        {
            return this.tbs.get(id);
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
    
    public boolean existeVariable(String id){
        if (this.tbs.containsKey(id))
        {
            return true;
        }
        else
        {
            if (this.padre == null)
            {
                return false;
            }
            else
            {
                return this.padre.existeVariable(id);
            }
        }
    }
}
