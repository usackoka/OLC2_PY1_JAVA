/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraficasArit;

import AST.Entorno;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graf_Line extends GraficaArit{

    private final LinkedList<Double> data;
    private final String xlab;
    private final String ylab;
    private final String main;
    int fila, columna;
    
    public Graf_Line(LinkedList<Double> l1, String p, String eje_X, String eje_Y, String title, int fila, int columna) {
        this.data =l1;
        this.xlab=eje_X;
        this.ylab=eje_Y;
        this.main=title;
        this.fila = fila;
        this.columna = columna;
    }

   private CategoryDataset createDataset(Entorno entorno) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 15 , "" , "1970" );
        dataset.addValue( 30 , "" , "1980" );
        dataset.addValue( 60 , "" ,  "1990" );
        dataset.addValue( 120 , "" , "2000" );
        dataset.addValue( 240 , "" , "2010" );
        dataset.addValue( 300 , "" , "2014" );
        return dataset;
   }
   
   public void generarImagen(Entorno entorno){
        JFreeChart chart= ChartFactory.createLineChart(
        this.main,
        this.xlab,this.ylab,
        createDataset(new Entorno()),
        PlotOrientation.VERTICAL,
        false,true,true);

        ChartPanel chartPanel = new ChartPanel( chart );    
        GraficaArit.GenerarImagen(chart, this.main);
   }
        
    @Override
    public ChartPanel CreatePane() {
        JFreeChart chart= ChartFactory.createLineChart(
        this.main,
        this.xlab,this.ylab,
        createDataset(new Entorno()),
        PlotOrientation.VERTICAL,
        false,true,true);

        ChartPanel chartPanel = new ChartPanel( chart );    
        GraficaArit.GenerarImagen(chart, this.main);

        return chartPanel;
    }
}
