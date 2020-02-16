/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST;

import Analyzer.Token;

public class Entorno {
    
    Entorno padre;
    Principal principal;
    
    public Entorno(Entorno padre){
        this.padre = padre;
    }
    
    public Entorno(Principal principal){
        this.padre = null;
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
}
