/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import Analyzer.Token;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Principal {
    
    DefaultTableModel dtmErrores, dtmDebugger;
    JTable tablaErrores, tablaDebugger;
    
    public Principal(JTable tablaErrores, JTable tablaDebugger){
        this.tablaErrores = tablaErrores;
        this.tablaDebugger = tablaDebugger;
        inicializarTablas();
    }
    
    public void addError(Token te){
        Object[] newRow={te.lexema, te.componenteLexico,te.descripcion,
            te.columna,te.fila,te.archivo};
        dtmErrores.addRow(newRow);
    }
    
    private void inicializarTablas(){
        Object[][] data = {{null,null,null,null,null,null}};
        String[] columnNames2 = {"Lexema","Tipo","Valor","Ambito","Columna","Fila"};
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
        
        /*
        //=================modificar el alto =========================
        MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
        //set TableCellRenderer into a specified JTable column class
        tablaErrores.setDefaultRenderer(String[].class, renderer);
        //or, set TableCellRenderer into a specified JTable column
        tablaErrores.getColumnModel().getColumn(2).setCellRenderer(renderer);
        */
        
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
    
    /*
    public static void MostrarErrores(ArrayList<Token> TablaErrores, String nombreArchivo){
        tabPaneTablas.setSelectedIndex(0);
            for (int i = dtmErrores.getRowCount()-1;i>-1; i--) {
                dtmErrores.removeRow(i);
            }
            for (int i = 0; i < TablaErrores.size(); i++) {
                Token te = TablaErrores.get(i);
                Object[] newRow={te.lexema, te.componenteLexico,te.descripcion,
                    te.columna,te.fila,nombreArchivo};
                dtmErrores.addRow(newRow);
            }
    }
    */
    
}
