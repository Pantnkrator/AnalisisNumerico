/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisnumerico;

import java.awt.BorderLayout;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author toto
 */
public class Graficador{
public static String convertir(Funcion F, double x){
    String S="";
    String q="("+Double.toString(x)+")";
    String f=F.getF();
    for(int i=0; i<f.length(); i++){
        if(f.charAt(i)=='x'){
            S+=q;
        }else
            S+=f.charAt(i);
    }
    return S;
    }
    
    public Graficador(Funcion F,double lower, double upper){
        //super("Graficador");
        JFrame f= new JFrame("Grafica");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new BorderLayout(0, 5));
        
        XYDataset paresDeDatos = generarDatos(F,lower,upper);
        JFreeChart diagrama = crearDiagrama(paresDeDatos);
        ChartPanel chartPanel = new ChartPanel(diagrama);
        chartPanel.setPreferredSize(new Dimension(500,400));
        //setContentPane(chartPanel);
        f.add(chartPanel, BorderLayout.CENTER);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setHorizontalAxisTrace(true);
        chartPanel.setVerticalAxisTrace(true);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
 
    private XYDataset generarDatos(Funcion F,double lower,double upper){
        //le pasamos una funcion generadora f(x)
        XYSeries datos = new XYSeries("Linea Funcion");
        for(double x=lower; x<=upper; x+=0.2) 
            datos.add(x,f(x,F));
        XYSeriesCollection conjuntoDatos = new XYSeriesCollection();
        conjuntoDatos.addSeries(datos);
 
        return conjuntoDatos;
    }
 
    private JFreeChart crearDiagrama(XYDataset conjuntoDatos){
        JFreeChart diag = ChartFactory.createXYLineChart(
                                "Graficador", //Titulo Grafica
                                "X", // Leyenda eje X
                                "Y", // Leyenda eje Y
                                conjuntoDatos, // Los datos
                                PlotOrientation.VERTICAL, //orientacion
                                false, // ver titulo de linea
                                false, //tooltips
                                false  //URL
                            );
        return diag;
    }
 
    //aqui definimos la funcion que desees, en esta caso la f(x) = 4sen(x)
    private double f(double x, Funcion F){
        Evaluador e=new Evaluador();
        String fx=convertir(F, x);
        return e.eval(fx);
    }    
}
