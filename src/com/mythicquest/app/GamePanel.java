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
    private final int tileSize = 16;
    private final int scaler = 3;

    // Window size
    private final int scaledTileSize = tileSize * scaler; // 48 * 48 tile
    private final int maxColumns = 16;
    private final int maxRows = 12;
    public final int screenWidth = scaledTileSize * maxColumns; // 768 pixels
    public final int screenHeight = scaledTileSize * maxRows; // 576 pixels

    // Frames per second (FPS)
    int FPS = 60;

    // World settings
    public final int maxWorldCol = 50;
    public final  int maxWorldRow = 50;

    public TileManager tm = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Board board = new Board(this);

    public PlayerA player = new PlayerA(this, keyH);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10];

    public int gameState;
    public final int playState = 1;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improves rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        aSetter.setObject();
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.017 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
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

        // TILE
        tm.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);

        board.draw(g2);
        g2.dispose();
    }

    public int getScaledTileSize() {
        return scaledTileSize;
    }

}