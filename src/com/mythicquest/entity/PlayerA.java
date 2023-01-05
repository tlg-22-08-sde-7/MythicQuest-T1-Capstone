package com.mythicquest.entity;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.KeyHandler;
import com.mythicquest.app.UtilityTool;

import javax.imageio.ImageIO;
import  java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
        setDown1(setup("kidFront1"));
        setDown2(setup("kidFront2"));
        setDown3(setup("kidFront3"));

        setUp1(setup("kidRear1"));
        setUp2(setup("kidRear2"));
        setUp3(setup("kidRear3"));
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
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 3;
                }
                else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
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
        BufferedImage image = null;

        switch (getDirection()) {
            case "down":
                if (spriteNum == 1) {
                    image = getDown1();
                }
                if (spriteNum == 2) {
                    image = getDown2();
                }
                if (spriteNum == 3) {
                    image = getDown3();
                }
                break;
            case "up":
                if (spriteNum == 1) {
                    image = getUp1();
                }
                if (spriteNum == 2) {
                    image = getUp2();
                }
                if (spriteNum == 3) {
                    image = getUp3();
                }
                break;
        }

//        g2.drawImage(image, screenX, screenY, gp.getScaledTileSize(), gp.getScaledTileSize(), null);
        g2.drawImage(image, screenX, screenY, null);
    }

    public int getHasKey() {
        return hasKey;
    }

    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }
}