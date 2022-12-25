package com.mythicquest.engine;

import com.mythicquest.app.GamePanel;
import com.mythicquest.app.KeyHandler;
import com.mythicquest.app.TileManager;

import javax.swing.ImageIcon;
import java.awt.*;
import java.security.Key;

public class PlayerA extends Sprite {
    GamePanel gp;
    KeyHandler keyH;
    public Rectangle solidArea;
    public final int screenX;
    public final int screenY;




    //public int playerAction = IDLE;

    public PlayerA(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.getScaledTileSize()/2);
        screenY = gp.screenHeight/2 - (gp.getScaledTileSize()/2);
        solidArea = new Rectangle(8,12,16,16);
        setDefaultValues();
        getImage();
    }


    public void setDefaultValues() {
        setWorldX(gp.getScaledTileSize() * 23);
        setWorldY(gp.getScaledTileSize() * 21);
        setSpeed(4);
        setDirection("up");

    }

    public void getImage() {
        String playerImagePath = "resources/Sprites/pixil-frame-0.png";
        ImageIcon player = new ImageIcon(playerImagePath);
        setPlayerImage(player.getImage());
    }


    public void update() {
        if (keyH.up || keyH.down || keyH.left || keyH.right){
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
            if (!collisionOn){
                switch (getDirection()){
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
        g2.drawImage(getPlayerImage(), screenX, screenY, gp.getScaledTileSize(), gp.getScaledTileSize(), null);

    }
}