package com.mythicquest.app;

import com.mythicquest.object.Heart;
import com.mythicquest.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

public class Board {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage emptyHeart, quarterHeart, halfHeart, threeQuarterHeart, fullHeart;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;

    public Board(GamePanel gp) {
        this.gp = gp;

        SuperObject heart = new Heart(gp);
        emptyHeart = heart.image;
        quarterHeart = heart.image1;
        halfHeart = heart.image2;
        threeQuarterHeart = heart.image3;
        fullHeart = heart.image4;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
    }

    public void drawPlayerLife() {
        int x = gp.getScaledTileSize() / 2;
        int y = gp.getScaledTileSize() / 2;
        int i = 0;

        while (i < gp.player.getMaxLife() / 2) {
            g2.drawImage(emptyHeart, x, y, null);
            i++;
            x += gp.getScaledTileSize();
        }

        x = gp.getScaledTileSize() / 2;
        y = gp.getScaledTileSize() / 2;
        i = 0;

        while (i < gp.player.getLife()) {
            g2.drawImage(halfHeart, x, y, null);
            i++;
            if (i < gp.player.getLife()) {
                g2.drawImage(fullHeart, x, y, null);
            }
            i++;
            x += gp.getScaledTileSize();
        }
    }
}