package com.mythicquest.entity;

import com.mythicquest.app.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    private int worldX;
    private int worldY;
    private int speed;
    private BufferedImage down1, down2, down3, up1, up2, up3, left1, left2, left3, right1, right2, right3;
    private String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    private int maxLife;
    private int life;

//    public Sprite(GamePanel gp) {          // TODO: check this constructor
//        solidArea = new Rectangle();
//        direction = "down";
//    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getDown3() {
        return down3;
    }

    public void setDown3(BufferedImage down3) {
        this.down3 = down3;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getUp3() {
        return up3;
    }

    public void setUp3(BufferedImage up3) {
        this.up3 = up3;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getLeft3() {
        return left3;
    }

    public void setLeft3(BufferedImage left3) {
        this.left3 = left3;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public BufferedImage getRight3() {
        return right3;
    }

    public void setRight3(BufferedImage right3) {
        this.right3 = right3;
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

    public String getDirection() {
        return direction;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}