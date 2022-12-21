package com.mythicquest.app;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int tileSize = 16;
    final int scaler = 3;

    final int scaledTileSize = tileSize * scaler; // 48 * 48 tile
    final int maxColumns = 16;
    final int maxRows = 12;
    final int screenWidth = scaledTileSize * maxColumns; // 768 pixels
    final int screenHeight = scaledTileSize * maxRows; // 576 pixels

    // Frames per second (FPS)
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improves rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.017 seconds
        // double delta = 0;
        // long lastTime = System.nanoTime();
        long currentTime;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            // delta += (currentTime - lastTime) / drawInterval;
            // lastTime = currentTime;

            // if (delta >= 1) { update(); repaint(); delta--; }

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; // Convert nano to milli

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (keyH.up == true) {
            playerY -= playerSpeed;
        } else if (keyH.down == true) {
            playerY += playerSpeed;
        } else if (keyH.left == true) {
            playerX -= playerSpeed;
        } else if (keyH.right == true) {
            playerX += playerSpeed;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, scaledTileSize, scaledTileSize);

        g2.dispose();
    }
}