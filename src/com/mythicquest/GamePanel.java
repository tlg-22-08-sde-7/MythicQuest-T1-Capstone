package com.mythicquest;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    JFrame window;
    Container con;
    JPanel titleNamePanel;
    JLabel titleNameLabel;

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

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150); // Space around text, width and height
        titleNamePanel.setBackground(Color.blue);
        titleNameLabel = new JLabel("MYTHIC QUEST");
        titleNameLabel.setForeground(Color.white);
        titleNamePanel.add(titleNameLabel);
        con.add(titleNamePanel);
    }
}