package GUI;

import Colores.*;
import Otros.ColineDocumentFilter;
import Otros.ListaBreakPoint;
import Otros.breakPoint;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;

public class Pagina extends javax.swing.JPanel {

    /**
     * Creates new form Pagina
     */
    public String rutaArchivo;
    public SyntaxTextPane txtEntrada;
    public String nombreArchivo;
    
    int fila = 0;
    int columna = 0;
    int click = 0;
    int cursorX = 0;
    int cursorY = 0;
    String nombre;
    public ListaBreakPoint misBreaks;
    public LineCounter tln;
    String ruta_relativa;
    
    public Pagina() {
        
        this.ruta_relativa=this.nombre = this.nombreArchivo = this.rutaArchivo ="";
        misBreaks = new ListaBreakPoint();
        initComponents();
        this.txtEntrada = new SyntaxTextPane();
        tln = new LineCounter(this,this.txtEntrada);
        this.scrollPane.setRowHeaderView(tln);
        this.scrollPane.setViewportView(txtEntrada);
        
        ((AbstractDocument) txtEntrada.getDocument()).setDocumentFilter(new ColineDocumentFilter(txtEntrada));
        
        tln.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                click = click + 1;
                if (click == 1) {
                    PointerInfo a = MouseInfo.getPointerInfo();
                    Point b = a.getLocation();
                    cursorX = (int) b.getX();
                    cursorY = (int) b.getY();
                }
                if (click == 2) {
                    PointerInfo a = MouseInfo.getPointerInfo();
                    Point b = a.getLocation();
                    int posx = (int) b.getX();
                    int posy = (int) b.getY();
                    if (posx == cursorX && posy == cursorY) {
                        tln.changeLineColor();
                        breakPoint br = new breakPoint(fila,nombre);
                        misBreaks.agregar(br);
                        revalidate();
                        repaint();
                    }
                    click = 0;

                }
            }
        });
        setText();
    }
    
    //================ BUSCAR =============================
    public void highlight(String pattern) {
        removeAllHighlight();
        try {
            Highlighter hilite = txtEntrada.getHighlighter();
            javax.swing.text.Document doc = txtEntrada.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos + pattern.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY));
                pos += pattern.length();
            }
        } catch (BadLocationException e) {
            System.out.println(e);
        }
    }
    
    //=================== REEMPLAZAR =========================
    public void replaceAllHighlight(String oldString,String newString){
        
        /*javax.swing.text.Document doc = txtEntrada.getDocument();
        for(Highlighter.Highlight high:txtEntrada.getHighlighter().getHighlights()){
            
        }*/
        this.txtEntrada.setText(this.txtEntrada.getText().replace(oldString, newString));
        removeAllHighlight();
    }
    
    public void removeAllHighlight(){
        txtEntrada.getHighlighter().removeAllHighlights();
    }
    
    public int getFilaCarret(){
        int pos = this.txtEntrada.getCaretPosition();
        Element map = this.txtEntrada.getDocument().getDefaultRootElement();
        return map.getElementIndex(pos)+1;
    }
    
    public void actualizarFilaColumna(int f, int c) {
        fila = f;
        columna = c;
        GUI.GUI_Principal.txtLineas.setText("Fila: " + f + " Columna: " + c);
    }
    
    public void removeBK(breakPoint bk){
        tln.removeBreak();
        this.misBreaks.remove(bk);
    }

    public void cambiarLineaAVerde(int f){
        tln.gotoStartOfLine(f);
        tln.setActualLineColor();
        this.repaint();
    }
    
    public void setText() {
        /* try {
           
            File file = new File(nombre);

            BufferedReader br = new BufferedReader(new FileReader(file));
            String todoTexto = "";
            String st;
            while ((st = br.readLine()) != null) {
                todoTexto = todoTexto + st + "\n";
            }
            this.txtEntrada.setText(todoTexto);
            */
            Object filtro = ((AbstractDocument) txtEntrada.getDocument()).getDocumentFilter();
            if (filtro instanceof ColineDocumentFilter) {
                ((ColineDocumentFilter) filtro).primero = false;
            } else if (filtro instanceof ColineDocumentFilter) {
                ((ColineDocumentFilter) filtro).primero = false;
            }
        /*
        } catch (IOException ex) {
            Logger.getLogger(Pagina.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();

        setPreferredSize(new java.awt.Dimension(671, 420));

        scrollPane.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
