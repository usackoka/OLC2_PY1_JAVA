/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraficasArit;

import AST.Entorno;
import Analyzer.Token;
import java.awt.Color;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graf_Plot extends GraficaArit{

    private final LinkedList<Double> data;
    private final String xlab;
    private final String ylab;
    private final String main;
    private  double ylimS;
    private  double ylimI;
    int fila, columna;
    
    public Graf_Plot(LinkedList<Double> data,String titleX,String titleY,String title,
                   double ylims, double ylimi, int fila, int columna) { 
        this.data =data;
        this.xlab=titleX;
        this.ylab=titleY;
        this.main=title;
        this.ylimS=ylims;
        this.ylimI=ylimi;
        this.fila = fila;
        this.columna = columna;

        if (ylimI>ylimS){this.ylimI =Double.MIN_VALUE;System.out.println("Limite inferior y mayor que limite superior");
                        this.ylimS =Double.MAX_VALUE;System.out.println("Limite superior y menor que limite inferior");}
    }
    
    public XYDataset FillDataset(Entorno entorno){
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries data0 = new XYSeries("");

        int x=1;
        for (Double y : this.data) {
            if (revisarY(y)) 
                data0.add(x, y);      
            else 
                entorno.addError(new Token("Grafica Dispersion","Error Dimension fuera del rango minimo o maximo fila: "+x,fila,columna));
            x++;
        }

        dataset.addSeries(data0);

        return dataset;
  }
    
    public void generarImagen(Entorno entorno){
        JFreeChart chart = ChartFactory.createScatterPlot(
        this.main, 
        this.xlab, this.ylab, FillDataset(entorno),
        PlotOrientation.VERTICAL, false,true,true);
        GraficaArit.GenerarImagen(chart, this.main);
    }
     
    @Override
    public ChartPanel CreatePane() {
        JFreeChart chart = ChartFactory.createScatterPlot(
        this.main, 
        this.xlab, this.ylab, FillDataset(new Entorno()),
        PlotOrientation.VERTICAL, false,true,true);
        GraficaArit.GenerarImagen(chart, this.main);
 
        return new ChartPanel(chart);
    }

    private boolean revisarY(Double g1) {
         return (g1<=this.ylimS && g1>=this.ylimI);  
    }
    
}
