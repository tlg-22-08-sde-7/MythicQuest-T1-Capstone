package com.mythicquest.engine;


import javax.swing.*;
import java.awt.image.BufferedImage;

class Sprite {
    private int x;
    private int y;
    private int speed;
    private BufferedImage playerImage;
    private String direction;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPlayerImage(BufferedImage playerImage) {
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

    public BufferedImage getPlayerImage() {
        return playerImage;
    }

    public String getDirection() {
        return direction;
    }
}