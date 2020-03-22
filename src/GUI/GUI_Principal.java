package GUI;

import AST.Principal;
import Analyzer.*;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.net.URISyntaxException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import Analyzer.*;
import GraficasArit.Graf_Bar;
import GraficasArit.Graf_Hist;
import GraficasArit.Graf_Line;
import GraficasArit.Graf_Pie;
import GraficasArit.Graf_Plot;
import java.awt.Font;
import java.util.LinkedList;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;

public class GUI_Principal extends javax.swing.JFrame{

    public static Pagina paginaActual;
    ArrayList<Pagina> paginas;
    public static String ventanaAbierta;
    public static DefaultTableModel dtmDebugger,dtmErrores;
    public Principal principal;

    public GUI_Principal() {
        initComponents();
        ventanaAbierta = "";
        paginas = new ArrayList<>();
        paginaActual = null;
        this.setLocationRelativeTo(null);
        txtConsola2.setEditable(false);
        txtLineas.setEditable(false);
        //this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        inicializarTablas();
        
    }
    
    public void restartApplication() throws URISyntaxException, IOException 
    { 
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java"; 
        final File currentJar = new File(GUI_Principal.class.getProtectionDomain().getCodeSource().getLocation().toURI()); 

        /* is it a jar file? */ 
        if(!currentJar.getName().endsWith(".jar")) 
        return; 

        /* Build command: java -jar application.jar */ 
        final ArrayList<String> command = new ArrayList<>(); 
        command.add(javaBin); 
        command.add("-jar"); 
        command.add(currentJar.getPath()); 

        final ProcessBuilder builder = new ProcessBuilder(command); 
        builder.start(); 
        System.exit(0); 
    } 
    
    private void cargarImagen(String ruta, JLabel lbl2){
        //eventos para el debbugger
        //lbl2.addMouseListener(this);
        ImageIcon fot = new ImageIcon(ruta);
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbl2.getWidth(), lbl2.getHeight(), Image.SCALE_DEFAULT));
        lbl2.setIcon(icono);
        lbl2.repaint();
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
        
        //Object[][] data2 = {{null,null,null,null,null,null}};
        Object[][] data2 = {{null,null,null}};
       // String[] columnNames = {"Lexema","Tipo","Descripcion","Columna","Fila","Archivo"};
        String[] columnNames = {"Descripcion","Fila","Columna"};
        dtmErrores = new DefaultTableModel(data2,columnNames);
        tablaErrores.setModel(dtmErrores);
        
        //================== modificar el ancho ===========================
        TableColumnModel columnModel = tablaErrores.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(20);
        columnModel.getColumn(2).setPreferredWidth(20);
        tablaErrores.setShowGrid(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jFileChooser1 = new javax.swing.JFileChooser();
        jFileFolder = new javax.swing.JFileChooser();
        tabPrincipal = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsola = new javax.swing.JTextArea();
        txtConsola2 = new javax.swing.JTextField();
        tabPaneTablas = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaErrores = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaDebugger = new javax.swing.JTable();
        txtLineas = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        txtReemplazar = new javax.swing.JTextField();
        btnReemplazar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jTree2 = new javax.swing.JTree();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        jMenu4.setText("jMenu4");

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto 1 201503712");

        tabPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabPrincipal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPrincipalStateChanged(evt);
            }
        });

        txtConsola.setEditable(false);
        txtConsola.setColumns(20);
        txtConsola.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        txtConsola.setForeground(new java.awt.Color(51, 0, 0));
        txtConsola.setRows(5);
        jScrollPane1.setViewportView(txtConsola);

        txtConsola2.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        txtConsola2.setForeground(new java.awt.Color(51, 0, 0));

        tablaErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaErrores);

        tabPaneTablas.addTab("Errores", jScrollPane2);

        tablaDebugger.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaDebugger);

        tabPaneTablas.addTab("Debugger", jScrollPane3);

        txtLineas.setBackground(new java.awt.Color(102, 102, 102));

        txtBuscar.setBackground(new java.awt.Color(102, 102, 102));

        txtReemplazar.setBackground(new java.awt.Color(102, 102, 102));

        btnReemplazar.setText("R");
        btnReemplazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReemplazarActionPerformed(evt);
            }
        });

        btnBuscar.setText("B");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jTree2.addTreeExpansionListener(new javax.swing.event.TreeExpansionListener() {
            public void treeCollapsed(javax.swing.event.TreeExpansionEvent evt) {
                jTree2TreeCollapsed(evt);
            }
            public void treeExpanded(javax.swing.event.TreeExpansionEvent evt) {
            }
        });
        jTree2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree2MouseClicked(evt);
            }
        });

        jButton1.setText("CrearGraficos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem2.setText("Nuevo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Cerrar Pestaña");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Abrir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Guardar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Guardar Como");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem9.setText("Abrir Proyecto");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem14.setText("Guardar Proyecto");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem14);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Herramientas");

        jMenuItem12.setText("Graficar");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setText("Ejecutar");
        jMenuItem13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem13MouseClicked(evt);
            }
        });
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Reportes");

        jMenuItem10.setText("Manual de Usuario");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem11.setText("Manual Tecnico");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTree2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReemplazar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReemplazar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtConsola2, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(tabPaneTablas)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tabPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReemplazar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLineas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtReemplazar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(tabPaneTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConsola2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTree2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //NUEVO
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        AgregarPestana("Untitled");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    //abrir archivo
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //abro el file dialog para seleccionar el archivo
        Abrir();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    //guardar
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        Guardar(paginaActual.txtEntrada.getText());
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    //GuardarComo
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        GuardarComo(paginaActual.txtEntrada.getText());
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    //CERRAR PESTAÑA
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(tabPrincipal.getTabCount()>0)
        {
            paginas.remove(tabPrincipal.getSelectedIndex());
            tabPrincipal.removeTabAt(tabPrincipal.getSelectedIndex());
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    //cambio de pestaña
    private void tabPrincipalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPrincipalStateChanged
        // TODO add your handling code here:
        if(tabPrincipal.getTabCount()>0)
        {
            int index = tabPrincipal.getSelectedIndex();
            try{
                paginaActual = paginas.get(index);
            }catch(Exception e)
            {
            }
        }
    }//GEN-LAST:event_tabPrincipalStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if(paginaActual!=null){
            paginaActual.highlight(txtBuscar.getText());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnReemplazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReemplazarActionPerformed
        // TODO add your handling code here:
        paginaActual.replaceAllHighlight(txtBuscar.getText(), txtReemplazar.getText());
        txtBuscar.setText("");
        txtReemplazar.setText("");
    }//GEN-LAST:event_btnReemplazarActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed

        AnalizarColine();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem13MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem13MouseClicked

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        
        //GraficarArbol.GraficarArbol(ejecutar.padre, paginaActual.nombreArchivo.replace(".fs", "").replace(".js",""));
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    public void iniciarArbol(File selectedFile) {
        File root = new File("Proyectos");
        FileTreeModel model = new FileTreeModel(selectedFile);
        this.jTree2.setModel(model);
       
        this.jTree2.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
//                FileTreeModel.TreeFile node = (FileTreeModel.TreeFile) e
//                        .getPath().getLastPathComponent();
                String value = "";
                TreePath treepath = e.getPath();
                Object elements[] = treepath.getPath();
                for (int i = 0, n = elements.length; i < n; i++) {
                    value += elements[i].toString() + "\\";
                }
                value = value.substring(0, value.length() - 1);
                //System.out.println("You selected " + value);
                
                File file1 = new File(value);
                Abrir2(file1.getName(),value,file1.getParent()+"\\");
                // System.out.println("-------------"+file1.getParent()+"\\");
                if (file1.exists()) {
                    if (file1.isFile()) {
                      //  Abrir2("val",value);
//                        paginaActual.txtEntrada.setText(cadena);
//                        paginaActual.rutaArchivo = rutatotal;
//                        paginaActual.nombreArchivo = archivo.getName();
                    }
                }
            }
        }
        );
       
         this.revalidate();
        this.repaint();
    }
    
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        
          jFileFolder = new JFileChooser(); 
            jFileFolder.setCurrentDirectory(new java.io.File("."));
            jFileFolder.setDialogTitle("Carpeta");
            jFileFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //
            // disable the "All files" option.
            //
            jFileFolder.setAcceptAllFileFilterUsed(false);
            //     
            if (jFileFolder.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
//              System.out.println("getCurrentDirectory(): " 
//                 +  jFileFolder.getCurrentDirectory());
              /*System.out.println("getSelectedFile() : " 
                 +  jFileFolder.getSelectedFile());*/
               iniciarArbol(jFileFolder.getSelectedFile());
              }
            else {
              //System.out.println("No Selection ");
              }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jTree2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTree2MouseClicked

    private void jTree2TreeCollapsed(javax.swing.event.TreeExpansionEvent evt) {//GEN-FIRST:event_jTree2TreeCollapsed
      
    }//GEN-LAST:event_jTree2TreeCollapsed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        for (Pagina pag : paginas) {
                GuardarPag2(pag);
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed

    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().open(new File("Manuales\\mu.pdf"));
        } catch (IOException ex) {
            //System.out.println("catch abrir manual");
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "IPhone 5s" , new Double( 20 ) );  
      dataset.setValue( "SamSung Grand" , new Double( 20 ) );   
      dataset.setValue( "MotoG" , new Double( 40 ) );    
      dataset.setValue( "Nokia Lumia" , new Double( 10 ) );
      
      LinkedList <Double> datos = new LinkedList<>();
      LinkedList <String> labels = new LinkedList<>();
      String titulo ="Grafica Pie";
      datos.add(12.6);
      datos.add(83.0);
      datos.add(14.0);
      datos.add(15.0);
      datos.add(16.0);
      
      for (int i = 0; i < 4; i++) {
            labels.add("A"+i);

     }
      
       //--------------------------Ejemplo Pie-------------------------------
       Graf_Pie graf0 = new Graf_Pie(datos,labels,titulo); 
       ChartPanel p1 = graf0.CreatePane();
       
       //--------------------------Ejemplo Barras-------------------------------
       LinkedList<LinkedList<Double>> l1=new LinkedList<>();
       l1.add(datos);
       l1.add(datos);
       Graf_Bar graf01 = new Graf_Bar(l1,"Titulo eje X","Titulo Eje Y","Titulo",labels); 
       p1 = graf01.CreatePane();
       
        //--------------------------Ejemplo histograma-------------------------------
       double limiteXS =13, limiteYS =-1000,limiteXI =10,limiteYI =-10;
       
       Graf_Hist graf02 = new Graf_Hist(l1, "Eje X","Titulo Grafica",limiteXS,limiteXI,limiteYS,limiteYI); 
       p1 = graf02.CreatePane();
        this.repaint();
        
       //--------------------------Ejemplo lineal-------------------------------
       Graf_Line graf03 = new Graf_Line(l1,"P", "Eje X","Eje Y","Titulo Grafica"); 
       p1 = graf03.CreatePane();
       
      
        //--------------------------Ejemplo Dispersa-------------------------------
       limiteXS =13; limiteYS =-1000;limiteXI =10;limiteYI =-10;
       
       Graf_Plot graf05 = new Graf_Plot(l1,
               "Eje X","Eje Y","Titulo Grafica",limiteXS,limiteXI,limiteYS,limiteYI,true); 
       p1 = graf05.CreatePane();
       
       this.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                } catch (Exception e) {
                }
                new GUI_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnReemplazar;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileFolder;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTree jTree2;
    private javax.swing.JTabbedPane tabPaneTablas;
    private javax.swing.JTabbedPane tabPrincipal;
    private javax.swing.JTable tablaDebugger;
    private javax.swing.JTable tablaErrores;
    public static javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextArea txtConsola;
    public static javax.swing.JTextField txtConsola2;
    public static javax.swing.JTextField txtLineas;
    public static javax.swing.JTextField txtReemplazar;
    // End of variables declaration//GEN-END:variables

    private void GuardarPag2(Pagina pag) {
          FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(pag.rutaArchivo);
            pw = new PrintWriter(fichero);
            pw.println(pag.txtEntrada.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Nunca se guardo el archivo");
            GuardarComo(pag.txtEntrada.getText());
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
               //System.out.println("ruta_g "+pag.rutaArchivo);
           JOptionPane.showMessageDialog(null, "Guardado con éxito");
           } catch (IOException e2) {
              e2.printStackTrace();
           }
        }
    }
    
    private void AnalizarColine()
    {
        //si hay algo para analizar
        if(paginaActual==null){
            JOptionPane.showMessageDialog(null, "Nada para analizar");
            return;
        }
        
        txtConsola.setText("");
        dtmErrores.setRowCount(0);
        String cadena = paginaActual.txtEntrada.getText();
        
        Lexer lex = new Lexer(new BufferedReader(new StringReader(cadena+"\n\n")));
        lex.setPrincipal(new Principal(tablaErrores, tablaDebugger,txtConsola));

        try {
            parser parse = new parser(lex);
            parse.setPrincipal(lex.getPrincipal());
            parse.parse();
            principal = parse.getPrincipal();
            principal.start();
            
        } catch (Exception ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
        }
    }
    
    private void GuardarComo(String cadena)
    {
        FileOutputStream f = null;
        DataOutputStream h = null;
        FileDialog d = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
        d.setVisible(true);
        String dir = d.getDirectory();
        String ruta = dir + d.getFile();
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.println(cadena);
            
                paginaActual.rutaArchivo = ruta;
                paginaActual.ruta_relativa=dir;
                paginaActual.nombreArchivo = "nomb";
                paginaActual.repaint();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (IOException e2) {
              e2.printStackTrace();
           }
        }
    }
    
    private void Guardar(String cadena)
    {   
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(paginaActual.rutaArchivo);
            pw = new PrintWriter(fichero);
            pw.println(cadena);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Nunca se guardo el archivo");
            GuardarComo(paginaActual.txtEntrada.getText());
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
               //System.out.println("ruta_g "+paginaActual.rutaArchivo);
           JOptionPane.showMessageDialog(null, "Guardado con éxito");
           } catch (IOException e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public void Abrir2(String nombreArchivo, String rutatotal,String ruta_rel)
    {
      AgregarPestana(nombreArchivo);
        
      //leo el archivo
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (rutatotal);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea="";
         String cadena="";
         while((linea=br.readLine())!=null){
            cadena += linea+"\n";
         }
         
       paginaActual.txtEntrada.setText(cadena);
       paginaActual.rutaArchivo = rutatotal;
       paginaActual.nombreArchivo = archivo.getName();
       paginaActual.ruta_relativa =ruta_rel;
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      paginaActual.txtEntrada.setCaretPosition(0);
    }
    
    private void Abrir()
    {
        FileDialog dialogoArchivo;
        dialogoArchivo = new FileDialog(this, "Lista de Archivos",FileDialog.LOAD);
        dialogoArchivo.setVisible(true);
        String rutatotal;
        String nombreArchivo;
        String directorio;
        if(dialogoArchivo.getFile()!=null){ /* Validar que se haya Seleccionado un Archivo*/
           directorio = dialogoArchivo.getDirectory();
           nombreArchivo =dialogoArchivo.getFile(); 
           rutatotal = directorio + nombreArchivo;
        }
        else{
           //System.out.println("No Seleccionó Archivo");
           return;
        }
        //System.out.println("-------------"+directorio);
        Abrir2(nombreArchivo,rutatotal,directorio);
    }

    private void AgregarPestana(String nombre)
    {
        Pagina paginaNueva = new Pagina();
        tabPrincipal.addTab(nombre,paginaNueva);
        paginaActual = paginaNueva;
        paginas.add(paginaNueva);
    }
    
    private void irLinea(int line,JTextArea area){
        String []t = area.getText().split("\n");
	int position=0;
	for(int index=0;index<t.length;index++){
		if(index == line-1)break;
		if(t[index].length()!=0)
		        position+=t[index].length();
	}
	area.setCaretPosition(position+line-1);
    }
    
}
