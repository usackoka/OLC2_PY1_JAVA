package GraficasArit;

import AST.Entorno;
import java.util.LinkedList;
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

    private IntervalXYDataset FillDataset(Entorno entorno) {
        double[] datan = new double[data.size()];
        for(int p = 0; p < data.size();p++){
             datan[p] = data.get(p);
        }
        
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        dataset.addSeries("Histogram",datan,50);
        return dataset;
    }
    
    public void generarImagen(Entorno entorno){// Create chart
        JFreeChart chart = ChartFactory.createHistogram(
        this.main, 
        this.xlab, "", FillDataset(entorno),
        PlotOrientation.VERTICAL, false,true,true);

        //guardar imagen
        GraficaArit.GenerarImagen(chart, this.main);
    }
        
    @Override
    public ChartPanel CreatePane() {
        // Create chart
        JFreeChart chart = ChartFactory.createHistogram(
        this.main, 
        this.xlab, "", FillDataset(new Entorno()),
        PlotOrientation.VERTICAL, false,true,true);

        //guardar imagen
        GraficaArit.GenerarImagen(chart, this.main);
        return new ChartPanel(chart);
    
    }
}
