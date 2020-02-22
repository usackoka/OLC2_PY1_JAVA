/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraficasArit;

import java.awt.Color;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author D_ALCAZAR
 */
public class Graf_Plot extends GraficaArit{

    private final LinkedList<LinkedList<Double>> data;
    private final String xlab;
    private final String ylab;
    private final String main;
    private final boolean byrow;
    private  double xlimS;
    private  double xlimI;
    private  double ylimS;
    private  double ylimI;


    
    public Graf_Plot(LinkedList<LinkedList<Double>> data,String titleX,String titleY,String title,
                   double xlims, double xlimi, double ylims, double ylimi,boolean byrow) { 
    this.data =data;
    this.xlab=titleX;
    this.ylab=titleY;
    this.main=title;
    this.xlimS=xlims;
    this.xlimI=xlimi;
    this.ylimS=ylims;
    this.ylimI=ylimi;
    

    //revisar error de cambio de eje
    if (xlimI>xlimS){this.xlimI =Double.MIN_VALUE;System.out.println("Limite inferior x mayor que limite superior");//}
   // if (xlimS<xlimI){
                    this.xlimS =Double.MAX_VALUE;System.out.println("Limite superior x menor que limite inferior");}
    
    
    if (ylimI>ylimS){this.ylimI =Double.MIN_VALUE;System.out.println("Limite inferior y mayor que limite superior");//}
    //if (ylimS<ylimI){
                    this.ylimS =Double.MAX_VALUE;System.out.println("Limite superior y menor que limite inferior");}
    
    this.byrow=byrow;
    }
    
    public XYDataset  FillDataset(){
    XYSeriesCollection dataset = new XYSeriesCollection();
    XYSeries data0 = new XYSeries("");

    int i =1;
    for (LinkedList<Double> arr1 : this.data) {
        double x , y ;
        
        //cambiar ejes
        if (this.byrow){x =arr1.get(0); y =arr1.get(1);}
        else {x =arr1.get(1); y =arr1.get(0);}
        
        if (revisarX(x)&&revisarY(y))       
        data0.add(x, y);      
        else 
            System.out.println("Graf_Plot: Error Dimension fuera del rango minimo o maximo fila: "+i);
        i++;
    }
          

    dataset.addSeries(data0);
    


    return dataset;
  }
     
    @Override
    public ChartPanel CreatePane() {
    // Create chart
    JFreeChart chart = ChartFactory.createScatterPlot(
        this.main, 
        this.xlab, this.ylab, FillDataset(),
        PlotOrientation.VERTICAL, false,true,true);

    
    //Changes background color
//    XYPlot plot = (XYPlot)chart.getPlot();
//    plot.setBackgroundPaint(new Color(255,228,196));

    //guardar imagen
    GraficaArit.GenerarImagen(chart);
 
    return new ChartPanel(chart);
  
    
    }

    private boolean revisarX(Double g1) {
        return (g1<=this.xlimS && g1>=this.xlimI);  
    }

    private boolean revisarY(Double g1) {
         return (g1<=this.ylimS && g1>=this.ylimI);  
    }
    
}
