package com.mythicquest;

import javax.swing.*;
import com.mythicquest.app.GamePanel;

public class Main {

    private static JFrame window;

    public static void main(String[] args) {
        TitleScreen title = new TitleScreen();
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Mythic Quest");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(false);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    public static void setWindowVisible(boolean value) {
        Main.window.setVisible(value);
    }
}