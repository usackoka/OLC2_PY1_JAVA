/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Analyzer.Lexer;
import Analyzer.parser;
import GUI.GUI_Principal;
import static GUI.GUI_Principal.dtmErrores;
import static GUI.GUI_Principal.paginaActual;
import Interpreter.Enviorement;
import Interpreter.Instruction.*;
import Interpreter.Objeto.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import org.omg.CORBA.Environment;

public class Principal {

    public final LinkedList<Instruction> lista;
    private Instruction main;

    public Principal(LinkedList<Instruction> lista) {
        this.lista = lista;
    }

    public void ejecutar(String ruta_ejecucion) {
        Enviorement env = new Enviorement();
        addNative_aent(env);
        addNative_aent2(env);
        addNative_adec(env);
        addNative_adec2(env);
        addNative_pesode(env);
        addNative_reservar(env);
        addNative_atxtInt(env);
        addNative_atxtDecimal(env);
        addNative_eqls(env);
        addNative_atxtString(env);
        addNative_atxtRstring(env);
        addNative_conc(env);
        addNative_NewGUI_RLabel(env);
        addNative_NewGUI_Rboton(env);
        addNative_NewGUI_Rtxt(env);
        addNative_NewGUI_RtxtA(env);
        addNative_NewGUI_RtxtN(env);
        addNative_NewGUI_RtxtP(env);
        addNative_openWindow(env);
        addNative_AnchoAlto(env);

        //============================= IMPORT ===================================
        boolean fin = false;
        while (!fin) {
            //imports a eliminar, los que ya fueron importados
            LinkedList<Instruction> imports = new LinkedList<>();
            //debería terminar el while
            fin = true;
            //busco los imports
            for (int i = 0; i < lista.size(); i++) {
                Instruction ins = lista.get(i);
                if(ins instanceof ImportDef){
                    this.lista.addAll(getImport((ImportDef)ins, env,ruta_ejecucion));
                    imports.add(ins);
                    //con un import que haya debe volver a hacer la busqueda
                    fin = false;
                }
            }

            //limpio los imports
            imports.forEach((ins) -> {
                lista.remove(ins);
            });
        }
        //==================== FIN IMPORT ====================================

        lista.forEach((ins) -> {
            if (!(ins instanceof Main)) {
                ins.ejecutar(env);
            } else {
                this.main = ins;
            }
        });

        if (this.main != null) {
            this.main.ejecutar(env);
        }
        Singleton.getInstance().printError();
    }
    
    private LinkedList<Instruction> getImport(ImportDef importt, Enviorement env,String RutaEjecucion){
        String cadena = leerArchivo(RutaEjecucion+(importt.ruta.replace("\"", "")));
        Lexer lex = new Lexer(new BufferedReader(new StringReader(cadena)));
        
        try {
            parser parse = new parser(lex);
            parse.parse();
            Principal p = parse.GetAst();
            
            //import interno
            
            
                  boolean fin = false;
                   while(!fin){
                       //imports a eliminar, los que ya fueron importados
                       LinkedList<Instruction> imports = new LinkedList<>();
                       //debería terminar el while
                       fin = true;
                       //busco los imports
                       for (int i = 0; i < p.lista.size(); i++) {
                           Instruction ins = p.lista.get(i);
                           if(ins instanceof ImportDef){
                               File rr =new File (RutaEjecucion+(importt.ruta.replace("\"", "")));
                               p.lista.addAll(getImport((ImportDef)ins, env,rr.getParent()+"\\"));
                               imports.add(ins);
                               //con un import que haya debe volver a hacer la busqueda
                               fin = false;
                           }
                       }

                       //limpio los imports
                       imports.forEach((ins)->{
                           p.lista.remove(ins);
                       });
                   }
            for (Instruction i : p.lista) {
                if (i instanceof FunctionGui) {
                    FunctionGui fg = (FunctionGui) i;
                    fg.setNombre(cadena);
                }
            }
            return p.lista;
        } catch (Exception ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Import: " + importt.ruta + " " + ex.toString());
            return new LinkedList<>();
        }
    }

    private String leerArchivo(String rutatotal) {
        //leo el archivo
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(rutatotal);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = "";
            String cadena = "";
            while ((linea = br.readLine()) != null) {
                cadena += linea + "\n";
            }
            return cadena;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    private void addNative_aent(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Cadena(""), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_aent());
        list.add(param);
        Function f = new Function("_aent", list, inst, new Primitivo(Objeto.Tipo.INT, 0), 0, 0, false);
        env.AddFunction(f, "_aent");
    }

    private void addNative_aent2(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_aent());
        list.add(param);
        Function f = new Function("_aent", list, inst, new Primitivo(Objeto.Tipo.INT, 0), 0, 0, false);
        env.AddFunction(f, "_aent");
    }

    private void addNative_adec(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Cadena(""), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_adec());
        list.add(param);
        Function f = new Function("_adec", list, inst, new Primitivo(Objeto.Tipo.DECIMAL, 0), 0, 0, false);
        env.AddFunction(f, "_adec");
    }

    private void addNative_adec2(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_adec());
        list.add(param);
        Function f = new Function("_adec", list, inst, new Primitivo(Objeto.Tipo.DECIMAL, 0), 0, 0, false);
        env.AddFunction(f, "_adec");
    }

    private void addNative_pesode(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Cadena(""), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_pesode());
        list.add(param);
        Function f = new Function("_pesode", list, inst, new Primitivo(Objeto.Tipo.INT, 0), 0, 0, false);
        env.AddFunction(f, "_pesode");
    }

    private void addNative_reservar(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Primitivo(Objeto.Tipo.INT, 0), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_reservar());
        list.add(param);
        Function f = new Function("_reservar", list, inst, new Fusion("", null), 0, 0, false);
        env.AddFunction(f, "_reservar");
    }

    private void addNative_atxtString(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Cadena(""), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_atxt());
        list.add(param);
        Function f = new Function("_atxt", list, inst, new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0, false);
        env.AddFunction(f, "_atxt");
    }

    private void addNative_atxtRstring(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new RString(""), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_atxt());
        list.add(param);
        Function f = new Function("_atxt", list, inst, new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0, false);
        env.AddFunction(f, "_atxt");
    }

    private void addNative_atxtInt(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Primitivo(Objeto.Tipo.INT, 0), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_atxt());
        list.add(param);
        Function f = new Function("_atxt", list, inst, new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0, false);
        env.AddFunction(f, "_atxt");
    }

    private void addNative_atxtDecimal(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Primitivo(Objeto.Tipo.DECIMAL, 0.0), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_atxt());
        list.add(param);
        Function f = new Function("_atxt", list, inst, new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0, false);
        env.AddFunction(f, "_atxt");
    }

    private void addNative_eqls(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0);
        Parameter param2 = new Parameter("arg1", new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_eqls());
        list.add(param);
        list.add(param2);
        Function f = new Function("_eqls", list, inst, new Primitivo(Objeto.Tipo.BOOLEAN, false), 0, 0, false);
        env.AddFunction(f, "_eqls");
    }

    private void addNative_conc(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0);
        Parameter param2 = new Parameter("arg1", new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_conc());
        list.add(param);
        list.add(param2);
        Function f = new Function("_conc", list, inst, new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0, false);
        env.AddFunction(f, "_conc");
    }

    private void addNative_NewGUI_RLabel(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new RLabel(), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_NuevoGui());
        list.add(param);
        Function f = new Function("_Nuevo_GUI", list, inst, null, 0, 0, true);
        env.AddFunction(f, "_Nuevo_GUI");
    }

    private void addNative_NewGUI_Rtxt(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Rtxt(), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_NuevoGui());
        list.add(param);
        Function f = new Function("_Nuevo_GUI", list, inst, null, 0, 0, true);
        env.AddFunction(f, "_Nuevo_GUI");
    }

    private void addNative_NewGUI_RtxtA(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new RtxtA(), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_NuevoGui());
        list.add(param);
        Function f = new Function("_Nuevo_GUI", list, inst, null, 0, 0, true);
        env.AddFunction(f, "_Nuevo_GUI");
    }

    private void addNative_NewGUI_RtxtP(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new RtxtP(), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_NuevoGui());
        list.add(param);
        Function f = new Function("_Nuevo_GUI", list, inst, null, 0, 0, true);
        env.AddFunction(f, "_Nuevo_GUI");
    }

    private void addNative_NewGUI_RtxtN(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new RtxtN(), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_NuevoGui());
        list.add(param);
        Function f = new Function("_Nuevo_GUI", list, inst, null, 0, 0, true);
        env.AddFunction(f, "_Nuevo_GUI");
    }

    private void addNative_NewGUI_Rboton(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Rboton(), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_NuevoGui());
        list.add(param);
        Function f = new Function("_Nuevo_GUI", list, inst, null, 0, 0, true);
        env.AddFunction(f, "_Nuevo_GUI");
    }

    private void addNative_openWindow(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Arreglo(new Primitivo(Objeto.Tipo.CHAR, null)), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_abrir_ventana());
        list.add(param);
        Function f = new Function("_abrir_ventana", list, inst, null, 0, 0, true);
        env.AddFunction(f, "_abrir_ventana");
    }

    private void addNative_AnchoAlto(Enviorement env) {
        LinkedList<Parameter> list = new LinkedList<>();
        Parameter param = new Parameter("arg0", new Primitivo(Objeto.Tipo.INT, 0), 0, 0);
        Parameter param1 = new Parameter("arg1", new Primitivo(Objeto.Tipo.INT, 0), 0, 0);
        LinkedList<Instruction> inst = new LinkedList<>();
        inst.add(new Nativa_DimVentana());
        list.add(param);
        list.add(param1);
        Function f = new Function("_alto_y_ancho", list, inst, null, 0, 0, true);
        env.AddFunction(f, "_alto_y_ancho");
    }
}

/*
 */
