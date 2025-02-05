/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import AST.Sentencias.Funcion;
import Analyzer.Token;
import GraficasArit.Graph_AST;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Principal extends Thread{
    
    DefaultTableModel dtmErrores, dtmDebugger;
    JTable tablaErrores, tablaDebugger;
    JTextArea txtConsola;
    Entorno entorno;
    LinkedList<Nodo> nodos;
    
    public Principal(JTable tablaErrores, JTable tablaDebugger, JTextArea txtConsola){
        super("Hilo ejecución");
        this.tablaErrores = tablaErrores;
        this.tablaDebugger = tablaDebugger;
        this.txtConsola = txtConsola;
        this.entorno = new Entorno(this);
        this.nodos = new LinkedList<>();
        inicializarTablas();
    }
    
    @Override
    public void run(){
        //guardo las funciones encontradas
        this.nodos.forEach((nodo)->{
            if(nodo instanceof Funcion){
                entorno.funciones.put(((Funcion)nodo).id.toLowerCase(), (Funcion)nodo);
            }
        });
        
        //por cada uno de los nodos que encontré, los ejecuto
        this.nodos.forEach((nodo)->{
            if(nodo instanceof Expresion){
                ((Expresion)nodo).getValor(entorno);
            }else{
                if(!(nodo instanceof Funcion)){
                    ((Sentencia)nodo).ejecutar(entorno);
                }
            }
        });
        
        System.out.println("Ejecutado con éxito!!");
    }
    
    public void graficar(String syte){
        Graph_AST ast = new Graph_AST();
        
        int cont_raiz = ast.getNextContGraph();
        ast.addNodoGraph(cont_raiz, "AST-ARIT-SOFT");
        
        for(Nodo nodo:this.nodos){
            ast.addRelacion(cont_raiz,nodo.Recorrido(ast));
        }
        
        ast.graficarAST(syte);
    }
    
    public void print(String cadena){
        this.txtConsola.append(cadena);
    }
    
    public void addError(Token te){
        Object[] newRow={te.lexema, te.componenteLexico,te.descripcion,
            te.columna,te.fila,te.archivo};
        dtmErrores.addRow(newRow);
    }
    
    public void cargarTablaSimbolos(){
        //cargo las funciones
        Set<String> keys = entorno.funciones.keySet();
        for(String key:keys){
            Object[] newRow ={key, "Funcion", "---", "Global", entorno.funciones.get(key).fila, entorno.funciones.get(key).columna};
            dtmDebugger.addRow(newRow);
        }
        //cargo las variables globales que quedaron
        keys = entorno.tbs.keySet();
        for(String key:keys){
            Object[] newRow ={key, "Variable - "+ entorno.tbs.get(key).TIPO, entorno.tbs.get(key).getValor(entorno), "Global", entorno.tbs.get(key).fila, entorno.tbs.get(key).columna};
            dtmDebugger.addRow(newRow);
        }
        
    }
    
    private void inicializarTablas(){
        Object[][] data = {{null,null,null,null,null,null}};
        String[] columnNames2 = {"Lexema","Tipo","Valor","Ambito","Fila","Columna"};
        dtmDebugger = new DefaultTableModel(data, columnNames2);
        tablaDebugger.setModel(dtmDebugger);
        
        //================== modificar el ancho ===========================
        TableColumnModel columnModel2 = tablaDebugger.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(150);
        columnModel2.getColumn(1).setPreferredWidth(90);
        columnModel2.getColumn(2).setPreferredWidth(100);
        columnModel2.getColumn(3).setPreferredWidth(400);
        columnModel2.getColumn(4).setPreferredWidth(30);
        columnModel2.getColumn(5).setPreferredWidth(30);
        tablaDebugger.setShowGrid(true);
        
        Object[][] data2 = {{null,null,null,null,null,null}};
        String[] columnNames = {"Lexema","Tipo","Descripcion","Columna","Fila","Archivo"};
        dtmErrores = new DefaultTableModel(data2,columnNames);
        tablaErrores.setModel(dtmErrores);
        
        //================== modificar el ancho ===========================
        TableColumnModel columnModel = tablaErrores.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(500);
        columnModel.getColumn(3).setPreferredWidth(30);
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(30);
        tablaErrores.setShowGrid(true);
    }
    
    public void setNodos(LinkedList<Nodo> nodos){
        this.nodos = nodos;
    }
}
