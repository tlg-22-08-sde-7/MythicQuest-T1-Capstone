package com.mythicquest.app;

import com.mythicquest.CollisionChecker;
import com.mythicquest.entity.PlayerA;
import com.mythicquest.object.SuperObject;

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
    public final int screenWidth = scaledTileSize * maxColumns; // 768 pixels
    public final int screenHeight = scaledTileSize * maxRows; // 576 pixels

    // Frames per second (FPS)
    int FPS = 60;

    //world settings
    public final int maxWorldCol = 48;
    public final  int maxWorldRow = 48;
    public final int worldWidth = scaledTileSize * maxWorldCol;
    public final int worldHeight = scaledTileSize * maxWorldRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public TileManager tm = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public PlayerA player = new PlayerA(this, keyH);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10];

//    public void initClasses(){
//        player.loadTileData(tm.getCurrentTile());
//    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improves rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        aSetter.setObject();
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

    public int getTileSize() {
        return tileSize;
    }

    public void update() {
        player.update();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tm.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);

        g2.dispose();
    }

    public int getScaledTileSize() {
        return scaledTileSize;
    }
}