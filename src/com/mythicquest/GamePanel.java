package com.mythicquest;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    JFrame window;
    Container con;
    JPanel topNamePanel, bottomNamePanel;
    JLabel topNameLabel, bottomNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);

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

        topNamePanel = new JPanel();
        topNamePanel.setBounds(100, 100, 600, 110); // Space around text, width and height
        topNamePanel.setBackground(Color.black);
        bottomNamePanel = new JPanel();
        bottomNamePanel.setBounds(100, 210, 600, 100);
        bottomNamePanel.setBackground(Color.black);

        topNameLabel = new JLabel("MYTHIC");
        topNameLabel.setForeground(Color.white);
        topNameLabel.setFont(titleFont);
        bottomNameLabel = new JLabel("QUEST");
        bottomNameLabel.setForeground(Color.white);
        bottomNameLabel.setFont(titleFont);

        // Add label to panels, panels to containers in order to be visible
        topNamePanel.add(topNameLabel);
        bottomNamePanel.add(bottomNameLabel);
        con.add(topNamePanel);
        con.add(bottomNamePanel);
    }
}