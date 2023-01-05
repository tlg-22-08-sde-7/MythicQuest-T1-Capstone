package com.mythicquest.entity;

import com.mythicquest.app.GamePanel;
import com.mythicquest.object.HealthPotion;

import java.util.Random;

public class Minion extends Sprite {
    private int health = 50;


//    GamePanel gp;

    public Minion(GamePanel gp) {
//        super();
    //    TODO  fix this super call

//        this.gp = gp;


        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }


    public int attack() {
        return (int) (Math.random() * 5) + 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public void getImage() {        // TODO fix file path?
//        up1 = setup("Sprites/rat-idle-1.png", gp.tileSize, gp.tileSize);
//            up2 = setup("file-path",gp.tileSize,gp.tileSize);
//            down1 = setup("file-path",gp.tileSize,gp.tileSize);
//            down2 = setup("file-path",gp.tileSize,gp.tileSize);
//            left1 = setup("file-path",gp.tileSize,gp.tileSize);
//            left2 = setup("file-path",gp.tileSize,gp.tileSize);
//            right1 = setup("file-path",gp.tileSize,gp.tileSize);
//            right2 = setup("file-path",gp.tileSize,gp.tileSize);
    }


    public void checkDropItem() {
        // Randomly drop an item
        int i = new Random().nextInt(100) + 1;

        // Set the monster drop
        if (i < 75) {
//            dropItem(new HealthPotion(gp));
        }

    }

    private void dropItem(HealthPotion HealthPotion) {

    }


}
