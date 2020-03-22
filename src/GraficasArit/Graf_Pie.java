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
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class Graf_Pie extends GraficaArit{

    private final LinkedList<String> labels;
    private final LinkedList<Double> data;
    private final String title;

    public Graf_Pie(LinkedList<Double> datos,LinkedList<String> labels, String titulo) {
      this.data =datos;
      this.labels = labels;
      this.title = titulo;
        
    }
    
    public DefaultPieDataset FillDataset(){
        DefaultPieDataset dataset = new DefaultPieDataset( );
        //datos y labels Iguales
        if(this.data.size()==this.labels.size()){
            for (int i = 0; i < this.data.size(); i++)
                dataset.setValue( this.labels.get(i) , this.data.get(i));  
        }
        //mas datos que labels 
        else if(this.data.size()>this.labels.size()){
           //error
            System.out.println("Graf_pie: Error dataset mas datos que labels ");
            int sizelabel = this.labels.size();
            for (int i = 0; i < this.data.size(); i++){
                if (i<sizelabel) dataset.setValue( this.labels.get(i) , this.data.get(i));  
                else 
                    //agregar desconocido a los datos
                     dataset.setValue( "Desconocido "+i , this.data.get(i));  
            
            }
                   
        }
        //mas labels que datos
        else {
            //error
            System.out.println("Graf_pie: Error dataset mas labels que datos ");
            int sizedatos= this.data.size();
            for (int i = 0; i < this.labels.size(); i++){
                if (i<sizedatos) dataset.setValue( this.labels.get(i) , this.data.get(i));  
                else 
                    //agregar desconocido a los datos
                     dataset.setValue( this.labels.get(i) , 0);  
            
            }
        
        }
        return dataset;

    }
    
    @Override
    public ChartPanel CreatePane(){
        
        JFreeChart chart = ChartFactory.createPieChart(      
         this.title,   
         FillDataset(),          
         true,             
         true, 
         false);
 
       
       PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
        "{1} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
       //"{2}", new DecimalFormat("0"), new DecimalFormat("0%"));
       ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
       
       GraficaArit.GenerarImagen(chart, this.title);
       return new ChartPanel( chart );  
    }
}
