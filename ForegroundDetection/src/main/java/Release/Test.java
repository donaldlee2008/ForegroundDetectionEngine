/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Release;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Reetoo
 */
public class Test {
    public static void main(String[] args) throws IOException{
    
        ForegroundDetection fd = new ForegroundDetection("rgbtrain.eg");
        String filename = "elephant.jpg";
        String path = "assets/"+filename;
        fd.setImage(path);
             
            BufferedImage image = fd.getForegroundPixels(path);
            fd.generateRegions(path);
            fd.thresholdRegions();
            
            ImageIO.write(image, "jpg", new File("mainout/"+filename));
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
        
            ImageIcon img = new ImageIcon(image);
        
            JLabel imgLabel = new JLabel(img);
        
            frame.add(imgLabel);
            frame.setVisible(true);
            frame.setSize(400,400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle(path);
            

           //System.out.println("Could not find image");
        
        
        
    }
}
