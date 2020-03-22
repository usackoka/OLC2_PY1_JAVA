/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraficasArit;

import AST.Entorno;
import Analyzer.Token;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class Graf_Bar extends GraficaArit{

    private final String titleX;
    private final String titleY;
    private final String title;
    private final LinkedList<String> labels;
    private final LinkedList<Double> data;
    int fila, columna;

    public Graf_Bar(LinkedList<Double> data,String titleX,String titleY,String title,LinkedList<String> labels, int fila, int columna) { 
        this.data =data;
        this.titleX=titleX;
        this.titleY=titleY;
        this.title=title;
        this.labels=labels;
        this.fila = fila;
        this.columna = columna;
    }

    public DefaultCategoryDataset FillDataset(Entorno entorno) {     
      DefaultCategoryDataset dataset =  new DefaultCategoryDataset( );  
      
////----------------datos y labels Iguales ------------------------------------------

        if(this.data.size()==this.labels.size()){
            for (int i = 0; i < this.data.size(); i++)
                dataset.addValue(this.data.get(i) , "" , this.labels.get(i));    
        }
////-----------------------mas datos que labels -------------------------------------------------
        else if(this.data.size()>this.labels.size()){
           //error
            entorno.addError(new Token("Grafica BarPlot","Mas datos que labels",fila,columna));
            int sizelabel = this.labels.size();
            for (int i = 0; i < this.data.size(); i++){
                if (i<sizelabel) 
                    dataset.addValue(this.data.get(i) , "" , this.labels.get(i));    
                else 
                    //agregar desconocido a los datos
                     dataset.addValue(this.data.get(i) , "" , "Desconocido "+i);  
            
            }
                   
        }
////------------------------------/mas labels que datos----------------
        else {
            //error
            entorno.addError(new Token("Grafica BarPlot","Error dataset mas labels que datos",fila,columna));
            int sizedatos= this.data.size();
            for (int i = 0; i < this.labels.size(); i++){
                if (i<sizedatos) dataset.addValue(this.data.get(i) , "" , this.labels.get(i));    
                else 
                    //agregar desconocido a los datos
                     dataset.addValue( 0 , "" , this.labels.get(i));  
            
            }
        
        }
      return dataset; 
        
    }
    
    public void generarImagen(Entorno entorno){
        JFreeChart chart = ChartFactory.createBarChart(this.title,           
        this.titleX,            
        this.titleY, FillDataset(entorno),          
        PlotOrientation.VERTICAL,           
        false, true, false);

        CategoryItemRenderer renderer = ((CategoryPlot)chart.getPlot()).getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, 
                TextAnchor.TOP_CENTER);
        renderer.setBasePositiveItemLabelPosition(position);
 
        GraficaArit.GenerarImagen(chart, this.title);
    }

    @Override
    public ChartPanel CreatePane() {
        JFreeChart chart = ChartFactory.createBarChart(this.title,           
        this.titleX,            
        this.titleY, FillDataset(new Entorno()),          
        PlotOrientation.VERTICAL,           
        false, true, false);

        CategoryItemRenderer renderer = ((CategoryPlot)chart.getPlot()).getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, 
                TextAnchor.TOP_CENTER);
        renderer.setBasePositiveItemLabelPosition(position);
 
        ChartPanel chartPanel = new ChartPanel( chart );    
        GraficaArit.GenerarImagen(chart, this.title);
        
        return chartPanel;
    }

    private Number SumPosArreglo(LinkedList<Double> d1) {
        //sumar cada fila
        double n1 = 0;
        for (Double valPos : d1) {
            n1+=valPos;
        }
        return n1;
    }
    
}
