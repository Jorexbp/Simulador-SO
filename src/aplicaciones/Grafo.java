package aplicaciones;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;




public class Grafo extends JFrame {

    private static final long serialVersionUID = 1L;
    JFreeChart chart ;

    public Grafo(String applicationTitle, String chartTitle,String[][] arr) {
        super(applicationTitle);
        // This will create the dataset
        PieDataset dataset = createDataset(arr);
        // based on the dataset we create the chart
      chart= createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset
     */
    private  PieDataset createDataset(String[][] arr) {
        DefaultPieDataset result = new DefaultPieDataset();
        String uno="";
        int dato=0;
        
        for (int i = 0; i < arr.length; i++) {
        	uno = arr[i][0];
        	dato = Integer.parseInt(arr[i][1]);
        	result.setValue(uno, dato);
		}
        return result;

    }

    /**
     * Creates a chart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
            title,                  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }
    
    public byte[] convertirImagen() {
    	BufferedImage objBufferedImage=chart.createBufferedImage(600,800);
    	ByteArrayOutputStream bas = new ByteArrayOutputStream();
    	        try {
    	            ImageIO.write(objBufferedImage, "png", bas);
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }

    	byte[] byteArray=bas.toByteArray();
    	return byteArray;
    }
}


