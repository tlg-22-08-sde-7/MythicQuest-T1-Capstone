package com.mythicquest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class TitleScreen {
    JFrame window;
    Container con;
    JPanel topNamePanel, bottomNamePanel, readPanel, startPanel;
    JLabel topNameLabel, bottomNameLabel;
    JButton readBtn, startBtn;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font btnFont = new Font("Times New Roman", Font.PLAIN, 25);

    public static void main(String[] args) throws IOException {
        new TitleScreen();
    }

    public TitleScreen() {
        try {
            window = new JFrame();
            window.setSize(800, 600);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLayout(null);
            window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/images/title_screen_img.jpg")))));
            window.setVisible(true);
            window.setLocationRelativeTo(null);
            con = window.getContentPane();

            // Title panel and text (top)
            topNamePanel = new JPanel();
            topNamePanel.setBounds(100, 80, 600, 110); // Space around text, width and height
            topNamePanel.setOpaque(false); // Creates transparent background
            topNameLabel = new JLabel("MYTHIC");
            topNameLabel.setForeground(Color.yellow); // Font color
            topNameLabel.setFont(titleFont);

            // Title panel and text (bottom)
            bottomNamePanel = new JPanel();
            bottomNamePanel.setBounds(100, 195, 600, 100);
            bottomNamePanel.setOpaque(false);
            bottomNameLabel = new JLabel("QUEST");
            bottomNameLabel.setForeground(Color.yellow);
            bottomNameLabel.setFont(titleFont);

            // Read panel and button
            readPanel = new JPanel();
            readPanel.setBounds(285, 330, 250, 80);
            readPanel.setOpaque(false);
            readBtn = new JButton("Read Instructions");
            readBtn.setBackground(Color.black);
            readBtn.setForeground(Color.white);
            readBtn.setFont(btnFont);
            readBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Shows instruction
                }
            });

            // Start panel and button
            startPanel = new JPanel();
            startPanel.setBounds(282, 400, 250, 80);
            startPanel.setOpaque(false);
            startBtn = new JButton("Start Game");
            startBtn.setBackground(Color.black);
            startBtn.setForeground(Color.white);
            startBtn.setFont(btnFont);
            startBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.setWindowVisible(true);
                    window.setVisible(false);
                }
            });

            // Add label to panels, panels to containers in order to be visible
            topNamePanel.add(topNameLabel);
            bottomNamePanel.add(bottomNameLabel);
            readPanel.add(readBtn);
            startPanel.add(startBtn);

            con.add(topNamePanel);
            con.add(bottomNamePanel);
            con.add(readPanel);
            con.add(startPanel);

            window.revalidate();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}