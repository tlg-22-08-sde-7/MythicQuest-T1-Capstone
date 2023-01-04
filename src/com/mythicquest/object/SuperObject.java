package com.mythicquest.object;

import com.mythicquest.app.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image1, image2, image3, image4;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    // Shows the objects on the map
    // public void draw(Graphics2D g2, GamePanel gp)
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.getWorldX() + gp.player.screenX;
        int screenY = worldY - gp.player.getWorldX() + gp.player.screenY;

        if (worldX + gp.getScaledTileSize() > gp.player.getWorldX() - gp.player.screenX &&
                worldX - gp.getScaledTileSize() < gp.player.getWorldX() + gp.player.screenX &&
                worldY + gp.getScaledTileSize() > gp.player.getWorldY() - gp.player.screenY &&
                worldY - gp.getScaledTileSize() < gp.player.getWorldY() + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.getScaledTileSize(), gp.getScaledTileSize(), null);
        }
    }

}