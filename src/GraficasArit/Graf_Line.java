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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graf_Line extends GraficaArit{

    private final LinkedList<Double> data;
    private final String xlab;
    private final String ylab;
    private final String main;
    int fila, columna;
    private final String tipoG;
    
    public Graf_Line(LinkedList<Double> l1, String p1, String eje_X, String eje_Y, String title, int fila, int columna) {
        this.data =l1;
        this.xlab=eje_X;
        this.ylab=eje_Y;
        this.main=title;
        this.fila = fila;
        this.columna = columna;
        this.tipoG=p1;
    }

   private XYDataset createDataset(Entorno entorno) {
       XYSeries dataset = new XYSeries("");

       for (int i = 0; i < this.data.size(); i++) {
                 dataset.add(i+1,(Number)this.data.get(i));
       }
        XYSeriesCollection dataset1 = new XYSeriesCollection();
        dataset1.addSeries(dataset);
        return dataset1;
   }
   
   public void generarImagen(Entorno entorno){
        JFreeChart chart = ChartFactory.createXYLineChart(this.main,
            this.xlab,
            this.ylab,  createDataset(new Entorno()),                  
            PlotOrientation.VERTICAL,
            false,                     
            false,                     
            false                    
        );
        
        XYPlot plot = chart.getXYPlot();
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        if (this.tipoG.toLowerCase().equals("p")){
                renderer.setSeriesLinesVisible(0, false);
        } else if (this.tipoG.toLowerCase().equals("i")){
                renderer.setSeriesShapesVisible(0, false);
        } else  if (!this.tipoG.toLowerCase().equals("o")){
   //''''''''''''''''''''guardar error ''''''''''''''''''''''''''''''''''''''''''''    
          entorno.addError(new Token("Graf_Line", " letra grafica lineal incorrecta :"+this.tipoG, fila, columna));
        }
        plot.setRenderer(renderer);

       
        ChartPanel chartPanel = new ChartPanel( chart );    
        GraficaArit.GenerarImagen(chart, this.main);
   }
        
    @Override
    public ChartPanel CreatePane() {
        JFreeChart chart = ChartFactory.createXYLineChart(this.main,
            this.xlab,
            this.ylab,  createDataset(new Entorno()),                  
            PlotOrientation.VERTICAL,
            false,                     
            false,                     
            false                     
        );
        
        XYPlot plot = chart.getXYPlot();
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        if (this.tipoG.toLowerCase().equals("p")){
                renderer.setSeriesLinesVisible(0, false);
        } else if (this.tipoG.toLowerCase().equals("i")){
                renderer.setSeriesShapesVisible(0, false);
        } else  if (!this.tipoG.toLowerCase().equals("o")){
   //''''''''''''''''''''guardar error ''''''''''''''''''''''''''''''''''''''''''''             
                System.out.println("Error letra grafica linea incorrecta :"+this.tipoG);
        }
        plot.setRenderer(renderer);


        ChartPanel chartPanel = new ChartPanel( chart );    
        GraficaArit.GenerarImagen(chart, this.main);

        return chartPanel;
    }
}
