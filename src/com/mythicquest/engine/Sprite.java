package com.mythicquest.engine;

import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class Sprite {
    public int getWorldX() {
        return worldX;
    }

    private int worldX;
    private int worldY;
    private int speed;
    private Image playerImage;
    private String direction;
    public Rectangle solidArea;
    public boolean collisionOn = false;



    public void setWorldX(int x) {
        this.worldX = x;
    }

    public void setWorldY(int y) {
        this.worldY = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPlayerImage(Image playerImage) {
        this.playerImage = playerImage;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public String getDirection() {
        return direction;
    }
}