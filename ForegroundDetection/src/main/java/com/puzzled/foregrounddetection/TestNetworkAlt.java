/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puzzled.foregrounddetection;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.encog.Encog;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;

/**
 *
 * @author Reetoo
 */
public class TestNetworkAlt {
    
    
    
    public static void main(String[] args){
        
        Color[] colors = new Color[]{Color.WHITE,Color.MAGENTA,Color.GREEN,Color.BLUE,Color.CYAN,Color.YELLOW,Color.ORANGE,Color.RED,Color.BLACK, Color.GRAY};

        
          int rgbRed = 255;
        rgbRed = (rgbRed << 8) + 0;
        rgbRed = (rgbRed << 8) + 0;
        
        int rgbBlue = 0;
        rgbBlue = (rgbBlue << 8) + 0;
        rgbBlue = (rgbBlue << 8) + 255;
        
        rgbRed = (rgbBlue + rgbRed)/2;
         
        
        File folder = new File("assets//trainingset");
        for (final File file : folder.listFiles()) {
            
        //String FILENAME = "assets//images//3002154.jpg"; //main
        //String FILENAME = "assets//images//9000868.jpg"; //main
        //String FILENAME = "assets//images//1001685.jpg";
        //String FILENAME = "assets//images//3000148.jpg";
        //String FILENAME = "assets//images//9002577.jpg";
        //String FILENAME = "assets//images//1100007.jpg";
        //String FILENAME = "assets//test.png";
        
        if(file.isDirectory()){
            
        }
        else{
         
        BasicNetwork network = (BasicNetwork)EncogDirectoryPersistence.loadObject(new File("encogexample2.eg"));
        
        
        BufferedImage image = null;
        try{
            image = ImageIO.read(file);
        }
        catch(IOException e){
            System.out.println("Cannot find image.");
        }
        
        double[][] inputs = new double[image.getWidth()][3];
        double[][] outputs = new double[image.getWidth()][1];
        MLDataSet trainingSet = null;
        ResilientPropagation train = null;
        
        for (int y = 0; y < image.getHeight(); y++) {
           
            
            
            for (int x = 0; x < image.getWidth(); x++) {
                final int clr = image.getRGB(x, y);
                final int red = (clr & 0x00ff0000) >> 16;
                final int green = (clr & 0x0000ff00) >> 8;
                final int blue = clr & 0x000000ff;
                
                               
                double r = red;
                double g = green;
                double b = blue;
                
                double total = r+g+b;
                
                r = r/total;
                g = g/total;
                b = b/total;
                
                
                inputs = new double[][]{{1, r, g, b}};
                    
                    //System.out.println("Hello");
                trainingSet = new BasicMLDataSet(inputs, outputs);
         
             for(MLDataPair pair: trainingSet){
                    final MLData output = network.compute(pair.getInput());
                    //System.out.println(Arrays.toString(output.getData()));
                    
                    //image.setRGB(i,y, colors[colors.length-1].getRGB());
                    
                    if(output.getData(0)>=0.2){
                         image.setRGB(x, y, colors[1].getRGB());
                        //image.setRGB(i, y, rgbRed);
                        //System.out.println(Arrays.toString(output.getData()));
                    }
                    if(output.getData(0)>=0.3){
                         image.setRGB(x, y, colors[2].getRGB());
                        //image.setRGB(i, y, rgbRed);
                        //System.out.println(Arrays.toString(output.getData()));
                    }
                    if(output.getData(0)>=0.4){
                         image.setRGB(x, y, colors[3].getRGB());
                        //image.setRGB(i, y, rgbRed);
                        //System.out.println(Arrays.toString(output.getData()));
                    }
                    if(output.getData(0)>=0.5){
                         image.setRGB(x, y, colors[4].getRGB());
                        //image.setRGB(i, y, rgbRed);
                        //System.out.println(Arrays.toString(output.getData()));
                    }
                    if(output.getData(0)>=0.6){
                         image.setRGB(x, y, colors[5].getRGB());
                        //image.setRGB(i, y, rgbRed);
                        //System.out.println(Arrays.toString(output.getData()));
                    }
                    if(output.getData(0)>=0.7){
                         image.setRGB(x, y, colors[6].getRGB());
                        //image.setRGB(i, y, rgbRed);
                        //System.out.println(Arrays.toString(output.getData()));
                    }
                    if(output.getData(0)>=0.8){
                         image.setRGB(x, y, colors[7].getRGB());
                        //image.setRGB(i, y, rgbRed);
                        //System.out.println(Arrays.toString(output.getData()));
                    }
                    if(output.getData(0)>=0.9){
                         image.setRGB(x, y, colors[8].getRGB());
                        //image.setRGB(i, y, rgbRed);
                        //System.out.println(Arrays.toString(output.getData()));
                    }
                    

                    else{
                       
                        //System.out.print("O");
                    }
                   
            //System.out.println(pair.getInput().getData(0) + ", " + pair.getInput().getData(1) + ", actual=" + output.getData(0) + ",ideal =" + pair.getIdeal().getData(0));
            }    
                
                
                
               
                
                
            }
                        
            
             
             //System.out.println();

        }
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        
        ImageIcon img = new ImageIcon(image);
        
        JLabel imgLabel = new JLabel(img);
        
        frame.add(imgLabel);
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(file.getName());
        }
        }
        Encog.getInstance().shutdown();
    }
}
