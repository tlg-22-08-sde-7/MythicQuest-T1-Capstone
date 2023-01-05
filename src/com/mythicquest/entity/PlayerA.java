package com.mythicquest.entity;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.KeyHandler;

import javax.imageio.ImageIO;
import  java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

public class PlayerA extends Sprite {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    private int hasKey = 0;

    public PlayerA(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.getScaledTileSize()/2);
        screenY = gp.screenHeight/2 - (gp.getScaledTileSize()/2);
        solidArea = new Rectangle(8,16,32,32);
        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        setWorldX(gp.getScaledTileSize() * 23);
        setWorldY(gp.getScaledTileSize() * 21);
        setSpeed(4);
        setDirection("down");

        setMaxLife(8);
        setLife(getMaxLife());
    }

    public void getImage() {
        try {
            setPlayerImage(ImageIO.read(getClass().getResourceAsStream("/Sprites/kidFront2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.up || keyH.down || keyH.left || keyH.right) {
            if (keyH.up) {
                setDirection("up");
                //setWorldY(getWorldY() - getSpeed());
            } else if (keyH.down) {
                setDirection("down");
                // setWorldY(getWorldY() + getSpeed());
            } else if (keyH.left) {
                setDirection("left");
                // setWorldX(getWolrdX() - getSpeed());
            } else if (keyH.right) {
                setDirection("right");
                //  setWorldX(getWolrdX() + getSpeed());
            }
            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //if collision is false, player can move
            if (!collisionOn) {
                switch (getDirection()) {
                    case "up":
                        setWorldY(getWorldY() - getSpeed());
                        break;
                    case "down":
                        setWorldY(getWorldY() + getSpeed());
                        break;
                    case "left":
                        setWorldX(getWorldX() - getSpeed());
                        break;
                    case "right":
                        setWorldX(getWorldX() + getSpeed());
                        break;
                }
            }
        }
    }

    public void pickUpObject(int index) {
        // Any number fine except for object array's index
        if (index != 999) {
            String objectName = gp.obj[index].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[index] = null;
                    System.out.println("Key count:" + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[index] = null;
                        hasKey--;
                        System.out.println("Key count:" + hasKey);
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(getPlayerImage(), screenX, screenY, gp.getScaledTileSize(), gp.getScaledTileSize(), null);
    }

    public int getHasKey() {
        return hasKey;
    }

    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }
}