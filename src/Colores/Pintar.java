/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colores;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


public class Pintar {
    
    public JTextPane caja2=new JTextPane(); 
    public StyleContext sc = new StyleContext();
    public DefaultStyledDocument doc = new DefaultStyledDocument(sc);

    public void insertar(String texto){
        caja2.setDocument(doc);
        try {
            doc.insertString(0, texto, null);
        }catch (BadLocationException ex) {
            System.out.println("ERROR: no se pudo establecer estilo de documento");
            System.out.println(ex);
        }
   }
   
     public void pintar(int posini,int posfin, String Color){
            Style rojo = sc.addStyle("ConstantWidth", null);
            StyleConstants.setForeground(rojo, toColor(Color));
            doc.setCharacterAttributes(posini,posfin, rojo, false);
     }
     
    public static Color toColor(String colorStr) {
        try{
            if(colorStr.equalsIgnoreCase("red")){
                return Color.RED;
            }else if(colorStr.equalsIgnoreCase("purple")){
                return toColor("A551D2");
            }else if(colorStr.equalsIgnoreCase("blue")){
                return Color.BLUE;
            }else if(colorStr.equalsIgnoreCase("green")){
                return Color.GREEN;
            }else if(colorStr.equalsIgnoreCase("black")){
                return Color.BLACK;
            }else if(colorStr.equalsIgnoreCase("white")){
                return Color.WHITE;
            }else if(colorStr.equalsIgnoreCase("magenta")){
                return Color.MAGENTA;
            }else if(colorStr.equalsIgnoreCase("yellow")){
                return Color.YELLOW;
            }else if(colorStr.equalsIgnoreCase("orange")){
                return Color.ORANGE;
            }else if(colorStr.equalsIgnoreCase("cyan")){
                return Color.CYAN;
            }else if(colorStr.equalsIgnoreCase("gray")){
                return Color.GRAY;
            }
            return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
        }catch(NumberFormatException e){
            return Color.BLACK;
        }
    }
    
}
