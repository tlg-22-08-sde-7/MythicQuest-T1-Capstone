package com.mythicquest;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    JFrame window;
    Container con;
    JPanel titleNamePanel;

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
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150); // Space around text
        titleNamePanel.setBackground(Color.blue);

    }
}