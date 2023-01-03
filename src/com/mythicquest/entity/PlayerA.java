package com.mythicquest.entity;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerA extends Sprite {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public Rectangle solidArea;

    public PlayerA(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.getScaledTileSize()/2);
        screenY = gp.screenHeight/2 - (gp.getScaledTileSize()/2);
        setDefaultValues();
        solidArea = new Rectangle(8,16,32,32);
        getImage();
    }

    public void setDefaultValues() {
        setWorldX(gp.getScaledTileSize() * 2);
        setWorldY(gp.getScaledTileSize());
        setSpeed(4);
        setDirection("up");
    }

    public void getImage() {
        try {
            setPlayerImage(ImageIO.read(getClass().getResourceAsStream("/Sprites/pixil-frame-0.png")));
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

    public void draw(Graphics2D g2) {
        g2.drawImage(getPlayerImage(), getWorldX(), getWorldY(), gp.getScaledTileSize(), gp.getScaledTileSize(), null);
    }
}