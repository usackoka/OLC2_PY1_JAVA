/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import java.util.LinkedList;

public class ListaBreakPoint extends LinkedList<breakPoint>{

    public ListaBreakPoint() {
    }
    
    public boolean esBreakPoint(int f, String a){
        for (breakPoint v : this) {
            if (v.fila == f && v.archivo.equals(a)) {
                return true;
            }
        }
        return false;
    }
    
    public void agregar(breakPoint n) {
        for (breakPoint v : this) {
            if (v.fila == n.fila && 
                    v.archivo.equals(n.archivo)) {
                eliminar(v.fila, v.archivo);
                return;
            }
        }
        this.add(n);
        GUI.GUI_Principal.txtConsola.append(">> Breakpoint at row: "+n.fila+"\n");
    }

    public void eliminar(int n, String a) {
        for (breakPoint v : this) {
            if (v.fila == n && v.archivo.equals(a)) {
                this.remove(v);
                GUI.GUI_Principal.txtConsola.append(">> Deleted Breakpoint at row: "+v.fila+"\n");
                return;
            }
        }
       
    }
}
