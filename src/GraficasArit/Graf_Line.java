/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraficasArit;

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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author D_ALCAZAR
 */
public class Graf_Line extends GraficaArit{

    private final LinkedList<LinkedList<Double>> data;
    private final String xlab;
    private final String ylab;
    private final String main;
    
    public Graf_Line(LinkedList<LinkedList<Double>> l1, String p, String eje_X, String eje_Y, String title) {
            this.data =l1;
            this.xlab=eje_X;
            this.ylab=eje_Y;
            this.main=title;    
    }

   private CategoryDataset createDataset() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 15 , "" , "1970" );
      dataset.addValue( 30 , "" , "1980" );
      dataset.addValue( 60 , "" ,  "1990" );
      dataset.addValue( 120 , "" , "2000" );
      dataset.addValue( 240 , "" , "2010" );
      dataset.addValue( 300 , "" , "2014" );
      return dataset;
       
   }
        
    @Override
    public ChartPanel CreatePane() {
   
      JFreeChart chart= ChartFactory.createLineChart(
         this.main,
         this.xlab,this.ylab,
         createDataset(),
         PlotOrientation.VERTICAL,
         false,true,true);

      ChartPanel chartPanel = new ChartPanel( chart );    
      GraficaArit.GenerarImagen(chart);
 
         
      return chartPanel;
    
    }


    
}
