package com.mythicquest.app;

import com.mythicquest.object.Heart;
import com.mythicquest.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Board {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage emptyHeart, quarterHeart, halfHeart, threeQuarterHeart, fullHeart;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;

    public Board(GamePanel gp) {
        this.gp = gp;

        SuperObject heart = new Heart(gp);
        fullHeart = heart.image;
        threeQuarterHeart = heart.image1;
        halfHeart = heart.image2;
        quarterHeart = heart.image3;
        emptyHeart = heart.image4;
    }
}