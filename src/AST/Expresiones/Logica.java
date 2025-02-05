/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.Nativas.Matrix;
import AST.Expresiones.Nativas.VectorArit;
import Analyzer.Token;
import GraficasArit.Graph_AST;

public class Logica extends Expresion{
    Expresion left, right;
    TIPO_OPERACION TipoOperacion;
    
    public Logica(Expresion left,Expresion right, Logica.TIPO_OPERACION TipoOperacion, int fila, int columna){
        this.left = left;
        this.right = right;
        this.fila = fila;
        this.columna = columna;
        this.TipoOperacion = TipoOperacion;
    }
    
    public enum TIPO_OPERACION{
        AND,OR
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        Object valLeft = left.getValor(entorno);
        Object valRight = right.getValor(entorno);
        
        try {
            //========== VALIDO OPERACIONES ENTRE VECTORES
            if(valLeft instanceof VectorArit && valRight instanceof VectorArit){
                VectorArit tempLeft = (VectorArit)valLeft;
                VectorArit tempRight = (VectorArit)valRight;
                VectorArit tempNew = new VectorArit();
                if(tempLeft.size()!=tempRight.size()){
                    entorno.addError(new Token("OPERACION-"+TipoOperacion+" entre vectores", "Los vectores no poseen el mismo tamaño", fila, columna));
                    return new VectorArit();
                }
                for(int i=0;i<tempLeft.size();i++){
                    Primitivo pleft = new Primitivo(tempLeft.get(i), Primitivo.getTipoDato(tempLeft.get(i)),fila,columna);
                    Primitivo pright = new Primitivo(tempRight.get(i), Primitivo.getTipoDato(tempRight.get(i)),fila,columna);
                    Logica operacion = new Logica(pleft,pright,TipoOperacion,fila, columna);
                    tempNew.add(operacion.getValor(entorno));
                }
                return tempNew;
            }
            else if(valLeft instanceof VectorArit){
                VectorArit temp = (VectorArit)valLeft;
                VectorArit tempNew = new VectorArit();
                for(int i = 0; i<temp.size(); i++){
                    Primitivo element = new Primitivo(temp.get(i), Primitivo.getTipoDato(temp.get(i)),fila,columna);
                    Primitivo pright = new Primitivo(valRight, Primitivo.getTipoDato(valRight), fila, columna);
                    Logica operacion = new Logica(element,pright,TipoOperacion, fila, columna);
                    tempNew.add(operacion.getValor(entorno));
                }
                return tempNew;
            }
            else if(valRight instanceof VectorArit){
                VectorArit temp = (VectorArit)valRight;
                VectorArit tempNew = new VectorArit();
                for(int i = 0; i<temp.size(); i++){
                    Primitivo element = new Primitivo(temp.get(i), Primitivo.getTipoDato(temp.get(i)),fila,columna);
                    Primitivo pleft = new Primitivo(valLeft, Primitivo.getTipoDato(valLeft), fila, columna);
                    Logica operacion = new Logica(pleft,element,TipoOperacion, fila, columna);
                    tempNew.add(operacion.getValor(entorno));
                }
                return tempNew;
            }
            
            //===================== VALIDO OPERACIONES ENTRE MATRICES
            else if(valLeft instanceof Matrix && valRight instanceof Matrix){
                Matrix tempLeft = (Matrix)valLeft;
                Matrix tempRight = (Matrix)valRight;
                Matrix tempNew = new Matrix(tempLeft.getNRow(),tempLeft.getNCol());
                if(tempLeft.getNCol()!=tempRight.getNCol() && tempLeft.getNRow()!=tempRight.getNRow()){
                    entorno.addError(new Token("OPERACION-"+TipoOperacion+" entre matrices", "Las matrices no tienen las mismas dimensiones", fila, columna));
                    return tempNew;
                }
                for(int i=0; i<tempLeft.getNRow(); i++){
                    for(int j=0; j<tempLeft.getNCol(); j++){
                        Primitivo pleft = new Primitivo(tempLeft.getValorIndex(i+1, j+1,null, entorno,fila,columna), Primitivo.getTipoDato(tempLeft.getValorIndex(i+1, j+1, null,entorno,fila,columna)),fila,columna);
                        Primitivo pright = new Primitivo(tempRight.getValorIndex(i+1, j+1, null,entorno,fila,columna), Primitivo.getTipoDato(tempRight.getValorIndex(i+1, j+1, null,entorno,fila,columna)),fila,columna);
                        Logica operacion = new Logica(pleft,pright,TipoOperacion,fila, columna);
                        tempNew.setValor(i+1,j+1,operacion.getValor(entorno),entorno);
                    }
                }
                return tempNew;
            }
            else if(valLeft instanceof Matrix){
                Matrix tempLeft = (Matrix)valLeft;
                Matrix tempNew = new Matrix(tempLeft.getNRow(),tempLeft.getNCol());
                for(int i=0; i<tempLeft.getNRow(); i++){
                    for(int j=0; j<tempLeft.getNCol(); j++){
                        Primitivo pleft = new Primitivo(tempLeft.getValorIndex(i+1, j+1, null,entorno,fila,columna), Primitivo.getTipoDato(tempLeft.getValorIndex(i+1, j+1, null,entorno,fila,columna)),fila,columna);
                        Primitivo pright = new Primitivo(valRight, Primitivo.getTipoDato(valRight),fila,columna);
                        Logica operacion = new Logica(pleft,pright,TipoOperacion,fila, columna);
                        tempNew.setValor(i+1,j+1,operacion.getValor(entorno),entorno);
                    }
                }
                return tempNew;
            }
            else if(valRight instanceof Matrix){
                Matrix tempRight = (Matrix)valRight;
                Matrix tempNew = new Matrix(tempRight.getNRow(),tempRight.getNCol());
                for(int i=0; i<tempRight.getNRow(); i++){
                    for(int j=0; j<tempRight.getNCol(); j++){
                        Primitivo pleft = new Primitivo(valLeft, Primitivo.getTipoDato(valLeft),fila,columna);
                        Primitivo pright = new Primitivo(tempRight.getValorIndex(i+1, j+1, null,entorno,fila,columna), Primitivo.getTipoDato(tempRight.getValorIndex(i+1, j+1,null, entorno,fila,columna)),fila,columna);
                        Logica operacion = new Logica(pleft,pright,TipoOperacion,fila, columna);
                        tempNew.setValor(i+1,j+1,operacion.getValor(entorno),entorno);
                    }
                }
                return tempNew;
            }
            
            switch (TipoOperacion)
            {
                case AND:
                    if (valLeft instanceof Boolean && valRight instanceof Boolean)
                    {
                        return Boolean.parseBoolean(String.valueOf(valLeft)) && Boolean.parseBoolean(String.valueOf(valRight));
                    }
                case OR:
                    if (valLeft instanceof Boolean && valRight instanceof Boolean)
                    {
                        return Boolean.parseBoolean(String.valueOf(valLeft)) || Boolean.parseBoolean(String.valueOf(valRight));
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        entorno.addError(new Token("Logica", "No soportado: "+TipoOperacion+" tipos:" + Primitivo.getTipoDato(valLeft) + " y " + Primitivo.getTipoDato(valRight), fila, columna));
        return false;
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        int cont_raiz = arbol.getNextContGraph();
        arbol.addNodoGraph(cont_raiz, TipoOperacion.toString());
        
        int cont_hijo = left.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        cont_hijo = right.Recorrido(arbol);
        arbol.addRelacion(cont_raiz,cont_hijo);
        
        return cont_raiz;
    }
}
