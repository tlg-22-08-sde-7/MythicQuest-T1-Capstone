package com.mythicquest.app;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    // Screen settings
    final int tileSize = 16;
    final int scaler = 3;

    final int scaledTileSize = tileSize * scaler; // 48 * 48 tile
    final int maxColumns = 16;
    final int maxRows = 12;
    final int screenWidth = scaledTileSize * maxColumns; // 768 pixels
    final int screenHeight = scaledTileSize * maxRows; // 576 pixels

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improves rendering performance
    }
}