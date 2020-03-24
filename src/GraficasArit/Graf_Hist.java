package GraficasArit;

import AST.Entorno;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.IntervalXYDataset;

public class Graf_Hist extends GraficaArit{

    private final LinkedList<Double> data;
    private final String xlab;
    private final String main;

    public Graf_Hist(LinkedList<Double> l1, String eje_X, String title) {
        this.data =l1;
        this.xlab=eje_X;
        this.main=title;
    }

    
    public void generarImagen(Entorno entorno){// Create chart

    HistogramDataset dataset = new HistogramDataset();
    double[] values = new double[this.data.size()];//{10,15,5,8,7,4,11,18,27,14,12};
    for (int i = 0; i < this.data.size(); i++) values[i]=this.data.get(i);

    dataset.setType(HistogramType.FREQUENCY);
     int bins =(int)(1+3.322*Math.log10(values.length));
     if (bins%2==0)bins++;

     //dataset.addSeries("H1", values, ope1,0,calcularmax()); //evaluar cual de las 2 distribuciones es la correcta
     dataset.addSeries("H1", values, bins,0,calcularmax(values,bins)); // Pruebas


      JFreeChart barGraph = ChartFactory.createHistogram
    (null, "", null, dataset, PlotOrientation.VERTICAL, false, false, false);
     
    //guardar imagen
        GraficaArit.GenerarImagen(barGraph, this.main);
    }
        
    @Override
    public ChartPanel CreatePane() {
        // Create chart
       HistogramDataset dataset = new HistogramDataset();
        double[] values = new double[this.data.size()];//{10,15,5,8,7,4,11,18,27,14,12};
        for (int i = 0; i < this.data.size(); i++) values[i]=this.data.get(i);

        dataset.setType(HistogramType.FREQUENCY);
         int bins =(int)(1+3.322*Math.log10(values.length));
         if (bins%2==0)bins++;

       //dataset.addSeries("H1", values, ope1,0,calcularmax()); //evaluar cual de las 2 distribuciones es la correcta
       dataset.addSeries("H1", values, bins,0,calcularmax(values,bins)); // Pruebas


        JFreeChart barGraph = ChartFactory.createHistogram
       (null, "", null, dataset, PlotOrientation.VERTICAL, false, false, false);
      
        return new ChartPanel(barGraph );
    
    }


    private double calcularmax( double[] values,int op1) {
        double VM=getMaxValue(values);
        double ret = op1;
        if(op1==0)return ret;
        while(true){
            if(VM<ret)break;
            else {
                ret=ret+op1;
            }
            
        }
        return ret;
        
    
    }
    public static double getMaxValue(double[] numbers){
        if (numbers.length==0)return 1;
        double maxValue = numbers[0];
        for(int i=1;i < numbers.length;i++){
          if(numbers[i] > maxValue){
                maxValue = numbers[i];
              }
        }
        return maxValue;
    }
}

