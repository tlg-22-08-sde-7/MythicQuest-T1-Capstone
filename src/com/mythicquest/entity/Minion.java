package com.mythicquest.entity;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.UtilityTool;
import com.mythicquest.object.HealthPotion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Minion extends Sprite {
    private int health = 50;
    GamePanel gp;
    BufferedImage up1;

    public Minion(GamePanel gp) {
        super();
    //    TODO  fix this super call

        this.gp = gp;


//        solidArea.x = 3;
//        solidArea.y = 15;
//        solidArea.width = 42;
//        solidArea.height = 21;
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        setWorldX(gp.getScaledTileSize() * 23);
        setWorldY(gp.getScaledTileSize() * 21);
        setSpeed(4);
        setDirection("down");
    }

    public int attack() {
        return (int) (Math.random() * 5) + 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public void getImage() {        // TODO fix file path?
        up1 = setup("rat-idle-1");
//            up2 = setup("file-path",gp.tileSize,gp.tileSize);
//            down1 = setup("file-path",gp.tileSize,gp.tileSize);
//            down2 = setup("file-path",gp.tileSize,gp.tileSize);
//            left1 = setup("file-path",gp.tileSize,gp.tileSize);
//            left2 = setup("file-path",gp.tileSize,gp.tileSize);
//            right1 = setup("file-path",gp.tileSize,gp.tileSize);
//            right2 = setup("file-path",gp.tileSize,gp.tileSize);
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/Sprites/" + imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.getScaledTileSize(), gp.getScaledTileSize());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void checkDropItem() {
        // Randomly drop an item
        int i = new Random().nextInt(100) + 1;

        // Set the monster drop
        if (i < 75) {
//            dropItem(new HealthPotion(gp));
        }

    }

    private void dropItem(HealthPotion HealthPotion) {

    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = getWorldX() - gp.player.getWorldX() + gp.player.screenX;
        int screenY = getWorldY() - gp.player.getWorldY() + gp.player.screenY;

        if (getWorldX() + gp.getScaledTileSize() > gp.player.getWorldX() - gp.player.screenX &&
                getWorldX() - gp.getScaledTileSize() < gp.player.getWorldX() + gp.player.screenX &&
                getWorldY() + gp.getScaledTileSize() > gp.player.getWorldY() - gp.player.screenY &&
                getWorldY() - gp.getScaledTileSize() < gp.player.getWorldY() + gp.player.screenY) {
            g2.drawImage(up1, screenX, screenY, gp.getScaledTileSize(), gp.getScaledTileSize(), null);
        }
    }
}
