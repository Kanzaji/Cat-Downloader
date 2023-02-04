package com.kanzaji.catdownloader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GUI {
    public static void startGUI() {
        // Getting the screen resolution, so the app can scale itself with the resolution the user uses
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        JFrame frame = new JFrame("Cat-Downloader " + CatDownloader.VERSION);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width/2, height/2);

        JButton button;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints GBC = new GridBagConstraints();
        
        button = new JButton("TEST");
        GBC.fill = GridBagConstraints.HORIZONTAL;
        GBC.gridx = 0;
        GBC.gridy = 0;
        panel.add(button, GBC);
        try {
            BufferedImage myPicture = ImageIO.read(new File("test.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            panel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        } 


        // Adding stuff to the frame or whatever
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
