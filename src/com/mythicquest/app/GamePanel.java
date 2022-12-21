package com.mythicquest.app;

import com.mythicquest.engine.PlayerA;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
    PlayerA player = new PlayerA(this, keyH);

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
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        g2.dispose();
    }

    public int getScaledTileSize() {
        return scaledTileSize;
    }
}