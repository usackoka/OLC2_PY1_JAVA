/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Koka
 */
public class MultiLineTableCellRenderer extends JList<String> implements TableCellRenderer {

    Integer MAX = 50;
    
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //make multi line where the cell value is String[]
        if (value instanceof String) {
            if(((String)value).length()>MAX){
                Integer tam = value.toString().length();
                double div = tam/MAX;
                Double divv = div;
                int pivote = 0;
                int lim = MAX;
                String lineas[] = new String[divv.intValue()];
                
                for (int i = 0; i < divv.intValue(); i++) {
                    lineas[i] = value.toString().substring(pivote, lim);
                    pivote = lim;
                    lim += MAX;
                    if(lim>=tam){
                        lim-= (lim-tam-1);
                    }
                }
                setListData(lineas);
                
            }else{
                String lineas[] = {value.toString()};
                setListData(lineas);
            }
        }

        //cell backgroud color when selected
        if (isSelected) {
            setBackground(UIManager.getColor("Table.selectionBackground"));
        } else {
            setBackground(UIManager.getColor("Table.background"));
        }

        return this;
    }
}
