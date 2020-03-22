/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraficasArit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.IntervalXYDataset;

public class Graf_Hist extends GraficaArit{

    private final LinkedList<LinkedList<Double>> data;
    private final String xlab;
    private final String main;
    private double xlimS;
    private double xlimI;
    private double ylimS;
    private double ylimI;

    public Graf_Hist(LinkedList<LinkedList<Double>> l1, String eje_X, String title, double xlims, double xlimi, double ylims, double ylimi) {
    this.data =l1;
    this.xlab=eje_X;
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
    

    
    }

    private IntervalXYDataset FillDataset() {
        double[] value = new double[11];
       for (int i=1; i < 10; i++) {
       value[i] = i;}
           int number = 10;
           
           value[10] = 5;
  ArrayList<Double> data= new ArrayList<Double>();         
           data.add(1.48);
data.add(1.44);
data.add(1.51);
data.add(1.54);
data.add(1.66);
data.add(1.73);
data.add(1.50);
data.add(1.79);
data.add(1.76);
data.add(1.70);
data.add(1.70);
data.add(1.71);
data.add(1.71);
data.add(1.54);
data.add(1.64);
data.add(1.56);
data.add(1.76);
data.add(1.53);
data.add(1.59);
data.add(1.65);
data.add(1.42);
data.add(1.47);
data.add(1.48);
data.add(1.65);
data.add(1.78);
data.add(1.59);
data.add(1.43);
data.add(1.80);
data.add(1.73);
data.add(1.65);
data.add(1.48);
data.add(1.51);
data.add(1.64);
data.add(1.71);
data.add(1.45);
data.add(1.63);
data.add(1.48);
data.add(1.57);
data.add(1.41);
data.add(1.42);
data.add(1.46);
data.add(1.49);
data.add(1.42);
data.add(1.64);
data.add(1.44);
data.add(1.66);
data.add(1.77);
data.add(1.55);
data.add(1.70);
data.add(1.76);

  double[] datan = new double[data.size()];
        for(int p = 0; p < data.size();p++)
             datan[p] = data.get(p);
        
       HistogramDataset dataset = new HistogramDataset();
       dataset.setType(HistogramType.RELATIVE_FREQUENCY);
       dataset.addSeries("Histogram",datan,50);
       return dataset;
       
//        double[] data = new double[dataArrayList.size()];
//
//        for(int p = 0; p < dataArrayList.size();p++)
//             data[p] = dataArrayList.get(p);
    
    }
        
    @Override
    public ChartPanel CreatePane() {
   
    // Create chart
    JFreeChart chart = ChartFactory.createHistogram(
        this.main, 
        this.xlab, "", FillDataset(),
        PlotOrientation.VERTICAL, false,true,true);

    //Changes background color
//    XYPlot plot = (XYPlot)chart.getPlot();
//    plot.setBackgroundPaint(new Color(255,228,196));

    //guardar imagen
    GraficaArit.GenerarImagen(chart, this.main);
 
    return new ChartPanel(chart);
    
    }


    
}
