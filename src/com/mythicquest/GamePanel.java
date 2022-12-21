package com.mythicquest;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    JFrame window;
    Container con;
    JPanel topNamePanel, bottomNamePanel, readPanel, startPanel;
    JLabel topNameLabel, bottomNameLabel;
    JButton readBtn, startBtn;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font btnFont = new Font("Times New Roman", Font.PLAIN, 25);

    public static void main(String[] args) {
        new GamePanel();
    }

    public GamePanel() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        con = window.getContentPane();

        // Title panel and text (top)
        topNamePanel = new JPanel();
        topNamePanel.setBounds(100, 80, 600, 110); // Space around text, width and height
        topNamePanel.setBackground(Color.black);
        topNameLabel = new JLabel("MYTHIC");
        topNameLabel.setForeground(Color.yellow); // font color
        topNameLabel.setFont(titleFont);

        // Title panel and text (bottom)
        bottomNamePanel = new JPanel();
        bottomNamePanel.setBounds(100, 195, 600, 100);
        bottomNamePanel.setBackground(Color.black);
        bottomNameLabel = new JLabel("QUEST");
        bottomNameLabel.setForeground(Color.yellow);
        bottomNameLabel.setFont(titleFont);

        // Read panel and button
        readPanel = new JPanel();
        readPanel.setBounds(285, 330, 250, 80);
        readPanel.setBackground(Color.black);
        readBtn = new JButton("Read Instructions");
        readBtn.setBackground(Color.black);
        readBtn.setForeground(Color.white);
        readBtn.setFont(btnFont);

        // Start panel and button
        startPanel = new JPanel();
        startPanel.setBounds(280, 400, 250, 80);
        startPanel.setBackground(Color.black);
        startBtn = new JButton("Start Game");
        startBtn.setBackground(Color.black);
        startBtn.setForeground(Color.white);
        startBtn.setFont(btnFont);

        // Add label to panels, panels to containers in order to be visible
        topNamePanel.add(topNameLabel);
        bottomNamePanel.add(bottomNameLabel);
        readPanel.add(readBtn);
        startPanel.add(startBtn);

        con.add(topNamePanel);
        con.add(bottomNamePanel);
        con.add(readPanel);
        con.add(startPanel);
    }
}