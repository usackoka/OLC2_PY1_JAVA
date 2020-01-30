package Principal;

import Interpreter.Objeto.Errorr;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Singleton {
    private final LinkedList<Errorr> errorlist;
    private static Singleton singleton;
    private javax.swing.JTextArea txtConsola;
    private BufferedWriter buffer;
    private BufferedReader bufferreader;

    private Singleton(){
        this.errorlist = new LinkedList<>();
    }
    
    public javax.swing.JTextArea getTxt(){
        return this.txtConsola;
    }
    
    public static Singleton getInstance(){
        if(singleton == null)
            singleton = new Singleton();
        return singleton;
    }
    
    public void setTxtConsola(javax.swing.JTextArea txtConsola){
        this.txtConsola = txtConsola;
    }
    
    public Errorr addError(Errorr error){
        this.errorlist.add(error);
        return error;
    }
    
    public Errorr addError(int row, int column, String message){
        Errorr err = new Errorr(row,column,message);
        this.errorlist.add(err);
        return err;
    }
    
    public void clearErrorList(){
        this.errorlist.clear();
    }
    
    public void addMessage(String message){
        txtConsola.append(message);  
    }
    
    public void addMessage(char message){
        txtConsola.append(String.valueOf(message));
    }
    
    public void printError(){
        System.out.println("Mostrar Errores "+ errorlist.size());
        errorlist.forEach((err) -> {
            System.out.println(err);
        });
    }

    public BufferedWriter getBuffer(int row, int column) {
        if(buffer == null)
            addError(row,column,"No existe un archivo montado");
        return buffer;
    }

    public void setBuffer(BufferedWriter buffer, int row, int column) {
        if(this.buffer == null && this.bufferreader == null){
            this.buffer = buffer;            
        }
        else{
            this.addError(row, column, "Ya existe un archivo montado");
        }
    }
    
    public boolean setBufferReder(BufferedReader buffer, int row, int column){
        if(this.buffer == null && this.bufferreader == null){
            this.bufferreader = buffer;
            return true;
        }
        else{
            this.addError(row, column, "Ya existe un archivo montado");
            return false;
        }
    }
    
    public void closeBuffer(int row, int column){
        if(this.buffer == null && this.bufferreader == null){
            this.addError(row, column, "No existe un archivo montado");
        }
        else{
            try {
                if(this.buffer != null)
                    this.buffer.close();
                else
                    this.bufferreader.close();
            } catch (IOException ex) {
                Logger.getLogger(Singleton.class.getName()).log(Level.SEVERE, null, ex);
                this.addError(row, column, "Error al cerrar el disco");
            }
        }
        this.buffer = null;
        this.bufferreader = null;
    }

    public void setModelError(DefaultTableModel dtmErrores) {
        errorlist.forEach((err) -> {
             Object[] newRow={err.getMessage(),err.getRow(),err.getColumn()};
                dtmErrores.addRow(newRow);
        });
    
    }

    public void crearHTMLErrores() {
         {
      FileWriter  filewriter ;
      PrintWriter  printw ;
       
 try{
     filewriter = new FileWriter("Errores.html");
     printw = new PrintWriter(filewriter);
     printw.println("<!DOCTYPE html>");
        printw.println("<html lang=\"es\">");
	printw.println("<head>");
		printw.println("<title>Reportes</title>");
		printw.println("<meta charset=\"utf-8\">");
		printw.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
	printw.println("</head>");
	printw.println("<body>");
        printw.println("<h2 align=\"center\">TABLA DE ERRORES</h2><br>");
        printw.println("<table align=\"center\">");
        printw.println("        <tr>");
       printw.println("                 <td>#</td>");
       printw.println("                 <td>Descripcion </td><td>FILA</td>");
       printw.println("                 <td>COLUMNA</td>");
       printw.println("         </tr>");

      for (int i = 0; i < errorlist.size(); i++) {
          Errorr err =errorlist.get(i);
            printw.println("         <tr>");
            printw.println("                 <td>"+i+"</td>");
            printw.println("                 <td>"+err.getMessage()+"</td>");
            printw.println("                 <td>"+err.getRow()+"</td>");
            printw.println("                 <td>"+err.getColumn()+"</td>");
            printw.println("         </tr>");
          
         
     }

       printw.println(" </table>");
	printw.println("</body>");
        
    printw.println("</html>	");

     printw.close();
           

    }catch(Exception e){   System.err.println("Error al crear HTML"); }
    }
    }

    public void crearTablaSimbolos(String cadHtml) {
         {
      FileWriter  filewriter ;
      PrintWriter  printw ;
       
 try{
     filewriter = new FileWriter("TablaS.html");
     printw = new PrintWriter(filewriter);
     printw.println("<!DOCTYPE html>");
        printw.println("<html lang=\"es\">");
	printw.println("<head>");
		printw.println("<title>Reportes</title>");
		printw.println("<meta charset=\"utf-8\">");
		printw.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
	printw.println("</head>");
	printw.println("<body>");
        printw.println("<h2 align=\"center\">TABLA DE ERRORES</h2><br>");
        printw.println("<table align=\"center\">");
        printw.println("        <tr>");
       printw.println("                 <td>#</td>");
       printw.println("                 <td>Id </td><td>Estructura</td>");
       printw.println("                 <td>Tipo</td>");
       printw.println("                 <td>Valor</td>");
       printw.println("         </tr>");
       
        printw.println(cadHtml);



       printw.println(" </table>");
	printw.println("</body>");
        
    printw.println("</html>	");

     printw.close();
           

    }catch(Exception e){   System.err.println("Error al crear HTML"); }
    }
    }
}

