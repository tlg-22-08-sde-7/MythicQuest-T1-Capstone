package com.mythicquest.engine;

import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class Sprite {

    private int x;
    private int y;
    private int speed;
    private Image playerImage;
    private String direction;

    public Rectangle solidArea;
    public boolean collisionOn = false;



    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
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