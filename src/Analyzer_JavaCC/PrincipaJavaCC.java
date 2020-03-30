/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analyzer_JavaCC;

import AST.Entorno;
import AST.Principal;
import java.io.BufferedReader;
import java.io.StringReader;

/**
 *
 */
public class PrincipaJavaCC {

    public static void Analizar(String cad, Principal principal) throws ParseException {
          try {
            Gramatica parser = new Gramatica(new BufferedReader(new StringReader(cad)));
             parser.setPrincipal(principal);

             parser.Analizar();
           
             System.out.println("Ejecucion Correcta JavaCC");

        } catch (ParseException  e) {
            Analyzer.Token token = new Analyzer.Token();
            token.lexema = e.getMessage();
            token.componenteLexico = "Sintáctico";
            token.descripcion = "No se esperaba: "+ e.getMessage();;
            token.columna = "-";
            token.fila = "-";
            principal.addError(token);
            System.out.println("Error Sintactico:"+e);
            
        }
          catch (TokenMgrError e){
                Analyzer.Token token = new Analyzer.Token();
                token.lexema = e.getMessage();
                token.componenteLexico = "Léxico";
                token.descripcion = "El caracter no pertenece al lenguaje" + e.getLocalizedMessage();
                token.columna = "-";
                token.fila = "-";
                principal.addError(token);
                
                  System.out.println("Error Lexico: "+e);
          }
    
          
          
          
    }



   
}
