/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interpreter.Objeto;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;

public class Frame {
    private String nombreFrame;
    private AnchorPane ventana;
    private JFXPanel jfxPanel;
    
    public Frame(String nombre){
        this.nombreFrame = nombre;
        this.ventana = new AnchorPane();
        this.jfxPanel = new JFXPanel();
    }
    
    
    public String getNombreFrame() {
        return nombreFrame;
    }

    public void setNombreFrame(String nombreFrame) {
        this.nombreFrame = nombreFrame;
    }

    public AnchorPane getVentana() {
        return ventana;
    }

    public void setVentana(AnchorPane ventana) {
        this.ventana = ventana;
    }

    public JFXPanel getJfxPanel() {
        return jfxPanel;
    }

    public void setJfxPanel(JFXPanel jfxPanel) {
        this.jfxPanel = jfxPanel;
    }

}
