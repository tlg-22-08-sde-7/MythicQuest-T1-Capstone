package com.mythicquest.engine;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.KeyHandler;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;

public class PlayerA extends Sprite {
    GamePanel gp;
    KeyHandler keyH;

    public PlayerA(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        setX(100);
        setY(100);
        setSpeed(4);
    }

    public void getImage() {
        String playerImagePath = "resources/Sprites/pixil-frame-0.png";
        ImageIcon player = new ImageIcon(playerImagePath);
        setPlayerImage(player.getImage());
    }

    public void update() {
        if (keyH.up) {
            setY(getY() - getSpeed());
        } else if (keyH.down) {
            setY(getY() + getSpeed());
        } else if (keyH.left) {
            setX(getX() - getSpeed());
        } else if (keyH.right) {
            setX(getX() + getSpeed());
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(getPlayerImage(), getX(), getY(), gp.getScaledTileSize(), gp.getScaledTileSize(), null);
    }
}