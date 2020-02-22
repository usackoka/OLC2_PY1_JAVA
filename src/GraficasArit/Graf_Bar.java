/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraficasArit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author D_ALCAZAR
 */
public class Graf_Bar extends GraficaArit{


    private final String titleX;
    private final String titleY;
    private final String title;
    private final LinkedList<String> labels;
    private final LinkedList<LinkedList<Double>> data;

    public Graf_Bar(LinkedList<LinkedList<Double>> data,String titleX,String titleY,String title,LinkedList<String> labels) { 
    this.data =data;
    this.titleX=titleX;
    this.titleY=titleY;
    this.title=title;
    this.labels=labels;
    }

    public DefaultCategoryDataset FillDataset() {     
      DefaultCategoryDataset dataset =  new DefaultCategoryDataset( );  
      
////----------------datos y labels Iguales ------------------------------------------

        if(this.data.size()==this.labels.size()){
            for (int i = 0; i < this.data.size(); i++)
                dataset.addValue( SumPosArreglo(this.data.get(i)) , "" , this.labels.get(i));    
        }
////-----------------------mas datos que labels -------------------------------------------------
        else if(this.data.size()>this.labels.size()){
           //error
            System.out.println("Grad_Bar: Error dataset: mas datos que labels ");
            int sizelabel = this.labels.size();
            for (int i = 0; i < this.data.size(); i++){
                if (i<sizelabel) 
                    dataset.addValue( SumPosArreglo(this.data.get(i)) , "" , this.labels.get(i));    
                else 
                    //agregar desconocido a los datos
                     dataset.addValue( SumPosArreglo(this.data.get(i)) , "" , "Desconocido "+i);  
            
            }
                   
        }
////------------------------------/mas labels que datos----------------
        else {
            //error
            System.out.println("Grad_Bar: Error dataset mas labels que datos ");
            int sizedatos= this.data.size();
            for (int i = 0; i < this.labels.size(); i++){
                if (i<sizedatos) dataset.addValue( SumPosArreglo(this.data.get(i)) , "" , this.labels.get(i));    
                else 
                    //agregar desconocido a los datos
                     dataset.addValue( 0 , "" , this.labels.get(i));  
            
            }
        
        }
      return dataset; 
        
    }

    @Override
    public ChartPanel CreatePane() {
     JFreeChart chart = ChartFactory.createBarChart(this.title,           
         this.titleX,            
         this.titleY, FillDataset(),          
         PlotOrientation.VERTICAL,           
         false, true, false);
     

    CategoryItemRenderer renderer = ((CategoryPlot)chart.getPlot()).getRenderer();
    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    renderer.setBaseItemLabelsVisible(true);
    ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, 
            TextAnchor.TOP_CENTER);
    renderer.setBasePositiveItemLabelPosition(position);
 
      ChartPanel chartPanel = new ChartPanel( chart );    
      GraficaArit.GenerarImagen(chart);
 
         
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
