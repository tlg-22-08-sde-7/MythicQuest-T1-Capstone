package com.mythicquest.entity;

import java.awt.*;

public class Sprite {

    private int worldX;
    private int worldY;
    private int speed;
    private Image playerImage;
    private String direction;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;



    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
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


    public int getWorldX() {
        return worldX;
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