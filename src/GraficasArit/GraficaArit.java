/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraficasArit;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public abstract class GraficaArit {

    static int contador=0;
    
    public abstract ChartPanel CreatePane();
    
    static void GenerarImagen(JFreeChart chart, String name) {
        contador++;
        try{
            GraficaArit.saveToFile(chart,"Grafica"+contador+".jpg",1500,900,200);
        }catch(IOException e){
             System.out.println("Error crear imagen GraficaArit");
        }
    }
    
    public static void saveToFile(JFreeChart chart, String aFileName, int width, int height,double quality) throws FileNotFoundException, IOException
    {
        BufferedImage img = draw( chart, width, height );
        FileOutputStream fos = new FileOutputStream("Graficas\\"+aFileName);
        JPEGImageEncoder encoder2 = JPEGCodec.createJPEGEncoder(fos);
        JPEGEncodeParam param2 = encoder2.getDefaultJPEGEncodeParam(img);
        param2.setQuality((float) quality, true);
        encoder2.encode(img,param2);
        fos.close();
        
        Desktop.getDesktop().open(new File("Graficas\\"+aFileName));
    }    

    protected static BufferedImage draw(JFreeChart chart, int width, int height)
    {
        BufferedImage img =new BufferedImage(width , height,
        BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        chart.draw(g2, new Rectangle2D.Double(0, 0, width, height));
        g2.dispose();
        return img;

    }
}
