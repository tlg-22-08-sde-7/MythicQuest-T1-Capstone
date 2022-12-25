package com.mythicquest.engine;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.KeyHandler;

import javax.swing.ImageIcon;
import java.awt.*;

public class PlayerA extends Sprite {
    GamePanel gp;
    KeyHandler keyH;
    public Rectangle solidArea;

    public PlayerA(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        solidArea = new Rectangle(8,16,32,32);
        getImage();
    }

    public void setDefaultValues() {
        setX(gp.getScaledTileSize() * 2);
        setY(gp.getScaledTileSize());
        setSpeed(4);
        setDirection("up");
    }

    public void getImage() {
        String playerImagePath = "resources/Sprites/pixil-frame-0.png";
        ImageIcon player = new ImageIcon(playerImagePath);
        setPlayerImage(player.getImage());
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
                        setY(getY() - getSpeed());
                        break;
                    case "down":
                        setY(getY() + getSpeed());
                        break;
                    case "left":
                        setX(getX() - getSpeed());
                        break;
                    case "right":
                        setX(getX() + getSpeed());
                        break;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(getPlayerImage(), getX(), getY(), gp.getScaledTileSize(), gp.getScaledTileSize(), null);
    }
}