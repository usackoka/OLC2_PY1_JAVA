/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import Interpreter.Enviorement;
import Interpreter.Operation.Operation;
import Principal.Singleton;

public class Arreglo extends Objeto{

    private Objeto tipoarreglo;
    private Objeto value[];
    private Operation tamanio;
    private int size;
    
    public Arreglo(Objeto tipoarreglo) {
        super(Tipo.ARRAY);
        this.tipoarreglo = tipoarreglo;
        this.size = 0;
    }

    public Arreglo(Objeto tipoarreglo, Operation tamanio){
        super(Tipo.ARRAY);
        this.tamanio = tamanio;
        this.tipoarreglo = tipoarreglo;
        this.size = 0;
    }
    
    public Objeto getTipoarreglo() {
        return tipoarreglo;
    }

    public void setTipoarreglo(Objeto tipoarreglo) {
        if(this.tipoarreglo == null)
            this.tipoarreglo = tipoarreglo;
        else
            if(this.tipoarreglo.getTipo() == Tipo.ARRAY)
                ((Arreglo)this.tipoarreglo).setTipoarreglo(tipoarreglo);
    }
    
    public Operation getTamanio(){
        return this.tamanio;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (Objeto[])value;
    }

    @Override
    public Objeto Equals(Objeto compare, int fila, int columna) {
        return new Primitivo(Tipo.BOOLEAN,this == compare);
    }

    @Override
    public Objeto NotEquals(Objeto compare, int fila, int columna) {
        return new Primitivo(Tipo.BOOLEAN,this != compare);
    }

    @Override
    public boolean compare(Objeto value) {
        if(this.compare(value.getTipo())){
            Arreglo arr2 = (Arreglo)value;
            if(this.size == 0){
                this.value = arr2.value;
                this.size = arr2.size;
                return this.tipoarreglo.compare(arr2.tipoarreglo);
            }
            else if(this.size == arr2.size){
                this.value = arr2.value;
                return this.tipoarreglo.compare(arr2.tipoarreglo);
            }
        }
        else if(this.tipoarreglo.compare(Tipo.CHAR) && value.compare(Tipo.STRING)){
            ((Cadena)value).setIsArray(true);
            String cadena = value.toString();
            if(this.size == 0){
                newArray(cadena.length());
                for(int i = 0; i < cadena.length(); i++){
                    addValue(new Primitivo(Tipo.CHAR,cadena.charAt(i)), i);
                }
                return true;
            }
            else{
                for(int i = 0; i < this.size; i++){
                    if(i < cadena.length())
                        addValue(new Primitivo(Tipo.CHAR,cadena.charAt(i)),i);
                    else
                        break;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean compareFunc(Objeto value){
        if(this.compare(value.getTipo())){
            Arreglo arr2 = (Arreglo)value;
            return this.tipoarreglo.compareFunc(arr2.tipoarreglo);
        }
        return false;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void addValue(Objeto obj, int pos){
        value[pos] = obj;
    }
    
    public void newArray(int size){
        this.value = new Objeto[size];
        this.size = size;
    }
    
    public Objeto getValue(int size, int row, int column){
        if(size >= this.size)
            return Singleton.getInstance().addError(row, column, "IndexOutException: Indice " + size + " fuera de rango");
        return value[size];
    }
    
    public Objeto ejecutar(Enviorement env){
        Arreglo arr = new Arreglo(this.tipoarreglo);
        if(tamanio == null){
            return this;
        }
        Objeto obj = tamanio.Ejecutar(env);
        if(obj.compare(Tipo.INT) || obj.compare(Tipo.CHAR)){
            arr.newArray(Integer.parseInt(obj.toString()));
        }
        for(int i = 0; i < arr.size; i++){
            if(this.tipoarreglo instanceof Arreglo){
                arr.addValue(((Arreglo)this.tipoarreglo).ejecutar(env),i);
            }
            else{
                arr.addValue(this.tipoarreglo.nuevoFusion(env),i);
            }
        }
        return arr;
    }
    
    public void Print(){
        for(int i = 0; i < size; i++){
            Singleton.getInstance().addMessage(value[i].toString());
        }
    }
    
    @Override
    public String toString(){
        String cadena = "";
        for(int i = 0; i < size; i++){
            cadena += value[i].toString();
        }
        return cadena;
    }
    
    public boolean comparar(Arreglo arr){
        for(int i = 0; i < size; i++){
            if(!this.value[i].toString().equals(arr.value[i].toString())){
                return false;
            }
        }
        return true;
    }
    
    public void conc(String cadena){
        int i;
        for(i = 0; i < this.size; i++){
            if(value[i].toString().equals("\0"))
                break;
        }
        int contador = 0;
        for(;i < this.size; i++){
            if(contador < cadena.length()){
                addValue(new Primitivo(Tipo.CHAR,cadena.charAt(contador)),i);
                contador++;
            }
            else{
                break;
            }
        }
    }
}
