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

public final class Array extends Expresion{

    Expresion expresion;
    Expresion vector;
    LinkedList<Object> data;
    LinkedList<Object> valoresIngresar;
    LinkedList<Integer> dimensiones;
    
    public Array(Expresion expresion, Expresion vector, Entorno entorno, int fila, int columna){
        this.expresion = expresion;
        this.vector = vector;
        this.fila = fila;
        this.columna = columna;
        this.data = new LinkedList<>();
        this.dimensiones = new LinkedList<>();
        this.valoresIngresar = new LinkedList<>();
        this.indexAccesos = new LinkedList<>();
        
        instanciarDimensiones(entorno);
        instanciarValoresIngresar(entorno);
        fillData(this.data, dimensiones.size()-1, dimensiones);
    }
    
    public Array(){
        this.data = new LinkedList<>();
        this.dimensiones = new LinkedList<>();
        this.valoresIngresar = new LinkedList<>();
        this.indexAccesos = new LinkedList<>();
    }
    
    public Array(LinkedList data, int fila, int columna){
        this.data = data;
        this.dimensiones = new LinkedList<>();
        this.valoresIngresar = new LinkedList<>();
        this.indexAccesos = new LinkedList<>();
        this.fila = fila;
        this.columna =columna;
    }
    
    @Override
    public Object getValor(Entorno entorno) {
        return this;
    }
    
    //============================ INSTANCIANDO VALORES ======================================================
    public void instanciarDimensiones(Entorno entorno){
        Object valorVector = vector.getValor(entorno);
        
        //valido que el parámetro sea un vector de integer
        if(valorVector instanceof Matrix || valorVector instanceof ListArit || valorVector instanceof Array){
            entorno.addError(new Token("Instancia Array-"+Primitivo.getTipoDato(valorVector).toString(), "No se puede utilizar como vector este tipo de dato", fila, columna));
            return;
        }
        
        //saco las dimensiones en una lista
        this.dimensiones = new LinkedList<>();
        if(valorVector instanceof VectorArit){
            LinkedList elements = (VectorArit)valorVector;
            for(Object element : elements){
                try {
                    dimensiones.add((int)element);
                } catch (Exception e) {
                    entorno.addError(new Token("Instancia Array-"+Primitivo.getTipoDato(valorVector).toString(), "Se esperaba un valor integer para las dimensiones", fila, columna));
                    return;
                }
            }
        }else{
            try {
                dimensiones.add((int)valorVector);
            } catch (Exception e) {
                entorno.addError(new Token("Instancia Array-"+Primitivo.getTipoDato(valorVector).toString(), "Se esperaba un valor integer para las dimensiones", fila, columna));
                return;
            }
        }
    }
    
    public void instanciarValoresIngresar(Entorno entorno){
        Object valorExpresion = expresion.getValor(entorno);
        
        if(valorExpresion instanceof VectorArit){
            LinkedList elements = (VectorArit)valorExpresion;
            for(Object element : elements){
                valoresIngresar.add(element);
            }
        }else if(valorExpresion instanceof ListArit){
            ListArit elements = (ListArit)valorExpresion;
            for(Object element : elements.getData()){
                LinkedList<Object> temp = new LinkedList<>();
                temp.add(element);
                ListArit list = new ListArit(temp);
                valoresIngresar.add(list);
            }
        }else{
            valoresIngresar.add(valorExpresion);
        }
    }
    
    int cont = 0;
    private Object getDato(){
        if(cont>=valoresIngresar.size()){
            cont = 0;
        }
        return valoresIngresar.get(cont++);
    }
    
    public LinkedList<Object> fillData(LinkedList<Object> data, int index, LinkedList<Integer> dimensiones){
        int limite = dimensiones.get(index);
        if(index>1){
            for (int i = 0; i < limite; i++) {
                data.add(fillData(new LinkedList<>(), index-1, dimensiones));
            }
        }else if(index==1){
            for (int i = 0; i < dimensiones.get(0); i++) {
                data.add(new LinkedList<>());
            }
            for (int i = 0; i < limite; i++) {
                for (int j = 0; j < dimensiones.get(0); j++) {
                    ((LinkedList)data.get(j)).add(getDato());
                }
            }
        }else{
            data.add(getDato());
        }
        return data;
    }
    
    //=============================== SUB ARRAY =========================
    LinkedList<Integer> indexAccesos;
    public Object subArray(int x, Entorno entorno, int fila, int columna){
        int noDimensiones = getNoDimensiones(data, 0);
        indexAccesos.add(x);
        if(indexAccesos.size()!=noDimensiones){
            return this;
        }
        
        //ya es momento de devolver el valor
        Object result = 0;
        Object obj = this.data;
        for (int i = this.indexAccesos.size()-1; i >= 0; i--) {
            if(i>1){
                int index = this.indexAccesos.get(i)-1;
                LinkedList<Object> temp = ((LinkedList)obj);
                if(index>=temp.size() || index<0){
                    entorno.addError(new Token("Array-setValor", "IndexOutOfBounds size: "+temp.size()+" index: "+index, fila, columna));
                    break;
                }
                obj = temp.get(index);
            }
            else if(i==1){
                int indexColumna = this.indexAccesos.get(1)-1;
                int indexFila = this.indexAccesos.get(0)-1;
                LinkedList<Object> temp = (LinkedList)obj;
                if(indexFila>=temp.size() || indexFila<0){
                    entorno.addError(new Token("Array-setValor", "IndexOutOfBounds size: "+temp.size()+" indexFila: "+indexFila, fila, columna));
                    break;
                }
                LinkedList<Object> temp2 = (LinkedList)(temp.get(indexFila));
                if(indexColumna>=temp2.size() || indexColumna<0){
                    entorno.addError(new Token("Array-setValor", "IndexOutOfBounds size: "+temp.size()+" indexColumna: "+indexColumna, fila, columna));
                    break;
                }
                result = temp2.get(indexColumna);
                break;
            }else{
                int index = this.indexAccesos.get(i)-1;
                LinkedList<Object> temp = ((LinkedList)obj);
                if(index>=temp.size() || index<0){
                    entorno.addError(new Token("Array-setValor", "IndexOutOfBounds size: "+temp.size()+" indexFila: "+index, fila, columna));
                    break;
                }
                result = temp.get(index);
                break;
            }
        }
        
        //reincio la lista
        this.indexAccesos.clear();
        return result;
    }
    
    public void setValor(int x, Entorno entorno, Object val, int fila, int columna){
        indexAccesos.add(x);
        if(val == null){
            return;
        }
        
        //ya es momento de setear el valor
        Object obj = this.data;
        for (int i = this.indexAccesos.size()-1; i >= 0; i--) {
            if(i>1){
                int index = this.indexAccesos.get(i)-1;
                LinkedList<Object> temp = ((LinkedList)obj);
                if(index>=temp.size() || index<0){
                    entorno.addError(new Token("Array-setValor", "IndexOutOfBounds size: "+temp.size()+" index: "+index, fila, columna));
                    break;
                }
                obj = temp.get(index);
            }
            else if(i==1){
                int indexColumna = this.indexAccesos.get(1)-1;
                int indexFila = this.indexAccesos.get(0)-1;
                LinkedList<Object> temp = (LinkedList)obj;
                if(indexFila>=temp.size() || indexFila<0){
                    entorno.addError(new Token("Array-setValor", "IndexOutOfBounds size: "+temp.size()+" indexFila: "+indexFila, fila, columna));
                    break;
                }
                LinkedList<Object> temp2 = (LinkedList)(temp.get(indexFila));
                if(indexColumna>=temp2.size() || indexColumna<0){
                    entorno.addError(new Token("Array-setValor", "IndexOutOfBounds size: "+temp.size()+" indexColumna: "+indexColumna, fila, columna));
                    break;
                }
                temp2.set(indexColumna, val);
                break;
            }else{
                int index = this.indexAccesos.get(i)-1;
                LinkedList<Object> temp = ((LinkedList)obj);
                if(index>=temp.size() || index<0){
                    entorno.addError(new Token("Array-setValor", "IndexOutOfBounds size: "+temp.size()+" indexFila: "+index, fila, columna));
                    break;
                }
                temp.set(index, val);
                break;
            }
        }
        
        //reincio la lista
        this.indexAccesos.clear();
    }
    
    public LinkedList<Object> getData(){
        return this.data;
    }
    
    public int getNoDimensiones(LinkedList data, int conteo){
        return data.isEmpty()?conteo+1:(data.get(0) instanceof LinkedList)?getNoDimensiones(((LinkedList)data.get(0)), conteo+1):conteo+1;
    }
    
    public LinkedList<Object> getMapeo(){
        return getMapeo(this.data);
    }
    
    public LinkedList<Object> getMapeo(LinkedList<Object> data){
        LinkedList map = new LinkedList();
        for(Object element:data){
            if(element instanceof LinkedList){
                map.addAll(getMapeo((LinkedList)element));
            }else{
                map.add(element);
            }
        }
        return map;
    }

    @Override
    public String toString() {
        return printArray(data, dimensiones.size()-1, dimensiones).toString();
    }
    
    public Object printArray(LinkedList<Object> lista, int index, LinkedList<Integer> dimensiones){
        String ret = "";
        if(index>1){
            for(Object element :lista){
                ret += printArray((LinkedList)element, index-1, dimensiones)+"\n";
            }
        }else if(index==1){
            for(Object element : lista){
                ret += element+"\n";
            }
        }else{
            ret += lista+"\n";
        }
        return ret;
    }

    @Override
    public int Recorrido(Graph_AST arbol) {
        return 0;
    }
}
