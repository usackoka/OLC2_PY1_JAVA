/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AST.Expresiones.Nativas;

import AST.Entorno;
import AST.Expresion;
import AST.Expresiones.ListArit;
import AST.Expresiones.Primitivo;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;

public final class Matrix extends Expresion implements Cloneable{

    Expresion dataExpresion, filasExpresion, columnasExpresion;
    LinkedList<LinkedList<Object>> data;

    public Matrix(Expresion dataExpresion, Expresion filaExpresion, Expresion columnasExpresion, Entorno entorno, int fila, int columna){
        this.dataExpresion = dataExpresion;
        this.filasExpresion = filaExpresion;
        this.columnasExpresion = columnasExpresion;
        this.fila = fila;
        this.columna = columna;
        this.data = new LinkedList<>();
        
        instanciarData(entorno);
    }
    
    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
    
    public Matrix(){
        this.data = new LinkedList<>();
    }
    
    public Matrix(int filas, int columnas){
        this.data = new LinkedList<>();
        for (int i = 0; i < filas; i++) {
            this.data.add(new LinkedList<>());
            for (int j = 0; j < columnas; j++) {
                this.data.get(i).add(0);
            }
        }
    }
    
    public Matrix getClone(){
        Matrix x = new Matrix();
        for(Object row:this.data){
            x.data.add(new LinkedList<>());
            for(Object column:((LinkedList)row)){
                x.data.get(x.data.size()-1).add(column);
            }
        }
        return x;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        return this;
    }
    
    public Object getValorIndex(Integer indexFila, Integer indexColumna, Integer z, Entorno entorno, int fila, int columna){
        //PREGUNTO EL TIPO DE ACCESO
        if(indexFila==null && indexColumna==null){
            //acceso Z
            z = z - 1;
            LinkedList<Object> list = getMapeo();
            if(z>=list.size() || z<0){
                entorno.addError(new Token("Matrix-Acceso(Z)","IndexOutOfBounds size:"+list.size()+" index:"+(z+1),fila,columna));
                return 0;
            }else{
                return list.get(z);
            }
        }else if(indexFila==null){
            //ACCESO Y
            indexColumna = indexColumna - 1;
            if(indexColumna>=getNCol()|| indexColumna<0){
                entorno.addError(new Token("Matrix-Acceso(Y)","IndexOutOfBounds Col size:"+getNCol()+" index:"+(indexColumna+1),fila,columna));
                return 0;
            }else{
                LinkedList<Object> temp = new LinkedList<>();
                for (int i = 0; i < this.data.size(); i++) {
                    temp.add(this.data.get(i).get(indexColumna));
                }
                return temp;
            }
        }else if(indexColumna==null){
            //ACCSO X
            indexFila = indexFila - 1;
            if(indexFila>=getNRow() || indexFila<0){
                entorno.addError(new Token("Matrix-Acceso(X)","IndexOutOfBounds Row size:"+getNRow()+" index:"+(indexFila+1),fila,columna));
                return 0;
            }else{
                return this.data.get(indexFila);
            }
        }else{
            //SI ES ACCESO XY
            indexFila = indexFila - 1;
            indexColumna = indexColumna - 1;
            if(indexFila>=getNRow() || indexFila<0){
                entorno.addError(new Token("Matrix-getValor","IndexOutOfBounds Row size:"+getNRow()+" index:"+(indexFila+1),fila,columna));
                return 0;
            }
            if(indexColumna>=getNCol()|| indexColumna<0){
                entorno.addError(new Token("Matrix-getValor","IndexOutOfBounds Col size:"+getNCol()+" index:"+(indexColumna+1),fila,columna));
                return 0;
            }

            return this.data.get(indexFila).get(indexColumna);
        }
    }
    
    public void setValor(int indexFila, int indexColumna, Object value, Entorno entorno){
        indexFila = indexFila - 1;
        indexColumna = indexColumna - 1;
        
        if(indexFila>=getNRow() || indexFila<0){
            entorno.addError(new Token("Matrix-SetValor","IndexOutOfBounds Row size:"+getNRow()+" index:"+(indexFila+1),fila,columna));
            return;
        }
        
        if(indexColumna>=getNCol()|| indexFila<0){
            entorno.addError(new Token("Matrix-SetValor","IndexOutOfBounds Col size:"+getNCol()+" index:"+(indexColumna+1),fila,columna));
            return;
        }
        
        this.data.get(indexFila).set(indexColumna,value);
    }
    
    //CUANDO SETEO EL VALOR DESDE ACCESOS
    public void setValor(Integer indexFila, Integer indexColumna, Integer z, Object value, Entorno entorno, int fila, int columna){
        if(value == null){
            return;
        }
        
        //PREGUNTO EL TIPO DE ACCESO
        if(indexFila==null && indexColumna==null){
            //acceso Z
            z = z - 1;
            LinkedList<Object> list = getMapeo();
            if(z>=list.size() || z<0){
                entorno.addError(new Token("Matrix-setValor(Z)","IndexOutOfBounds size:"+list.size()+" index:"+(z+1),fila,columna));
                return;
            }else{
                setMapeo(z,value);
                return;
            }
        }else if(indexFila==null){
            //ACCSO Y
            indexColumna = indexColumna - 1;
            if(indexColumna>=getNCol()|| indexColumna<0){
                entorno.addError(new Token("Matrix-setValor(Y)","IndexOutOfBounds Col size:"+getNCol()+" index:"+(indexColumna+1),fila,columna));
                return ;
            }else{
                //pregunto por la cantidad de valores
                if(value instanceof VectorArit){
                    if(((VectorArit)value).size()>getNRow()){
                        entorno.addError(new Token("Matrix-setValor(Y)","Sizes diferentes-ReemplazarFila Row size:"+getNRow()+" size:"+((VectorArit)value).size(),fila,columna));
                        return ;
                    }
                    //reemplazo los valores
                    int i = 0;
                    for(Object element:((VectorArit)value)){
                        this.data.get(i++).set(indexColumna, element);
                    }
                    return;
                }else if(value instanceof ListArit){
                    if(((ListArit)value).getData().size()!=getNCol()){
                        entorno.addError(new Token("Matrix-setValor(Y)","Sizes diferentes-ReemplazarColumna Row size:"+getNRow()+" size:"+((ListArit)value).getData().size(),fila,columna));
                        return ;
                    }//reemplazo los valores
                    int i = 0;
                    for(Object element:((ListArit)value).getData()){
                        this.data.get(i++).set(indexColumna, element);
                    }
                    return;
                }else{
                    for(int i = 0; i<getNRow(); i++){
                        this.data.get(i).set(indexColumna, value);
                    }
                }
            }
        }else if(indexColumna==null){
            //ACCSO X
            indexFila = indexFila - 1;
            if(indexFila>=getNRow() || indexFila<0){
                entorno.addError(new Token("Matrix-setValor(X)","IndexOutOfBounds Row size:"+getNRow()+" index:"+(indexFila+1),fila,columna));
                return ;
            }else{
                //pregunto por la cantidad de valores
                if(value instanceof VectorArit){
                    if(((VectorArit)value).size()>getNCol()){
                        entorno.addError(new Token("Matrix-setValor(X)","Sizes diferentes-ReemplazarFila Col size:"+getNCol()+" size:"+((VectorArit)value).size(),fila,columna));
                        return ;
                    }
                    //reemplazo los valores
                    int i = 0;
                    for(Object element:((VectorArit)value)){
                        this.data.get(indexFila).set(i++, element);
                    }
                    return;
                }else if(value instanceof ListArit){
                    if(((ListArit)value).getData().size()!=getNCol()){
                        entorno.addError(new Token("Matrix-setValor(X)","Sizes diferentes-ReemplazarFila Col size:"+getNCol()+" size:"+((ListArit)value).getData().size(),fila,columna));
                        return ;
                    }
                    //reemplazo los valores
                    int i = 0;
                    for(Object element:((ListArit)value).getData()){
                        this.data.get(indexFila).set(i++, element);
                    }
                    return;
                }else{
                    for(int i = 0; i<getNCol(); i++){
                        this.data.get(indexFila).set(i, value);
                    }
                }
            }
        }else{
            //SI ES ACCESO XY
            indexFila = indexFila - 1;
            indexColumna = indexColumna - 1;
            if(indexFila>=getNRow() || indexFila<0){
                entorno.addError(new Token("Matrix-setValor","IndexOutOfBounds Row size:"+getNRow()+" index:"+(indexFila+1),fila,columna));
                return;
            }
            if(indexColumna>=getNCol()|| indexColumna<0){
                entorno.addError(new Token("Matrix-setValor","IndexOutOfBounds Col size:"+getNCol()+" index:"+(indexColumna+1),fila,columna));
                return;
            }

            this.data.get(indexFila).set(indexColumna,value);
        }
    }
    
    public VectorArit getMapeo(){
        VectorArit mapeo = new VectorArit();
        int col = getNCol();
        for(int i = 0; i<col; i++){
            for(LinkedList list : this.data){
                mapeo.add(list.get(i));
            }
        }
        
        return mapeo;
    }
    
    public void setMapeo(int z, Object value){
        int col = getNCol();
        int cont = 0;
        for(int i = 0; i<col; i++){
            for(LinkedList list : this.data){
                if(cont==z){
                    list.set(i, value);
                    return;
                }
                cont++;
            }
        }
    }
    
    public void instanciarData(Entorno entorno){
        //valido los parámetros
        Object valorFilas = filasExpresion.getValor(entorno);
        Object tipoFilas = Primitivo.getTipoDato(valorFilas);
        Object valorColumnas = columnasExpresion.getValor(entorno);
        Object tipoColumnas = Primitivo.getTipoDato(valorColumnas);
        
        if(!tipoFilas.equals(Expresion.TIPO_PRIMITIVO.integer)){
            entorno.addError(new Token("Vector["+tipoFilas+"]", "No se puede utilizar como Expresion de Filas este tipo de vector", fila, columna));
            return;
        }
        if(!tipoColumnas.equals(Expresion.TIPO_PRIMITIVO.integer)){
            entorno.addError(new Token("Vector["+tipoColumnas+"]", "No se puede utilizar como Expresion de Columnas este tipo de vector", fila, columna));
            return;
        }
        
        Object valores = dataExpresion.getValor(entorno);
        int filas = (int)valorFilas;
        int columnas = (int)valorColumnas;
        
        //le creo las linkedList de filas
        for(int i=0; i<filas; i++){
            this.data.add(new LinkedList<>());
        }
        
        //hago el llenado por columnas
        int element = 0;
        if(valores instanceof VectorArit){
            VectorArit elements = (VectorArit)valores;
            for(int i=0; i<columnas; i++){
                for (int j = 0; j < filas; j++) {
                    //el index del valor a obtener
                    if(element>=elements.size()){
                        element = 0;
                    }
                    data.get(j).add(elements.get(element++));
                }
            }
        }else{
            for(int i=0; i<columnas; i++){
                for (int j = 0; j < filas; j++) {
                    data.get(j).add(valores);
                }
            }
        }
    }
    
    public int getNRow(){
        return this.data.size();
    }
    
    public int getNCol(){
        return this.data.isEmpty()?0:this.data.get(0).size();
    }

    @Override
    public String toString() {
        String ret = "";
        for(LinkedList<Object> row : this.data){
            ret += row+"\n";
        }
        return ret;
    }
    
    

}
